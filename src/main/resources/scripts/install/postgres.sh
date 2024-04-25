#!/bin/bash


# Ask for sudo password upfront
echo "Enter your sudo password:"
read -s password

# Update package list and install PostgreSQL
echo "$password" | sudo -S apt update
echo "$password" | sudo -S apt install postgresql

# Access psql as the postgres user and run SQL commands
echo "$password" | sudo -S -u postgres psql -d template1 -c "ALTER USER postgres WITH ENCRYPTED PASSWORD 'your_password';"

# Restart PostgreSQL service
echo "$password" | sudo -S systemctl restart postgresql.service



postgres_version=$(psql --version)
echo "PostgreSQL has been installed and configured."
echo "PostgreSQL Version: $postgres_version"
echo "PostgreSQL USER: postgres"
echo "PostgreSQL PASSWORD: root"
