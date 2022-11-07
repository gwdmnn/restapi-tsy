package com.aulas.restAPI.services.exceptions;

public class InativeCategoryException extends RuntimeException{
    public InativeCategoryException(String msg){
        super(msg);
    }
}

