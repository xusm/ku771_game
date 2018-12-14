package com.ku771.service;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import com.ku771.pojo.Member;


public interface IntelligenceLineService {

	void switchLine(Member currentUser, Integer sonId, HttpServletResponse response) throws IOException;

	void callBackFunction(Integer memberId, Integer sonId, double balance);

}
