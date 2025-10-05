package com.example.Inventory_Management_App.exception;

public class BadRequestException extends RuntimeException{
    public BadRequestException(String message) { super(message); }
}
