package com.example.movies.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OwnerDto {
    private Long id;
    @NotEmpty
    private String username;
    @NotEmpty(message = "Поле не может быть пустым")
    @Email
    private String email;
    @NotEmpty(message = "Поле не может быть пустым")
    private String password;
}
