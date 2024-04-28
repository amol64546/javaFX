#!/bin/bash

echo "echo script is running ......"


if dpkg -l | grep -q redis-server; then
    echo "echo Uninstalling redis ...."
    sudo apt-get remove redis-server -y
    sudo apt-get purge redis-server -y
    sudo apt autoremove -y
    echo "echo Redis has been uninstalled."
else
    echo "echo Redis is not installed."
fi


