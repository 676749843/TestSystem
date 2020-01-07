package com.testSystem.service;

import com.testSystem.dao.DTSDao;
import com.testSystem.entity.DTS;
import com.testSystem.entity.DTSParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DTSService {
    @Autowired
    private DTSDao dtsDao;

    public List<DTS> selDTSByProId(DTSParam dtsParam) {
        int projectId = dtsParam.getProjectId();
        int startIndex = dtsParam.getStartIndex();
        int showLength = dtsParam.getShowLength();
        int type=dtsParam.getTestCaseType();
        int caseId= dtsParam.getTestCaseId();
        List<DTS> result = dtsDao.selDTSByProId(projectId,caseId,type,startIndex,showLength);
        return result;
    }

    public int countDTSNumByProId(DTSParam dtsParam) {
        int projectId = dtsParam.getProjectId();
        int type=dtsParam.getTestCaseType();
        int caseId= dtsParam.getTestCaseId();
        int num = dtsDao.countDTSNumByProId(projectId,caseId,type);

        return num;
    }

    public String addDTS(DTS dts) {
        dts = dtsDao.save(dts);
        if(dts!=null)
        {
            return "success";
        }
        return "error";
    }

    public String delDTS(int id) {
        dtsDao.deleteById(id);
        return "success";
    }

    public List<DTS> selDTSByProIdWithoutType(DTSParam dtsParam) {
        int projectId = dtsParam.getProjectId();
        int startIndex = dtsParam.getStartIndex();
        int showLength = dtsParam.getShowLength();
        String dtsName = dtsParam.getDtsName();
        List<DTS> result = dtsDao.selDTSByProIdWithoutType(projectId,dtsName,startIndex,showLength);
        return result;
    }

    public String editDTS(DTS dts) {
        DTS orginDts = dtsDao.findById(dts.getId()).orElse(null);
        dts.setProjectId(orginDts.getProjectId());
        dts = dtsDao.save(dts);
        if(dts!=null)
        {
            return "success";
        }
        return "error";
    }
}
