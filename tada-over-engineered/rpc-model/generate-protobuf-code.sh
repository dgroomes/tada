#!/usr/bin/env bash
#
# Generate Java code from the Protobuf definition files (*.proto files)

set -eu

dst_dir=src/main/java

protoc \
  "--java_out=$dst_dir" \
  "--java-grpc_out=$dst_dir" \
  echo.proto