package com.solarledger.app.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="address")
public class Address {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private Integer postCodes;

    public Address(){
        super();
    }
    public Address(Long id, String name,Integer postCodes){
        super();
        this.id=id;
        this.name=name;
        this.postCodes=postCodes;
    }

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

    public Integer getPostCodes() {
        return postCodes;
    }

    public void setPostCodes(Integer postCodes) {
        this.postCodes = postCodes;
    }
}
