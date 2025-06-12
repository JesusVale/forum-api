package com.jv.forum_api.dto.users;

import jakarta.validation.constraints.Email;
import org.hibernate.validator.constraints.URL;

public record UserUpdate(
        String username,
        @Email(message = "Email is not valid")
        String email,
        @URL(message = "Picture not valid")
        String picture,
        String about
) {}
