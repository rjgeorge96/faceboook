package com.testng.facebook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class XLSX_Reader {
	
	public static void readAll() throws FileNotFoundException {
		
		File f =new File("./Facebook/Data/FB_Project_Data.xlsx");
		FileInputStream fi = new FileInputStream(f); //May throw FileNotFoundException so add throws declaration.
		
	}

}
