cd terraform
terraform init
terraform apply -var-file=secret.tfvars -auto-approve
cd ..

echo "[all]" > ansible/inventory
terraform-inventory --list terraform/ | jq -r '.type_aws_instance[]' >> ansible/inventory
echo "[machineAppli]" >> ansible/inventory
bastionIp=$(terraform-inventory --list terraform/ | jq -r '.machineAppli[]')
echo $bastionIp >> ansible/inventory
echo "[machineBdd]" >> ansible/inventory
terraform-inventory --list terraform/ | jq -r '.machineBdd[]' >> ansible/inventory

echo -e "Host 10.0.* \n\
    ProxyCommand ssh -F ssh.cfg -W %h:%p $bastionIp \n\
    IdentityFile ./ssh/id_rsa \n\
 \n\
Host $bastionIp \n\
    Hostname $bastionIp \n\
    User ubuntu \n\
    IdentityFile ./ssh/id_rsa \n\
    ControlPath ~/.ssh/cm-%r@%h:%p \n\
    ControlMaster auto \n\
    ControlPersist 10m" > ansible/ssh.cfg

#cd ansible
#ansible-playbook -i inventory playbook.yml 
