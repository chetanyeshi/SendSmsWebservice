package com.sending.sendsmsdemo.controller;

import com.sending.sendsmsdemo.Domain.User;
import com.sending.sendsmsdemo.Repository.SendSmsRepo;
import com.sending.sendsmsdemo.Util.SendSMS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping(value = "/api")
public class SendSMSController {

    @Autowired
    SendSmsRepo sendSmsRepo;
    @Autowired
    SendSMS sendSMS;

    @PostMapping(value = "/sendOtp")
    public ResponseEntity<?> sendOtp(@RequestBody User user){

        java.util.Random generator = new java.util.Random();
        generator.setSeed(System.currentTimeMillis());
        int i = generator.nextInt(1000000) % 1000000;

        java.text.DecimalFormat f = new java.text.DecimalFormat("000000");
      //  System.out.println(f.format(i));
        //user.setData("");
        Map<String,Object> map=new HashMap<>();
        ResponseEntity<Map<String,Object>> entity =null;

        String otpAndData=user.getData()+" Your OTP "+f.format(i);
        user.setData(otpAndData);
      //  System.out.println("***********---"+otpAndData);
        User response=sendSmsRepo.save(user);
        map.put("status","Success");
        map.put("response",response);
        map.put("result",sendSMS.sendSms(user.getMobileNumber(),otpAndData));
        entity=new ResponseEntity(map, HttpStatus.OK);

        return entity;
    }


}
