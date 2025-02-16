# How to start

Before attempting to start the application, make sure you have virtualization enabled on your PC and make sure you have docker installed and configured. To install docker, simply download docker desktop on the following link: https://www.docker.com/products/docker-desktop/

To start all services, back-end front-end and database, run the command

```bash
docker-compose up
```

This will start all services on their corresponding ports. The front-end is located on url `http://localhost:4440/`. Backend is on `http://localhost:8080/`.
You can change these ports to your linking by modifying the docker-compose file for local development, if they are already in use.

## Structure
The root of the project contains one `docker-compose.yaml` file, which serves as the entry-point into the application. This file runs the Docker containers (defined in corresponding `Dockerfile` files in each service). 