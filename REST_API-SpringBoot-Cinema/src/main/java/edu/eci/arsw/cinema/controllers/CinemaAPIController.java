/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.cinema.controllers;

import edu.eci.arsw.cinema.persistence.CinemaException;
import edu.eci.arsw.cinema.services.CinemaServices;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
/**
 *
 * @author cristian
 */
@RestController
@Service
@RequestMapping(value = "/cinemas")
public class CinemaAPIController {
    
    @Autowired
    CinemaServices cs;
    
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> getAllCinemas(){
        try {
            //obtener datos que se enviarán a través del API
            return new ResponseEntity<>(cs.getAllCinemas(),HttpStatus.ACCEPTED);
        } catch (Exception ex) {
            Logger.getLogger(CinemaAPIController.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>("Error in the json generation",HttpStatus.NOT_FOUND);
        }
    }
    
    /*@GetMapping(path = "/{name}",  produces = "application/json;charset=UTF-8")
    @ResponseBody*/
    @GetMapping("/{name}")
    public ResponseEntity<?> getCinema(@PathVariable String name) throws ResourceNotFoundException{
        try {            
            return new ResponseEntity<>(cs.getCinemaByName(name), HttpStatus.ACCEPTED);
        } catch (CinemaException e) {
            Logger.getLogger(CinemaAPIController.class.getName()).log(Level.SEVERE, null, e);
            //throw new ResourceNotFoundException(e.getMessage());
            return new ResponseEntity<>(new ResourceNotFoundException(e.getMessage()).getMessage(), HttpStatus.NOT_FOUND);
        }        
    }
    
}
