#!/bin/bash

# Ask for sudo password upfront
echo "Enter your sudo password:"
read -s password

echo "$password" | sudo apt-get install gnupg curl

curl -fsSL https://www.mongodb.org/static/pgp/server-7.0.asc | \
   sudo gpg -o /usr/share/keyrings/mongodb-server-7.0.gpg \
   --dearmor

echo "deb [ arch=amd64,arm64 signed-by=/usr/share/keyrings/mongodb-server-7.0.gpg ] https://repo.mongodb.org/apt/ubuntu jammy/mongodb-org/7.0 multiverse" | sudo tee /etc/apt/sources.list.d/mongodb-org-7.0.list

echo "$password" | sudo apt-get update

echo "$password" | sudo apt-get install -y mongodb-org

echo "mongodb-org hold" | sudo dpkg --set-selections
echo "mongodb-org-database hold" | sudo dpkg --set-selections
echo "mongodb-org-server hold" | sudo dpkg --set-selections
echo "mongodb-mongosh hold" | sudo dpkg --set-selections
echo "mongodb-org-mongos hold" | sudo dpkg --set-selections
echo "mongodb-org-tools hold" | sudo dpkg --set-selections

echo "$password" | sudo systemctl start mongod
echo "$password" | sudo systemctl daemon-reload
echo "$password" | sudo systemctl status mongod
echo "$password" | sudo systemctl enable mongod

mongod_version=$(mongod --version)
echo "MongoDB has been installed"
echo "MongoDb Version: $mongod_version"
