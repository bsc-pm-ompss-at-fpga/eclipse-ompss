package autocomplete;

import java.io.File;

import OpenMP.OpenMP;

import OmpSs.OmpSs;
public class Main {
	
	
	public static void ensureFolder(String dir)
	{
		File directory = new File(dir);
	    if (! directory.exists()){
	        directory.mkdir();
	    }
		
	}
	public static void main(String[] args) {

			//generateJSON js = new generateJSON();
			
			ensureFolder("OpenMP");
			ensureFolder("OmpSs");
			OpenMP omp = new OpenMP();
			omp.generateTreeJson();
			omp.generateDirectivesInfo();
			OmpSs ompss = new OmpSs();
			ompss.generateTreeJson();
			ompss.generateDirectivesInfo();
	}

}
