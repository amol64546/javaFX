#!/bin/bash

sudo service mongod stop

sudo apt-get purge mongodb-org* -y
sudo rm -r /var/log/mongodb /var/lib/mongodb

sudo apt autoremove -y
mongod --version


