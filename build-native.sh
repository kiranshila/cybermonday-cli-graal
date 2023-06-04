#!/usr/bin/env sh

clj -T:build uber

if [ -z "$GRAALVM_HOME" ]; then
    echo 'Please set GRAALVM_HOME'
    exit 1
fi

# Ensure Graal native-image program is installed
"$GRAALVM_HOME/bin/gu" install native-image

"$GRAALVM_HOME/bin/native-image" \
    -jar target/cm-cli.jar \
    -H:Name=cm-cli \
    -H:+ReportExceptionStackTraces \
    --initialize-at-build-time  \
    --verbose \
    --no-fallback \
    --no-server \
    "-J-Xmx3g" \
    target/cm-cli
