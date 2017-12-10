#!/bin/bash
baseDir=${PWD}
printf "$baseDir"
for filename in $baseDir/build/test-results/test/*.xml; do
  curl \
    -F "externalProjectId=Project One" \
    -F "externalTestRunId=TestRun123" \
    -F "file=@$filename" \
    localhost:8080/api/upload-junit4-xml
  printf "\n\nUploaded $filename\n"
done
