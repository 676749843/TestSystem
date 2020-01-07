package com.testSystem.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "t_dts")
public class DTS {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @GenericGenerator(name = "persistenceGenerator", strategy = "increment")
    @Column(name = "id", nullable = false, unique = true)
     private int id;

    @Column(name = "project_id", nullable = false, unique = true)
     private int projectId;
    @Column(name = "dts_titile", nullable = false, unique = true)
     private String dtsTitile;
    @Column(name = "dts_desc", nullable = false, unique = true)
     private String dtsDesc;
    @Column(name = "dts_level", nullable = false, unique = true)
     private String dtsLevel;
    @Column(name = "dts_creator", nullable = false, unique = true)
     private String dtsCreator;
    @Column(name = "dts_solver", nullable = false, unique = true)
     private String dtsSolver;
    @Column(name = "dts_state", nullable = false, unique = true)
     private int dtsState;
    @Column(name = "testcase_id", nullable = false, unique = true)
    private int testcaseId;
    @Column(name = "testcase_type", nullable = false, unique = true)
    private int testcaseType;
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

    public String getDtsTitile() {
        return dtsTitile;
    }

    public void setDtsTitile(String dtsTitile) {
        this.dtsTitile = dtsTitile;
    }

    public String getDtsDesc() {
        return dtsDesc;
    }

    public void setDtsDesc(String dtsDesc) {
        this.dtsDesc = dtsDesc;
    }

    public String getDtsLevel() {
        return dtsLevel;
    }

    public void setDtsLevel(String dtsLevel) {
        this.dtsLevel = dtsLevel;
    }

    public String getDtsCreator() {
        return dtsCreator;
    }

    public void setDtsCreator(String dtsCreator) {
        this.dtsCreator = dtsCreator;
    }

    public String getDtsSolver() {
        return dtsSolver;
    }

    public void setDtsSolver(String dtsSolver) {
        this.dtsSolver = dtsSolver;
    }

    public int getDtsState() {
        return dtsState;
    }

    public void setDtsState(int dtsState) {
        this.dtsState = dtsState;
    }

    public int getTestcaseId() {
        return testcaseId;
    }

    public void setTestcaseId(int testcaseId) {
        this.testcaseId = testcaseId;
    }

    public int getTestcaseType() {
        return testcaseType;
    }

    public void setTestcaseType(int testcaseType) {
        this.testcaseType = testcaseType;
    }
}
