#!/usr/bin/env bash
set -euo pipefail
cd "$(dirname "$0")/.."
if [[ $# -eq 0 ]]; then
    echo 'Usage: bash scripts/run.sh fully.qualified.MainClass [args...]' >&2
    exit 2
fi
java_cmd=java
if [[ -n "${JAVA_HOME:-}" ]]; then java_cmd="$JAVA_HOME/bin/java"; fi
bash scripts/build.sh
exec "$java_cmd" -cp 'target/jdk/classes:libs/*' "$@"
