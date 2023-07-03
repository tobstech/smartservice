# Smart Service

This project uses Quarkus, the Supersonic Subatomic Java Framework.

It is a smart model which exposes APIs for Smart Service.

## Pre-requisites

- Install Docker
- Install Kubernetes
- Install Kubectl
- Install Minikube
- Install Open JDK 17
- Install Postgres
- H2 Database for Test

## List of available endpoints

- Create smart services http://localhost:9098/smart-services [POST method]
- Get smart services http://localhost:9098/smart-services [GET method]
- Search smart services http://localhost:9098/smart-services/search [GET method]
- Get smart service by service id http://localhost:9098/smart-services/SERVICE-ID [GET method]
- Delete an existing smart service http://localhost:9098/smart-services/SERVICE-ID [DELETE method]

## Pull postgres image from docker and start postgres database server on docker

You can pull postgres docker image using the command below:
```shell script
docker pull postgres
```

You can start postgres database server on docker using:
```shell script
docker run --name smart_service_db -e POSTGRES_USER=username -e POSTGRES_PASSWORD=password -e POSTGRES_DB=smart_service_db -p 5432:5432 postgres:14
```

## Postman Collection With Endpoints & Example Request

The postman collection with example request and response can be found in the dir:

```shell script
src/main/resources/Smart Service APIs.postman_collection.json
```

## Build & Running the application in dev mode

You can build run your application in dev mode that enables live coding using:
```shell script
mvn compile quarkus:dev
```
## Accessing the List of APIs Via Swagger UI

You can access all api endpoints using the link below (OpenAPI & Swagger UI): 

http://localhost:9098/smart-services-api


## Packaging and running the application

The application can be packaged using:
```shell script
mvn package
```

## To deploy the app in minikube

You can deploy the application on minikube using: 
```shell script
kubectl apply -f target/kubernetes/minikube.yml
```
After a few seconds or minutes, the application is available on Minikube

## List all Pods

You can get list of all pods using:
```shell script
kubectl get pod
```

## Access the application at the local machine

To access on the local machine, it is required to redirect Minikube address to 
localhost, type this command. (Each Pod there is a unique name):

```shell script
kubectl port-forward pod/PODNAME 9098:9098
```
## Access application in browser

Open your browser and type this address:[http://localhost:9098]
