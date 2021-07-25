package ru.job4j.hibernate.hql;


import javax.persistence.*;

@Entity
@Table(name = "vacancies")
public class Vacancy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String description;
    private float salary;

    public Vacancy() {
    }

    public Vacancy(String description, float salary) {
        this.description = description;
        this.salary = salary;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Vacancy{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", salary=" + salary +
                '}';
    }
}
