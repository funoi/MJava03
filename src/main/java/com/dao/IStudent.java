package com.dao;

import com.provider.StudentProvider;
import com.vo.Student;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface IStudent {
    public List<Student> selectByTid(int tid);


    // 查询全部学生
    @Select("select * from student")
    @Results(id = "myStu",value = {
            @Result(column = "id" ,property = "id",id = true),
            @Result(column = "tid",property = "tid"),
            @Result(column = "name",property = "name")
    })
    public List<Student> selectAllStu();


    // 多表查询，多对一
    @Select("select s.id id,tid,s.name name,t.id 'teacher.id',t.name 'teacher.name' from student s left join teacher t on s.tid=t.id")
    public List<Student> selectAllStu1();


    // 通过id查询
    @Select("select * from student where id=#{id}")
    public Student selectStuById(@Param("id") Integer id);


    // 添加学生
    @Insert("insert into student values (#{id},#{tid},#{name})")
    //@SelectKey(before = false,keyColumn = "id",keyProperty = "id",statement = "select last_insert_id()",resultType = Integer.class)
    public void insertStu(Student student);

    // 删除学生
    @Delete("delete from student where id=#{id}")
    public void deleteStuById(@Param("id") Integer id);


    // 动态sql，通过id更新学生
    @UpdateProvider(type = StudentProvider.class,method = "updateStudent")
    public void updateStuById(Student student);


    // 动态sql，通过id或tid或name查询学生
    @SelectProvider(type = StudentProvider.class,method = "selectStudent")
    public List<Student> selectStu(Student student);

    // 动态sql，多表查询，通过id或tid或name查询学生，包括老师
    @SelectProvider(type = StudentProvider.class,method = "selectStudent1")
    public List<Student> selectStu1(Student student);
}
