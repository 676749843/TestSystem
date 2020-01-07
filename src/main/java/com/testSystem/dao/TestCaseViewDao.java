package com.testSystem.dao;


import com.testSystem.entity.Project;
import com.testSystem.entity.TestCaseView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TestCaseViewDao extends JpaRepository<TestCaseView,Integer>,JpaSpecificationExecutor<TestCaseView> {
    @Query(value = "select count(*) from t_testcase_view  where project_id=:projectId and IF (:testcaseState != -1, testcase_state =:testcaseState, 1=1)"
            ,nativeQuery = true)
    public int selTestCaseNameByState(@Param("projectId") int projectId,@Param("testcaseState") int testcaseState);

    @Query(value = "select * from t_testcase_view where project_id=:projectId  "+
            " and IF (:testcaseName != '', testcase_name LIKE CONCAT('%',:testcaseName,'%'), 1=1) "+
            "LIMIT :startIndex,:showLength",nativeQuery = true)
    List<TestCaseView> selTestCaseViewByProId(@Param("projectId") int projectId,
                                              @Param("testcaseName")     String testcaseName,
                                @Param("startIndex") int startIndex,
                                @Param("showLength") int showLength);



    @Query(value = "SELECT COUNT(*) from ( " +
            "select t1.id tecaseId " +
            "from t_testcase_view t1 " +
            "where t1.project_id=:projectId " +
            "and t1.testcase_state=:testcaseState " +
            "UNION " +
            "select t2.id tecaseId " +
            "from t_testcase_interface t2 " +
            "where t2.project_id=:projectId " +
            "and t2.testcase_state=:testcaseState " +
            ") t",nativeQuery = true)
    public  int countByProIdAndTestCaseType(@Param("projectId") int projectId,@Param("testcaseState") int testcaseState);


}
