#che
# Dockerfile
FROM ompss_fpga_stack_base:che
#FROM ubuntu:xenial
MAINTAINER Programming Models Group at BSC <pm-tools@bsc.es> (https://pm.bsc.es)

USER root
ARG DEBIAN_FRONTEND="noninteractive"
RUN apt-get update \
 && apt-get install -q -y --no-install-recommends \
        autoconf \
        automake \
        binutils-dev \
        bison \
        build-essential \
        ca-certificates \
        curl \
        flex \
        gfortran \
        gperf \
        git \
        libboost-signals-dev \
        libiberty-dev \
        libltdl-dev \
        libsqlite3-dev \
        libtool \
        libxml2-dev \
        pkg-config \
        python \
        python3 \
        sudo \
        vim \
        wget \
# Needed by Xilinx tools
        libgtk2.0-0 \
        libncurses5 \
        libx11-6 \
        libxext6 \
        libxrender1 \
        libxtst6 \
        procps \
# Needed by Petalinux tools
        bc \
        gawk \
        gnupg \
        gnupg-agent \
        libncurses5-dev \
        libssl1.0-dev \
        lsb-release \
        net-tools \
        rsync \
        unzip \
        zlib1g-dev \
# Extra tools
	openssh-client \
 && dpkg --add-architecture arm64 \
 && dpkg --add-architecture armhf \
 && apt-get update \
 && apt-get install -q -y -q \
        binutils-multiarch \
        binutils-multiarch-dev \
        crossbuild-essential-arm64 \
        gfortran-aarch64-linux-gnu \
        crossbuild-essential-armhf \
        gfortran-arm-linux-gnueabihf \
 && apt-get clean

WORKDIR /tmp/work/
RUN /bin/bash -c "wget http://icl.utk.edu/projects/papi/downloads/papi-5.5.1.tar.gz \
 && tar -zxf papi-5.5.1.tar.gz \
 && rm papi-5.5.1.tar.gz \
 && pushd papi-5.5.1/src \
 && ./configure --prefix=/opt/install-arm64/papi --host=aarch64-linux-gnu --with-arch=aarch64 \
    --with-CPU=arm --with-ffsll --with-walltimer=cycle -with-tls=__thread \
    --with-virtualtimer=perfctr --with-perf-events \
 && make && make install \
 && make distclean \
 && ./configure --prefix=/opt/install-arm/papi --host=arm-linux-gnueabihf --with-arch=arm \
    --with-CPU=arm --with-ffsll --with-walltimer=cycle -with-tls=__thread \
    --with-virtualtimer=perfctr --with-perf-events \
 && make && make install \
 && make distclean \
 && ./configure --prefix=/opt/install-x86_64/papi --with-perf-events \
 && make && make install \
 && popd \
 && rm -r papi-5.5.1 \
 && wget https://ftp.gnu.org/gnu/binutils/binutils-2.31.tar.gz \
 && tar -zxf binutils-2.31.tar.gz \
 && rm binutils-2.31.tar.gz \
 && pushd binutils-2.31 \
 && ./configure --prefix=/opt/install-arm64/binutils --host=aarch64-linux-gnu --enable-shared \
    --enable-install-libiberty \
 && make tooldir=/opt/install-arm64/binutils && make tooldir=/opt/install-arm64/binutils install \
 && make distclean \
 && ./configure --prefix=/opt/install-arm/binutils --host=arm-linux-gnueabihf --enable-shared \
    --enable-install-libiberty \
 && make tooldir=/opt/install-arm/binutils && make tooldir=/opt/install-arm/binutils install \
 && popd \
 && rm -r binutils-2.31 \
 && wget https://zlib.net/zlib-1.2.11.tar.gz \
 && tar -zxf zlib-1.2.11.tar.gz \
 && rm zlib-1.2.11.tar.gz \
 && pushd zlib-1.2.11 \
 && CHOST=aarch64-linux-gnu ./configure --prefix=/opt/install-arm64/libz \
 && CHOST=aarch64-linux-gnu make install \
 && make distclean \
 && CHOST=arm-linux-gnueabihf ./configure --prefix=/opt/install-arm/libz \
 && CHOST=arm-linux-gnueabihf make install \
 && popd \
 && rm -r zlib-1.2.11 \
 && wget https://gitlab.gnome.org/GNOME/libxml2/-/archive/v2.9.8/libxml2-v2.9.8.tar.gz \
 && tar -zxf libxml2-v2.9.8.tar.gz \
 && rm libxml2-v2.9.8.tar.gz \
 && pushd libxml2-v2.9.8 \
 && autoreconf -ifv \
 && ./configure --prefix=/opt/install-arm64/libxml2 --host=aarch64-linux-gnu \
    --with-zlib=/opt/install-arm64/libz --without-python \
 && make install \
 && make distclean \
 && ./configure --prefix=/opt/install-arm/libxml2 --host=arm-linux-gnueabihf \
    --with-zlib=/opt/install-arm/libz --without-python \
 && make install \
 && popd \
 && rm -r libxml2-v2.9.8 \
 && wget https://ftp.tools.bsc.es/wxparaver/wxparaver-4.7.2-Linux_x86_64.tar.bz2 \
 && tar -xf wxparaver-4.7.2-Linux_x86_64.tar.bz2 \
 && rm wxparaver-4.7.2-Linux_x86_64.tar.bz2 \
 && mv wxparaver-4.7.2-Linux_x86_64 /opt/wxparaver"

ADD ompss-at-fpga-releases/Makefile ./
ADD ompss-at-fpga-releases/ait ./ait
ADD ompss-at-fpga-releases/extrae ./extrae
ADD ompss-at-fpga-releases/mcxx ./mcxx
ADD ompss-at-fpga-releases/nanox ./nanox
ADD ompss-at-fpga-releases/ompss-at-fpga-kernel-module ./ompss-at-fpga-kernel-module
ADD ompss-at-fpga-releases/xdma ./xdma
ADD ompss-at-fpga-releases/xtasks ./xtasks
RUN /bin/bash -c "pushd extrae \
 && ./bootstrap \
 && ./configure --prefix=/opt/install-arm64/extrae --host=aarch64-linux-gnu --enable-arm64 \
    --without-mpi --without-unwind --without-dyninst --with-papi=/opt/install-arm64/papi \
    --with-libz=/opt/install-arm64/libz --with-binutils=/opt/install-arm64/binutils \
    --with-xml-prefix=/opt/install-arm64/libxml2 \
 && make install \
 && make distclean \
 && ./configure --prefix=/opt/install-arm/extrae --host=arm-linux-gnueabihf --enable-arm \
    --without-mpi --without-unwind --without-dyninst --with-papi=/opt/install-arm/papi \
    --with-libz=/opt/install-arm/libz --with-binutils=/opt/install-arm/binutils \
    --with-xml-prefix=/opt/install-arm/libxml2 \
 && make install \
 && make distclean \
 && ./configure --prefix=/opt/install-x86_64/extrae \
    --without-mpi --without-unwind --without-dyninst --with-papi=/opt/install-x86_64/papi \
 && make install \
 && popd \
 && make PREFIX_TARGET=/opt/install-arm64 PREFIX_HOST=/opt/arm64 TARGET=aarch64-linux-gnu \
    EXTRAE_HOME=/opt/install-arm64/extrae all \
 && make mrproper \
 && make PREFIX_TARGET=/opt/install-arm PREFIX_HOST=/opt/arm TARGET=arm-linux-gnueabihf \
    EXTRAE_HOME=/opt/install-arm/extrae xdma-install xtasks-install nanox-install \
    mcxx-install envscript-install \
 && make mrproper \
 && make PREFIX_TARGET=/opt/install-x86 PREFIX_HOST=/opt/x86_64 \
    EXTRAE_HOME=/opt/install-x86_64/extrae xdma-install xtasks-install nanox-install \
    mcxx-install envscript-install"

RUN cd /bin \
 && ln -sf /bin/bash sh \
 && rm -rf /tmp/work \
 && adduser --disabled-password --gecos '' ompss \
 && adduser ompss sudo \
 && echo 'ompss:ompss' | chpasswd
ADD ompss-at-fpga-releases/dockerImageFiles/welcome_ompss_fpga.txt /opt/
WORKDIR /home/ompss/
ADD --chown=ompss:ompss ompss-at-fpga-releases/dockerImageFiles/example ./example/
RUN ln -s /opt/install-arm64/nanox/share/doc/nanox/paraver_configs/ompss ./example/paraver_configs \
 && echo "source /opt/arm64/environment_ompss_fpga.sh" >>.bashrc \
 && echo "source /opt/arm/environment_ompss_fpga.sh" >>.bashrc \
 && echo "export PATH=\$PATH:/opt/wxparaver/bin" >>.bashrc \
 && echo "cat /opt/welcome_ompss_fpga.txt" >>.bashrc
ADD  ompss-at-fpga-releases/dockerImageFiles/example /example
