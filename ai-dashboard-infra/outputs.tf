output "aws_public_ip" {
  value       = aws_instance.app_server.public_ip
  description = "The public IP of the EC2 instance"
}

output "os_instance_ip" {
  value       = openstack_compute_instance_v2.os_server.access_ip_v4
  description = "The IP address of the OpenStack instance"
}
