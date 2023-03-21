package com.example.demo.controllers;

import java.io.IOException;
import java.util.List;

import com.example.demo.entities.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.demo.services.meansOfTransportService;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/meanOfTransport")
public class meansOfTransportController {
    @Autowired
    meansOfTransportService meanOfTransportService;

    @PostMapping("/add")
    void addmeanOfTransport(@RequestParam("image") MultipartFile image , @RequestParam("nameTransport") String nameTransport , @RequestParam("capacity") Integer capacity , @RequestParam("type") Type type)  throws IOException {

        meansOfTransport  meansOfTransport =new meansOfTransport();
        meansOfTransport.setImage(image.getBytes());
        meansOfTransport.setNameTransport(nameTransport);
        meansOfTransport.setCapacity(capacity);
        meansOfTransport.setType(type);
        meanOfTransportService.addmeanOfTransport(meansOfTransport);
    }

    @PutMapping("/update/{id}")
    void updatemeanOfTransport(@PathVariable Integer id,@RequestBody meansOfTransport meanOfTransport) {
        meanOfTransportService.updatemeanOfTransport(id, meanOfTransport);
    }

    @DeleteMapping("/delete/{id}")
    void deletemeanOfTransport(@PathVariable Integer id) {
        meanOfTransportService.deletemeanOfTransport(id);
    }

    @GetMapping("/getAll")
    List<meansOfTransport> getAll(){
        return meanOfTransportService.getAll();
    }

    @GetMapping("/getById/{id}")
    meansOfTransport getById(@PathVariable Integer id) {
        return meanOfTransportService.getById(id);
    }
    @GetMapping("/getTranComByRat/{rating}")
    public List<transportCompany> getTransportCompaniesByMeansOfTransportRating(@PathVariable Double rating){
    	return meanOfTransportService.getTransportCompaniesByMeansOfTransportRating(rating);
    }

    @PutMapping("/affectMean/{idM}/{idC}")
    void addmeanOfTransport(@PathVariable Integer idM ,@PathVariable Integer idC) {
        meanOfTransportService.affectermeansToCompany(idM, idC);
    }

}
