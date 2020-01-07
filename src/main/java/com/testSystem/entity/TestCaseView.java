package com.testSystem.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "t_testcase_view")
public class TestCaseView {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @GenericGenerator(name = "persistenceGenerator", strategy = "increment")
    @Column(name = "id", nullable = false, unique = true)
    private int id;
    @Column(name = "project_id", nullable = false, unique = true)
    private int projectId;
    @Column(name = "testcase_name", nullable = false, unique = true)
    private String testcaseName;
    @Column(name = "testcase_desc", nullable = false, unique = true)
    private String testcaseDesc;
    @Column(name = "testcase_script", nullable = false, unique = true)
    private String testcaseScript;
    @Column(name = "testcase_except", nullable = false, unique = true)
    private String testcaseExcept;
    @Column(name = "testcase_dostate", nullable = false, unique = true)
    private String testcaseDostate;
    @Column(name = "testcase_state", nullable = false, unique = true)
    private String testcaseState;
    @Column(name = "testcase_remark", nullable = false, unique = true)
    private String testcaseRemark;

    @Transient
    private int startIndex;
    @Transient
    private int showLength;
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

    public String getTestcaseName() {
        return testcaseName;
    }

    public void setTestcaseName(String testcaseName) {
        this.testcaseName = testcaseName;
    }

    public String getTestcaseDesc() {
        return testcaseDesc;
    }

    public void setTestcaseDesc(String testcaseDesc) {
        this.testcaseDesc = testcaseDesc;
    }

    public String getTestcaseScript() {
        return testcaseScript;
    }

    public void setTestcaseScript(String testcaseScript) {
        this.testcaseScript = testcaseScript;
    }

    public String getTestcaseExcept() {
        return testcaseExcept;
    }

    public void setTestcaseExcept(String testcaseExcept) {
        this.testcaseExcept = testcaseExcept;
    }

    public String getTestcaseDostate() {
        return testcaseDostate;
    }

    public void setTestcaseDostate(String testcaseDostate) {
        this.testcaseDostate = testcaseDostate;
    }

    public String getTestcaseState() {
        return testcaseState;
    }

    public void setTestcaseState(String testcaseState) {
        this.testcaseState = testcaseState;
    }

    public String getTestcaseRemark() {
        return testcaseRemark;
    }

    public void setTestcaseRemark(String testcaseRemark) {
        this.testcaseRemark = testcaseRemark;
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
}
