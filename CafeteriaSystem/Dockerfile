From mcr.microsoft.com/openjdk/jdk:21-ubuntu

WORKDIR /app

COPY build/libs/CafeteriaSystem-0.0.1-SNAPSHOT.jar  CafeteriaSystem.jar

Expose 8080

ENTRYPOINT ["java","-jar","CafeteriaSystem.jar"]