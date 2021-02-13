FROM java:8
EXPOSE 8080:8080
ADD /target/salestaxes-1.0.jar salestaxes-1.0.jar
ENTRYPOINT ["java", "-jar", "salestaxes-1.0.jar"]