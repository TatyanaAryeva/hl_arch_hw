FROM maven:3.8.4-openjdk-17
WORKDIR /app
RUN mkdir logs
ADD ./core/target/core-*.jar /app/core.jar
# ENV JAVA_OPTS="-XX:+HeapDumpOnOutOfMemoryError -XX:+UseStringDeduplication -XX:+OptimizeStringConcat -XX:HeapDumpPath=debug/java_pid.hprof -Xmx8g"
ENTRYPOINT ["sh", "-c", "java -jar core.jar"]