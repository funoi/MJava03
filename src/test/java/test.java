import com.dao.IStudent;
import com.dao.ITeacher;
import com.vo.Student;
import com.vo.Teacher;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;
import java.util.List;

public class test {
    private  SqlSession session=null;
    private ITeacher ITeacherDAO;
    private IStudent IStudentDAO;
    private List<Teacher> list1;
    private List<Student> list2;

    private Teacher teacher;
    private Student student;

    @Before
    public  void init(){
        InputStream is;
        try {
            is= Resources.getResourceAsStream("mybatis-config.xml");
            SqlSessionFactory build = new SqlSessionFactoryBuilder().build(is);
            session = build.openSession();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @After
    public void destroy(){
        if(session!=null){
            session.close();
        }
    }

    @Test
    public void testSelectAllTeacher(){
        if(session!=null){
            ITeacherDAO = session.getMapper(ITeacher.class);
            list1 = ITeacherDAO.selectAll();
            for (Teacher t : list1) {
                System.out.println(t);
            }
        }

    }

    @Test
    public void testSelectAllStu(){
        if(session!=null){
            IStudentDAO=session.getMapper(IStudent.class);
            list2=IStudentDAO.selectAllStu();
            for (Student s : list2) {
                System.out.println(s);
            }
        }
    }

    @Test
    public void testSelectStuById(){
        if(session!=null){
            IStudentDAO=session.getMapper(IStudent.class);
            student=IStudentDAO.selectStuById(3);
            System.out.println(student);
        }
    }

    @Test
    public void testInsertStu(){
        if(session!=null){
            IStudentDAO=session.getMapper(IStudent.class);
            IStudentDAO.insertStu(new Student(9,4,"孤心沙龙",null));
        }
    }

    @Test
    public void testDeleteStuById(){
        if(session!=null){
            IStudentDAO=session.getMapper(IStudent.class);
            IStudentDAO.deleteStuById(8);
        }
    }

    @Test
    public void testSelectAll1(){
        if(session!=null){
            ITeacherDAO=session.getMapper(ITeacher.class);
            list1=ITeacherDAO.selectAllTea1();
            for (Teacher t : list1) {
                System.out.println(t);
            }
        }
    }

    @Test
    public void testSelectAllStu1(){
        if(session!=null){
            IStudentDAO=session.getMapper(IStudent.class);
            list2=IStudentDAO.selectAllStu1();
            for (Student s : list2) {
                System.out.println(s);
            }
        }
    }

    @Test
    public void testStuProviderUpdate(){
        if(session!=null){
            IStudentDAO=session.getMapper(IStudent.class);
            IStudentDAO.updateStuById(new Student(7,1,"雪乃",null));
        }
    }

    @Test
    public void testStuProviderSelect(){
        if(session!=null){
            IStudentDAO=session.getMapper(IStudent.class);
            list2=IStudentDAO.selectStu(new Student(7,3,"null",null));
            for (Student s : list2) {
                System.out.println(s);
            }
        }
    }

    @Test
    public void testStuProviderSelectStu1(){
        if(session!=null){
            IStudentDAO=session.getMapper(IStudent.class);
            list2=IStudentDAO.selectStu1(new Student(2,4,"芙宁娜",null));
            for (Student s : list2) {
                System.out.println(s);
            }
        }
    }
}
