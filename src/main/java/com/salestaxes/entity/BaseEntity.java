package com.salestaxes.entity;

public class BaseEntity {


    private Long id;

    private String cod;

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
