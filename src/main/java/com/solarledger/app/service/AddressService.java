package com.solarledger.app.service;

import com.solarledger.app.dto.Response;
import com.solarledger.app.exception.AddressNotFoundException;
import com.solarledger.app.model.Address;
import com.solarledger.app.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressService {

    @Autowired
    AddressRepository addressRepository;

    public Response saveListofAddres(List<Address> lista) {
        Response response = new Response();
        try {
            lista.stream().forEach(p -> addressRepository.save(p));
            response.setResult_code(HttpStatus.OK.toString());
            response.setLista(lista);
        }catch(Exception e){
            e.printStackTrace();
            response.setResult_code(e.toString());
        }
        return response;
    }
    public Response getNamesIntoRange(Integer start,Integer end) {
        Response response = new Response();
        try {
            List<Address> query = addressRepository.getAllBetweenPostCodes(start, end);
            if (!query.isEmpty()) {

            int totalCaracter = query.stream().filter(c -> c.getName() != null).map(x -> x.getName().length())
                    .reduce(0, Integer::sum);

            response.setLista(query);
            response.setResult_code(HttpStatus.OK.toString());
            response.setTotalLenght(totalCaracter);
            }else{
                throw new AddressNotFoundException("There's no Address into this range "+start+"-"+end);
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.setResult_code(e.toString());
        }
        return response;
    }
}
