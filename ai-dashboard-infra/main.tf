terraform {
  required_providers {
    aws = {
      source  = "hashicorp/aws"
      version = "~> 6.0"
    }
  }
}

provider "aws" {
  region = "ap-northeast-2"
}

data "aws_ami" "amazon_linux" {
  most_recent = true
  owners      = ["amazon"]

  filter {
    name   = "name"
    values = ["al2023-ami-2023.*-x86_64"]
  }
}

# 1. 보안 그룹 생성 (방화벽 역할)
resource "aws_security_group" "allow_ssh" {
  name        = "allow_ssh"
  description = "Allow SSH inbound traffic"

  ingress {
    from_port   = 22
    to_port     = 22
    protocol    = "tcp"
    cidr_blocks = ["0.0.0.0/0"] # 어디서든 접근 가능 (실무에선 본인 IP만 적는 게 안전해요!)
  }

  egress {
    from_port   = 0
    to_port     = 0
    protocol    = "-1"
    cidr_blocks = ["0.0.0.0/0"]
  }
}

# 2. 인스턴스에 보안 그룹과 키 페어 연결
resource "aws_instance" "app_server" {
  ami           = data.aws_ami.amazon_linux.id
  instance_type = "t3.micro"
  
  # 보안 그룹 적용
  vpc_security_group_ids = [aws_security_group.allow_ssh.id]
  
  # 퍼블릭 IP 할당 강제
  associate_public_ip_address = true
  
  # ★중요: AWS 콘솔에서 미리 만들어둔 키 페어 이름(확장자 제외)을 여기에 적어주세요!
  key_name               = "my-key" 

  tags = {
    Name = "My-AI-Dashboard-Server"
  }
}

output "public_ip" {
  value       = aws_instance.app_server.public_ip
  description = "The public IP of the EC2 instance"
}