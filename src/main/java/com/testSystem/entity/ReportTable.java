package com.testSystem.entity;

public class ReportTable {
    private int id;
    private int projectId;
    private String testcaseName;
    private int testcaseType;
    private int dtsNum;
    private int dtsFinishNum;
    private int testcaseState;

    public String getTestcaseName() {
        return testcaseName;
    }

    public void setTestcaseName(String testcaseName) {
        this.testcaseName = testcaseName;
    }

    public int getTestcaseType() {
        return testcaseType;
    }

    public void setTestcaseType(int testcaseType) {
        this.testcaseType = testcaseType;
    }

    public int getDtsNum() {
        return dtsNum;
    }

    public void setDtsNum(int dtsNum) {
        this.dtsNum = dtsNum;
    }

    public int getDtsFinishNum() {
        return dtsFinishNum;
    }

    public void setDtsFinishNum(int dtsFinishNum) {
        this.dtsFinishNum = dtsFinishNum;
    }

    public int getTestcaseState() {
        return testcaseState;
    }

    public void setTestcaseState(int testcaseState) {
        this.testcaseState = testcaseState;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }
}
