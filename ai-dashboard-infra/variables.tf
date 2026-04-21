# AWS Variables
variable "aws_region" {
  description = "AWS region"
  type        = string
  default     = "ap-northeast-2"
}

variable "aws_instance_type" {
  description = "EC2 instance type"
  type        = string
  default     = "t3.micro"
}

variable "aws_key_name" {
  description = "AWS Key pair name"
  type        = string
  default     = "my-key"
}

# OpenStack Variables
variable "os_auth_url" {
  description = "OpenStack Auth URL"
  type        = string
  default     = "https://keystone.example.com:5000/v3"
}

variable "os_username" {
  description = "OpenStack Username"
  type        = string
  default     = "admin"
}

variable "os_password" {
  description = "OpenStack Password"
  type        = string
  sensitive   = true
  default     = "password"
}

variable "os_tenant_name" {
  description = "OpenStack Project/Tenant Name"
  type        = string
  default     = "admin"
}

variable "os_region" {
  description = "OpenStack Region"
  type        = string
  default     = "RegionOne"
}

variable "os_image_name" {
  description = "OpenStack Image Name"
  type        = string
  default     = "Ubuntu 22.04"
}

variable "os_flavor_name" {
  description = "OpenStack Flavor Name"
  type        = string
  default     = "m1.small"
}

variable "os_key_name" {
  description = "OpenStack Key Pair Name"
  type        = string
  default     = "my-os-key"
}

variable "os_network_name" {
  description = "OpenStack Network Name"
  type        = string
  default     = "public"
}
