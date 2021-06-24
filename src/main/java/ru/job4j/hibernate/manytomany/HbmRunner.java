package ru.job4j.hibernate.manytomany;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class HbmRunner {
    public  static void main(String[] args) {
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
        try {
            SessionFactory sf = new MetadataSources(registry).buildMetadata().buildSessionFactory();
            Session session = sf.openSession();
            session.beginTransaction();
            Author gogol = new Author("Nikolay Gogol");
            Book viy = new Book("Viy");
            Book revizor = new Book("Revizor");
            gogol.addBook(viy);
            gogol.addBook(revizor);

            Author perumov = new Author("Nik Perumov");
            Book bs = new Book("Black spear");
            perumov.addBook(bs);

            Author lukiyanenko = new Author("Sergey Lukiyanenko");
            Book dr = new Book("A Farewell To Dragon");
            Book nw = new Book("Night Watch");

            lukiyanenko.addBook(dr);
            lukiyanenko.addBook(nw);
            perumov.addBook(dr);

            session.persist(gogol);
            session.persist(perumov);
            session.persist(lukiyanenko);

            session.remove(perumov);
            session.getTransaction().commit();
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }
}
