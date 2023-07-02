# smartservice

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