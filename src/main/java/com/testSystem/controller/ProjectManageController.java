package com.testSystem.controller;

import com.testSystem.dao.TestCaseInterfaceDao;
import com.testSystem.entity.Project;
import com.testSystem.entity.ProjectParam;
import com.testSystem.service.ProjectManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
public class ProjectManageController {
    @Autowired
    private ProjectManageService projectManageService;


    @RequestMapping("/selProjectAll")
    @ResponseBody
    public List<Project> selProjectAll(@RequestBody  ProjectParam projectParam)
    {
        return projectManageService.selProjectAll(projectParam);
    }

    @RequestMapping("/countProjectAll")
    @ResponseBody
    public int countProjectAll(@RequestBody  ProjectParam projectParam)
    {
        return projectManageService.countProjectAll(projectParam);
    }

    @RequestMapping("/addProjectInfo")
    @ResponseBody
    public String addProjectInfo(@RequestBody  Project project)
    {
        return projectManageService.addProjectInfo(project);
    }


    @RequestMapping("/initProjectSelect")
    @ResponseBody
    public List<Project> initProjectSelect()
    {
        return projectManageService.initProjectSelect();
    }


    @RequestMapping("/projectViewNum")
    @ResponseBody
    public List<Integer> projectViewNum()
    {
        return projectManageService.projectViewNum();
    }

    @RequestMapping("/dtsNumTop5")
    @ResponseBody
    public Map<String,Integer> dtsNumTop5()
    {
        return projectManageService.dtsNumTop5();
    }

    @RequestMapping("/testcaseNumTop5")
    @ResponseBody
    public  List<Project> testcaseNumTop5()
    {
        return projectManageService.testcaseNumTop5();
    }

    @RequestMapping("/scriptType")
    @ResponseBody
    public Map<String,Integer> scriptType()
    {
        return projectManageService.scriptType();
    }
    @RequestMapping("/testCaseType")
    @ResponseBody
    public Map<String,Integer> testCaseType()
    {
        return projectManageService.testCaseType();
    }
}
