FROM gradle:8.12.1-jdk21 AS builder
ENV GRADLE_OPTS="-Xmx2g -Xms512m -XX:MaxMetaspaceSize=512m -Dorg.gradle.daemon=false"
WORKDIR /app
COPY . .
RUN ./gradlew clean build --no-daemon --stacktrace
RUN echo "🔍 Checking for built JARs:" && find app -type f -name "*.jar" || true
RUN ls -R app/build/libs

FROM eclipse-temurin:21-jre
WORKDIR /app
COPY --from=builder /app/build/libs/app-1.0-SNAPSHOT-all.jar app.jar
CMD ["java", "-jar", "app.jar"]
