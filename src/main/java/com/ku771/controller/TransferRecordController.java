package com.ku771.controller;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.ku771.pojo.Member;
import com.ku771.service.TransferRecordService;
import com.ku771.util.CorsAccessControl;

/**   
 * @title：转账记录控制中心
 * @Class TransferRecordController   
 * @description：  账户转入转出实际操作，加载转账记录等
 * @author：Eric
 * @date：2018年12月11日
*/
@Controller
@RequestMapping("/record/")
public class TransferRecordController {

	@Autowired
	private TransferRecordService transferRecordService;
	
	/**   
	 * @title：获取转账记录
	 * @description 添加分页查询
	 * @author：Eric
	 * @date：2018年12月11日
	 * @return: ServerResponse
	*/
	//@RequestMapping(value = "check_member_num.do",method = RequestMethod.POST)
	@RequestMapping(value = "record_list.do")
    @ResponseBody
	public JSONObject getRecordList(String startTurnDate,String endTurnDate,Integer turnInId,HttpSession session, HttpServletResponse response,
    		@RequestParam(value = "pageNum",defaultValue = "1")int pageNum,
            @RequestParam(value = "pageSize",defaultValue = "10")int pageSize){  
		//解决跨域性问题
		CorsAccessControl.setHearder(response);
		
		//获取session中的会员信息
		String token = (String) session.getAttribute("SESSION_KEY");
   		Member currentUser = (Member)session.getAttribute(token);
   		
        if(currentUser == null){
        	return null;
        }
		//获取账户信息
        return transferRecordService.getAccountInfo(startTurnDate,endTurnDate,turnInId,currentUser.getMemberId(),pageNum,pageSize);
    }
}
