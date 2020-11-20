# Autocomplete Generator

This project generates the configuration file that is used by Eclipse and Eclipse Theia / Visual Studio Code in order to generate the code suggestions on OmpSs@FPGA/Xilinx HLS pragmas.


# Compile Code

In order to compile, y there is a script ``build.sh`` that will generate a jar file called ``generator.jar``
This generator can be invoked using the following command ``java -jar generator.jar``, and will generate token/tree.json in OmpSs folder and OpenMP folder, each one corresponding to its implementation.
