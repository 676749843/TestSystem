package com.testSystem.entity;


import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "t_script")
public class Script {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @GenericGenerator(name = "persistenceGenerator", strategy = "increment")
    @Column(name = "id", nullable = false, unique = true)
    private int id;
    @Column(name = "project_id", nullable = false, unique = true)
    private int projectId;
    @Column(name = "testcase_id", nullable = false, unique = true)
    private int testCaseId;

    @Column(name = "testcase_type", nullable = false, unique = true)
    private int testcaseType;


    @Column(name = "script_name", nullable = false, unique = true)
    private String scriptName;
    @Column(name = "script_desc", nullable = false, unique = true)
    private String scriptDesc;

    @Column(name = "script_content", nullable = false, unique = true)
    private String scriptContent;

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

    public int getTestCaseId() {
        return testCaseId;
    }

    public void setTestCaseId(int testCaseId) {
        this.testCaseId = testCaseId;
    }

    public int getTestcaseType() {
        return testcaseType;
    }

    public void setTestcaseType(int testcaseType) {
        this.testcaseType = testcaseType;
    }

    public String getScriptName() {
        return scriptName;
    }

    public void setScriptName(String scriptName) {
        this.scriptName = scriptName;
    }

    public String getScriptContent() {
        return scriptContent;
    }

    public void setScriptContent(String scriptContent) {
        this.scriptContent = scriptContent;
    }

    public String getScriptDesc() {
        return scriptDesc;
    }

    public void setScriptDesc(String scriptDesc) {
        this.scriptDesc = scriptDesc;
    }
}
