# Runbook for Appointment Scheduling Service

## Introduction
This runbook provides instructions to deploy and test the Appointment Scheduling Service on AWS ECS. 
It automates the process through GitHub Actions and includes steps for testing and verification.

---

## Prerequisites

- GitHub account
- AWS account with ECS and ECR access
- Docker installed
- IAM roles configured in AWS
- GitHub Secrets configured for AWS credentials

---
# Runbook for Appointment Scheduling Service

## Introduction
This runbook provides instructions to deploy and test the **Appointment Scheduling Service** on **AWS ECS** using GitHub Actions. The deployment process is automated through a CI/CD pipeline and includes steps for testing, verification, and monitoring.

---

## Prerequisites

- **GitHub Account** with access to the repository.
- **AWS Account** with permissions to manage ECS, ECR, IAM, and CloudWatch.
- **Docker** installed on your machine.
- **IAM Roles** configured for appropriate access in AWS.
- **GitHub Secrets** configured for AWS credentials:
  - `AWS_ACCESS_KEY_ID`
  - `AWS_SECRET_ACCESS_KEY`
- **Kubernetes Cluster (EKS)** setup in AWS for ECS deployments.

---

## Cluster Setup

Before deploying the service, make sure your Kubernetes cluster (EKS) is set up. Follow these steps to configure your cluster and other settings:

1. **Create an EKS Cluster**
   - Go to the **Amazon EKS Console** and create a new cluster.
   - Set the cluster name (e.g., `medilab-cluster`) and other configurations such as VPC and security settings.
   - After creation, note the cluster name as you will need it in your Kubernetes configuration.
   
   Ex – Run this in AWS terminal.
   eksctl create cluster --name my-cluster --region us-east-1 --zones us-east-1a,us-east-1b --nodegroup-name linux-nodes --node-type t2.micro --nodes 2

2. **Configure Kubernetes CLI (kubectl)**
   - Install eksctl
      curl --silent --location "https://github.com/weaveworks/eksctl/releases/latest/download/eksctl_$(uname -s)_amd64.tar.gz" |tar xz -C /tmp
      sudo mv /tmp/eksctl /usr/local/bin
   - Run the following command to configure your local `kubectl` to interact with your EKS cluster:
     ```bash
     aws eks update-kubeconfig --name medilab-cluster
     ```

3. **Database Credentials Setup**
   - In your **AWS RDS** instance, ensure that the database credentials are available.
   - Set the following in your GitHub secrets:
     - `DB_USERNAME`
     - `DB_PASSWORD`
     - `DB_HOST`
     - `DB_NAME`

4. **Repository URL in GitHub Actions**
   - Your Docker image will be pushed to Amazon ECR. You need to specify the repository name and URL.
   - Update the `deploy.yml` workflow file with the correct repository name:
     ```yaml
     REPOSITORY: appointment-scheduling-service
     ```

5. **Edit Configuration Files**
   In your project files, make sure to update the following variables:

   - **Database Connection**: In your `application.properties` or `application.yml`, set the database connection details:
     ```properties
     spring.datasource.url=jdbc:mysql://${DB_HOST}:${DB_PORT}/${DB_NAME}
     spring.datasource.username=${DB_USERNAME}
     spring.datasource.password=${DB_PASSWORD}
     ```
   - **ECR Repository URL**: In `Dockerfile` or `deploy.yaml`, make sure the ECR repository URL and image tag are set correctly:
     ```bash
     ECR_REGISTRY: 123456789012.dkr.ecr.us-east-1.amazonaws.com/appointment-scheduling-service
     IMAGE_TAG: ${GITHUB_SHA}
     ```
   - **Cluster Name**: In the Kubernetes deployment configuration (`deploy.yaml`), set the cluster name and image tag:
     ```yaml
     apiVersion: apps/v1
     kind: Deployment
     metadata:
       name: appointment-scheduling-service
     spec:
       containers:
       - name: appointment-scheduling-service
         image: ${ECR_REGISTRY}/appointment-scheduling-service:${IMAGE_TAG}
         ports:
         - containerPort: 8080
     ```

---

## Deployment Steps

1. **Clone the Repository**
   ```bash
   git clone https://github.com/your-org/appointment-scheduling-service.git
   cd appointment-scheduling-service
   
2. **Modify the Code**
Make any necessary updates or modifications to the service code

Update business logic or API endpoints as needed.
Ensure that all configuration files (e.g., database connections, environment variables) are correctly set.

3. **Push Changes to the Repository**
Commit your changes to the branch and push it to the repository


## Monitoring and Troubleshooting
1. **GitHub Actions Logs**
Monitor the GitHub Actions logs to troubleshoot build, test, or deployment issues.

2. **ECS and ECR Consoles**
Check the ECS and ECR consoles to verify the status of deployments:

ECS Console: Ensure that the task for the service is running correctly.
ECR Console: Confirm that the Docker image was successfully pushed and tagged.
