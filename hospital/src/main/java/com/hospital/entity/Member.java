package com.hospital.entity;

import com.hospital.constant.Role;
import com.hospital.dto.MemberFormDto;
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
public class Member extends BaseEntity{
    @Id
    @Column(name = "member_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; //pk값의 타입은 레퍼런스 타입 Long으로 지정

    private String name; //String 사이즈를 지정하지 않으면 -> varchar(255)

    @Column(unique = true) //유니크 제약조건(값이 중복되면 안되는 컬럼)
    private String email;

    private String password;

    private String address;

    @Enumerated(EnumType.STRING)
    private Role role;

    //MemberFormDto -> Member 엔티티 객체로 변환
    //JPA에서는 영속성 컨텍스트에 엔티티 객체를 통해 crud를 진행하므로 반드시
    // DTO객체를 엔티티 객체로
    //변경시켜줘야한다.
    public  static Member createMember(MemberFormDto memberFormDto, PasswordEncoder passwordEncoder){
        //패스워드 암호화
        String password = passwordEncoder.encode(memberFormDto.getPassword());

        Member member = new Member();

        //사용자가 입력한 회원가입 정보를 member엔티티로 변환

        member.setName(memberFormDto.getName());
        member.setEmail(memberFormDto.getEmail());
        member.setAddress(memberFormDto.getAddress());
        member.setPassword(password); //DB에는 최종적으로 암호화된 패스워드가 저장되도록 한다.

        //개발자가 지정해줘야 하는 정보
        //member.setRole(Role.USER); //일반 사용자로 가입할때
        member.setRole(Role.ADMIN); //관리자로 가입할때
        return member;
    }
}
