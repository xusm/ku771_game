package com.ku771.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ku771.pojo.Member;
import com.ku771.service.IntelligenceLineService;
import com.ku771.util.CorsAccessControl;

/**
 * @title：线路切换控制中心
 * @Class IntelligenceLineController
 * @description：线路切换、回调控制
 * @author：Eric
 * @date：2018年12月14日
 */
@Controller
@RequestMapping("/intelligenceline/")
public class IntelligenceLineController {

	@Autowired
	private IntelligenceLineService intelligenceLineService;

	/**
	 * @title：智能线路切换
	 * @description 切换线路，并向第三方平台传递账户信息
	 * @author：Eric
	 * @date：2018年12月14日
	 * @param: sonId(子账户ID，可对照数据库son_account进行传递，或在切换前先调用/account/get_account_info.do接口获取账户信息)
	 * @return: ServerResponse
	 */
	// @RequestMapping(value = "check_member_num.do",method =RequestMethod.POST)
	@RequestMapping(value = "switch_line.do")
	@ResponseBody
	public void switchLine(Integer sonId,HttpServletResponse response,HttpSession session)  throws IOException{
		// 解决跨域性问题
		CorsAccessControl.setHearder(response);

		// 获取session中的会员信息
		String token = (String) session.getAttribute("SESSION_KEY");
		Member currentUser = (Member) session.getAttribute(token);
		// 获取账户信息
		intelligenceLineService.switchLine(currentUser,sonId,response);
	}
	
	/**
	 * @title：第三方平台回调----第三方平台关闭后需要回调该接口
	 * @description 修改账户余额，修改接入状态
	 * @author：Eric
	 * @date：2018年12月14日
	 */
	@RequestMapping(value = "call_back_function.do")
	@ResponseBody
	public void callBackFunction(Integer memberId,Integer sonId,double balance,HttpServletResponse response,HttpSession session)  throws IOException{
		// 解决跨域性问题
		CorsAccessControl.setHearder(response);
		// 获取账户信息
		intelligenceLineService.callBackFunction(memberId,sonId,balance);
	}
}
