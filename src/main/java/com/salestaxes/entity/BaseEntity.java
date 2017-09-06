package com.salestaxes.entity;

/**
 * Created by Leonardo Galati on 06/09/2017.
 */
public class BaseEntity {

    protected Long id;

    protected String cod;

    protected String des;

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
