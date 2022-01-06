package com.solarledger.app.controller;

import com.solarledger.app.dto.Response;
import com.solarledger.app.model.Address;
import com.solarledger.app.service.AddressService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.util.List;

@RestController
public class AddressController {

    Logger logger = LoggerFactory.getLogger(AddressController.class);

    @Autowired
    AddressService addressService;

    @PostMapping("/saveList")
    public ResponseEntity<Response> saveList(@RequestBody List<Address> lista) {
        Response response = new Response();
        try {
            response= addressService.saveListofAddres(lista);
            logger.info("Saved "+lista.size()+" record");
        }catch(Exception e){
            e.printStackTrace();
            logger.error("Error during record ingestion");
        }
        return !response.getResult_code().equals(HttpStatus.OK.toString())? ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response):ResponseEntity.ok().body(response);

    }

    @GetMapping("/getListInRange")
    public ResponseEntity<Response> getNamesInRange(@RequestParam Integer start,@RequestParam Integer end) {
        Response response = new Response();
        try {
            response= addressService.getNamesIntoRange(start, end);
            logger.info(response.getList().size()+" record found");
        }catch(Exception e){
            logger.warn("Error getting record into the range");
            e.printStackTrace();
        }

        return !response.getResult_code().equals(HttpStatus.OK.toString())? ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response):ResponseEntity.ok().body(response);

    }

}
