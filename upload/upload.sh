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
echo "uploading test results for project '$PROJECT' (test run externalId: '$TEST_RUN')"
for filename in $baseDir/*.xml; do
  echo $filename
  curl \
    -F "externalProjectId=$PROJECT" \
    -F "externalTestRunId=$TEST_RUN" \
    -F "file=@$filename" \
    https://tdb-service.herokuapp.com/api/upload/junit4-xml
done
