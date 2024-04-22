package com.board.entity;

import com.board.constant.Role;
import com.board.dto.MemberFormDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.crypto.password.PasswordEncoder;

@Entity
@Table(name = "member")
@Getter
@Setter
@ToString
public class Member {
    @Id
    @Column(name = "member_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;

    private String name;

    private String password;
    private String address;

    @Enumerated(EnumType.STRING)
    private Role role;

    public static  Member createMember(MemberFormDto memberFormDto, PasswordEncoder passwordEncoder){
        String password = passwordEncoder.encode(memberFormDto.getPassword());
        Member member =new Member();

        member.setName(memberFormDto.getName());
        member.setEmail(memberFormDto.getEmail());
        member.setAddress(memberFormDto.getAddress());
        member.setPassword(password);

        member.setRole(Role.USER);

        return member;
    }
}
