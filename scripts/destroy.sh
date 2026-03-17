#!/bin/bash
set -e

echo "Starting cleanup..."

########################################

# Step 1 — Delete Kubernetes ingress

########################################

echo "Deleting Kubernetes ingress..."

kubectl delete ingress --all -n furniture-store || true

########################################

# Step 2 — Wait for AWS load balancer deletion

########################################

echo "Waiting for AWS load balancer to delete..."
MAX_RETRIES=30
COUNT=0

while aws elbv2 describe-load-balancers 
--query "LoadBalancers[?contains(LoadBalancerName, 'k8s-furnitur')]" 
--output text | grep -q "k8s"; do

    if [ $COUNT -ge $MAX_RETRIES ]; then
            echo "Timeout waiting for load balancer deletion. Continuing..."
            break
    fi

echo "Load balancer still exists... waiting 20 seconds"
sleep 20
COUNT=$((COUNT+1))
done

echo "Load balancer deleted."

########################################

# Step 3 — Delete remaining k8s workloads

########################################

kubectl delete deployment --all -n furniture-store || true
kubectl delete svc --all -n furniture-store || true


########################################
# Step 4 — Delete EKS cluster
########################################

echo "Deleting EKS cluster..."

CLUSTER_NAME="furniture-store-eks"

if aws eks describe-cluster --name $CLUSTER_NAME >/dev/null 2>&1; then
    aws eks delete-cluster --name $CLUSTER_NAME
    echo "Cluster deletion initiated..."
else
    echo "Cluster already deleted."
fi



########################################

# Step 5 — Terraform destroy

########################################

cd ../terraform

TAG=$(cat ../.image-tag)
ACCOUNT_ID=$(aws sts get-caller-identity --query Account --output text)
REGION=us-east-1

BACKEND_IMAGE=$ACCOUNT_ID.dkr.ecr.$REGION.amazonaws.com/furniture-backend:$TAG
FRONTEND_IMAGE=$ACCOUNT_ID.dkr.ecr.$REGION.amazonaws.com/furniture-frontend:$TAG

terraform init

terraform destroy -auto-approve -lock=false 
-var="mongo_uri=mongodb+srv://jonathinmakori:JonathinMakori@cluster0.zckwpq7.mongodb.net/furniturestoredb?retryWrites=true&w=majority" 
-var="backend_image=$BACKEND_IMAGE" 
-var="frontend_image=$FRONTEND_IMAGE" 
-var="image_tag=$TAG" 
-var="ecr_repo_backend=$ACCOUNT_ID.dkr.ecr.$REGION.amazonaws.com/furniture-backend" 
-var="ecr_repo_frontend=$ACCOUNT_ID.dkr.ecr.$REGION.amazonaws.com/furniture-frontend"

echo "Infrastructure destroyed."
