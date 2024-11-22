# docker build -t web:latest --build-arg profiles=dev-pc -f modules/web/Dockerfile .
# --platform=linux/amd64
# --progress=plain
# --no-cache

FROM eclipse-temurin:17-jammy AS builder

WORKDIR /app

COPY gradle /app/gradle
COPY gradlew /app/
COPY modules/web/build.gradle /app/
RUN ./gradlew build || return 0

COPY modules/web/src /app/src
RUN ./gradlew clean bootJar

# jdeps does not support spring executable jar
RUN mkdir -p /app/deps/unpack
RUN apt-get update && apt-get install -y unzip && rm -rf /var/lib/apt/lists/
RUN unzip /app/build/libs/*.jar -d /app/deps/unpack

# find dependencies
RUN jdeps \
    --ignore-missing-deps \
    --print-module-deps \
    --recursive \
    --multi-release 17 \
    --module-path="/app/deps/unpack/BOOT-INF/lib/*" \
    --class-path="/app/deps/unpack/BOOT-INF/lib/*" \
    /app/build/libs/*.jar > /app/deps/modules.info

# make jre
# compress [0: no compression, 1: constant string sharing, 2: zip]
RUN jlink \
    --module-path /usr/lib/jvm/java-17-openjdk-amd64/jmods \
    --add-modules $(cat /app/deps/modules.info) \
    --output /app/deps/jre \
    --compress=2  \
    --no-header-files  \
    --no-man-pages

FROM ubuntu:jammy

ARG profiles
RUN if [ -z "$profiles" ]; then \
  echo "require --build-arg profiles"; \
  exit 1; \
fi
ENV SPRING_PROFILES_ACTIVE=$profiles

WORKDIR /app
COPY --from=builder /app/deps/jre /opt/jre
COPY --from=builder /app/build/libs/*.jar /app/app.jar
COPY modules/web/entrypoint.sh /app/entrypoint.sh

ENV JAVA_HOME=/opt/jre
ENV PATH="$JAVA_HOME/bin:$PATH"
RUN chmod 500 /app/entrypoint.sh

RUN mkdir /logs
RUN chmod +w /logs

EXPOSE 80

ENTRYPOINT ["/app/entrypoint.sh"]
