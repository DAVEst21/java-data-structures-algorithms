#!/usr/bin/env bash
set -euo pipefail
cd "$(dirname "$0")/.."
javac_cmd=javac
java_cmd=java
if [[ -n "${JAVA_HOME:-}" ]]; then
    javac_cmd="$JAVA_HOME/bin/javac"
    java_cmd="$JAVA_HOME/bin/java"
fi
bash scripts/build.sh
mkdir -p target/jdk/test-classes
find src/test/java -name '*.java' -print > target/jdk/test-sources.txt
"$javac_cmd" --release 21 -encoding UTF-8 -cp 'target/jdk/classes:libs/*' \
    -d target/jdk/test-classes @target/jdk/test-sources.txt
"$java_cmd" -Djava.awt.headless=true \
    -cp 'target/jdk/test-classes:target/jdk/classes:libs/*' comprobaciones.Comprobaciones
"$java_cmd" -Djava.awt.headless=true \
    -cp 'target/jdk/test-classes:target/jdk/classes:libs/*' comprobaciones.ComprobacionesAmpliadas
