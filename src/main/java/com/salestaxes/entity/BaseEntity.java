package com.salestaxes.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class BaseEntity {


    @Id
    @Column(name = "ID")
    private Long id;

    @Column(name = "COD")
    private String cod;

    @Column(name = "DESCRIPTION")
    private String des;

    public BaseEntity() {
    }

    public BaseEntity(Long id, String cod, String des) {
        this.id = id;
        this.cod = cod;
        this.des = des;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCod() {
        return cod;
    }

    public void setCod(String cod) {
        this.cod = cod;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }
}
