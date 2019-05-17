import com.entity.Course;
import com.entity.StudentTwo;
import com.xiao.Classess;
import com.xiao.GoodsEntity;
import com.xiao.Student;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.Set;

public class TestGoods {
    private SessionFactory sessionFactory;
    private Session session;
    private Transaction transaction;


    /**
     * 执行test之前执行的代码
     */
    @Before
    public void init() {
        Configuration configuration = new Configuration().configure();//创建配置对象
        sessionFactory = configuration.buildSessionFactory();//创建session工厂
        session = sessionFactory.openSession();//获得session对象
        transaction = session.beginTransaction();//打开事务管理
    }

    /**
     * 执行After之后的的代码
     */
    @After
    public void destory() {
        transaction.commit();//事务提交
        session.close();//关闭会话
        sessionFactory.close();//关闭会话工厂
    }

    @Test
    public void testGoods() {
        //生成对象
        GoodsEntity goodsEntity = new GoodsEntity("男朋友", 5633333.3);
        //保存对象数据库
        session.save(goodsEntity);

        Classess classess = new Classess("软件一班");

        Student student1 = new Student("肖杰斌");
        Student student2 = new Student("鹿丽娟");

        student1.setClassess(classess);
        student2.setClassess(classess);

        session.save(classess);
        session.save(student1);
        session.save(student2);
    }

    @Test
    public void test() {
        Student student = session.get(Student.class, "402880ea6abf8115016abf811aa10001");
        System.out.println(student.getSname());
        System.out.println(student.getClassess().getName());
    }

    /**
     * 双向级联保存数据
     */

    @Test
    public void testOne() {
        Classess r1 = new Classess("软件一班");

        Student s1 = new Student("小胖子");
        Student s2 = new Student("大胖子");

        r1.getStu().add(s1);
        r1.getStu().add(s2);

        session.save(r1);

    }

    @Test
    public void testTwo() {
        Classess classess = session.get(Classess.class, "402880ea6abfcf45016abfcf49ce0000");
        session.delete(classess);
    }

    @Test
    public void update() {
        Student student = session.get(Student.class, "402880ea6abfdd92016abfdd97820001");
        Classess classess = session.get(Classess.class, "402880ea6abfdd92016abfdd97650000");
        student.setClassess(classess);
    }

    /**
     * 级联添加
     */
    @Test
    public void testTree() {
        Course course = new Course();
        course.setCname("物理");
        StudentTwo student = new StudentTwo();
        student.setSname("李窝");
        student.getCourseSet().add(course);
        session.save(student);
    }

    @Test
    public void testDelete() {
        Course course = session.get(Course.class, 1);
        session.delete(course);
    }

    @Test
    public void testFive() {
        Course course = session.get(Course.class, 2);
        StudentTwo studentTwo = session.get(StudentTwo.class, 3);
        course.getStudentSet().add(studentTwo);

    }

    @Test
    public void testSix() {
        Course course = session.get(Course.class, 2);
        StudentTwo studentTwo = session.get(StudentTwo.class, 3);
        course.getStudentSet().remove(studentTwo);
    }

    /**
     * 对象导航查询
     */
    @Test
    public void testSelect() {

        Classess classess = session.get(Classess.class, "402880ea6ac3ec5e016ac3ec619c0000");
        Set<Student> stu = classess.getStu();
        System.out.println(stu.size());
    }

    /**
     * HQL查询所有数据
     */
    @Test
    public void testSelect1() {
        Query from_student_ = session.createQuery("from Student ");
        List<Student> list = from_student_.list();
        for (Student student : list) {
            System.out.println(student);
        }
    }

    /**
     * HQL排序查询
     */
    @Test
    public void testSelect2() {
        Query from_student_ = session.createQuery("from Student order by id desc ");
//        from_student_.setParameter(0,"%胖%");
        List<Student> list = from_student_.list();
        for (Student student : list) {
            System.out.println(student);
        }
    }

