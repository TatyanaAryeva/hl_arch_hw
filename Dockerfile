FROM maven:3.8.4-openjdk-17
WORKDIR /app
RUN mkdir logs
ADD ./core/target/core-*.jar /app/core.jar
ENTRYPOINT ["sh", "-c", "java -jar core.jar"]