package com.testSystem.dao;

import com.testSystem.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;

public interface ProjectDao extends JpaRepository<Project,Integer>,JpaSpecificationExecutor<Project> {
   @Query(value = "select * from t_project where 1=1  and IF (:projectName != '', project_name LIKE CONCAT('%',:projectName,'%'), 1=1)"+
           "LIMIT :startIndex,:showLength",nativeQuery = true)
    List<Project> selProjectAll(@Param("projectName") String projectName,
                                @Param("startIndex") int startIndex,
                                @Param("showLength") int showLength);

    @Query(value = "select count(*) from t_project where 1=1  and IF (:projectName != '', project_name LIKE CONCAT('%',:projectName,'%'), 1=1)"
            ,nativeQuery = true)
   int countProjectAll(@Param("projectName") String projectName);



    @Query(value = "SELECT * from ( " +
            "select t1.testcase_name testcaseName, " +
            " t1.testcase_state testcaseState, " +
            " '0' testcaseType " +
            " from t_testcase_view t1 where t1.project_id=:projectId " +
            " UNION " +
            " select t2.interface_name testcaseName, " +
            " t2.testcase_state testcaseState, " +
            "  '1' testcaseType " +
            "from t_testcase_interface t2 where t2.project_id=:projectId " +
            ") t  LIMIT :startIndex,:showLength",nativeQuery = true)
    List<Map<String,Object>> selectProjectDetail(@Param("projectId") int projectId,@Param("startIndex") int startIndex,@Param("showLength") int showLength);

    @Query(value = " SELECT t1.project_name projectName,IFNULL(t2.num,0) num  from t_project t1 LEFT JOIN " +
            " ( " +
            " SELECT " +
            " COUNT( * ) num," +
            " t.project_id " +
            " FROM " +
            " t_dts t " +
            " GROUP BY " +
            "  t.project_id  " +
            " ) t2 " +
            " ON t2.project_id = t1.id  " +
            "  ORDER BY " +
            "        num  LIMIT 0," +
            "        5",nativeQuery = true)
    List<Map<String,Object>> dtsNumTop5();


 @Query(value = "  SELECT " +
         "    count(*) num, " +
         "    t.testcase_type  testcaseType " +
         "    FROM " +
         "    t_script t " +
         "    GROUP BY testcaseType ",nativeQuery = true)
 List<Map<String,Object>> scriptType();


    @Query(value = "SELECT COUNT(*) from ( " +
            "select t1.id tecaseId,t1.testcase_name testcaseName, " +
            " t1.testcase_state testcaseState, " +
            " '0' testcaseType " +
            " from t_testcase_view t1 where t1.project_id=:projectId " +
            " UNION " +
            " select t2.id tecaseId,t2.interface_name testcaseName, " +
            " t2.testcase_state testcaseState, " +
            "  '1' testcaseType " +
            "from t_testcase_interface t2 where t2.project_id=:projectId " +
            ") t  ",nativeQuery = true)
    int countProjectDetail(@Param("projectId") int projectId);

    @Query(value = "SELECT COUNT(*) from t_project where 1=1  and IF (:projectState != -1, project_state=:projectState, 1=1)",nativeQuery = true)
    int countProjectByState(@Param("projectState") int projectState);


}
