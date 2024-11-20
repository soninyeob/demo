#!/bin/bash
set -e

JAVA_OPTS="${JAVA_OPTS:--Xms64m -Xmx64m -XX:+UseG1GC -XX:MaxGCPauseMillis=100}"

# shellcheck disable=SC2086
exec java $JAVA_OPTS -jar /app/app.jar
