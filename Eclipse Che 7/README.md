# Eclipse Che 7

[Che-7](https://www.eclipse.org/che/) is an OpenSource cloud IDE with containerized workspaces that has support for large teams. 

# Eclipse Che 7 deployment

Che-7 introduced various changes on the deployment, removing the option to run Che in docker, and making the default deployment via kubernetes. However, this increases the work that needs to be done by the user in order to set-up the environment and introduces some limitations that were not present in the previous version.

In order to lease some of these limitations, we created a **vagrant** set of scripts that generates a virtual machine with Che7 already deployed, and created a Docker Image that can be used inside Che-7.


# Creating Docker Image

To create the docker image you can simply do:

``cd docker_img && docker build . -t ompssfpga/che7``


# Vagrant

[Vagrant](https://www.vagrantup.com/intro)
Vagrant is a tool for building and managing virtual machine environments in a single workflow.


In this repository there are 4 scripts that helps with managing vagrant.

* `che.sh` :  This script creates or resumes the Eclipse Che installation, it returns
the address you must use to connect to che!

* `start.sh` :  This script creates or resumes the execution of the VM and the
Kubernetes cluster

* `stop.sh` :  This script halts the VM

* `destroy.sh` :  This script destroys and erases the VM


You must take into account that che provisioning can fail, this is due to some bugs while awaiting for some connections that che automatically starts. In case this occurs, you can **run it again** until it works. When it returns the **WEB ADDRESS** from the server, the one you must use to connect to Eclipse Che 7, it will be finished.


# Adding Certificates

In order to be able to use Che-7, you must add the certificate that has been created **(cheCA.crt)** to your browser. You can use the following [official tutorial](https://www.eclipse.org/che/docs/che-7/end-user-guide/importing-certificates-to-browsers/)


# Creating workspace

To create a workspace, you must first create a yaml file with the workspace configuration and, for ease, upload the generated docker image to dockerhub.

```
metadata:
  name: ompssatfpga
projects:
  - name: cpp-fpga-project
    source:
      location: '[LOCATION OF AN EXAMPLE PROJECT IN A GIT REPOSITORY]'
      type: git
components:
  - mountSources: true
    command:
      - sleep
      - infinity
    memoryLimit: 512Mi
    type: dockerimage
    image: [GENERATED DOCKER IMAGE NAME, ALREADY UPLOADED TO DOCKERHUB]
    env:
      - value: >-
          /usr/local/sbin:/usr/local/bin:/usr/sbin:/usr/bin:/sbin:/bin:/usr/games:/usr/local/games:/opt/arm64/mcxx/bin:/opt/arm/mcxx/bin:/opt/arm64/ait
        name: PATH
      - value: aarch64-linux-gnu-
        name: CROSS_COMPILE
apiVersion: 1.0.0
```

Once you have already set-up this configuration file, you will be able to create a che workspace with the OmpSs@FPGA toolchain.

Due to some limitations, It's not possible yet to forward the host Vivado installation into the workspace, however, you can install vivado inside the machine. 