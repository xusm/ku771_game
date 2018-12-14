package com.ku771.service.impl;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ku771.mapper.AccountMapper;
import com.ku771.mapper.IntelligenceLineMapper;
import com.ku771.pojo.Account;
import com.ku771.pojo.Member;
import com.ku771.pojo.ThirdPartyInsert;
import com.ku771.pojo.example.AccountExample;
import com.ku771.service.IntelligenceLineService;

@Service("intelligenceLineService")
public class IntelligenceLineServiceImpl implements IntelligenceLineService {
	@Autowired
	private IntelligenceLineMapper intelligenceLineMapper;
	
	@Autowired
	private AccountMapper accountMapper;
	
	//智能线路切换
	//@Transactional(propagation = Propagation.REQUIRED,readOnly = false)
	public void switchLine(Member currentUser, Integer sonId, HttpServletResponse response) throws IOException {
		if(currentUser == null){
			response.sendRedirect("http://localhost:8080/inserFail.html?msg="+URLEncoder.encode("会员未登录", "utf-8"));
		}else{
			//接入前获取账户信息
			AccountExample account = accountMapper.getTurnOutOrInAccount(sonId,currentUser.getMemberId());
			
			//将数据接入到第三方平台表中--添加接入--修改接入状态
			int updateFlag = intelligenceLineMapper.updateInsert(new ThirdPartyInsert(null,currentUser.getMemberId(),sonId,1));
			
			//封装传递参数
		    String memberInfo = "{\"memberName\":\"" + URLEncoder.encode(currentUser.getNickName(), "utf-8") + "\",\"memeberId\":\"" + URLEncoder.encode(currentUser.getMemberId().toString(), "utf-8") + "\",\"sonId\":\"" + URLEncoder.encode(account.getSonId().toString(), "utf-8") + "\",\"sonName\":\"" + URLEncoder.encode(account.getSonName(), "utf-8") + "\",\"balance\":\"" + URLEncoder.encode(String.valueOf(account.getBalance()), "utf-8") + "\"}";
			if(updateFlag > 0){
				//开始接入--跳转
				//return "redirect:"+account.getLineAddr()+"?balance="+account.getBalance();
				try {
					response.sendRedirect("http://localhost:8080/inserSuccess.html?memberInfo="+memberInfo);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					response.sendRedirect("http://localhost:8080/inserFail.html");
				}
			}
		}
	}

	//修改账户余额，修改接入状态
	@Transactional(propagation = Propagation.REQUIRED,readOnly = false)
	public void callBackFunction(Integer memberId, Integer sonId, double balance) {
		//修改账户余额
		Account account = new Account();
		account.setSonId(sonId);
		account.setMemberId(memberId);
		account.setBalance(balance);
		accountMapper.updateAccountBySonIdAndMemberId(account);
		
		//修改接入状态
		intelligenceLineMapper.updateInsert(new ThirdPartyInsert(null,memberId,sonId,0));
	}
}
