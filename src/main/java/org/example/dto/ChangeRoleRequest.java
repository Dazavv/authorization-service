package org.example.dto;

import lombok.Getter;
import lombok.Setter;
import org.example.model.Role;

@Getter
@Setter
public class ChangeRoleRequest {
    private String login;
    private Role role;
}
