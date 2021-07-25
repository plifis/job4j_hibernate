package ru.job4j.hibernate.hql;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "basevacancy")
public class BaseVacancy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToMany
    private List<Vacancy> list = new ArrayList<>();

    public BaseVacancy() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Vacancy> getList() {
        return list;
    }

    public void setList(List<Vacancy> list) {
        this.list = list;
    }

    public void addVacancy(Vacancy vacancy) {
        this.list.add(vacancy);
    }

    @Override
    public String toString() {
        return "BaseVacancy{" +
                "id=" + id +
                ", list=" + list +
                '}';
    }
}
