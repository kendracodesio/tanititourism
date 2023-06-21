package com.kendrareynolds.tanititourism.security;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequest {

    String firstName;
    String lastName;
    String username;
    String password;

}
