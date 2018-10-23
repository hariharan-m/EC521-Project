This is the configuration used as the basis for openjdk-8-jdk-alpine

It is the root configuration for all Docker donfigurations, the
absolute minimum possible runnable system.

- - -

Source: https://hub.docker.com/_/alpine/

A minimal Docker image based on Alpine Linux with a complete package
index and only 5 MB in size!

Full Description

Supported tags and respective Dockerfile links

3.6 (versions/library-3.6/x86_64/Dockerfile)

3.7 (versions/library-3.7/x86_64/Dockerfile)

3.8, latest (versions/library-3.8/x86_64/Dockerfile)

edge (versions/library-edge/x86_64/Dockerfile)

3.1 (versions/library-3.1/Dockerfile)

3.2 (versions/library-3.2/Dockerfile)

3.3 (versions/library-3.3/Dockerfile)

3.4 (versions/library-3.4/Dockerfile)

3.5 (versions/library-3.5/Dockerfile)

Quick reference

Where to get help:

the Docker Community Forums, the Docker Community Slack, or Stack
Overflow

Where to file issues:

https://github.com/gliderlabs/docker-alpine/issues

Maintained by:

Glider Labs (an Alpine community contributor)

Supported architectures: (more info)

amd64, arm32v6, arm64v8, i386, ppc64le, s390x

Published image artifact details:

repo-info repo's repos/alpine/ directory (history)

(image metadata, transfer size, etc)

Image updates:

official-images PRs with label library/alpine

official-images repo's library/alpine file (history)

Source of this description:

docs repo's alpine/ directory (history)

Supported Docker versions:

the latest release (down to 1.6 on a best-effort basis)

What is Alpine Linux?

Alpine Linux is a Linux distribution built around musl libc and
BusyBox. The image is only 5 MB in size and has access to a package
repository that is much more complete than other BusyBox based images.
This makes Alpine Linux a great image base for utilities and even
production applications. Read more about Alpine Linux here and you can
see how their mantra fits in right at home with Docker images.

How to use this image

Usage

Use like you would any other base image:

FROM alpine:3.7

RUN apk add --no-cache mysql-client

ENTRYPOINT ["mysql"]

This example has a virtual image size of only 36.8MB. Compare that to
our good friend Ubuntu:

FROM ubuntu:18.04

RUN apt-get update \

&& apt-get install -y --no-install-recommends mysql-client \

&& rm -rf /var/lib/apt/lists/*

ENTRYPOINT ["mysql"]

This yields us a virtual image size of about 145MB image.

Documentation

This image is well documented. Check out the documentation at
Viewdocs.

License

View license information for the software contained in this image.

As with all Docker images, these likely also contain other software
which may be under other licenses (such as Bash, etc from the base
distribution, along with any direct or indirect dependencies of the
primary software being contained).

Some additional license information which was able to be auto-detected
might be found in the repo-info repository's alpine/ directory.

As for any pre-built image usage, it is the image user's
responsibility to ensure that any use of this image complies with any
relevant licenses for all software contained within.

