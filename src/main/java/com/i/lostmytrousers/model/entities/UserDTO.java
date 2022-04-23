package com.i.lostmytrousers.model.entities;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserDTO
{
    private String username;
    private String role;
}