#!/bin/bash
baseDir=${PWD}/../build/test-results/test
while getopts p:t: option
do
 case "${option}"
 in
 p) PROJECT=${OPTARG};;
 t) TEST_RUN=${OPTARG};;
 esac
done
echo $PROJECT
echo $TEST_RUN
for filename in $baseDir/*.xml; do
  curl \
    -F "externalProjectId=$PROJECT" \
    -F "externalTestRunId=$TEST_RUN" \
    -F "file=@$filename" \
    localhost:8080/api/upload-junit4-xml
done
