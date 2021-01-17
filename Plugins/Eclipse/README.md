# Eclipse plugin 

This project is divided in three parts.

1. Eclipse Plugin (BSCPragmas)
2. Eclipse Feature Project (OmpSs Autocompletion Features)
3. Eclipse P2 repository (BSCPL)

This is due to the way Eclipse accepts plugins to be installed. For ease of use, these projects must be created using Eclipse, following these instructions:

# Requirements
Eclipse requires a Java JRE or JDK version 8 or newer.
The installation of plugins in Eclipse requires to have the support for
   - Eclipse PDE Plug-in Developer Resources
   - Oomph Setup PDE

You can install them from Eclipse itself using the "Install New Software..."
entry from the "Help" menu.


# Open Eclipse
![](images/1.png)
# Open Projects from File System

![](images/2.png)

# Select the folder with all 3 projects

![](images/3.png)

# Unselect ECLIPSE and finish the configuration

![](images/4.png)

# Check that the resources folder has the tree/tokens.json files

![](images/5.png)

# Synchronize OmpSs Autocompletion Features project with BSCPragmas

DSelect an display the file "features.xml" in OmpSs Autocompletion Features, and use the "synchronize" button to synchronize it with BSCPragmas.

When asked about the synchronization method, select "Synchronize versions on build (recommended)"

![](images/6.png)

# Synchronize and build the repository

Select and display the file "site.xml" from the BSCPL project, and
 - Synchronize (selected features only)
 - Build All


![](images/7.png)

# Now, you can install the plugin in your Eclipse installation

To do so, go to Help/Install New Software..., and add the BSCPL directory 
containing the project.

![](images/8.png)

When installed, you will need to restart the Eclipse process, in order for the new plugin to become active.

You can also build a zip file with the contents of the directory
 - eclipse-ompss/Plugins/Eclipse/BSCPL

For example:
 - cd eclipse-ompss/Plugins/Eclipse
 - zip -r BSCPL.zip BSCPL

And go to Help/Install New Software..., and add the BSCPL.zip, containing
the  project. 

You can share this BSCPL.zip project in order to distribute the plugin to
other users.


