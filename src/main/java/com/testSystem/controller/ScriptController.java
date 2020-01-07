package com.testSystem.controller;

import com.testSystem.entity.Script;
import com.testSystem.service.ScriptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class ScriptController {
    @Autowired
    ScriptService scriptService;

    @RequestMapping("/testJavaScript")
    @ResponseBody
    public List<String> testJavaScript(String scriptContent,String scriptName)
    {
         return  scriptService.testJavaScript(scriptContent,scriptName);
    }

    @RequestMapping("/selByScriptProId")
    @ResponseBody
    public List<Script> selByScriptProId(int proId)
    {
        return  scriptService.selByScriptProId(proId);
    }

    @RequestMapping("/addScript")
    @ResponseBody
    public Script addScript(@RequestBody  Script script)
    {
        return  scriptService.addScript(script);
    }

    @RequestMapping("/updateScript")
    @ResponseBody
    public String updateScript(@RequestBody  Script script)
    {
        return  scriptService.updateScript(script);
    }

    @RequestMapping("/runScript")
    @ResponseBody
    public List<String> runScript(String scriptContent,String scriptName,String scriptParam)
    {
        return  scriptService.runScript(scriptContent,scriptName,scriptParam);
    }



}
