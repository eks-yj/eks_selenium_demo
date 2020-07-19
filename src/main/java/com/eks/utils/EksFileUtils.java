package com.eks.utils;

import lombok.Cleanup;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class EksFileUtils {
    public static final String ISO_8859_1 = "ISO-8859-1";
    public static final String US_ASCII = "US-ASCII";
    public static final String UTF_16 = "UTF-16";
    public static final String UTF_16BE = "UTF-16BE";
    public static final String UTF_16LE = "UTF-16LE";
    public static final String UTF_8 = "UTF-8";
    public static final String PROJECT_PATH_STRING = System.getProperty("user.dir");
    public static String getProjectPath(){
        return PROJECT_PATH_STRING;
    }
    public static String getPathString(String ...itemFileNameStringArray){
        StringBuilder stringBuilder = new StringBuilder();
        if (itemFileNameStringArray != null && itemFileNameStringArray.length > 0){
            for(int i = 0;i < itemFileNameStringArray.length;i++){
                if (i != 0){
                    stringBuilder.append(File.separator);
                }
                stringBuilder.append(itemFileNameStringArray[i]);
            }
        }
        return stringBuilder.toString();
    }
    public static String getPathBaseProject(String ...itemFileNameStringArray){
        return PROJECT_PATH_STRING + File.separator + getPathString(itemFileNameStringArray);
    }
    public static File getFileBaseProject(String ...itemFileNameStringArray){
        return new File(getPathBaseProject(itemFileNameStringArray));
    }
    public static List<String> convertFileToStringList(String filePath) throws Exception {
        return convertFileToStringList(new File(filePath), EksFileUtils.UTF_8);
    }
    public static List<String> convertFileToStringList(File file) throws Exception {
        return convertFileToStringList(file, EksFileUtils.UTF_8);
    }
    public static List<String> convertFileToStringList(File file, String encoding) throws Exception {
        List<String> list = new ArrayList<String>();
        if (file.exists() && file.isFile()) {
            @Cleanup BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(file), encoding));
            String lineTxt = null;
            while ((lineTxt = bufferedReader.readLine()) != null) {
                list.add(lineTxt);
            }
        } else {
            throw new Exception("The specified file could not be found");
        }
        return list;
    }
    public static String convertFileToContentString(String filePathString) throws Exception {
        return convertFileToContentString(new File(filePathString));
    }
    public static String convertFileToContentString(File file) throws Exception {
        List<String> stringList = convertFileToStringList(file);
        if (stringList == null || stringList.size() == 0){
            return null;
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (String string : stringList) {
            stringBuilder.append(string);
        }
        return stringBuilder.toString();
    }
    public static Boolean writeStringToFile(Boolean throwExceptionBoolean, String filePathString, String writeString, Boolean appendBoolean) throws IOException {
        if (writeString != null && writeString.trim().length() > 0){
            File file = createFileIfNotExist(filePathString);
            @Cleanup FileOutputStream fileOutputStream = new FileOutputStream(file,appendBoolean);
            fileOutputStream.write(writeString.getBytes());
            return true;
        }
        return false;
    }
    public static byte[] convertFileToByteArray(File file) throws IOException {
        byte[] byteArray = null;
        @Cleanup FileInputStream fileInputStream = new FileInputStream(file);
        @Cleanup ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        int len;
        byte[] buffer = new byte[1024];
        while ((len = fileInputStream.read(buffer)) != -1) {
            byteArrayOutputStream.write(buffer, 0, len);
        }
        byteArray = byteArrayOutputStream.toByteArray();
        return byteArray;
    }
    public static String getNewFileNameString(String oldFilePathString){
        File file = new File(oldFilePathString);
        if (!file.exists()){
            throw new RuntimeException("The path must exist:" + oldFilePathString);
        }
        return getNewFileNameString(file);
    }
    public static String getNewFileNameString(File file){
        String fileNameString = file.getName();
        String[] fileNameItemStringArray = fileNameString.split("\\.");
        String oldFileNameString = fileNameItemStringArray[0];
        return  oldFileNameString + "_" + DateUtils.getNowDateString() + fileNameString.substring(oldFileNameString.length() + 1, fileNameString.length() - fileNameItemStringArray[fileNameItemStringArray.length - 1].length());

    }
    public static String getNewFilePathString(String oldFilePathString){
        return getNewFilePathString(oldFilePathString);
    }
    public static String getNewFilePathString(File file){
        return file.getParentFile().getAbsolutePath() + File.separator + getNewFileNameString(file.getAbsolutePath());
    }
    public static String getUuidString(){
        return DateUtils.getNowDateString() + "_" + UUID.randomUUID().toString().replace("-", "");
    }
    public static File createFileIfNotExist(String filePathString) throws IOException {
        return createFileIfNotExist(new File(filePathString));
    }
    public static File createFileIfNotExist(File file) throws IOException {
        if (!file.exists()) {
            File dirFile = new File(file.getParent());
            if (!dirFile.exists()) {
                boolean mkdirsboolean = dirFile.mkdirs();
                if (!mkdirsboolean){
                    throw new RuntimeException("Directory creation failed");
                }
            }
            boolean newFileboolean = file.createNewFile();
            if (!newFileboolean){
                throw new RuntimeException("File creation failed");
            }
        }
        return file;
    }
    public static File getNowDateFile(String parentRelativePathString, String filenameExtensionString){
        return new File(EksFileUtils.getPathString(PROJECT_PATH_STRING, parentRelativePathString, DateUtils.getNowDateString() + "." + filenameExtensionString));
    }
}
