FROM openjdk:latest

ADD target/filmeflix-0.0.1.jar app.jar

ENTRYPOINT ["java","-jar","app.jar"]

EXPOSE 8080
