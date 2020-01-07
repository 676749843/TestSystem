package com.testSystem.controller;

import com.testSystem.entity.TestCaseInterface;
import com.testSystem.entity.TestCaseView;
import com.testSystem.service.TestCaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
public class TestCaseController {

    @Autowired
    public TestCaseService testCaseService;

    @RequestMapping("/selTestCaseView")
    @ResponseBody
    public List<TestCaseView> selTestCaseView( @RequestBody TestCaseView testCaseView){
        List<TestCaseView> list = testCaseService.selTestCaseView(testCaseView);
        return list;
    }

    @RequestMapping("/countTestCaseView")
    @ResponseBody
    public int countTestCaseView( @RequestBody TestCaseView testCaseView){
        int num = testCaseService.countTestCaseView(testCaseView);
        return num;
    }
    @RequestMapping("/addNewTestCaseView")
    @ResponseBody
    public String addNewTestCaseView( @RequestBody TestCaseView testCaseView){
        String result = testCaseService.addNewTestCaseView(testCaseView);
        return result;
    }
    @RequestMapping("/editDostate")
    @ResponseBody
    public String editDostate( @RequestBody TestCaseView testCaseView){
        String result = testCaseService.editDostate(testCaseView);
        return result;
    }
    @RequestMapping("/delTestcaseView")
    @ResponseBody
    public String delTestcaseView( @RequestParam("id")int id){
        String result = testCaseService.delTestcaseView(id);
        return result;
    }



    @RequestMapping("/uploadTestCaseViewByExcel")
    @ResponseBody
    public String uploadTestCaseViewByExcel(@RequestParam("projectId") String  projectIdStr,@RequestParam("fileUpload")
            MultipartFile file){
        String result = testCaseService.uploadTestCaseViewByExcel(projectIdStr,file);



        return result;
    }


    @RequestMapping("/selTestCaseInterface")
    @ResponseBody
    public List<TestCaseInterface> selTestCaseInterface( @RequestBody TestCaseInterface testCaseInterface){
        List<TestCaseInterface> list = testCaseService.selTestCaseInterface(testCaseInterface);
        return list;
    }
    @RequestMapping("/countTestCaseInterface")
    @ResponseBody
    public int countTestCaseInterface( @RequestBody TestCaseInterface testCaseInterface){
        int num = testCaseService.countTestCaseInterface(testCaseInterface);
        return num;
    }


    @RequestMapping("/addNewTestCaseInterface")
    @ResponseBody
    public String addNewTestCaseInterface( @RequestBody TestCaseInterface testCaseInterface){
        String result = testCaseService.addNewTestCaseInterface(testCaseInterface);
        return result;
    }
    @RequestMapping("/editDostateInterface")
    @ResponseBody
    public String editDostateInterface( @RequestBody TestCaseInterface testCaseInterface){
        String result = testCaseService.editDostateInterface(testCaseInterface);
        return result;
    }
    @RequestMapping("/delTestcaseViewInterface")
    @ResponseBody
    public String delTestcaseViewInterface( @RequestParam("id")int id){
        String result = testCaseService.delTestcaseViewInterface(id);
        return result;
    }

}
