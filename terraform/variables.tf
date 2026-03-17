variable "aws_region" {
  default = "us-east-1"
}

variable "cluster_name" {
  default = "furniture-store-eks"
}

variable "node_instance_type" {
  default = "t3.medium"
}

variable "desired_capacity" {
  default = 2
}

variable "max_capacity" {
  default = 3
}

variable "min_capacity" {
  default = 1
}
variable "mongo_uri" {
  description = "MongoDB Atlas connection string"
  type        = string
  sensitive   = true
}

variable "image_tag" {
  description = "Docker image tag"
  type        = string
}

variable "ecr_repo_backend" {
  type = string
}

variable "ecr_repo_frontend" {
  type = string
}

variable "backend_image" {}
variable "frontend_image" {}

