FROM openjdk:17-alpine
WORKDIR /opt/app
ARG JAR_FILE=./build/devservice.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","app.jar"]