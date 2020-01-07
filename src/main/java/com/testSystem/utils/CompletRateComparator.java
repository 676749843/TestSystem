package com.testSystem.utils;

import com.testSystem.entity.Project;

import java.util.Comparator;

public class CompletRateComparator implements Comparator<Project> {
    @Override
    public int compare(Project o1, Project o2) {
        int allNum1 = o1.getTestCaseNum();
        int finishNum1 = o1.getTestCaseFinishNum();
        float rate1=0;
        if(allNum1!=0)
        {
            rate1 = finishNum1/allNum1;
        }


        int allNum2 = o2.getTestCaseNum();
        int finishNum2 = o2.getTestCaseFinishNum();
        float rate2=0;
        if(allNum2!=0)
        {
            rate2 = finishNum2/allNum2;
        }
        if(rate1>=rate2)
        {
            return -1;
        }else
            {
                return 1;
            }

    }
}
