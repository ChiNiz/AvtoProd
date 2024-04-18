package com.taibov.vkr;

import com.taibov.vkr.entities.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class Main
{
    public static void main(String[] args)
    {

    }

    public static Session GetSession(){
        Configuration configuration = new Configuration();
        configuration.addAnnotatedClass(Client.class);
        configuration.addAnnotatedClass(Address.class);
        configuration.addAnnotatedClass(Car.class);
        configuration.configure();
        try (SessionFactory sessionFactory = configuration.buildSessionFactory();
             Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            return session;
        }catch (Exception e){
            return null;
        }
    }

    public static void DoInSession(IDo iDo) {
        Configuration configuration = new Configuration();
        configuration.addAnnotatedClass(Client.class);
        configuration.addAnnotatedClass(Address.class);
        configuration.addAnnotatedClass(Car.class);
        configuration.addAnnotatedClass(Order.class);
        configuration.configure();
        try (SessionFactory sessionFactory = configuration.buildSessionFactory();
             Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            iDo.Do(session);
            session.getTransaction().commit();
        }
    }

    public static void DoInSessionWithoutCommit(IDo iDo) {
        Configuration configuration = new Configuration();
        configuration.addAnnotatedClass(Client.class);
        configuration.addAnnotatedClass(Address.class);
        configuration.addAnnotatedClass(Car.class);
        configuration.addAnnotatedClass(Order.class);
        configuration.configure();
        try (SessionFactory sessionFactory = configuration.buildSessionFactory();
             Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            iDo.Do(session);
        }
    }

    public static List<Client> GetClientByAllName(String firstname, String secondname){
        Configuration configuration = new Configuration();
        configuration.addAnnotatedClass(Client.class);
        configuration.addAnnotatedClass(Address.class);
        configuration.addAnnotatedClass(Car.class);
        configuration.configure();
        try (SessionFactory sessionFactory = configuration.buildSessionFactory();
             Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            return session.createSQLQuery(String.format("SELECT * FROM clients WHERE firstname = '%s' AND secondname = '%s'", firstname, secondname))
                    .addEntity(Client.class)
                    .list();
        }
    }
}
