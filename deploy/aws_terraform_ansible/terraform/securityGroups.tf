resource "aws_security_group" "machineRebondSG" {
  vpc_id = aws_vpc.vpcTodoApp.id
  ingress {
    from_port = 22
    to_port = 22
    protocol = "tcp"
    cidr_blocks = [
      "0.0.0.0/0"]
    ipv6_cidr_blocks = [
      "::/0"]
  }

  egress {
    from_port = 0
    to_port = 0
    protocol = -1
    cidr_blocks = [
      "0.0.0.0/0"]
    ipv6_cidr_blocks = [
      "::/0"]
  }

  tags = {
    Owner = "jmbimbi-bene"
    Name = "machineRebondSG"
  }
}

resource "aws_security_group" "backendSG" {
  vpc_id = aws_vpc.vpcTodoApp.id
  ingress {
    from_port = 0
    to_port = 0
    protocol = -1
    security_groups = [aws_security_group.machineRebondSG.id]
  }

  egress {
    from_port = 0
    to_port = 0
    protocol = -1
    cidr_blocks = [
      "0.0.0.0/0"]
    ipv6_cidr_blocks = [
      "::/0"]
  }

  tags = {
    Owner = "jmbimbi-bene"
    Name = "backendSG"
  }
}