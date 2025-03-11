package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Util {
//    private final static String DB_URL = "jdbc:mysql://localhost:3306/mydb";
//    private final static String DB_LOGIN = "root";
//    private final static String DB_PASSWORD = "root";
//
//    public static Connection getConnection(){
//        Connection connection = null;
//        try {
//            Class.forName("com.mysql.cj.jdbc.Driver");
//            connection = DriverManager.getConnection(DB_URL,DB_LOGIN,DB_PASSWORD);
//        } catch (ClassNotFoundException | SQLException e) {
//            throw new RuntimeException(e);
//        }
//        return connection;
//    }

    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Properties properties = new Properties();
                properties.put(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");
                properties.put(Environment.URL, "jdbc:mysql://localhost:3306/mydb");
                properties.put(Environment.USER, "root");
                properties.put(Environment.PASS, "root");

                properties.put(Environment.DIALECT, "org.hibernate.dialect.MySQL5Dialect");
                properties.put(Environment.SHOW_SQL, "false");
                properties.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
                properties.put(Environment.HBM2DDL_AUTO, "");

                Configuration configuration = new Configuration().setProperties(properties).addAnnotatedClass(User.class);

                StandardServiceRegistry standardServiceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();

                sessionFactory = configuration.buildSessionFactory(standardServiceRegistry);
            } catch (HibernateException he) {
                he.printStackTrace();
            }
        } return sessionFactory;
    }

    public static Session openSession() {
        return sessionFactory.getCurrentSession();
    }
}