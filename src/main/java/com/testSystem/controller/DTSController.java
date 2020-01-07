package com.testSystem.controller;

import com.testSystem.entity.DTS;
import com.testSystem.entity.DTSParam;
import com.testSystem.service.DTSService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class DTSController {
    @Autowired
    private DTSService dtsService;


    @RequestMapping("/selDTSByProId")
    @ResponseBody
    public List<DTS> selDTSByProId(@RequestBody DTSParam dtsParam)
    {
        return dtsService.selDTSByProId(dtsParam);
    }


    @RequestMapping("/selDTSByProIdWithoutType")
    @ResponseBody
    public List<DTS> selDTSByProIdWithoutType(@RequestBody DTSParam dtsParam)
    {
        return dtsService.selDTSByProIdWithoutType(dtsParam);
    }



    @RequestMapping("/countDTSNumByProId")
    @ResponseBody
    public int countDTSNumByProId(@RequestBody DTSParam dtsParam)
    {
        return dtsService.countDTSNumByProId(dtsParam);
    }

    @RequestMapping("/addDTS")
    @ResponseBody
    public String addDTS(@RequestBody DTS dts)
    {
        return dtsService.addDTS(dts);
    }

    @RequestMapping("/delDTS")
    @ResponseBody
    public String delDTS( @RequestParam("id")int id){
        String result = dtsService.delDTS(id);
        return result;
    }

    @RequestMapping("/editDTS")
    @ResponseBody
    public String editDTS(@RequestBody DTS dts)
    {
        return dtsService.editDTS(dts);
    }

}
