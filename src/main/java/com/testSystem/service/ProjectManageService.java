package com.testSystem.service;

import com.testSystem.dao.DTSDao;
import com.testSystem.dao.ProjectDao;
import com.testSystem.dao.TestCaseInterfaceDao;
import com.testSystem.dao.TestCaseViewDao;
import com.testSystem.entity.Project;
import com.testSystem.entity.ProjectParam;
import com.testSystem.entity.ReportTable;
import com.testSystem.utils.CompletRateComparator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ProjectManageService {

    @Autowired
    private ProjectDao projectDao;

    @Autowired
    private TestCaseInterfaceDao testCaseInterfaceDao;
    @Autowired
    private TestCaseViewDao testCaseViewDao;
    @Autowired
    private DTSDao dtsDao;
    @Autowired
    private UserService userService;


    public List<Project> selProjectAll(ProjectParam projectParam) {
        String projectName = projectParam.getProjectName();
        int startIndex = projectParam.getStartIndex();
        int showLength = projectParam.getShowLength();
        List<Project> list =projectDao.selProjectAll(projectName,startIndex,showLength);

        for(int i=0;i<list.size();i++)
        {
            Project project = list.get(i);
            int projectId = project.getId();
            //查询用例数
            int allTestCaseViewNum =testCaseViewDao.selTestCaseNameByState(projectId,-1);
            int finishedTestCaseViewNum = testCaseViewDao.selTestCaseNameByState(projectId,1);
            int allTestCaseInterfaceNum =testCaseInterfaceDao.selTestCaseNameByState(projectId,"",-1);
            int finishedTestCaseInterfaceNum = testCaseInterfaceDao.selTestCaseNameByState(projectId,"",1);
            list.get(i).setTestCaseNum(allTestCaseViewNum+allTestCaseInterfaceNum);
            list.get(i).setTestCaseFinishNum(finishedTestCaseViewNum+finishedTestCaseInterfaceNum);

            //查询缺陷数
            int dtsAllNum = dtsDao.selDTSByState(projectId,-1);
            list.get(i).setDtsNum(dtsAllNum);
            int finishedNum = dtsDao.selDTSByState(projectId,2);
            list.get(i).setDtsFinishNum(finishedNum);
        }





        return list;
    }

    public int countProjectAll(ProjectParam projectParam) {
        String projectName = projectParam.getProjectName();
        int num = projectDao.countProjectAll(projectName);
        return num;
    }

    public String addProjectInfo(Project project) {
        String userName = userService.getCurrentUser();
        project.setProjectCreator(userName);
        projectDao.save(project);
        return "success";
    }

    public List<Project> initProjectSelect() {
        List<Project> list =   projectDao.findAll();
        return list;
    }

    public List<Integer> projectViewNum() {

        List<Integer> result = new ArrayList<Integer>();
        int all = projectDao.countProjectByState(-1);
        result.add(all);
        int finish = projectDao.countProjectByState(2);
        result.add(finish);
        return result;
    }

    public Map<String,Integer> dtsNumTop5() {
        Map<String,Integer>  result = new HashMap<String,Integer>();

        List<Map<String,Object>> list = projectDao.dtsNumTop5();
        for(int i=0;i<list.size();i++)
        {
            Map<String,Object> temp = list.get(i);
            int num =-1;
            String projectName ="";
            for(String key : temp.keySet()){
                if("projectName".equals(key))
                {
                    projectName = String.valueOf(temp.get(key));

                }else if("num".equals(key))
                {
                    num = Integer.valueOf(String.valueOf(temp.get(key)));
                }
            }
            result.put(projectName,num);
        }
        return result;
    }

    public  List<Project> testcaseNumTop5() {
        List<Project> projectList = projectDao.findAll();

        for(int i=0;i<projectList.size();i++)
        {
            int proId = projectList.get(i).getId();
            int allInterfaceNum = testCaseInterfaceDao.selTestCaseNameByState(proId,"",-1);
            int allTestcaseNum =  testCaseViewDao.selTestCaseNameByState(proId,-1);
            int allNum =allInterfaceNum+allTestcaseNum;
            projectList.get(i).setTestCaseNum(allNum);
            int allInterfaceFinishNum = testCaseInterfaceDao.selTestCaseNameByState(proId,"",1);
            int allTestcaseFinishNum =  testCaseViewDao.selTestCaseNameByState(proId,1);
            int finishNum =allInterfaceFinishNum+allTestcaseFinishNum;
            projectList.get(i).setTestCaseFinishNum(finishNum);
        }
        Collections.sort(projectList, new CompletRateComparator());
        if(projectList.size()<=5)
        {
            return projectList;
        }
        return projectList.subList(0,5);
    }

    public Map<String,Integer> scriptType() {
        List<Map<String,Object>> list = projectDao.scriptType();
        Map<String,Integer>  result = new HashMap<String,Integer>();
        for(int i=0;i<list.size();i++)
        {
            Map<String,Object> temp = list.get(i);
            int num =-1;
            String testcaseType ="";
            for(String key : temp.keySet()){
                if("testcaseType".equals(key))
                {
                    testcaseType= String.valueOf(temp.get(key));

                }else if("num".equals(key))
                {
                    num = Integer.valueOf(String.valueOf(temp.get(key)));
                }
            }
            result.put(testcaseType,num);
        }
        return result;
    }

    public Map<String,Integer> testCaseType() {
        Map<String,Integer>  result = new HashMap<String,Integer>();


        int interfaceUnFinishNum = testCaseInterfaceDao.selTestCaseNameByState(-1,"",0);
        int testcaseUnFinishNum =  testCaseViewDao.selTestCaseNameByState(-1,0);
        result.put("未执行",interfaceUnFinishNum+testcaseUnFinishNum);

        int interfaceSuccessNum = testCaseInterfaceDao.selTestCaseNameByState(-1,"",1);
        int testcaseSuccessNum =  testCaseViewDao.selTestCaseNameByState(-1,1);
        result.put("执行成功",interfaceSuccessNum+testcaseSuccessNum);

        int interfaceErroFinishNum = testCaseInterfaceDao.selTestCaseNameByState(-1,"",2);
        int testcaseErroFinishNum =  testCaseViewDao.selTestCaseNameByState(-1,2);
        result.put("执行失败",interfaceErroFinishNum+testcaseErroFinishNum);


        int interfaceNum = testCaseInterfaceDao.selTestCaseNameByState(-1,"",3);
        int testcasehNum =  testCaseViewDao.selTestCaseNameByState(-1,3);
        result.put("无效用例",interfaceNum+testcasehNum);
        return result;
    }
}