    /**
     * HQL分页查询
     */

    @Test
    public void testSelect3() {
        Query from_student_ = session.createQuery("from Student");
        //设置分页数据
        //设置开始位置
        from_student_.setFirstResult(0);
        //设置每页记录数
        from_student_.setMaxResults(2);
        List<Student> list = from_student_.list();
        for (Student student : list) {
            System.out.println(student);
        }

    }

    /**
     * HQL投影查询
     */

    @Test
    public void testSelect4() {
        Query from_student_ = session.createQuery("select id,sname from Student");
        List<Object> list = from_student_.list();
        for (Object student : list) {
            System.out.println(student.toString());
        }
    }

    /**
     * HQL聚集函数
     */
    @Test
    public void testSelect5() {
        Query from_student_ = session.createQuery("select count(*) from Student");
        Object o = from_student_.uniqueResult();
        System.out.println(o);
    }

    @Test
    public void testSelect6() {
        Criteria criteria = session.createCriteria(Student.class);
        List<Student> list = criteria.list();
        for (Student stu : list
        ) {
            System.out.println(stu.getId() + "::" + stu.getSname()
            );
        }
    }

    @Test
    public void testSelect7() {
        Criteria criteria = session.createCriteria(Student.class);
        //使用criteria对象里面的方法设置条件值
        //首先使用add方法，表示设置条件值
        //在add方法里面实用类的方法实现条件设置，类似于cid=:cid
        criteria.add(Restrictions.eq("id", "402880ea6ac3ec5e016ac3ec61bc0001"));
        List<Student> list = criteria.list();
        for (Student stu : list
        ) {
            System.out.println(stu.getId() + "::" + stu.getSname()
            );
        }
    }

    @Test
    public void testSelect8() {
        Criteria criteria = session.createCriteria(Student.class);
        //使用criteria对象里面的方法设置条件值
        //首先使用add方法，表示设置条件值
        //在add方法里面实用类的方法实现条件设置，类似于cid=:cid
        criteria.add(Restrictions.like("sname", "%胖%"));
        List<Student> list = criteria.list();
        for (Student stu : list
        ) {
            System.out.println(stu.getId() + "::" + stu.getSname()
            );
        }
    }

    @Test
    public void testSelect9() {
        Criteria criteria = session.createCriteria(Student.class);
        criteria.addOrder(Order.desc("id"));
        List<Student> list = criteria.list();
        for (Student stu : list
        ) {
            System.out.println(stu.getId() + "::" + stu.getSname()
            );
        }
    }

    @Test
    public void testSelect10() {
        Criteria criteria = session.createCriteria(Student.class);
        //设置分页数据
        //设置开始位置
        criteria.setFirstResult(0);
        //每页显示记录数
        criteria.setMaxResults(1);
        List<Student> list = criteria.list();
        for (Student stu : list
        ) {
            System.out.println(stu.getId() + "::" + stu.getSname()
            );
        }
    }

    @Test
    public void testSelect11() {
        Criteria criteria = session.createCriteria(Student.class);
        criteria.setProjection(Projections.rowCount());
        Object o= criteria.uniqueResult();
        System.out.println(o);
    }


    @Test
    public void testSelect12() {
        //创建对象
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Student.class);
        //最终执行时候需要session
        Criteria executableCriteria = detachedCriteria.getExecutableCriteria(session);
        List<Student> list = executableCriteria.list();
        for (Student stu:list
             ) {
            System.out.println(stu.getId() + "::" + stu.getSname());
        }
    }

    @Test
    public void testSelect13() {
        Query query = session.createQuery("from Classess c inner join c.stu");
        List list = query.list();

    }
    /**
     * 迫切内连接
     */
    @Test
    public void testSelect14() {
        Query query = session.createQuery("from Classess c inner join fetch c.stu");
        List list = query.list();

    }
}
