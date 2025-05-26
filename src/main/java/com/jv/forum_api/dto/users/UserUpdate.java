package com.jv.forum_api.dto.users;

import jakarta.validation.constraints.Email;
import lombok.Data;
import org.hibernate.validator.constraints.URL;

@Data
public class UserUpdate {

    private String username;

    @Email(message = "Email is not valid")
    private String email;

    @URL(message = "Picture not valid")
    private String picture;

    private String about;

}
