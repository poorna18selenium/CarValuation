package com.registration.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.regex.Pattern;

public class ReadFile {
    public  static List<String> getCarRegistrationNumbers(String inputFileName) throws IOException {
        BufferedReader BufferedReader = null;
        String[] words = null;
        List<String> regNumbers= new ArrayList<String>();
        try{
            File inputFile = getFile(inputFileName);
            BufferedReader = new BufferedReader(new FileReader(inputFile.getAbsolutePath()));
            String strLine;
            while ((strLine = BufferedReader.readLine()) != null)   {
                String reg = "(^[A-Z]{2}[0-9]{2}([A-Z]{3}|\\s[A-Z]{3})$)";
                words= strLine.split(" ");
                for (String str : words) {
                    if (Pattern.matches(reg, str)) {
                        regNumbers.add(str);
                    }
                }

            }

        }catch (Exception exception){
            System.err.println("Error: " + exception.getMessage());
        }finally {
            BufferedReader.close();
        }
        return regNumbers;
    }
    public static  Map<String,List<String>> getExpectedDetails(String outputFileName) throws IOException {
        BufferedReader BufferedReader = null;
        String outLine=" ";
        List<String> outputValues=new ArrayList<String>();
        Map<String,List<String>> expectedMap=new HashMap<String, List<String>>();
        try{
            File inputFile = getFile(outputFileName);
            BufferedReader = new BufferedReader(new FileReader(inputFile.getAbsolutePath()));
            while ((outLine = BufferedReader.readLine()) != null) {
                outputValues.add(outLine);
            }
            //remove headers
            outputValues.remove(0);

            //creating map
            for(String expectedValue:outputValues)
            {
                String[] exp = expectedValue.split(",");
                List<String> list = Arrays.asList(exp);
                expectedMap.put(list.get(0),list);
            }

        }catch (Exception e){
            System.err.println("Error: " + e.getMessage());
        }finally {
            BufferedReader.close();
        }

        return  expectedMap;
    }

    private static File getFile(String filepath) {
        ClassLoader classLoader = ReadFile.class.getClassLoader();
        return new File(classLoader.getResource(filepath).getFile());
    }
}

