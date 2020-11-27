FROM openjdk:11
ADD target/Reading-Is-Good.jar Reading-Is-Good.jar
EXPOSE 8083
ENTRYPOINT ["java", "-jar", "Reading-Is-Good.jar"]