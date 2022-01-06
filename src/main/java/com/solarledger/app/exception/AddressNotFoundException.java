package com.solarledger.app.exception;
public class AddressNotFoundException extends Exception {
    private long book_id;
    public AddressNotFoundException(long addres_id) {
        super(String.format("Address is not found with id : '%s'", addres_id));
    }
}

