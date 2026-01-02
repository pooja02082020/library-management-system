package com.example.lbms.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.lbms.model.Member;
import com.example.lbms.model.MemberStatus;
import com.example.lbms.repository.MemberRepository;

@Service
public class MemberService {

    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public Member registerMember(Member member) {
        member.setStatus(MemberStatus.ACTIVE);
        member.setJoinedDate(LocalDate.now());
        return memberRepository.save(member);
    }

    public Member getMemberById(Long id) {
        return memberRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Member not found"));
    }

    public List<Member> getAllMembers() {
        return memberRepository.findAll();
    }

    public Member blockMember(Long id) {
        Member member = getMemberById(id);
        member.setStatus(MemberStatus.BLOCKED);
        return memberRepository.save(member);
    }
}
