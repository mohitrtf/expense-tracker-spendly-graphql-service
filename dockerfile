FROM eclipse-temurin:21-jdk
COPY graphql-0.0.1-SNAPSHOT.jar graphql-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "graphql-0.0.1-SNAPSHOT.jar"]