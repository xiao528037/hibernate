import com.xiao.Classess;
import com.xiao.GoodsEntity;
import com.xiao.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

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
        Classess r1 = new Classess("软件二班");

        Student s1 = new Student("小胖子");
        Student s2 = new Student("大胖子");

        r1.getStu().add(s1);
        r1.getStu().add(s2);

        session.save(r1);

    }
    @Test
    public void testTwo(){
        Classess classess = session.get(Classess.class, "402880ea6abfcf45016abfcf49ce0000");
        session.delete(classess);
    }

    @Test
    public void update(){
        Student student = session.get(Student.class, "402880ea6abfdd92016abfdd97820001");
        Classess classess = session.get(Classess.class, "402880ea6abfdd92016abfdd97650000");
        student.setClassess(classess);
    }
}
