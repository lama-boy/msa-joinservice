package msa.prj.userservice.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UserDto {
    private String user_id;
    private String user_pw;
    private String user_name;
    private String interest;
    private String role;
}
