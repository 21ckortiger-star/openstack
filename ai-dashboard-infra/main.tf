# --- AWS Resources ---

data "aws_ami" "amazon_linux" {
  most_recent = true
  owners      = ["amazon"]

  filter {
    name   = "name"
    values = ["al2023-ami-2023.*-x86_64"]
  }
}

resource "aws_security_group" "allow_ssh" {
  name        = "allow_ssh"
  description = "Allow SSH inbound traffic"

  ingress {
    from_port   = 22
    to_port     = 22
    protocol    = "tcp"
    cidr_blocks = ["0.0.0.0/0"]
  }

  egress {
    from_port   = 0
    to_port     = 0
    protocol    = "-1"
    cidr_blocks = ["0.0.0.0/0"]
  }
}

resource "aws_instance" "app_server" {
  ami           = data.aws_ami.amazon_linux.id
  instance_type = var.aws_instance_type
  
  vpc_security_group_ids = [aws_security_group.allow_ssh.id]
  associate_public_ip_address = true
  key_name               = var.aws_key_name

  tags = {
    Name = "My-AI-Dashboard-Server"
  }
}

# --- OpenStack Resources ---

# Data source for OpenStack Image
data "openstack_images_image_v2" "ubuntu" {
  name        = var.os_image_name
  most_recent = true
}

# Data source for OpenStack Network
data "openstack_networking_network_v2" "network" {
  name = var.os_network_name
}

# OpenStack Instance
resource "openstack_compute_instance_v2" "os_server" {
  name            = "My-AI-Dashboard-OS-Server"
  image_id        = data.openstack_images_image_v2.ubuntu.id
  flavor_name     = var.os_flavor_name
  key_pair        = var.os_key_name
  security_groups = ["default"]

  network {
    uuid = data.openstack_networking_network_v2.network.id
  }
}