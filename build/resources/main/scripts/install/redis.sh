#!/bin/bash


sudo apt update
sudo apt install redis-server -y
sudo systemctl restart redis.service
sudo systemctl enable redis.service
redis-server --version
