package ru.job4j.hibernate.integration;

import org.apache.commons.dbcp2.BasicDataSource;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class OrdersStoreTest {
    private BasicDataSource pool = new BasicDataSource();

    @Before
    public void setUp() throws SQLException {
        pool.setDriverClassName("org.hsqldb.jdbcDriver");
        pool.setUrl("jdbc:hsqldb:mem:tests;sql.syntax_pgs=true");
        pool.setUsername("sa");
        pool.setPassword("");
        pool.setMaxTotal(2);
        StringBuilder builder = new StringBuilder();
        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(new FileInputStream("./db/update_001.sql")))
        ) {
            br.lines().forEach(line -> builder.append(line).append(System.lineSeparator()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        pool.getConnection().prepareStatement(builder.toString()).executeUpdate();
    }

    @Test
    public void whenSaveOrderAndFindAllOneRowWithDescription() {
        OrdersStore store = new OrdersStore(pool);

        store.save(Order.of("name1", "description1"));

        List<Order> all = (List<Order>) store.findAll();

        assertThat(all.size(), is(1));
        assertThat(all.get(0).getDescription(), is("description1"));
        assertThat(all.get(0).getId(), is(1));
    }

    @Test
    public void whenUpdateDescriptionAndGetRow() throws SQLException {
        OrdersStore store = new OrdersStore(pool);
        store.save(Order.of("one", "descriptionOne"));
        Order exp = Order.of("one", "UpdateDescription");
        store.update(exp);
        assertThat(store.findById(1).getDescription(), is (exp.getDescription()));
    }

    @Test
    public void whenSaveAndFindByName() {
        OrdersStore store = new OrdersStore(pool);
        store.save(Order.of("one", "descriptionOne"));
        Order exp = store.save(Order.of("two", "descriptionTwo"));
        assertThat(store.findByName("two").getDescription(), is (exp.getDescription()));
    }

    @After
    public void getDown() throws SQLException {
        pool.getConnection().prepareStatement("drop table orders").execute();
        pool.close();
    }
}