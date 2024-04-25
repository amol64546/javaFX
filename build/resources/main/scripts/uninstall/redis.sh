#!/bin/bash


sudo systemctl stop redis
sudo apt remove redis-server
sudo apt purge redis-server -y

sudo apt autoremove -y
redis-server --version
