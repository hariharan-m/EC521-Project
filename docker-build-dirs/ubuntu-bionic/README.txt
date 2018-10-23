This is the version of Ubuntu on which "bhdresh-cve-2018-11776" is
built. (Specifically the one called "bionic/Dockerfile")

- - -

Source: https://hub.docker.com/_/ubuntu/

Ubuntu is a Debian-based Linux operating system based on free software.

Full Description

Supported tags and respective Dockerfile links

18.04, bionic-20181018, bionic, latest (bionic/Dockerfile)

18.10, cosmic-20181018, cosmic, rolling, devel (cosmic/Dockerfile)

14.04, trusty-20180929, trusty (trusty/Dockerfile)

16.04, xenial-20181005, xenial (xenial/Dockerfile)

Quick reference

Where to get help:

the Docker Community Forums, the Docker Community Slack, or Stack Overflow

Where to file issues:

the cloud-images bug tracker (include the docker tag)

Maintained by:

Canonical and Tianon (Debian Developer)

Supported architectures: (more info)

amd64, arm32v7, arm64v8, i386, ppc64le, s390x

Published image artifact details:

repo-info repo's repos/ubuntu/ directory (history)

(image metadata, transfer size, etc)

Image updates:

official-images PRs with label library/ubuntu

official-images repo's library/ubuntu file (history)

Source of this description:

docs repo's ubuntu/ directory (history)

Supported Docker versions:

the latest release (down to 1.6 on a best-effort basis)

What is Ubuntu?

Ubuntu is a Debian-based Linux operating system, with Unity as its
default desktop environment. It is based on free software and named
after the Southern African philosophy of ubuntu (literally,
"human-ness"), which often is translated as "humanity towards others"
or "the belief in a universal bond of sharing that connects all
humanity".

Development of Ubuntu is led by UK-based Canonical Ltd., a company
owned by South African entrepreneur Mark Shuttleworth. Canonical
generates revenue through the sale of technical support and other
services related to Ubuntu. The Ubuntu project is publicly committed
to the principles of open-source software development; people are
encouraged to use free software, study how it works, improve upon it,
and distribute it.

wikipedia.org/wiki/Ubuntu_(operating_system)

What's in this image?

This image is built from official rootfs tarballs provided by
Canonical (specifically, https://partner-images.canonical.com/core/).

The ubuntu:latest tag points to the "latest LTS", since that's the
version recommended for general use. The ubuntu:rolling tag points to
the latest release (regardless of LTS status).

Along a similar vein, the ubuntu:devel tag is an alias for whichever
release the "devel" suite on the mirrors currently points to, as
determined by the following one-liner: wget -qO-
http://archive.ubuntu.com/ubuntu/dists/devel/Release | awk -F ': ' '$1
== "Codename" { print $2; exit }'

Locales

Given that it is a minimal install of Ubuntu, this image only includes
the C, C.UTF-8, and POSIX locales by default. For most uses requiring
a UTF-8 locale, C.UTF-8 is likely sufficient (-e LANG=C.UTF-8 or ENV
LANG C.UTF-8).

For uses where that is not sufficient, other locales can be
installed/generated via the locales package. PostgreSQL has a good
example of doing so, copied below:

  RUN apt-get update && apt-get install -y locales && rm -rf /var/lib/apt/lists/* \
    && localedef -i en_US -c -f UTF-8 -A /usr/share/locale/locale.alias en_US.UTF-8
  ENV LANG en_US.utf8

/etc/apt/sources.list

ubuntu:18.04

$ docker run ubuntu:18.04 grep -v '^#' /etc/apt/sources.list

deb http://archive.ubuntu.com/ubuntu/ bionic main restricted

deb http://archive.ubuntu.com/ubuntu/ bionic-updates main restricted

deb http://archive.ubuntu.com/ubuntu/ bionic universe
deb-src http://archive.ubuntu.com/ubuntu/ bionic universe
deb http://archive.ubuntu.com/ubuntu/ bionic-updates universe
deb-src http://archive.ubuntu.com/ubuntu/ bionic-updates universe

deb http://archive.ubuntu.com/ubuntu/ bionic multiverse
deb http://archive.ubuntu.com/ubuntu/ bionic-updates multiverse

deb http://archive.ubuntu.com/ubuntu/ bionic-backports main restricted universe multiverse


deb http://security.ubuntu.com/ubuntu/ bionic-security main restricted
deb http://security.ubuntu.com/ubuntu/ bionic-security universe
deb-src http://security.ubuntu.com/ubuntu/ bionic-security universe
deb http://security.ubuntu.com/ubuntu/ bionic-security multiverse


ubuntu:16.04

$ docker run ubuntu:16.04 grep -v '^#' /etc/apt/sources.list

deb http://archive.ubuntu.com/ubuntu/ xenial main restricted
deb-src http://archive.ubuntu.com/ubuntu/ xenial main restricted

deb http://archive.ubuntu.com/ubuntu/ xenial-updates main restricted
deb-src http://archive.ubuntu.com/ubuntu/ xenial-updates main restricted

deb http://archive.ubuntu.com/ubuntu/ xenial universe
deb-src http://archive.ubuntu.com/ubuntu/ xenial universe
deb http://archive.ubuntu.com/ubuntu/ xenial-updates universe
deb-src http://archive.ubuntu.com/ubuntu/ xenial-updates universe


deb http://archive.ubuntu.com/ubuntu/ xenial-security main restricted
deb-src http://archive.ubuntu.com/ubuntu/ xenial-security main restricted
deb http://archive.ubuntu.com/ubuntu/ xenial-security universe
deb-src http://archive.ubuntu.com/ubuntu/ xenial-security universe


ubuntu:14.04

$ docker run ubuntu:14.04 grep -v '^#' /etc/apt/sources.list

deb http://archive.ubuntu.com/ubuntu/ trusty main restricted
deb-src http://archive.ubuntu.com/ubuntu/ trusty main restricted

deb http://archive.ubuntu.com/ubuntu/ trusty-updates main restricted
deb-src http://archive.ubuntu.com/ubuntu/ trusty-updates main restricted

deb http://archive.ubuntu.com/ubuntu/ trusty universe
deb-src http://archive.ubuntu.com/ubuntu/ trusty universe
deb http://archive.ubuntu.com/ubuntu/ trusty-updates universe
deb-src http://archive.ubuntu.com/ubuntu/ trusty-updates universe


deb http://archive.ubuntu.com/ubuntu/ trusty-security main restricted
deb-src http://archive.ubuntu.com/ubuntu/ trusty-security main restricted
deb http://archive.ubuntu.com/ubuntu/ trusty-security universe
deb-src http://archive.ubuntu.com/ubuntu/ trusty-security universe


License

View license information for the software contained in this image.

As with all Docker images, these likely also contain other software
which may be under other licenses (such as Bash, etc from the base
distribution, along with any direct or indirect dependencies of the
primary software being contained).

Some additional license information which was able to be auto-detected
might be found in the repo-info repository's ubuntu/ directory.

As for any pre-built image usage, it is the image user's
responsibility to ensure that any use of this image complies with any
relevant licenses for all software contained within.

