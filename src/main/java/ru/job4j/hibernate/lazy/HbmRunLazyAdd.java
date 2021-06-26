package ru.job4j.hibernate.lazy;

import org.hibernate.CallbackException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;


public class HbmRunLazyAdd {
    public static void main(String[] args) {
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
        try {
            SessionFactory sf = new MetadataSources(registry).buildMetadata().buildSessionFactory();
            Session session = sf.openSession();
            session.beginTransaction();
            CarMark toyota = new CarMark("Toyota");
            session.save(toyota);

            CarModel corolla = new CarModel("Corolla", toyota);
            session.save(corolla);
            CarModel camry = new CarModel("Camry", toyota);
            session.save(camry);
            CarModel rav = new CarModel("Rav4", toyota);
            session.save(rav);
            CarModel prado = new CarModel("Prado", toyota);
            session.save(prado);
            CarModel supra = new CarModel("Supra", toyota);
            session.save(supra);
//
//            toyota.addCarModel(session.load(CarModel.class, 1));
//            toyota.addCarModel(session.load(CarModel.class, 2));
//            toyota.addCarModel(session.load(CarModel.class, 3));
//            toyota.addCarModel(session.load(CarModel.class, 4));
//            toyota.addCarModel(session.load(CarModel.class, 5));
//            session.save(toyota);
            session.getTransaction().commit();
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }

}
