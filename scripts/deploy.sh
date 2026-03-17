#!/bin/bash
set -e

# Read image tag produced by build script

TAG=$(cat ../.image-tag)

# Mongo connection

MONGO_URI="mongodb+srv://jonathinmakori:JonathinMakori@cluster0.zckwpq7.mongodb.net/furniturestoredb?retryWrites=true&w=majority"

ACCOUNT_ID=$(aws sts get-caller-identity --query Account --output text)
REGION=us-east-1

BACKEND_IMAGE=$ACCOUNT_ID.dkr.ecr.$REGION.amazonaws.com/furniture-backend:$TAG
FRONTEND_IMAGE=$ACCOUNT_ID.dkr.ecr.$REGION.amazonaws.com/furniture-frontend:$TAG

echo "Deploying version: $TAG"

########################################

# Terraform Infrastructure

########################################

cd ../terraform

terraform init

terraform apply -lock=false -auto-approve \
-var="mongo_uri=$MONGO_URI" \
-var="backend_image=$BACKEND_IMAGE" \
-var="frontend_image=$FRONTEND_IMAGE" \
-var="image_tag=$TAG" \
-var="ecr_repo_backend=$ACCOUNT_ID.dkr.ecr.$REGION.amazonaws.com/furniture-backend" \
-var="ecr_repo_frontend=$ACCOUNT_ID.dkr.ecr.$REGION.amazonaws.com/furniture-frontend"

cd ..

########################################

# Update Kubernetes deployments

########################################

echo "Updating container images..."

kubectl set image deployment/backend backend=$BACKEND_IMAGE -n furniture-store
kubectl set image deployment/frontend frontend=$FRONTEND_IMAGE -n furniture-store

########################################

# Wait for rollout

########################################

kubectl rollout status deployment/backend -n furniture-store
kubectl rollout status deployment/frontend -n furniture-store

echo "Deployment complete."
