package com.gy.girl.service;

import com.gy.girl.enums.ResultEnum;
import com.gy.girl.exception.GirlException;
import com.gy.girl.repository.GirlRepository;
import com.gy.girl.domain.Girl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;

@Service
public class GirlService {

    @Autowired
    private GirlRepository girlRepository;


    @Transactional
    public void insertTwo(){
        Girl girlA = new Girl();
        girlA.setSize("sd");
        girlA.setAge(21);
        girlRepository.save(girlA);

        Girl girlB = new Girl();
        girlB.setAge(12);
        girlB.setSize("DD");
        girlRepository.save(girlB);
    }

    public void getAge(Integer id) throws Exception{
        Girl girl = girlRepository.getOne(id);
        Integer age = girl.getAge();
        if(age<10){
            throw new GirlException(ResultEnum.PRIMARY_SCHOOL);
        }else if(age > 10 && age < 16){
            throw new GirlException(ResultEnum.MIDDLE_SCHOOL);
        }
        //后面处理
    }

    public Girl findOne(Integer id){
        return  girlRepository.getOne(id);
    }
}
