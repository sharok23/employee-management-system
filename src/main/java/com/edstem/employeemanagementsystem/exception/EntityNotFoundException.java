package com.edstem.employeemanagementsystem.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class EntityNotFoundException extends RuntimeException{
    private String firstEntity;
    private String secondEntity;
    private Long id;

    public EntityNotFoundException(String firstEntity,Long id){
        super(firstEntity +" not found with id " + id);
        this.firstEntity = firstEntity;
        this.id=id;
    }
    public EntityNotFoundException(String firstEntity, String secondEntity){
        super(firstEntity +" not found with department "+secondEntity);
        this.firstEntity= firstEntity;
        this.secondEntity = secondEntity;
    }
}
