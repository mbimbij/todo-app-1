data "aws_route53_zone" "joseph_dns" {
  name="joseph-aws-test.com"
}

resource "aws_instance" "machineAppli" {
  ami = "ami-013ae7c08b0ad5b2c"
  instance_type = "t2.micro"
  key_name = aws_key_pair.myKeys.key_name
  subnet_id = aws_subnet.todoApp[0].id
  security_groups = [aws_security_group.public.id]
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
  subnet_id = aws_subnet.todoApp[1].id
  tags = {
    Owner = "jmbimbi-bene"
    Name = "machineBdd"
  }
}