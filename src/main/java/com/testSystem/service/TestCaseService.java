package com.testSystem.service;

import com.testSystem.dao.DTSDao;
import com.testSystem.dao.TestCaseInterfaceDao;
import com.testSystem.dao.TestCaseViewDao;
import com.testSystem.entity.TestCaseInterface;
import com.testSystem.entity.TestCaseView;
import org.apache.poi.ss.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Service
public class TestCaseService {
    @Autowired
    public TestCaseViewDao testCaseViewDao;
    @Autowired
    public DTSDao dtsDao;
    @Autowired
    public TestCaseInterfaceDao testCaseInterfaceDao;
    public List<TestCaseView> selTestCaseView(TestCaseView testCaseView) {
        int projectId = testCaseView.getProjectId();
        int startIndex = testCaseView.getStartIndex();
        int showLength = testCaseView.getShowLength();
        String testcaseName = testCaseView.getTestcaseName();
        List<TestCaseView> list = testCaseViewDao.selTestCaseViewByProId(projectId,testcaseName,startIndex,showLength);
        return list;
    }

    public int countTestCaseView(TestCaseView testCaseView) {
        int projectId = testCaseView.getProjectId();
        int num = testCaseViewDao.selTestCaseNameByState(projectId,-1);
        return num;
    }

    public String addNewTestCaseView(TestCaseView testCaseView) {
        testCaseView.setTestcaseState("0");
        testCaseView =   testCaseViewDao.save(testCaseView);
        if(testCaseView!=null)
        {
            return "success";
        }
        return "error";
    }

    public String uploadTestCaseViewByExcel(String projectIdS, MultipartFile file) {

        int projectId = Integer.valueOf(projectIdS);
        try {
            InputStream inputStream = file.getInputStream();
            Workbook workbook  = WorkbookFactory.create(inputStream);
            int sheetNum = workbook.getNumberOfSheets();
            for(int i=0;i<sheetNum;i++)
            {
                Sheet sheet = workbook.getSheetAt(i);
                int rowNum=sheet.getPhysicalNumberOfRows();
                for(int j=1;j<rowNum;j++)
                {
                    Row row = sheet.getRow(j);
                    //获取name
                    Cell nameCell =  row.getCell(0);
                    nameCell.setCellType(Cell.CELL_TYPE_STRING);
                    if(nameCell==null)
                    {
                        continue;
                    }
                    String testcaseName = nameCell.getStringCellValue();
                    if(StringUtils.isEmpty(testcaseName))
                    {
                        continue;
                    }


                    Cell testcaseDescCell =  row.getCell(1);
                    testcaseDescCell.setCellType(Cell.CELL_TYPE_STRING);
                    if(testcaseDescCell==null)
                    {
                        continue;
                    }
                    String testcaseDesc = testcaseDescCell.getStringCellValue();
                    if(StringUtils.isEmpty(testcaseDesc))
                    {
                        continue;
                    }



                    Cell testcaseExceptCell =  row.getCell(2);
                    testcaseExceptCell.setCellType(Cell.CELL_TYPE_STRING);
                    if(nameCell==null)
                    {
                        continue;
                    }
                    String testcaseExcept = testcaseExceptCell.getStringCellValue();
                    if(StringUtils.isEmpty(testcaseExcept))
                    {
                        continue;
                    }

                    Cell testcaseRemarkCell =  row.getCell(3);
                    testcaseRemarkCell.setCellType(Cell.CELL_TYPE_STRING);
                    if(testcaseRemarkCell==null)
                    {
                        continue;
                    }
                    String testcaseRemark = testcaseRemarkCell.getStringCellValue();
                    if(StringUtils.isEmpty(testcaseRemark))
                    {
                        continue;
                    }

                    TestCaseView testCaseView = new TestCaseView();
                    testCaseView.setProjectId(projectId);
                    testCaseView.setTestcaseName(testcaseName);
                    testCaseView.setTestcaseDesc(testcaseDesc);
                    testCaseView.setTestcaseExcept(testcaseExcept);
                    testCaseView.setTestcaseRemark(testcaseRemark);
                    testCaseViewDao.save(testCaseView);

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    public String editDostate(TestCaseView testCaseView) {
        testCaseView =   testCaseViewDao.save(testCaseView);
        if(testCaseView!=null)
        {
            return "success";
        }
        return "error";
    }

    public String delTestcaseView(int id) {
        testCaseViewDao.deleteById(id);
        dtsDao.delByTestCaseIdAndType(id,0);
        return "success";
    }

    public List<TestCaseInterface> selTestCaseInterface(TestCaseInterface testCaseInterface) {
        int projectId = testCaseInterface.getProjectId();
        int startIndex = testCaseInterface.getStartIndex();
        int showLength = testCaseInterface.getShowLength();
        String interfaceName = testCaseInterface.getInterfacName();
        List<TestCaseInterface> list = testCaseInterfaceDao.selTestCaseByProId(projectId,interfaceName,startIndex,showLength);
        return list;
    }

    public int countTestCaseInterface(TestCaseInterface testCaseInterface) {
        int projectId = testCaseInterface.getProjectId();
        String interfaceName = testCaseInterface.getInterfacName();
        int num = testCaseInterfaceDao.selTestCaseNameByState(projectId,interfaceName,-1);
        return num;
    }

    public String addNewTestCaseInterface(TestCaseInterface testCaseInterface) {
        testCaseInterface =   testCaseInterfaceDao.save(testCaseInterface);
        if(testCaseInterface!=null)
        {
            return "success";
        }
        return "error";
    }

    public String editDostateInterface(TestCaseInterface testCaseInterface) {
        testCaseInterface =   testCaseInterfaceDao.save(testCaseInterface);
        if(testCaseInterface!=null)
        {
            return "success";
        }
        return "error";
    }

    public String delTestcaseViewInterface(int id) {

        testCaseInterfaceDao.deleteById(id);
        dtsDao.delByTestCaseIdAndType(id,1);
        return "success";
    }
}
