package ru.job4j.hibernate.hql;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import javax.persistence.Query;
import java.util.List;


public class Runner {
    public static void main(String[] args) {
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();

        SessionFactory sf = new MetadataSources(registry).buildMetadata().buildSessionFactory();
        Session session = sf.openSession();
        session.beginTransaction();
        BaseVacancy baseVacancy = new BaseVacancy();
        Candidate one = new Candidate("Nikita", 5, 100f, baseVacancy);
        Candidate two = new Candidate("Alex", 7, 50.5f, baseVacancy);
        Candidate three = new Candidate("Bill", 0, 0.5f, baseVacancy);
        Vacancy java = new Vacancy("Java developer", 75f);
        Vacancy sales = new Vacancy("Sales", 100f);

        session.save(java);
        session.save(sales);
        session.save(baseVacancy);
        baseVacancy.addVacancy(java);
        baseVacancy.addVacancy(sales);
        session.save(one);
        session.save(two);
        session.save(three);

        session.getTransaction().commit();
        session.beginTransaction();


        Query select  = session.createQuery("from Candidate");
        for (Object c : select.getResultList()) {
            System.out.println("All candidates!!! " + c);
        }

        Query selectId = session.createQuery("from Candidate c where c.id = :cId ")
                .setParameter("cId", 2);
            System.out.println("Candidate N2!!! " + selectId.getSingleResult());

        Query selectName = session.createQuery("from Candidate c where c.name = :cName")
                .setParameter("cName", "Bill");
        for (Object c : selectName.getResultList()) {
            System.out.println("Candidate = Bill!!! " + c);
        }

        session.createQuery("update Candidate c set " +
                "c.experience = :newExperience, c.salary = :newSalary where c.id = :cId")
                .setParameter("newExperience", 6)
                .setParameter("newSalary", 150f)
                .setParameter("cId", 1)
                .executeUpdate();
        System.out.println("Update id = 1 " + session.createQuery("from Candidate where id = 1"));


        session.createQuery("delete from Candidate  where id = :oldId")
                .setParameter("oldId", 2)
                .executeUpdate();
        for (Object c : session.createQuery("from Candidate ").list()) {
            System.out.println("Result delete id = 2 " + c);
        }

        session.createQuery("insert into Candidate (name, experience, salary) " +
                "select concat('super', c.name), c.experience * 2, c.salary * 3 " +
                "from Candidate c where c.id = :cId").setParameter("cId", Integer.MAX_VALUE).executeUpdate();
        for (Object c : session.createQuery("from Candidate").list()) {
            System.out.println("Insert into " + c);
        }


    }
}
