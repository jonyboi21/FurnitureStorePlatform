#!/bin/bash
set -e

REGION=us-east-1
ACCOUNT_ID=$(aws sts get-caller-identity --query Account --output text)

BACKEND_REPO=$ACCOUNT_ID.dkr.ecr.$REGION.amazonaws.com/furniture-backend
FRONTEND_REPO=$ACCOUNT_ID.dkr.ecr.$REGION.amazonaws.com/furniture-frontend

TAG=$(git rev-parse --short HEAD 2>/dev/null || date +%s)

echo "Using image tag: $TAG"

docker buildx create --name multiarch-builder --use 2>/dev/null || docker buildx use multiarch-builder
docker buildx inspect --bootstrap

echo "Logging into ECR..."
aws ecr get-login-password --region $REGION \
| docker login \
--username AWS \
--password-stdin $ACCOUNT_ID.dkr.ecr.$REGION.amazonaws.com

echo "Building backend image..."

cd ../backend
mvn clean package -DskipTests

docker buildx build \
--platform linux/amd64,linux/arm64 \
-t $BACKEND_REPO:$TAG \
--push .

cd ..

echo "Building frontend image..."

cd frontend

docker buildx build \
--platform linux/amd64,linux/arm64 \
-t $FRONTEND_REPO:$TAG \
--push .

cd ..

echo "Images pushed successfully."

echo $TAG > .image-tag