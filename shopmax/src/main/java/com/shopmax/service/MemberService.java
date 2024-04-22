package com.shopmax.service;

import com.shopmax.entity.Member;
import com.shopmax.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional //하나의 메소드가 트랜잭션으로 묶인다(DB Exception 혹은 다른 Exception 발생시 롤백)
@RequiredArgsConstructor
public class MemberService {
    private  final MemberRepository memberRepository;
    //회원가입
    public Member saveMember(Member member){
        validateDuplicateMember(member);
        return memberRepository.save(member); //회원정보 insert

    }
    //회원 중복체크
    private void validateDuplicateMember(Member member){
        Member findMember = memberRepository.findByEmail(member.getEmail());

        if(findMember != null){
            throw new IllegalStateException("이미 가입된 회원입니다.");
        }
    }
}
