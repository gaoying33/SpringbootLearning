package com.gy.girl.controller;

import com.gy.girl.domain.Result;
import com.gy.girl.enums.ResultEnum;
import com.gy.girl.repository.GirlRepository;
import com.gy.girl.service.GirlService;
import com.gy.girl.domain.Girl;
import com.gy.girl.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class GirlController {

    @Autowired
    private GirlRepository girlRepository;

    @Autowired
    private GirlService girlService;

    @GetMapping(value = "/girls")
    public List<Girl> girlList(){
       return girlRepository.findAll();
    }

    @PostMapping(value = "/girls")
    public Result<Girl> girlAdd(@Valid Girl girl, BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            return ResultUtil.error(1,bindingResult.getFieldError().getDefaultMessage());
        }
       girl.setAge(girl.getAge());
       girl.setSize(girl.getSize());
       return ResultUtil.success(girlRepository.save(girl));
    }

    @GetMapping(value = "/girls/{id}")
    public Girl girlFindOne(@PathVariable("id") Integer id){
         return girlRepository.findById(id).get();
    }

    @PutMapping(value = "/girls/{id}")
    public Girl girlUpdate(@PathVariable("id") Integer id,
                           @RequestParam("size") String size,
                           @RequestParam("age") Integer age){
        Girl girl = new Girl();
        girl.setSize(size);
        girl.setAge(age);
        girl.setId(id);
        return girlRepository.save(girl);
    }

    @DeleteMapping(value = "/girls/{id}")
    public void girlDelete(@PathVariable("id") Integer id) {
        girlRepository.deleteById(id);
    }

    @GetMapping(value = "/girls/age/{age}")
    public List<Girl> girlListByAge(@PathVariable("age") Integer age){
        System.out.print("调用");
        return girlRepository.findByAge(age);
    }

    @PostMapping(value = "/girls/two")
    public void girlTwo(){
        girlService.insertTwo();
    }

    @GetMapping(value ="girls/getAge/{id}")
    public void getAge(@PathVariable("id") Integer id) throws Exception{
       girlService.getAge(id);
    }
}
