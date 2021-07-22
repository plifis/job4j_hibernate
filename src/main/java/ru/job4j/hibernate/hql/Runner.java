package ru.job4j.hibernate.hql;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.sql.OracleJoinFragment;

import javax.persistence.Query;


public class Runner {
    public static void main(String[] args) {
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();

        SessionFactory sf = new MetadataSources(registry).buildMetadata().buildSessionFactory();
        Session session = sf.openSession();
        session.beginTransaction();
        Candidate one = new Candidate("Nikita", 5, 100f);
        Candidate two = new Candidate("Alex", 7, 50.5f);
        Candidate three = new Candidate("Bill", 0, 0.5f);

        session.save(one);
        session.save(two);
        session.save(three);

        session.getTransaction().commit();
        session.beginTransaction();
        Query select  = session.createQuery("from Candidate");
        for (Object c : select.getResultList()) {
            System.out.println("All candidates!!! " + c.toString());
        }

        Query selectId = session.createQuery("from Candidate c where c.id = :cId ")
                .setParameter("cId", 2);
            System.out.println("Candidate N2!!! " + selectId.getSingleResult());

        Query selectName = session.createQuery("from Candidate c where c.name = :cName")
                .setParameter("cName", "Bill");
        for (Object c : selectName.getResultList()) {
            System.out.println("Candidate = Bill!!! " + c.toString());
        }

        session.createQuery("update Candidate c set " +
                "c.experience = :newExperience, c.salary = :newSalary where c.id = :cId")
                .setParameter("newExperience", 6)
                .setParameter("newSalary", 150f)
                .setParameter("cId", 1)
                .executeUpdate();

        System.out.println("Update id = 1 " + session.createQuery("from Candidate ").list().get(0).toString());


        session.createQuery("delete from Candidate  where id = :oldId").setParameter("oldId", 2);
        System.out.println("Result delete id = 2 " + session.createQuery("from Candidate ").list().get(0).toString());

        session.createQuery("insert into Candidate (name, experience, salary) " +
                "select concat('super', c.name), c.experience * 2, c.salary * 3 " +
                "from Candidate c where c.id = :cId").setParameter("cId", Integer.MAX_VALUE).executeUpdate();

        for (Object c : session.createQuery("from Candidate").list()) {
            System.out.println("Insert into " + c.toString());
        }

        session.close();

    }
}
