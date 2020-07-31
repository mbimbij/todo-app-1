# todo-app-1

# Deployment

## terraform + ansible
To deploy using the tools in `deploy/awsterraform_ansible`: 

1. install `terraform-inventory` : [https://github.com/adammck/terraform-inventory](https://github.com/adammck/terraform-inventory) to create an ansible inventory from Terraform tfstate file
2. copy `deploy/awsterraform_ansible/ansible/ansible.cfg` to `~/.ansible.cfg` 
3. run `deploy/awsterraform_ansible/deploy.sh`
4. go to  `deploy/awsterraform_ansible/ansible`
5. run `ansible-playbook -i inventory playbook.yml`