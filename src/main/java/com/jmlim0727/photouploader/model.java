package com.jmlim0727.photouploader;

import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class model {
    @Size(max = 20, message = "Name must be less than 20 characters")
    private String guest;
    @Size(max = 20, message = "Name must be less than 20 characters")
    private String relation;
    @Size(max = 10, message = "Name must be less than 10 characters")
    private String name;
}
