## IMPORT THE PROJECT INTO INTELLIJ

    1. Download the project as zip file into your computer
    2. Unzip the project at your desired location
    3. Open the project into intellij

## CREATE THE FOLDER FOR THE BDD DATA

    mkdir -p shared/mysql_data

## CREATE PROJECT TARGET ENTER EACH DIRECTORY

    mvn clean install -DskipTests

## CREATE THE DOCKERFILE BUILD

    docker build -t client .
    docker build -t mov .

## RUN THE COMPOSE

    sudo docker-compose up -d

## FINISH THE CONTAINERS

    docker-compose down

## Test API in Postman

    load File McsvBackendJava.postman_collection.json  
       
## SWAGGER API

    localhost:5000/swagger-ui/index.html 
    localhost:5001/swagger-ui/index.html 
