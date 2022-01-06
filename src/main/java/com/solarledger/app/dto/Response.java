package com.solarledger.app.dto;

import com.solarledger.app.model.Address;
import com.sun.istack.NotNull;

import java.util.List;

public class Response {

    private List<Address> list;

    @NotNull
    private String result_code;

    private int totalLenght;

    public List<Address> getList() {
        return list;
    }

    public void setLista(List<Address> list) {
        this.list = list;
    }

    public String getResult_code() {
        return result_code;
    }

    public void setResult_code(String result_code) {
        this.result_code = result_code;
    }

    public int getTotalLenght() {
        return totalLenght;
    }

    public void setTotalLenght(int totalLenght) {
        this.totalLenght = totalLenght;
    }
}
