package com.testSystem.entity;

public class DTSParam {
    private int projectId;
    private int startIndex;
    private int showLength;
    private String dtsName;
    private int testCaseId;
    private int testCaseType;
    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public int getStartIndex() {
        return startIndex;
    }

    public void setStartIndex(int startIndex) {
        this.startIndex = startIndex;
    }

    public int getShowLength() {
        return showLength;
    }

    public void setShowLength(int showLength) {
        this.showLength = showLength;
    }

    public int getTestCaseId() {
        return testCaseId;
    }

    public void setTestCaseId(int testCaseId) {
        this.testCaseId = testCaseId;
    }

    public int getTestCaseType() {
        return testCaseType;
    }

    public void setTestCaseType(int testCaseType) {
        this.testCaseType = testCaseType;
    }

    public String getDtsName() {
        return dtsName;
    }

    public void setDtsName(String dtsName) {
        this.dtsName = dtsName;
    }
}
