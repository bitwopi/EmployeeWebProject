package com.example.EmployeeProject.service.exceptions;

public class EmployeeStorageIsFullException extends RuntimeException{
    public EmployeeStorageIsFullException(String message){
        super(message);
    }
}
