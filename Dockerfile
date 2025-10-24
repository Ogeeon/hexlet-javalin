FROM gradle:8.12.1-jdk21
ENV GRADLE_OPTS="-Xmx2g -Xms512m -XX:MaxMetaspaceSize=512m -Dorg.gradle.daemon=false"


WORKDIR /

COPY / .

# RUN ["./gradlew", "clean", "build"]
RUN ./gradlew --no-daemon --refresh-dependencies clean --stacktrace

CMD ["./gradlew", "run"]