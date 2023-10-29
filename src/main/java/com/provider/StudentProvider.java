package com.provider;

import com.vo.Student;
import org.apache.ibatis.jdbc.SQL;

public class StudentProvider {
    public String updateStudent(Student student){
        return new SQL(){
            {
                UPDATE("student");
                if(student.getId()!=null){
                    SET("id=#{id}");
                }
                if(student.getTid()!=null){
                    SET("tid=#{tid}");
                }
                if(student.getName()!=null){
                    SET("name=#{name}");
                }
                WHERE("id=#{id}");
            }
        }.toString();
    }

    public String selectStudent(Student student){
        return new SQL(){
            {
                SELECT("*");
                FROM("student");
                if(student.getId()!=null){
                    WHERE("id=#{id}");
                }
                if(student.getTid()!=null){
                    OR();
                    WHERE("tid=#{tid}");
                }
                if(student.getName()!=null){
                    OR();
                    WHERE("name=#{name}");
                }
            }
        }.toString();
    }

    public String selectStudent1(Student student){
        return new SQL(){
            {
                SELECT("s.id id");
                SELECT("tid");
                SELECT("s.name name");
                SELECT("t.id 'teacher.id'");
                SELECT("t.name 'teacher.name'");
                FROM("student s");
                LEFT_OUTER_JOIN("teacher t ON s.tid=t.id");
                if(student.getId()!=null){
                    WHERE("s.id=#{id}");
                }
                if(student.getTid()!=null){
                    OR();
                    WHERE("tid=#{tid}");
                }
                if(student.getName()!=null){
                    OR();
                    WHERE("s.name=#{name}");
                }

            }
        }.toString();
    }

}
