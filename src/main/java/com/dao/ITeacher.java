package com.dao;

import com.vo.Student;
import com.vo.Teacher;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface ITeacher {
    public List<Teacher> selectAll();  //N+1查询


    // 多表查询，一对多
    @Select("select t.id tid,t.name tname, s.id sid, s.name sname , tid stid from teacher t left join student s on s.tid=t.id")
//    @ResultMap(value = "Tea")
    @Results(value = {
            @Result(column = "tid",property = "id",id = true),
            @Result(column = "tname",property = "name"),
            @Result(column = "student",property = "students",many = @Many(resultMap = "com.dao.IStudent.myStu",columnPrefix = "s"))
    })
    public List<Teacher> selectAllTea1();

}
