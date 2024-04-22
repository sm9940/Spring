package com.board.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
public class MemberFormDto {
    @NotBlank(message = "이름을 입력해주세요")
    private String name;
    @Email(message = "이메일 형식을 맞춰주세요")
    @NotEmpty(message = "이메일을 입력해주세요")
    private String email;
    @NotEmpty(message = "비밀번호를 입력해주세요")
    @Length(min=8,max=16,message = "비밀번호는 8~16자 사이로 입력해주세요")
    private String password;
    @NotEmpty(message = "주소를 입력해주세요")
    private String address;
}
