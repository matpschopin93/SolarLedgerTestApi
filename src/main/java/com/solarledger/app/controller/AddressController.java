package com.solarledger.app.controller;

import com.solarledger.app.dto.Response;
import com.solarledger.app.exception.AddressNotFoundException;
import com.solarledger.app.model.Address;
import com.solarledger.app.repository.AddressRepository;
import com.solarledger.app.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

@RestController
public class AddressController {

    @Autowired
    AddressRepository addressRepository;

    // Create a new Note
    @PostMapping("/saveList")
    public ResponseEntity<Response> saveList(@RequestBody List<Address> lista) {
        Response response = new Response();
        try {
        lista.stream().forEach(p -> addressRepository.save(p));
        response.setResult_code(HttpStatus.OK.toString());
        response.setLista(lista);

        }catch(Exception e){
            e.printStackTrace();
            response.setResult_code(e.toString());
        }
        return !response.getResult_code().equals(HttpStatus.OK.toString())? ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response):ResponseEntity.ok().body(response);

    }
    // Get All Notes
    @GetMapping("/getListInRange")
    public ResponseEntity<Response> getAllNotes(@RequestParam Integer start,@RequestParam Integer end) {
        Response response = new Response();
        try {
            List<Address> query = addressRepository.getAllBetweenPostCodes(start, end);

            int totalCaracter = query.stream().filter(c -> c.getName() != null).map(x -> x.getName().length())
                    .reduce(0, Integer::sum);

            response.setLista(query);
            response.setResult_code(HttpStatus.OK.toString());
            response.setTotalLenght(totalCaracter);
        }catch(Exception e){
            e.printStackTrace();
            response.setResult_code(e.toString());
        }
        return !response.getResult_code().equals(HttpStatus.OK.toString())? ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response):ResponseEntity.ok().body(response);
    }

}
