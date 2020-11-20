FROM bscpm/ompss_at_fpga:latest

USER 0
ADD --chown=0:0 entrypoint.sh /
RUN chmod 777 /entrypoint.sh
RUN chmod +x /entrypoint.sh

ARG UNAME=user
ARG UID=1724
ARG GID=1724
RUN groupadd -g $GID -o $UNAME
RUN useradd -m -u $UID -g $GID -o -s /bin/bash $UNAME

RUN echo  "user ALL=(ALL:ALL) NOPASSWD:ALL" >> /etc/sudoers

USER 1724

ENV HOME=/home/user


WORKDIR /projects



ENTRYPOINT [ "/entrypoint.sh" ]
CMD ["tail", "-f", "/dev/null"]

LABEL "che.base.image" bscpm/ompss_at_fpga:latest
