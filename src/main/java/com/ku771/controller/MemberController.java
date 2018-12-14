package com.ku771.controller;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ku771.common.Const;
import com.ku771.common.ServerResponse;
import com.ku771.pojo.Member;
import com.ku771.service.MemberService;
import com.ku771.util.CorsAccessControl;

/**   
 * @title：会员控制中心
 * @class：MemberController   
 * @description：  注册、登录、加载用户信息等操作
 * @author：Eric
 * @date：2018年12月6日
*/
@Controller
@RequestMapping("/member/")
public class MemberController {

	@Autowired
	private MemberService memberService;
	
	/**   
	 * @title：检测会员账号是否可注册
	 * @author：Eric
	 * @date：2018年12月6日
	 * @param： memberNum
	 * @return: ServerResponse
	*/
	//@RequestMapping(value = "check_member_num.do",method = RequestMethod.POST)
	@RequestMapping(value = "check_member_num.do")
    @ResponseBody
	public ServerResponse<?> checkMemberNum(String memberNum,HttpServletResponse response, HttpSession session){
		//解决跨域性问题
		CorsAccessControl.setHearder(response);
		
		//调用检测会员账号是否存在功能
        return memberService.checkMemberNum(memberNum);
    }
	
	/**   
	 * @title：发送手机验证码
	 * @author：Eric
	 * @date：2018年12月6日
	 * @param： memberNum
	 * @return: ServerResponse
	*//*
	@RequestMapping(value = "check_member_num.do",method = RequestMethod.POST)
    @ResponseBody
	public ServerResponse checkMemberNum(String memberNum,HttpServletResponse response, HttpSession session){
		//解决跨域性问题
		CorsAccessControl.setHearder(response);
		
		//调用检测会员账号是否存在功能
        return memberService.checkMemberNum(memberNum);
    }*/
	
	/**   
	 * @title：会员注册
	 * @author：Eric
	 * @date：2018年12月6日
	 * @param： memberNum,nickName,distributorNum,password,phoneNum
	 * @return: ServerResponse
	*/
	@RequestMapping(value = "register.do")
    @ResponseBody
	public ServerResponse<?> register(Member member,HttpServletResponse response, HttpSession session){
		//解决跨域性问题
		CorsAccessControl.setHearder(response);
		
		//调用注册功能
        return memberService.register(member);
    }
	
	/**   
	 * @title：会员登录
	 * @author：Eric
	 * @date：2018年12月7日
	 * @param： memberNum,password
	 * @return: ServerResponse
	*/
	@RequestMapping(value = "login.do")
    @ResponseBody
	public ServerResponse<?> login(String memberNum,String password,HttpServletResponse response, HttpSession session){
		//解决跨域性问题
		CorsAccessControl.setHearder(response);
		//调用登录功能
		ServerResponse<Member> responseData = memberService.login(memberNum,password);
		if(responseData.isSuccess()){
            // 保存会员信息保存到session中
        	session.setAttribute("SESSION_KEY",Const.CURRENT_USER);
            session.setAttribute(Const.CURRENT_USER,responseData.getData());
	    }
	    return responseData;
    }
	
	/**   
	 * @title：会员登录成功后加载会员信息
	 * @author：Eric
	 * @date：2018年12月7日
	 * @return: ServerResponse
	*/
	@RequestMapping(value = "load_member_info.do")
    @ResponseBody
	public ServerResponse<?> loadMemberInfo(HttpServletResponse response, HttpSession session){
		//解决跨域性问题
		CorsAccessControl.setHearder(response);
		//获取session中的会员信息
		String token = (String) session.getAttribute("SESSION_KEY");
   		Member currentUser = (Member)session.getAttribute(token);
   		
        if(currentUser == null){
        	return ServerResponse.createByErrorMessage("会员未登录");
        }
        return ServerResponse.createBySuccess("会员资料", currentUser);
    }
	
