package com.example.records.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class RecordRequestDto {
    
    @NotBlank(message = "Name is required")
    @Size(max = 255, message = "Name must not exceed 255 characters")
    private String name;
    
    @NotBlank(message = "Message is required")
    private String message;
    
    private String note;
    
    public RecordRequestDto() {}
    
    public RecordRequestDto(String name, String message, String note) {
        this.name = name;
        this.message = message;
        this.note = note;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name != null ? name.trim() : null;
    }
    
    public String getMessage() {
        return message;
    }
    
    public void setMessage(String message) {
        this.message = message != null ? message.trim() : null;
    }
    
    public String getNote() {
        return note;
    }
    
    public void setNote(String note) {
        this.note = note;
    }
}