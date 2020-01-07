package com.testSystem.service;

import com.testSystem.dao.DTSDao;
import com.testSystem.dao.ProjectDao;
import com.testSystem.dao.TestCaseViewDao;
import com.testSystem.entity.DTSParam;
import com.testSystem.entity.ReportTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ReportService {
        @Autowired
        private ProjectDao projectDao;
    @Autowired
    private TestCaseViewDao testCaseViewDao;
    @Autowired
    private DTSDao dtsDao;
    public  List<ReportTable> getReportTableInfo(DTSParam dtsParam) {
        int projectId = dtsParam.getProjectId();
        int startIndex = dtsParam.getStartIndex();
        int showLength = dtsParam.getShowLength();
        List<Map<String,Object>> list=projectDao.selectProjectDetail(projectId,startIndex,showLength);
        List<ReportTable> resultList = new ArrayList<>();
        for(int i=0;i<list.size();i++)
        {
            Map<String,Object> temp = list.get(i);
            ReportTable reportTable = new ReportTable();
            for(String key : temp.keySet()){
                if("testcaseName".equals(key))
                {
                    String testcaseName = String.valueOf(temp.get(key));
                    reportTable.setTestcaseName(testcaseName);
                }else if("testcaseState".equals(key))
                    {
                        int testcaseState = Integer.valueOf(String.valueOf(temp.get(key)));
                        reportTable.setTestcaseState(testcaseState);
                    }else if("testcaseType".equals(key))
                    {
                        int testcaseType = Integer.valueOf(String.valueOf(temp.get(key)));
                        reportTable.setTestcaseType(testcaseType);
                    }
                else if("id".equals(key))
                {
                    int id = Integer.valueOf(String.valueOf(temp.get(key)));
                    reportTable.setId(id);
                }
            }
            resultList.add(reportTable);
        }
        for(int i=0;i<resultList.size();i++)
        {
            int testcaseId = resultList.get(i).getId();
            int testType =resultList.get(i).getTestcaseType();
            int all =  dtsDao.countDTSNumByProId(projectId,testcaseId,testType);
            resultList.get(i).setDtsNum(all);
            int finish =  dtsDao.countDTSNumByProId(projectId,testcaseId,testType,2);
            resultList.get(i).setDtsFinishNum(finish);
        }

        return resultList;
    }


    public int countProjectDetail(DTSParam dtsParam) {
        int projectId = dtsParam.getProjectId();
        int num =projectDao.countProjectDetail(projectId);
        return num;
    }

    public Map<Integer,Integer> getTestCaseTypeInfo(DTSParam dtsParam) {
        Map<Integer,Integer> map = new HashMap<>();
        int projectId = dtsParam.getProjectId();
        for(int i=0;i<=3;i++)
        {
            int num = testCaseViewDao.countByProIdAndTestCaseType(projectId,i);
            map.put(i,num);
        }
        return map;
    }
}
