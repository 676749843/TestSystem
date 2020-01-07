package com.testSystem.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class FileUtils {

    @Value("${java.check.path}")
    private String testJavaPath;

    @Value("${selenium.path}")
    private String libJarPath;
    //获取driverPath

    //获取临时校验区

    //获取脚本存放地址

    public String createTestScript(String script,String fileName)
    {
        String uuidFilePath =testJavaPath+ File.separator+UUID.randomUUID().toString().replace("-","");
        File file = new File(uuidFilePath);
        if(!file.exists())
        {
            file.mkdirs();
        }
        String scrpitFileName = uuidFilePath+File.separator+fileName+".java";
        File scrpitFile = new File(scrpitFileName);
        try {
            if(!scrpitFile.exists()){
                scrpitFile.createNewFile();
            }
            FileWriter fileWriter = new FileWriter(scrpitFile.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fileWriter);
            bw.write(script);
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return scrpitFileName;
    }

    public List<String> excuteCMDCommand(String cmdCommand)
    {
        List<String> stringBuilder = new ArrayList<String>();
        Process process = null;
        try {
            process = Runtime.getRuntime().exec(cmdCommand);
            int exitValue = process.waitFor();

            BufferedReader bufferedReader = new BufferedReader(
                    new InputStreamReader(process.getInputStream(), "GBK"));
            String line = null;
            while((line=bufferedReader.readLine()) != null)
            {

                    stringBuilder.add(line);


                //stringBuilder.append(line+"\n");
            }


         bufferedReader = new BufferedReader(
                    new InputStreamReader(process.getErrorStream(), "GBK"));
            while((line=bufferedReader.readLine()) != null)
            {
                if(!stringBuilder.contains(line))
                {
                    stringBuilder.add(line);
                }
                //stringBuilder.append(line+"\n");
            }
            return stringBuilder;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public String getTestCommand(String fileName)
    {
        String command = "javac -cp  .;"+libJarPath+"  "+fileName;
        return command;
    }

    public String createcoomandPath(String command) {

        String uuidFilePath =testJavaPath+ File.separator+UUID.randomUUID().toString().replace("-","");
        File file = new File(uuidFilePath);
        if(!file.exists())
        {
            file.mkdirs();
        }
        String commandFileName = uuidFilePath+File.separator+"test.cmd";
        File scrpitFile = new File(commandFileName);
        try {
            if(!scrpitFile.exists()){
                scrpitFile.createNewFile();
            }
            FileWriter fileWriter = new FileWriter(scrpitFile.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fileWriter);
            bw.write(command);
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return commandFileName;
    }
}
