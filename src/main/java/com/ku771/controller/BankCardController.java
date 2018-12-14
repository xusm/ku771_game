package com.ku771.controller;

import java.math.BigInteger;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ku771.common.ServerResponse;
import com.ku771.pojo.BankCard;
import com.ku771.pojo.Member;
import com.ku771.service.BankCardService;
import com.ku771.util.CorsAccessControl;

/**   
 * @title：银行卡控制中心
 * @Class BankCardController   
 * @description：  银行卡操作
 * @author：Eric
 * @date：2018年12月7日
*/
@Controller
@RequestMapping("/bank/")
public class BankCardController {

	@Autowired
	private BankCardService bankCardService;
	
	/**   
	 * @title：新增银行卡
	 * @description 新增前前端需验证账号名称及账号是否匹配
	 * @author：Eric
	 * @date：2018年12月7日
	 * @param： BankCard
	 * @return: ServerResponse
	*/
	//@RequestMapping(value = "check_member_num.do",method = RequestMethod.POST)
	@RequestMapping(value = "add_bank_card.do")
    @ResponseBody
	public ServerResponse<?> addBankCard(BankCard bankCard,HttpServletResponse response, HttpSession session){
		//解决跨域性问题
		CorsAccessControl.setHearder(response);
		
		//获取session中的会员信息
		String token = (String) session.getAttribute("SESSION_KEY");
   		Member currentUser = (Member)session.getAttribute(token);
   		
        if(currentUser == null){
        	return ServerResponse.createByErrorMessage("会员未登录");
        }
        
        //设置银行卡对应的会员ID
        bankCard.setMemberId(currentUser.getMemberId());
		//调用银行卡新增功能
        return bankCardService.addBankCard(bankCard);
    }
	
	/**   
	 * @title：获取当前会员银行卡信息
	 * @author：Eric
	 * @date：2018年12月7日
	 * @return: ServerResponse
	*/
	//@RequestMapping(value = "check_member_num.do",method = RequestMethod.POST)
	@RequestMapping(value = "get_bank_card_info_by_member_id.do")
    @ResponseBody
	public ServerResponse<?> getBankCardInfoByMenumberId(HttpServletResponse response, HttpSession session){
		//解决跨域性问题
		CorsAccessControl.setHearder(response);
		
		//获取session中的会员信息
		String token = (String) session.getAttribute("SESSION_KEY");
   		Member currentUser = (Member)session.getAttribute(token);
   		
        if(currentUser == null){
        	return ServerResponse.createByErrorMessage("会员未登录");
        }
        
		//调用获取当前会员银行卡信息功能
        return bankCardService.getBankCardInfoByMenumberId(currentUser.getMemberId());
    }
	
	/**   
	 * @title：银行卡新增前检验该账号是否重复
	 * @author：Eric
	 * @date：2018年12月7日
	 * @param: backCardNum
	 * @return: ServerResponse
	*/
	@RequestMapping(value = "check_card_number.do")
    @ResponseBody
	public ServerResponse<?> checkCardNumber(BigInteger bankCardNum,HttpServletResponse response, HttpSession session){
		//解决跨域性问题
		CorsAccessControl.setHearder(response);
		
		//获取session中的会员信息
		String token = (String) session.getAttribute("SESSION_KEY");
   		Member currentUser = (Member)session.getAttribute(token);
   		
        if(currentUser == null){
        	return ServerResponse.createByErrorMessage("会员未登录");
        }
        
		//开始检测账户
        return bankCardService.checkCardNumber(bankCardNum);
    }
}
