#!/bin/bash


# Ask for sudo password upfront
echo "Enter your sudo password:"
read -s password


echo "$password" | sudo apt update
echo "$password" | sudo apt install redis-server -y
echo "$password" | sudo systemctl restart redis.service
echo "$password" | sudo systemctl enable redis.service


redis_version=$(redis --version)
echo "Redis has been installed"
echo "Redis Version: $redis_version"
