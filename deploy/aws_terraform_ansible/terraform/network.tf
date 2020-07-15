resource "aws_vpc" "vpcTodoApp" {
  cidr_block = "10.0.0.0/16"
  tags = {
    Owner = "jmbimbi-bene"
    Name = "todoApp"
  }
}

resource "aws_subnet" "todoApp" {
  count = 2
  cidr_block = var.subnets[count.index].cidr_block
  vpc_id = aws_vpc.vpcTodoApp.id
  map_public_ip_on_launch = var.subnets[count.index].isPublic
  availability_zone = var.subnets[count.index].availabilityZone
  tags = {
    Owner = "jmbimbi-bene"
    Name = var.subnets[count.index].name
  }
}

variable "subnets" {
  type = list(object({
    cidr_block = string
    name = string
    isPublic = bool
    availabilityZone = string
  }))
  default = [
    {
      cidr_block = "10.0.1.0/24"
      name = "public"
      isPublic = true
      availabilityZone = "eu-west-3a"
    },
    {
      cidr_block = "10.0.11.0/24"
      name = "application"
      isPublic = false
      availabilityZone = "eu-west-3a"
    },
    {
      cidr_block = "10.0.21.0/24"
      name = "database"
      isPublic = false
      availabilityZone = "eu-west-3a"
    }
  ]
}

resource "aws_internet_gateway" "todoAppIgw" {
  vpc_id = aws_vpc.vpcTodoApp.id
}

resource "aws_route_table" "todoAppPublicRT" {
  vpc_id = aws_vpc.vpcTodoApp.id
  route {
    cidr_block = "0.0.0.0/0"
    gateway_id = aws_internet_gateway.todoAppIgw.id
  }
  tags = {
    Name = "todoAppPublicRT"
  }
}

resource "aws_route_table_association" "todoAppPublicRTAssociation" {
  route_table_id = aws_route_table.todoAppPublicRT.id
  subnet_id = aws_subnet.todoApp[0].id
}

resource "aws_eip" "todoAppNatGwEip" {}

resource "aws_nat_gateway" "todoAppNatGw" {
  allocation_id = aws_eip.todoAppNatGwEip.id
  subnet_id = aws_subnet.todoApp[0].id
  tags = {
    Name = "todoAppNatGw"
  }
}

resource "aws_route_table" "privateSubnetsRT" {
  vpc_id = aws_vpc.vpcTodoApp.id
  route {
    cidr_block = "0.0.0.0/0"
    gateway_id = aws_nat_gateway.todoAppNatGw.id
  }
  tags = {
    Name = "privateSubnetsRT"
  }
}

resource "aws_route_table_association" "todoAppApplicationRTAssociation" {
  route_table_id = aws_route_table.privateSubnetsRT.id
  subnet_id = aws_subnet.todoApp[1].id
}
//resource "aws_route_table_association" "todoAppDatabaseRTAssociation" {
//  route_table_id = aws_route_table.privateSubnetsRT.id
//  subnet_id = aws_subnet.todoApp[2].id
//}