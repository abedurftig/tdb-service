#!/bin/bash
if [ "$TRAVIS_BRANCH" == "master" ] || [[ $TRAVIS_BRANCH =~ v[0-9]+\.[0-9]+\.[0-9]+$ ]]; then

    docker login -u $DOCKER_USER -p $DOCKER_PW
    repo=dasnervtdoch/tdb-service

    if [ "$TRAVIS_BRANCH" == "master" ]; then
        tag="latest";
    else
        branch_name="$TRAVIS_BRANCH";
        tag=${branch_name//v/""};
    fi

    docker build -f build/docker/jdk8/Dockerfile -t $repo:$tag .
    echo "Pushing tag '$tag' to Docker ($repo)."
    docker push $repo

fi
