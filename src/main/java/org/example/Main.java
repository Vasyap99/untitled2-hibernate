package org.example;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.annotations.Entity;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
//import org.hibernate.query.Query;

//import javax.persistence.Id;
//import javax.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.hibernate.query.Query;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Iterator;
import java.util.List;



public class Main {
    void insert_objects(Session session){
        Monter m=session.find(Monter.class,1);
        PultObject o=new PultObject(m,"CHTZ-Servis","Novgorodskiy","Pankovka","Industrial`naya","22");
        Transaction t = session.beginTransaction();
        session.save(o);
        t.commit();
    }
    public static void main(String[] args) {
        // Press Alt+Enter with your caret at the highlighted text to see how
        // IntelliJ IDEA suggests fixing it.
        System.out.printf("Hello and welcome!");

        // Press Shift+F10 or click the green arrow button in the gutter to run the code.
        for (int i = 1; i <= 5; i++) {

            // Press Shift+F9 to start debugging your code. We have set one breakpoint
            // for you, but you can always add more by pressing Ctrl+F8.
            System.out.println("i = " + i);
        }

        try {
            Connection connection = DriverManager.getConnection(
                    "jdbc:oracle:thin:@localhost:1521/XE",
                    "ts01", "ts1p");

            Statement statement = connection.createStatement();
            ResultSet results = statement.executeQuery("SELECT * FROM monter");

            while (results.next()) {
                Integer id = results.getInt(1);
                String name = results.getString(2);
                System.out.println(results.getRow() + ". " + id + "\t"+ name);
            }
            connection.close();
        }catch(Throwable t){System.out.println("exc1");t.printStackTrace();}


        SessionFactory sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                //
                .addPackage("org.example")
                .addAnnotatedClass(Monter.class)
                .addAnnotatedClass(PultObject.class)
                .buildSessionFactory();/**/

        /*StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure() // configures settings from hibernate.cfg.xml
                .build();
            SessionFactory sessionFactory = new MetadataSources(registry)
                    .addAnnotatedClass(org.example.Monter.class)
                    .buildMetadata()
                    .buildSessionFactory();*/

        try {
            Session session = sessionFactory.openSession();

            //1 ok
            /*
            Monter user = (Monter) session.get(org.example.Monter.class, 2);
            System.out.println(user.i);
            System.out.println(user.name);*/

            //2 ok
            /*Query<Monter> query = session.createQuery("from org.example.Monter", Monter.class);
            List<Monter> m=query.list();
            Iterator<Monter> i=m.iterator();
            while(i.hasNext()){
                Monter v=i.next();
                System.out.println(">>>"+v.i+' '+v.name);
            }*/

            //3
            Transaction transaction = session.beginTransaction();
            Monter mn=new Monter("Ivanov Boris Vasilyevich22");
            session.save(mn);
            transaction.commit();
            //session.save(new Monter("Ивлев Игорь Иванович2"));
            //session.flush();

            Query<Monter> query = session.createQuery("from org.example.Monter", Monter.class);
            List<Monter> m=query.list(); //
            Iterator<Monter> i=m.iterator();
            while(i.hasNext()){
                Monter v=i.next();
                v.name+="1";
                session.evict(v);
                Transaction t = session.beginTransaction();
                session.merge(v);
                t.commit();
                System.out.println(">>>"+v.i+' '+v.name);
            }

            new Main().insert_objects(session);

            session.close();/**/

        }catch (Throwable t){System.out.println("exc1");t.printStackTrace();}
    }
}