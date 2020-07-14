resource "aws_instance" "machineRebond" {
  ami = "ami-0bfddfb1ccc3a6993"
  instance_type = "t2.micro"
  key_name = aws_key_pair.myKeys.key_name
  security_groups = [aws_security_group.machineRebondSG.id]
  subnet_id = aws_subnet.todoApp[0].id
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
    Name = "machineRebond"
  }
}

resource "aws_instance" "machineAppli" {
  ami = "ami-0bfddfb1ccc3a6993"
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
  ami = "ami-0bfddfb1ccc3a6993"
  instance_type = "t2.micro"
  key_name = aws_key_pair.myKeys.key_name
  security_groups = [aws_security_group.backendSG.id]
  subnet_id = aws_subnet.todoApp[2].id
  tags = {
    Owner = "jmbimbi-bene"
    Name = "machineBdd"
  }
}