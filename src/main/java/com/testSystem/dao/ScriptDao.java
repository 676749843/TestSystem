package com.testSystem.dao;

import com.testSystem.entity.Project;
import com.testSystem.entity.Script;
import com.testSystem.entity.TestCaseInterface;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ScriptDao extends JpaRepository<Script,Integer>,JpaSpecificationExecutor<Project> {
    @Query(value = "select * from t_script where project_id=:projectId",nativeQuery = true)
    List<Script> selByScriptProId(@Param("projectId") int projectId);




}
