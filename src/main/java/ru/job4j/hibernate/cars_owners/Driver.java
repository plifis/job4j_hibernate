package ru.job4j.hibernate.cars_owners;


import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "drivers")
public class Driver {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private int license;

    @Temporal(TemporalType.DATE)
    private Date birthday;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLicense() {
        return license;
    }

    public void setLicense(int license) {
        this.license = license;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Driver driver = (Driver) o;
        return id == driver.id &&
                license == driver.license &&
                Objects.equals(name, driver.name) &&
                Objects.equals(birthday, driver.birthday);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, license, birthday);
    }
}
