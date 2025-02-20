package com.vijay.busseatbooking.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.vijay.busseatbooking.enums.UserRole;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "mobile"),
                @UniqueConstraint(columnNames = "userName")
        }
)
public class User extends BaseModel {

    @NotEmpty(message = "First Name can't be empty")
    @NotNull(message = "First Name can't be null")
    @NotBlank(message = "First Name can't be blank")
    private String firstName;

    private String lastName;

    @NotEmpty(message = "Mobile Number can't be empty")
    @NotNull(message = "Mobile Number can't be null")
    @NotBlank(message = "Mobile Number can't be blank")
    @Column(nullable = false, unique = true)
    @Size(min = 10, max = 10, message = "Mobile number must be exactly 10 digits")
    private String mobile;

    @NotEmpty(message = "User Name can't be empty")
    @NotNull(message = "User Name can't be null")
    @NotBlank(message = "User Name can't be blank")
    @Column(nullable = false, unique = true)
    private String userName;


    @NotEmpty(message = "Password can't be empty")
    @NotNull(message = "Password can't be null")
    @NotBlank(message = "Password can't be blank")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    @Enumerated(value = EnumType.ORDINAL)
    private UserRole role;

}
