javac -d ./build  src/autocomplete/Main.java src/OmpSs/*.java src/OpenMP/*.java src/org/json/*.java && cd build && mkdir -p META-INF && echo "Main-Class: autocomplete.Main" > META-INF/MANIFEST.MF && jar cmvf META-INF/MANIFEST.MF generator.jar * && mv generator.jar .. && cd - && rm -rf build