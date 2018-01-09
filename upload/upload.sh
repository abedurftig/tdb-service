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
for filename in $baseDir/*.xml; do
  curl \
    -F "externalProjectId=$PROJECT" \
    -F "externalTestRunId=$TEST_RUN" \
    -F "file=@$filename" \
    https://tdb-service.herokuapp.com/api/upload-junit4-xml
done
