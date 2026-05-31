package kr.stockwaifu.service;

import kr.stockwaifu.dto.member.MemberReqDTO;
import kr.stockwaifu.dto.member.MemberResDTO;

public interface MemberCommandService {
    MemberResDTO.JoinDTO signup(MemberReqDTO.JoinDTO dto);
}