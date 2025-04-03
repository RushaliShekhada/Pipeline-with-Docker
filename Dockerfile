FROM openjdk:17-jdk
WORKDIR /usr/src/myapp
COPY target/java-app-0.0.1-SNAPSHOT.jar /usr/src/myapp/
CMD ["java", "-jar", "/usr/src/myapp/java-app-0.0.1-SNAPSHOT.jar"]