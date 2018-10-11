package com.gy.girl.controller;

import com.gy.girl.properties.GirlProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

@RestController
public class HelloController {

    @Value("${girl.content}")
    String size;

    @Autowired
    private GirlProperties girlProperties;

    @RequestMapping(value ={"/hello","/hi"},method = RequestMethod.GET)
    public String sayHello(){
        return girlProperties.getContent();
    }

    @RequestMapping(value = "/say/{id}",method = RequestMethod.GET)
    public String saySomething(@PathVariable("id") Integer id) {
        return "id:" + id;
    }

   // @RequestMapping(value = "/say",method = RequestMethod.GET)
    @GetMapping(value = "/say")
    public String saySomething2(@RequestParam(value = "id",required = false,defaultValue = "0") Integer id) {
        return "id2:" + id;
    }

}
