package com.testSystem.service;

import com.testSystem.dao.ScriptDao;
import com.testSystem.entity.Script;
import com.testSystem.utils.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class ScriptService {

    @Autowired
    private  FileUtils fileUtils;
    @Autowired
    private ScriptDao scriptDao;
    public List<String> testJavaScript(String scriptContent,String scriptName) {
       // excuteCMDCommand("javac -cp  .;D:\\webdriver\\selenium-server-standalone-2.40.0.jar  D:\\webdriver\\test.java ");
       // excuteCMDCommand("D:\\webdriver\\test.cmd");
        String fileName = fileUtils.createTestScript(scriptContent,scriptName);

        String command = fileUtils.getTestCommand(fileName);
        String coomandPath = fileUtils.createcoomandPath(command);

        List<String> result = fileUtils.excuteCMDCommand(coomandPath);
        return result;
    }

    public  String excuteCMDCommand(String cmdCommand)
    {
        StringBuilder stringBuilder = new StringBuilder();
        Process process = null;
        try {
            process = Runtime.getRuntime().exec(cmdCommand);
            int exitValue = process.waitFor();
            BufferedReader bufferedReader = new BufferedReader(
                    new InputStreamReader(process.getInputStream(), "UTF-8"));
            String line = null;
            while((line=bufferedReader.readLine()) != null)
            {
                stringBuilder.append(line+"\n");
            }
            return stringBuilder.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    public List<Script> selByScriptProId(int proId) {
      List<Script> list =  scriptDao.selByScriptProId(proId);
      return list;
    }

    public Script addScript(Script script) {
        Script savaScript = new Script();
        savaScript.setScriptContent(script.getScriptContent());
        savaScript.setScriptDesc(script.getScriptDesc());
        savaScript.setProjectId(script.getProjectId());
        savaScript.setScriptName(script.getScriptName());
        script = scriptDao.save(savaScript);
        if(script==null)
        {
            return script;
        }
        return null;
    }

    public String updateScript(Script script) {
        script = scriptDao.save(script);
        if(script==null)
        {
            return "success";
        }
        return "success";
    }

    public List<String> runScript(String scriptContent, String scriptName,String scriptParam) {
        String fileName = fileUtils.createTestScript(scriptContent,scriptName);
        scriptParam = scriptParam.replace(","," ");
        String command = fileUtils.getTestCommand(fileName);
        String coomandPath = fileUtils.createcoomandPath(command);

        List<String> testScript = fileUtils.excuteCMDCommand(coomandPath);
        for(String content:testScript)
        {
            if(content.contains("错误")||content.contains("error")||content.contains("error"))
            {
                testScript=new ArrayList<>();
                testScript.add("testError");
                    return testScript;
            }
        }
        List<String>  runScript=new ArrayList<>();
        String javaPath = fileName.substring(0,fileName.lastIndexOf(File.separator));
        String commnd = " java -cp  .;E:\\webdriver\\selenium-server-standalone-2.40.0.jar  -classpath "+javaPath+" "+scriptName+" "+scriptParam;
        String cmdPath = fileUtils.createcoomandPath(commnd);
        runScript = fileUtils.excuteCMDCommand(cmdPath);
        return runScript;
    }
}
