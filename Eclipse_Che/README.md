# Eclipse Che 6

[Che-6](https://www.eclipse.org/che/) is an OpenSource cloud IDE with containerized workspaces that has support for large teams. 

# Eclipse Che Stack

An eclipse Che Stack is a conjuntion of compiler, runtimes, tools etc... that a machine must have in order to work in a project. 

Most of the times, an stack can simply be a Docker Image with some environment variables attached, however, due to our use case, where OmpSs@FPGA rely on [Vivado](https://www.xilinx.com/products/design-tools/vivado.html) in order to perform the FPGA compilation, we can't supply a Docker Image with that propietary tool installed, and also, there are space limitations since the vivado toolchain is pretty big.

For that, in order to be able to use Eclipse Che capabilities, apart from creating a base stack image with all our OmpSs@FPGA toolchain, we created an script that set-up the Eclipse Che installation in the local machine.

# Automatic Stack Generation

This project automatically generates and embeds into eclipse, two stacks per installed vivado version, one for OmpSs@FPGA 32 bits and one for OmpSs@fpga 64 bits.

In order to this automatic vivado stack generation to woerk, the user must supply some configurations, including where is vivado located.

This configurations can be modified from the script ``run_eclipse_che.py``.


At the beggining of the file, we can find this variables.
```
#user_defined_directories
_eclipseCheDirectory = "~/che_test"  #The directory where the che configurations will be stored
_xilinx_dir="/tools/Xilinx/"         #The folder that contains the Vivado installations
_petalinux_dir="/tools/petalinux/"   #The folder that contains the Petalinux installation
_CROSS_COMPILE_32="arm-linux-gnueabihf-" #Predefined cross_compile variable
_CROSS_COMPILE_64="aarch64-linux-gnu-"   #Predefined cross_compile variable
```

# Running the project

Most of the times, if configured correctly, a simple
``python3 run_eclipse_che.py`` will generate the necessary images, load the stacks and open eclipse che automatically.