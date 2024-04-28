#!/bin/bash

echo "echo script is running ......"




check_redis_version() {
    redis_version=$(redis-server -v | awk '{print $3}')
    echo "echo Version: $redis_version"
    return 0  # Return success
}

install_redis() {
    echo "echo Installing Redis..."
    sudo apt-get update
    sudo apt-get install redis-server -y
    sudo systemctl enable redis.service
}

# Check if Redis is installed
 if dpkg -l | grep -q redis-server; then
    echo "echo Redis is already installed."
    check_redis_version
    exit 0  # Exit successfully
else
    # Install Redis
    install_redis
    echo "echo Redis is installed."
    check_redis_version
    exit 0  # Exit successfully
fi
