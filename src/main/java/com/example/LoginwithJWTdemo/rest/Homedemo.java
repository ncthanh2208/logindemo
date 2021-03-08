package com.example.LoginwithJWTdemo.rest;



import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Homedemo {

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public ResponseEntity<String> hello(){
        return new ResponseEntity<>("hello!",HttpStatus.OK);
    }

}
