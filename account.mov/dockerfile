# Usa una imagen base con Java
FROM openjdk:17-jdk-alpine

MAINTAINER Edison Collaguazo "edisoncsi@hotmail.com"

# Define el directorio de trabajo dentro del contenedor
WORKDIR /app

# Copia los JARs de tus aplicaciones al contenedor
COPY target/account.mov-1.0.0.jar /app/account-mov.jar

# Expone los puertos en los que se ejecutan tus aplicaciones
EXPOSE 5001

# Comando para ejecutar tus aplicaciones cuando el contenedor se inicie
ENTRYPOINT ["java", "-jar", "account-mov.jar"]
