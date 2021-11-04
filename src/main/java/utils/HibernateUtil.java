package utils;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

    private static SessionFactory sessionFactory;
    private static Session session;

    public static SessionFactory buildSessionFactory(){
        Configuration cfg =  new Configuration();
        cfg.configure("hibernate.cfg.xml");
        if (sessionFactory == null){
            sessionFactory = cfg.buildSessionFactory();
        }
        return sessionFactory;
    }

    public static Session openSession(){
        if(session ==null || !session.isOpen()){
            session =  buildSessionFactory().openSession();
        }
        return session;
    }
}
