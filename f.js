const mysql = require('mysql2/promise'); 
const { Client } = require('pg'); 
const moment = require('moment');

exports.handler = async (event) => {
    const rdsConfig = {
        host: 'rds-endpoint', 
        user: 'rds-username',
        password: 'rds-password',
        database: 'database-name'
    };

    const redshiftConfig = {
        user: 'redshift-username',
        host: 'redshift-endpoint',
        database: 'redshift-database',
        password: 'redshift-password',
        port: 5439
    };

    let rdsConnection;
    let redshiftClient;

    try {
        rdsConnection = await mysql.createConnection(rdsConfig);
        const [patients] = await rdsConnection.query('SELECT * FROM patients');
        const [appointments] = await rdsConnection.query('SELECT * FROM appointments');

        const aggregatedData = patients.map(patient => {
            const patientAppointments = appointments.filter(app => app.patient_id === patient.id);

            const doctorCount = patientAppointments.reduce((acc, app) => {
                acc[app.doctor_name] = (acc[app.doctor_name] || 0) + 1;
                return acc;
            }, {});

            const frequencies = patientAppointments.map(app => moment(app.appointment_time).format('YYYY-MM-DD'));
            const frequency = moment(frequencies[0]).fromNow(); 
            return {
                patient,
                doctorCount,
                frequency,
                appointments: patientAppointments,
            };
        });

        redshiftClient = new Client(redshiftConfig);
        await redshiftClient.connect();

        for (const data of aggregatedData) {
            for (const [doctorName, count] of Object.entries(data.doctorCount)) {
                const query = `
                    INSERT INTO aggregated_data (patient_id, patient_name, doctor_name, appointment_count, appointment_frequency, first_appointment_date)
                    VALUES ($1, $2, $3, $4, $5, $6)
                `;
                await redshiftClient.query(query, [
                    data.patient.id,
                    `${data.patient.first_name} ${data.patient.last_name}`,
                    doctorName,
                    count,
                    data.frequency,
                    frequencies[0] 
                ]);
            }
        }

        return {
            statusCode: 200,
            body: JSON.stringify({
                message: "Data aggregated and inserted successfully into Redshift"
            }),
        };
    } catch (error) {
        console.error("Error during aggregation: ", error);
        return {
            statusCode: 500,
            body: JSON.stringify({
                message: "Error during data aggregation",
                error: error.message
            }),
        };
    } finally {
        if (rdsConnection) await rdsConnection.end();
        if (redshiftClient) await redshiftClient.end();
    }
};