	/**   
	 * @title：新增提款密码
	 * @author：Eric
	 * @date：2018年12月7日
	 * @param: drawingPassword
	 * @return: ServerResponse
	*/
	@RequestMapping(value = "add_drawing_pw.do")
    @ResponseBody
	public ServerResponse<?> addDrawingPassword(String drawingPassword,HttpServletResponse response, HttpSession session){
		//解决跨域性问题
		CorsAccessControl.setHearder(response);
		
		//获取session中的会员信息
		String token = (String) session.getAttribute("SESSION_KEY");
   		Member currentUser = (Member)session.getAttribute(token);
   		
        if(currentUser == null){
        	return ServerResponse.createByErrorMessage("会员未登录");
        }
        //参数封装
        Member member = new Member();
        member.setMemberId(currentUser.getMemberId());
        member.setDrawingPassword(drawingPassword);
        
		//开始新增提款密码
        return memberService.addDrawingPassword(member);
    }
	
	/**   
	 * @title：验证提款密码是否正确
	 * @author：Eric
	 * @date：2018年12月7日
	 * @param: drawingPassword
	 * @return: ServerResponse
	*/
	@RequestMapping(value = "check_drawing_pw.do")
    @ResponseBody
	public ServerResponse<?> checkDrawingPw(String drawingPassword,HttpServletResponse response, HttpSession session){
		//解决跨域性问题
		CorsAccessControl.setHearder(response);
		
		//获取session中的会员信息
		String token = (String) session.getAttribute("SESSION_KEY");
   		Member currentUser = (Member)session.getAttribute(token);
   		
        if(currentUser == null){
        	return ServerResponse.createByErrorMessage("会员未登录");
        }
        
		//开始检测提款密码
        return memberService.checkDrawingPw(currentUser.getMemberId(),drawingPassword);
    }
	
	/**   
	 * @title：加载会员资料
	 * @author：Eric
	 * @date：2018年12月7日
	 * @return: ServerResponse<MemberLoadExample>
	*/
	@RequestMapping(value = "get_member_info.do")
    @ResponseBody
	public ServerResponse<?> getMemberInfo(HttpServletResponse response, HttpSession session){
		//解决跨域性问题
		CorsAccessControl.setHearder(response);
		
		//获取session中的会员信息
		String token = (String) session.getAttribute("SESSION_KEY");
   		Member currentUser = (Member)session.getAttribute(token);
   		
        if(currentUser == null){
        	return ServerResponse.createByErrorMessage("会员未登录");
        }
        
		//开始加载会员资料
        return memberService.getMemberInfo(currentUser.getMemberId());
    }
	
	/**   
	 * @title：变更会员资料
	 * @author：Eric
	 * @date：2018年12月8日
	 * @param: Member
	 * @return: ServerResponse
	*/
	@RequestMapping(value = "update_member.do")
    @ResponseBody
	public ServerResponse<?> updateMember(Member member,HttpServletResponse response, HttpSession session){
		//解决跨域性问题
		CorsAccessControl.setHearder(response);
		
		//获取session中的会员信息
		String token = (String) session.getAttribute("SESSION_KEY");
   		Member currentUser = (Member)session.getAttribute(token);
   		
        if(currentUser == null){
        	return ServerResponse.createByErrorMessage("会员未登录");
        }
        member.setMemberId(currentUser.getMemberId());
		//开始修改
        return memberService.updateMember(member);
    }
	
	/**   
	 * @title：邮箱验证并发送验证链接
	 * @author：Eric
	 * @date：2018年12月8日
	 * @param: email
	 * @return: ServerResponse
	*/
	@RequestMapping(value = "check_email.do")
    @ResponseBody
	public ServerResponse<?> checkEmail(String email,HttpServletResponse response, HttpSession session) throws Exception {
		//解决跨域性问题
		CorsAccessControl.setHearder(response);
		
		//获取session中的会员信息
		String token = (String) session.getAttribute("SESSION_KEY");
   		Member currentUser = (Member)session.getAttribute(token);
   		
        if(currentUser == null){
        	return ServerResponse.createByErrorMessage("会员未登录");
        }
		//开始检验
        return memberService.checkEmail(currentUser.getMemberId(),email);
    }
	
	/**   
	 * @title：邮箱验证验证后更新会员邮件
	 * @author：Eric
	 * @date：2018年12月8日
	 * @param: email
	 * @return: ServerResponse
	*/
	@RequestMapping(value = "update_email.do")
    @ResponseBody
	public ServerResponse<?> updateEmail(Integer memberId,String email,HttpServletResponse response, HttpSession session) throws Exception {
		//解决跨域性问题
		CorsAccessControl.setHearder(response);
		//开始修改
        return memberService.updateEmail(memberId,email);
    }
}
