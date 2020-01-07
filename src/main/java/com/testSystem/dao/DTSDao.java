package com.testSystem.dao;

import com.testSystem.entity.DTS;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface DTSDao extends JpaRepository<DTS,Integer>,JpaSpecificationExecutor<DTS> {
    @Query(value = "select count(*) from t_dts  where project_id=:projectId and IF (:dtsState != -1, dts_state =:dtsState, 1=1)"
            ,nativeQuery = true)
    public int selDTSByState(@Param("projectId") int projectId, @Param("dtsState") int dtsState);



    @Query(value = "select * from t_dts where project_id=:projectId and testcase_id=:testCaseId and testcase_type=:testCaseType"+
            " LIMIT :startIndex,:showLength",nativeQuery = true)
    List<DTS> selDTSByProId( @Param("projectId") int projectId,
                             @Param("testCaseId") int id,
                             @Param("testCaseType") int type,

                            @Param("startIndex") int startIndex,
                            @Param("showLength") int showLength);

    @Query(value = "select count(*) from t_dts  where project_id=:projectId  and testcase_id=:testCaseId and testcase_type=:testCaseType"
            ,nativeQuery = true)
    int countDTSNumByProId(@Param("projectId") int projectId,
                           @Param("testCaseId") int id,
                           @Param("testCaseType") int type);

    @Query(value = "select count(*) from t_dts  where project_id=:projectId  and testcase_id=:testCaseId and testcase_type=:testCaseType and dts_state=:dtsState"
            ,nativeQuery = true)
    int countDTSNumByProId(@Param("projectId") int projectId,
                           @Param("testCaseId") int id,
                           @Param("testCaseType") int type,
                           @Param("dtsState") int dtsState
                           );



    @Query(value = "select * from t_dts where project_id=:projectId "+
            "  AND IF (:dtsName != '', dts_titile LIKE CONCAT('%',:dtsName,'%'), 1=1) " +
            " LIMIT :startIndex,:showLength",nativeQuery = true)
    List<DTS> selDTSByProIdWithoutType( @Param("projectId") int projectId,@Param("dtsName") String dtsName,@Param("startIndex") int startIndex,@Param("showLength") int showLength);

    @Modifying
    @Transactional
    @Query(value = "delete from t_dts where testcase_id=:testCaseId and testcase_type=:testcaseType",nativeQuery = true)
    void delByTestCaseIdAndType(@Param("testCaseId") int testCaseId, @Param("testcaseType")int testType);
}
