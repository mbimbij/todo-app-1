resource "aws_instance" "machineRebond" {
  ami = "ami-013ae7c08b0ad5b2c"
  instance_type = "t2.micro"
  key_name = aws_key_pair.myKeys.key_name
  security_groups = [aws_security_group.machineRebondSG.id]
  subnet_id = aws_subnet.todoApp[0].id
  user_data = ""
  provisioner "remote-exec" {
    inline = [
      "sleep 1"]

    connection {
      host = self.public_ip
      user = "ubuntu"
      private_key = file("ssh/id_rsa")
    }
  }
  tags = {
    Owner = "jmbimbi-bene"
    Name = "machineRebond"
  }
}

data "aws_route53_zone" "joseph_dns" {
  name="joseph-aws-test.com"
}

resource "aws_instance" "machineAppli" {
  ami = "ami-013ae7c08b0ad5b2c"
  instance_type = "t2.micro"
  key_name = aws_key_pair.myKeys.key_name
  security_groups = [aws_security_group.backendSG.id]
  subnet_id = aws_subnet.todoApp[1].id
  tags = {
    Owner = "jmbimbi-bene"
    Name = "machineAppli"
  }
}

resource "aws_instance" "machineBdd" {
  ami = "ami-013ae7c08b0ad5b2c"
  instance_type = "t2.micro"
  key_name = aws_key_pair.myKeys.key_name
  security_groups = [aws_security_group.backendSG.id]
  subnet_id = aws_subnet.todoApp[2].id
  tags = {
    Owner = "jmbimbi-bene"
    Name = "machineBdd"
  }
}