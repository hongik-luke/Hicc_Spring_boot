package com.luke.SpringStudy.controller;

import com.luke.SpringStudy.repository.MemberRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MemberController {

    private final MemberRepository memberRepository;

    public MemberController(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @GetMapping("/members")
    public String getMembers(Model model) {
        model.addAttribute("members", memberRepository.findAll());
        return "members";
    }
}