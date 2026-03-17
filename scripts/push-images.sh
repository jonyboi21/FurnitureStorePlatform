#!/bin/bash

AWS_ACCOUNT_ID=$(aws sts get-caller-identity --query Account --output text)
REGION=us-east-1

ECR_BACKEND=$AWS_ACCOUNT_ID.dkr.ecr.$REGION.amazonaws.com/furniture-backend
ECR_FRONTEND=$AWS_ACCOUNT_ID.dkr.ecr.$REGION.amazonaws.com/furniture-frontend

aws ecr get-login-password --region $REGION | \
docker login --username AWS --password-stdin $AWS_ACCOUNT_ID.dkr.ecr.$REGION.amazonaws.com

docker tag furniture-backend:latest $ECR_BACKEND:latest
docker tag furniture-frontend:latest $ECR_FRONTEND:latest

docker push $ECR_BACKEND:latest
docker push $ECR_FRONTEND:latest