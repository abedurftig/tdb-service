#!/bin/bash
baseDir=${PWD}/../build/test-results/test
for filename in $baseDir/*.xml; do
  curl \
    -F "externalProjectId=Project One" \
    -F "externalTestRunId=TestRun123" \
    -F "file=@$filename" \
    localhost:8080/api/upload-junit4-xml
done
