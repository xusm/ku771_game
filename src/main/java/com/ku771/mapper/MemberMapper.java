package com.ku771.mapper;

import org.apache.ibatis.annotations.Param;

import com.ku771.pojo.Account;
import com.ku771.pojo.Member;
import com.ku771.pojo.example.MemberLoadExample;

public interface MemberMapper {

	int checkMemberNum(@Param("memberNum")String memberNum);

	int register(Member member);

	Member login(@Param("memberNum")String memberNum, @Param("password")String password);

	int updateMember(Member member);
	
	int checkDrawingPw(@Param("memberId")Integer memberId, @Param("drawingPassword")String drawingPassword);

	int checkUpdatePassword(@Param("memberId")Integer memberId, @Param("password")String password);

	int checkUpdateDrawingPassword(@Param("memberId")Integer memberId, @Param("drawingPassword")String drawingPassword);

	int checkNewPasswordWhetherEqualsOldDrawingPassword(@Param("memberId")Integer memberId, @Param("password")String password);

	MemberLoadExample getMemberInfo(@Param("memberId")Integer memberId);
	
	int checkNewDrawingPasswordWhetherEqualsOldPassword(@Param("memberId")Integer memberId, @Param("drawingPassword")String drawingPassword);

	int updateEmail(@Param("memberId")Integer memberId, @Param("email")String email);

	void addMainAccount(Account mainAccount);

	int getDrawingFrequency(@Param("memberId")Integer memberId);

	int resetDrwingFrequency();

}
