package com.ll.iod.utils;

import com.ll.iod.constants.IodConstants;

import java.io.File;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Map;
import java.util.Random;

public class CommonUtils {

	private CommonUtils() {
		//Prevent new instantiation from outside
	}
	public static boolean isFileDownloaded_Ext(String dirPath, String ext){
		System.out.println(dirPath);
		boolean flag=false;
		File dir = new File(dirPath);
		File[] files = dir.listFiles();
		System.out.println(files.length);
		if (files == null || files.length == 0) {
			flag = false;
		}

		for (int i = 0; i <files.length; i++) {
			if(files[i].getName().contains(ext)) {
				System.out.println(files[i].getName());
				flag=true;
			}
		}
		return flag;
	}

	/**
	 * This method is used to generate random String for Contact Name while creating
	 * the seller credentials
	 * @paramlength Length is used to return particular size of string
	 * @return
	 *
	 *         Returns the String
	 */

	public static String getUniqueText() {
		String name = "AUT_";
		String sDateTime = CalendarDateHandler.getFormatedDate(IodConstants.DATE_MMddYYYYHHmmss);
		return name+sDateTime;
	}


	/**
	 * This method used to sleep the thread for 5000ms time before executing next
	 * line.
	 */
	public static void sleepForAWhile() {
		try {
			sleepForAWhile(Integer.parseInt(ConfigPropertyLoader.getConfigValue("sleepTimeFiveThousandMs")));
		} catch(NumberFormatException nfex) {
			sleepForAWhile(5000); // defaulted to 5000 millisecond if there is number format exception
		}
	}
	/**
	 * This method used to sleep the thread for 5000ms time before executing next
	 * line.
	 */
	public static void sleepForAWhile(int value) {
		try {
			Thread.sleep(value);
		}  catch(InterruptedException inEx) {
			Thread.currentThread().interrupt();
		}
	}

	public static String getXMLDirPath(Map<String,String> hashmap) {
			return IodConstants.XML_BASE_EXPORT_DIRECTORY + hashmap.get("ClientID") + "."
				 + hashmap.get(IodConstants.CLIENT_NAME) + "/" + CalendarDateHandler.getFormatedDate("MMM-dd-yyy")
				 + "/" + hashmap.get("LoanNumber") + "/0" + hashmap.get("Priority") + "_"
				 + hashmap.get("LoanNumber") + "~" + hashmap.get(IodConstants.CLIENT_NAME) + "~" + hashmap.get("BatchType")
				 + "_" + hashmap.get("BatchID") + ".xml";

	}

	public static String getPDFDirPath(Map<String,String> hashmap) {
			return IodConstants.XML_BASE_EXPORT_DIRECTORY + hashmap.get("ClientID") + "."
				+ hashmap.get(IodConstants.CLIENT_NAME) + "/" + CalendarDateHandler.getFormatedDate("MMM-dd-yyy")
				+ "/" + hashmap.get("LoanNumber") + "/" + hashmap.get("LoanNumber") + "~"
				+ hashmap.get(IodConstants.CLIENT_NAME) + "~" + hashmap.get("BatchType") + "*pdf";
	}
	/**
	 * This method used to get random number
	 */
	public static int getRandomNum(int min, int max)
	{

	   Random rand =  null;
	   int randomNum = 0;
	   try {
		   rand = SecureRandom.getInstanceStrong();
		   randomNum = rand.nextInt((max - min) + 1) + min;
	   } catch(NoSuchAlgorithmException noAlgEx) {
		   //Ignore noAlgEx and set to -1
		   randomNum = -1;
	   }
	   return randomNum;
	}

}
