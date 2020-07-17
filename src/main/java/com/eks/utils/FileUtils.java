package com.eks.utils;

import java.io.File;

public class FileUtils {
    public static String getProjectPath(){
        return System.getProperty("user.dir");
    }
    public static String getPathBaseProjectPath(String relativePathString){
        return getProjectPath() + File.separator + relativePathString;
    }
}
