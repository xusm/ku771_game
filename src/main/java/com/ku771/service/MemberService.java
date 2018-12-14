package com.ku771.service;


import com.ku771.common.ServerResponse;
import com.ku771.pojo.Member;

public interface MemberService {

	ServerResponse<?> checkMemberNum(String memberNum);

	ServerResponse<?> register(Member member);

	ServerResponse<Member> login(String memberNum, String password);

	ServerResponse<?> addDrawingPassword(Member member);
	
	ServerResponse<?> checkDrawingPw(Integer memberId, String drawingPassword);

	ServerResponse<?> getMemberInfo(Integer memberId);
	
	ServerResponse<?> updateMember(Member member);

	ServerResponse<?> checkEmail(Integer memberId, String email) throws Exception;

	ServerResponse<?> updateEmail(Integer memberId, String email);
}
