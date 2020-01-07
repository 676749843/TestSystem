package com.testSystem.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "t_project")
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @GenericGenerator(name = "persistenceGenerator", strategy = "increment")
    @Column(name = "id", nullable = false, unique = true)
    private int id;

    @Column(name = "project_name", nullable = false, unique = true)
    private String projectName;

    @Column(name = "project_desc", nullable = false, unique = true)
    private String projectDesc;

    @Column(name = "project_state", nullable = false, unique = true)
    private int projectState;

    @Column(name = "project_creator", nullable = false, unique = true)
    private String projectCreator;


    @Transient
    private int testCaseNum;
    @Transient
    private int testCaseFinishNum;
    @Transient
    private int dtsNum;
    @Transient
    private int dtsFinishNum;
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getProjectDesc() {
        return projectDesc;
    }

    public void setProjectDesc(String projectDesc) {
        this.projectDesc = projectDesc;
    }

    public int getProjectState() {
        return projectState;
    }

    public void setProjectState(int projectState) {
        this.projectState = projectState;
    }

    public String getProjectCreator() {
        return projectCreator;
    }

    public void setProjectCreator(String projectCreator) {
        this.projectCreator = projectCreator;
    }

    public int getTestCaseNum() {
        return testCaseNum;
    }

    public void setTestCaseNum(int testCaseNum) {
        this.testCaseNum = testCaseNum;
    }

    public int getTestCaseFinishNum() {
        return testCaseFinishNum;
    }

    public void setTestCaseFinishNum(int testCaseFinishNum) {
        this.testCaseFinishNum = testCaseFinishNum;
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
}
