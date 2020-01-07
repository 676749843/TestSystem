package com.testSystem.dao;

import com.testSystem.entity.Project;
import com.testSystem.entity.TestCaseInterface;
import com.testSystem.entity.TestCaseView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TestCaseInterfaceDao extends JpaRepository<TestCaseInterface,Integer>,JpaSpecificationExecutor<TestCaseInterface> {
    @Query(value = "select count(*) from t_testcase_interface  where 1=1 and IF (:projectId != -1, project_id =:projectId, 1=1) and IF (:testcaseState != -1, testcase_state =:testcaseState, 1=1)" +
            " and IF (:interfaceName != '', interface_name LIKE CONCAT('%',:interfaceName,'%'), 1=1) "
            ,nativeQuery = true)
    public int selTestCaseNameByState(@Param("projectId") int projectId,
                                      @Param("interfaceName") String interfaceName,
                                      @Param("testcaseState") int testcaseState);




    @Query(value = "select * from t_testcase_interface where project_id=:projectId  and IF (:interfaceName != '', interface_name LIKE CONCAT('%',:interfaceName,'%'), 1=1)"+
            "LIMIT :startIndex,:showLength",nativeQuery = true)
    List<TestCaseInterface> selTestCaseByProId(@Param("projectId") int projectId,
                                                   @Param("interfaceName") String interfaceName,
                                              @Param("startIndex") int startIndex,
                                              @Param("showLength") int showLength);
}
