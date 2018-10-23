FROM debian:latest
RUN apt-get update && apt-get install -y \
  procps \
  vim \
  yasm \
  gdb \
  gcc \
  bash-completion \
  && rm -rf /var/lib/apt/lists/*
#RUN groupadd cvrterr
#RUN useradd cvrterr -g cvrterr
#RUN echo cvrterr:supersecretpassword | chpasswd
#RUN mkdir home/cvrterr
#RUN chown cvrterr:cvrterr home/cvrterr
#RUN echo /bin/bash | chsh cvrterr
#USER cvrterr
#WORKDIR /home/cvrterr
