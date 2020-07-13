provider "aws" {
  region = "eu-west-3"
  access_key = var.access_key
  secret_key = var.secret_key
}

resource "aws_instance" "bastionHost" {
  ami = "ami-0bfddfb1ccc3a6993"
  instance_type = "t2.micro"
  subnet_id = aws_subnet.vpctest[1].id
  key_name = aws_key_pair.vpctest.key_name
  security_groups = [
    aws_security_group.bastionHostSG.id]
  provisioner "remote-exec" {
    inline = [
      "sleep 1"]

    connection {
      host = self.public_ip
      user = "ec2-user"
      private_key = file("ssh/id_rsa")
    }
  }
  tags = {
    Owner = "jmbimbi-bene"
    Name = "bastionHost"
  }
}