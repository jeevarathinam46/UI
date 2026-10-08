package com.ll.iod.utils;


import org.apache.commons.io.FileUtils;
import org.testng.Reporter;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.CRC32;
import java.util.zip.CheckedOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class FileIOHandler {
	private FileIOHandler() {
		//prevent object instantiation from outside	
	}
	
	/**
	 * This method picks up all the files from the given source directory & compresses to a zip file.
	 * The zip file name would be as given in the reference variable
	 * @param source directory where all individual files located
	 * @param fileName name of the file. The file extension is .zip 
	 * @return boolena value true if zip file is created successfully otherwise false
	 */
	public static boolean zipFiles(String source, String fileName)  {
		File sourceFile = new File(source);
		String zipFileName = fileName;
		String destinationFilePath = sourceFile + File.separator + zipFileName;
		System.out.println("dest path "+destinationFilePath);
		File destinationFile = new File(destinationFilePath);//File name after compression
		boolean isSuccess = false; // whether the compression is successful

		if(!destinationFile.exists()) {
			ZipOutputStream out = null;
			CheckedOutputStream cos = null;
			FileOutputStream fout = null;
			try{
				fout = new FileOutputStream(destinationFile);
				cos = new CheckedOutputStream(fout, new CRC32());
				out = new ZipOutputStream(cos);
				zip(sourceFile, out, "", true);
				isSuccess = true;
			}catch(Exception ex){
				ex.printStackTrace();
			}finally{
				try {
					if(out != null) {
						out.flush();
						out.close();
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}else {
			isSuccess = true;
		}
		return isSuccess;
	}
	public static String zipped(String path,String fName) {
		File directoryToBeZipped = null;
		try {
			directoryToBeZipped = new File(path);
			FileOutputStream fos = new FileOutputStream(directoryToBeZipped.getParent() + "\\" + fName + ".zip");
			System.out.println(directoryToBeZipped.getParent());
			ZipOutputStream zos = new ZipOutputStream(fos);
			zipDirectory(zos, directoryToBeZipped, null);
			zos.flush();
			fos.flush();
			zos.close();
			fos.close();


		} catch (Exception e) {
			e.printStackTrace();
		}
		return (directoryToBeZipped.getParent() + "\\" + fName + ".zip");
	}
	public static void zipDirectory(ZipOutputStream zos, File fileToZip, String parentDirectoryName) throws Exception
	{
		if (fileToZip == null || !fileToZip.exists())
		{
			return;
		}

		String zipEntryName = fileToZip.getName();
		if (parentDirectoryName!=null && !parentDirectoryName.isEmpty())
		{
			zipEntryName = parentDirectoryName + "/" + fileToZip.getName();
		}

		// If we are dealing with a directory:
		if (fileToZip.isDirectory())
		{
			System.out.println("+" + zipEntryName);

			if(parentDirectoryName == null) // if parentDirectory is null, that means it's the first iteration of the recursion, so we do not include the first container folder
			{
				zipEntryName = "";
			}

			for (File file : fileToZip.listFiles()) // we iterate over all the folders/files and archive them by keeping the structure too.
			{
				zipDirectory(zos, file, zipEntryName);
			}
		} else // If we are dealing with a file, then we zip it directly
		{
			System.out.println("   " + zipEntryName);
			byte[] buffer = new byte[1024];
			FileInputStream fis = new FileInputStream(fileToZip);
			zos.putNextEntry(new ZipEntry(zipEntryName));
			int length;
			while ((length = fis.read(buffer)) > 0)
			{
				zos.write(buffer, 0, length);
			}
			zos.closeEntry();
			fis.close();
		}
	}
	
	/**
	 * Compress zip file
	 * @param file compressed file object
	 * @param out output ZIP stream
	 * @param dir relative parent directory name
	 * @param boo Whether to compress the empty directory into it
	 */
	public static void zip(File file, ZipOutputStream out, String dir, boolean boo) throws IOException{
		if(file.isDirectory()){// is the directory
			File []listFile = file.listFiles();//Get all the file objects in the directory
			if(listFile.length == 0 && boo){//empty directory compression
				out.putNextEntry(new ZipEntry(file.getName() + "/"));// put the entity into the output ZIP stream
			}else{
				for(File cfile: listFile){
					if(!cfile.getName().contains(".zip"))
						zip(cfile,out,dir + file.getName() + "/",boo);//recursive compression
				}
			}

		}else if(file.isFile()){//is a file
			byte[] bt = new byte[2048*2];
			ZipEntry ze = new ZipEntry(file.getName());//Build a compressed entity
			// Set the file size before compression
			ze.setSize(file.length());
			out.putNextEntry(ze);//// put the entity into the output ZIP stream
			FileInputStream fis = null;
			try{
				fis = new FileInputStream(file);
				int i=0;
				while((i = fis.read(bt)) != -1) {// Loop reads and writes to the output Zip stream
					out.write(bt, 0, i);
				}
			}catch(IOException ex){
				throw new IOException("An exception occurred while writing to a compressed file", ex);
			}finally{
				try{
					if (fis != null)
						fis.close();//Close the input stream
				}catch(IOException ex){
					Reporter.log("An exception occurred while closing the input stream");
					ex.printStackTrace();
				}
			}           
		}
	}
	
	public static boolean deleteFiles(String filePath) {
		boolean isDeleted = true;
		try {
			File file = new File(filePath);
			FileUtils.forceDelete(file);
			/*File[] fileList = file.listFiles();

			for (File tmpFile : fileList) {
				System.out.println(tmpFile.getAbsolutePath());
				if(!tmpFile.isDirectory()) {
					System.out.println(tmpFile.getAbsolutePath());
					deleteFiles(tmpFile.getAbsolutePath());
				} else {
					Path path = Paths.get(tmpFile.getAbsolutePath());
					Files.delete(path);
				}
			}*/
		} catch(Exception ex) {
			//Ignore the exception if system failes to clean the folder
			isDeleted = false;
			Reporter.log("Exception occured due to " + ex.getMessage());
		}
		return isDeleted;
	}
	public static void main(String[] args) {
		String sourceFolder = "C:\\LL\\Automation\\Repository\\IDEA\\IDEA-WFS\\target\\test-output\\testReports\\C891566";
		String fileName = "C891566.zip";
		FileIOHandler.zipFiles(sourceFolder, fileName);
	}
 }
