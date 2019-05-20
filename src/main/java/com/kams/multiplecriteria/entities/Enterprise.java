package com.kams.multiplecriteria.entities;

import javax.persistence.*;

@Entity
@NamedQuery(name = "employeesById",query = "select e from Employee e where e.enterprise.id = :id")
public class Enterprise {

    @Id
    private Long id;
    private String name;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
