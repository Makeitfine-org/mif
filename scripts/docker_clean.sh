#!/usr/bin/env bash
#
# Main docker start script
#
echo "=========================="
echo " Remove docker containers"
echo "=========================="
echo "docker stop mys j11m:"
docker stop mys j11m
echo "docker rm mys j11m:"
docker rm mys j11m
