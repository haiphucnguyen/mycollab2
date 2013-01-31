package com.esofthead.mycollab.usertracking;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.LinkedList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class Unzip {
	public static final List<String> unzip(String inputZipFile, String outputFolder) throws Exception {
		File unzipFile = new File(inputZipFile);
		if (!unzipFile.exists() || !unzipFile.isFile() || !unzipFile.getName().endsWith(".zip"))
			throw new Exception("Input file does not exist or wrong format");
		
		File folder = new File(outputFolder);
		if (folder.exists() && folder.isFile()) {
			throw new Exception("The folder output is file");
		}
		
		if (!folder.exists()) {
			folder.mkdirs();
		}
		
		byte[] buffer = new byte[4096];
		List<String> lsResult = new LinkedList<String>();
		ZipInputStream zis = new ZipInputStream(new FileInputStream(unzipFile));
		ZipEntry ze = zis.getNextEntry();
		while (null != ze) {
			String fileName = ze.getName();
			File newFile = new File(outputFolder + File.separator + fileName);
			
			new File(newFile.getParent()).mkdirs();
			FileOutputStream fos = new FileOutputStream(newFile);   
			int len;
            while ((len = zis.read(buffer)) > 0) {
            	fos.write(buffer, 0, len);
            }
            fos.close();  
            
            lsResult.add(newFile.getAbsolutePath());
            ze = zis.getNextEntry();
		}
		
		zis.closeEntry();
    	zis.close();
		return lsResult;
	}
}
