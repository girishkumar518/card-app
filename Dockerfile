FROM java:8

EXPOSE 8080

ADD target/cards-app-1.0.jar cards-app.jar

ENTRYPOINT ["java","-jar","cards-app.jar"]