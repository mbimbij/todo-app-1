provider "aws" {
  region = "eu-west-3"
  access_key = var.access_key
  secret_key = var.secret_key
}

resource "aws_key_pair" "myKeys" {
  public_key = file("ssh/id_rsa.pub")
}
