# Dockerfile
FROM bscpm/ompss_at_fpga:2.4.0

USER root 

RUN apt-get update -qq \
 && apt-get install -y --no-install-recommends build-essential  \
    apt-transport-https curl ca-certificates gnupg2 apt-utils libxkbfile-dev libx11-dev

# Install node from nodesource
# uncomment the next 2 lines for fix
RUN curl -sL https://deb.nodesource.com/setup_12.x | bash - && apt-get install -y nodejs

# Install yarn
RUN curl -sS https://dl.yarnpkg.com/debian/pubkey.gpg | apt-key add - \
 && echo "deb https://dl.yarnpkg.com/debian/ stable main" | tee /etc/apt/sources.list.d/yarn.list \
 && apt-get update -qq \
 && apt-get install -y yarn


USER ompss
ENV SHELL /bin/bash

RUN git clone https://github.com/eclipse-theia/theia .theia 
RUN rm -rf /home/ompss/.theia/examples
COPY --chown=ompss:ompss  examples /home/ompss/.theia/examples/
RUN  cd .theia &&  yarn 

COPY --chown=ompss:ompss  plugins/OmpSS-completion-1.0.0.vsix /home/ompss/.plugins/OmpSS-completion-1.0.0.vsix 
WORKDIR /home/ompss/.theia/examples/browser
ENV THEIA_DEFAULT_PLUGINS local-dir:///home/ompss/.plugins
ENV CROSS_COMPILE aarch64-linux-gnu-
ENTRYPOINT [ "yarn", "run", "start", "/home/ompss/example", "--hostname=0.0.0.0" ]
