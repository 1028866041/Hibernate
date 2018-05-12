
import com.fasterxml.classmate.AnnotationConfiguration;
import com.myself.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.junit.Test;

import java.lang.annotation.Annotation;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.apache.log4j.Logger;

/**
 * Created by Administrator on 2017/6/16 0016.
 */

public class test {

    @Test
    public void test(){

        User user = new User();
        user.setName("zhangsan");
        user.setAge(20);

        Logger logger = Logger.getLogger(this.getClass().getName());

        /*hibernate4使用ServiceRegistryBuilder()*/
        Configuration cfg = new Configuration().configure();//xml与annotation方式
        //ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(cfg.getProperties()).buildServiceRegistry();
        SessionFactory sf = cfg.buildSessionFactory();

        /*meichao:Hibernate5正确方法:
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
        //根据服务注册类创建元数据资源集同时构建元数据载生成应用唯一session工厂
        SessionFactory sf = new MetadataSources(registry).buildMetadata().buildSessionFactory();*/

        Session session = sf.openSession();

        session.beginTransaction();
        session.save(user);
        session.getTransaction().commit();
        System.out.println("getTransaction.commit");

        logger.info("This is info message");
        logger.debug("This is debug message");
        //Slogger.error("This is error message");

        session.close();
        sf.close();
    }
}
