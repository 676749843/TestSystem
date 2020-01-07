package com.testSystem.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "t_testcase_interface")
public class TestCaseInterface {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @GenericGenerator(name = "persistenceGenerator", strategy = "increment")
    @Column(name = "id", nullable = false, unique = true)
    private int id;
    @Column(name = "project_id", nullable = false, unique = true)
    private int projectId;
    @Column(name = "interface_name", nullable = false, unique = true)
    private String interfacName;
    @Column(name = "interface_param", nullable = false, unique = true)
    private String interfaceParam;
    @Column(name = "testcase_except", nullable = false, unique = true)
    private String testcaseExcept;
    @Column(name = "testcase_result", nullable = false, unique = true)
    private String testcaseResult;
    @Column(name = "testcase_comments", nullable = false, unique = true)
    private String testcaseComments;
    @Column(name = "testcase_state", nullable = false, unique = true)
    private int testcaseState;

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

    public String getInterfacName() {
        return interfacName;
    }

    public void setInterfacName(String interfacName) {
        this.interfacName = interfacName;
    }

    public String getInterfaceParam() {
        return interfaceParam;
    }

    public void setInterfaceParam(String interfaceParam) {
        this.interfaceParam = interfaceParam;
    }

    public String getTestcaseExcept() {
        return testcaseExcept;
    }

    public void setTestcaseExcept(String testcaseExcept) {
        this.testcaseExcept = testcaseExcept;
    }

    public String getTestcaseResult() {
        return testcaseResult;
    }

    public void setTestcaseResult(String testcaseResult) {
        this.testcaseResult = testcaseResult;
    }

    public String getTestcaseComments() {
        return testcaseComments;
    }

    public void setTestcaseComments(String testcaseComments) {
        this.testcaseComments = testcaseComments;
    }

    public int getTestcaseState() {
        return testcaseState;
    }

    public void setTestcaseState(int testcaseState) {
        this.testcaseState = testcaseState;
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
