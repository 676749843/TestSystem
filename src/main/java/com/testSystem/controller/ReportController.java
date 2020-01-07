package com.testSystem.controller;

import com.testSystem.entity.DTS;
import com.testSystem.entity.DTSParam;
import com.testSystem.entity.ReportTable;
import com.testSystem.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
public class ReportController {

    @Autowired
    private ReportService reportService;
    @RequestMapping("/getReportTableInfo")
    @ResponseBody
    public List<ReportTable> getReportTableInfo(@RequestBody DTSParam dtsParam)
    {
        return reportService.getReportTableInfo(dtsParam);
    }
    @RequestMapping("/countProjectDetail")
    @ResponseBody
    public int countProjectDetail(@RequestBody DTSParam dtsParam)
    {
        return reportService.countProjectDetail(dtsParam);
    }

    @RequestMapping("/getTestCaseTypeInfo")
    @ResponseBody
    public Map<Integer,Integer> getTestCaseTypeInfo(@RequestBody DTSParam dtsParam)
    {
        return reportService.getTestCaseTypeInfo(dtsParam);
    }


}
