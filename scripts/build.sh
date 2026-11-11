#!/usr/bin/env bash
set -euo pipefail
cd "$(dirname "$0")/.."
javac_cmd=javac
if [[ -n "${JAVA_HOME:-}" ]]; then javac_cmd="$JAVA_HOME/bin/javac"; fi
mkdir -p target/jdk/classes
find src/main/java -name '*.java' -print > target/jdk/sources.txt
"$javac_cmd" --release 21 -encoding UTF-8 -cp 'libs/*' \
    -d target/jdk/classes @target/jdk/sources.txt
echo 'Compiled all Java sources.'
