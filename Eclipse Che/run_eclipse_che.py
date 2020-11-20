import subprocess
import os
import requests
import json
import sys
import webbrowser as wb
import copy 


from os import walk


#user_defined_directories
_eclipseCheDirectory = "~/che_test"
_xilinx_dir="/tools/Xilinx/"
_petalinux_dir="/tools/petalinux/"
_CROSS_COMPILE_32="arm-linux-gnueabihf-"
_CROSS_COMPILE_64="aarch64-linux-gnu-"


##THIS SHOULD NOT BE TOUCHED
_fpgaImage = "ompss_fpga_2.4:che"
_stackBase = "ompss_fpga_stack_base:che"
_che_version="6.19.0"
_gitURL = "https://pm.bsc.es/gitlab/ompss-at-fpga/eclipse-che"


_extra_mount="/tmp/.X11-unix:/tmp/.X11-unix"
_che_pull_off = "-e CHE_DOCKER_ALWAYS__PULL__IMAGE=false "
_che_workspace_volume = "-e CHE_WORKSPACE_VOLUME='"+_xilinx_dir+":/opt/xilinx;"+_petalinux_dir+":/opt/petalinux;"+_extra_mount+"'"
_docker_socket = "-v /var/run/docker.sock:/var/run/docker.sock"
_che_dir_bind = "-v "+_eclipseCheDirectory+":/data"

_che_run_params = "docker run -ti " + _docker_socket +" "+_che_dir_bind+" "+_che_workspace_volume+" "+_che_pull_off+"  -e DISPLAY=$DISPLAY  eclipse/che:"+_che_version+" start"  

_che_ip =""

def bashCall(command):
	print(command)
	return subprocess.getoutput(command)

def existsImage(image):
	return bashCall("docker inspect --type=image " +image)[0:2] != "[]"

def isInGit(url):
	return  bashCall("git config --get remote.origin.url") == _gitURL

def killAllContainers():
	bashCall("docker kill $(docker ps -q)")


def getVivadoVersions():
	(_, dirnames, _) = next(os.walk(_xilinx_dir+"/Vivado"))
	return dirnames


def dockerCheckValid():
	valid = False
	try:
		file = open("Dockerfile",'r')
		valid = file.readline()[0:4] == "#che"
		file.close()
	except:
		pass
	return valid


def loadStack(json_stack, arch_prefix, cross_compile_triplet, vivado_version):
	json_stack["workspaceConfig"]["environments"]["default"]["machines"]["dev-machine"]["env"]["PATH"] += ":/opt/xilinx/Vivado/"+vivado_version+"/bin"	
	json_stack["workspaceConfig"]["environments"]["default"]["machines"]["dev-machine"]["env"]["CROSS_COMPILE"] = cross_compile_triplet
	json_stack["id"] = arch_prefix+cross_compile_triplet+vivado_version
	json_stack["description"] = "Custom Stack to compile for arm "+ arch_prefix + " with vivado " +vivado_version
	json_stack["name"] = "OmpSs "+ arch_prefix + " " +vivado_version
	url = _che_ip+"/api/stack"
	r = requests.post(url, json=json_stack)


def addStacks():
	for version in getVivadoVersions():
		with open('./stacks/stack_base.json') as json_file:
			json_32 = json.load(json_file)
			json_64 = copy.deepcopy(json_32)
			loadStack(json_32,"32b",_CROSS_COMPILE_32, version)
			loadStack(json_64,"64b",_CROSS_COMPILE_64, version)

def eclipseCheInstallation(directory):
	print("loading eclipse... this can take about a minute.")
	tmp_s = bashCall(_che_run_params)
	tmp_use = tmp_s.find("Use:")
	tmp_n = tmp_s.find("\n",tmp_use)
	global _che_ip 
	_che_ip = tmp_s[tmp_use+5:tmp_n]
	print("DONE! to enter Eclipse go to: "+_che_ip)


def buildDockerfile():
	print("generating docker image "+_fpgaImage+" ... this can take up to 1h...")
	bashCall("docker build -f ./stacks/Dockerfile ./stacks -t "+_stackBase)
	bashCall("docker build . -t "+_fpgaImage)


def generateDockerInstallation():
	if(isInGit(_gitURL)):
		print("git detected!")
		if(dockerCheckValid()):
			print("You have a valid Dockerfile!")
			buildDockerfile()
		else:
			print("You have an invalid Dockerfile")
			sys.exit()
	else:
		print("First you need to do: \033[1;31;47m  git clone --recursive  "+_gitURL + " \033[0;37;40m")
		sys.exit()



def main():

	total = len(sys.argv) # Get the arguments list
	cmdargs = str(sys.argv) # Print it



	killAllContainers()
	if(not existsImage(_fpgaImage)):
		generateDockerInstallation()
	eclipseCheInstallation(_eclipseCheDirectory)
	addStacks()
	wb.open_new_tab(_che_ip)

main()






