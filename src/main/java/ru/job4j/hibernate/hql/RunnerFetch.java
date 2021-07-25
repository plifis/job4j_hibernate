package ru.job4j.hibernate.hql;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.List;

public class RunnerFetch {
    public static void main(String[] args) {
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();

        SessionFactory sf = new MetadataSources(registry).buildMetadata().buildSessionFactory();
        Session session = sf.openSession();
        session.beginTransaction();

        List<BaseVacancy> rsl = session.createQuery("select distinct b from BaseVacancy b " +
                "join fetch b.list " +
                "where b.id = :bId", BaseVacancy.class)
                .setParameter("bId", 1).list();
        System.out.println(rsl.get(0).getList());


        Candidate candidate = session.createQuery("select distinct c from Candidate c " +
                "where c.id = :cId", Candidate.class).setParameter("cId", 1).list().get(0);
        System.out.println(candidate.getBase());

        session.getTransaction().commit();
        session.close();
    }
}
