package com.ku771.controller;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.ku771.common.ServerResponse;
import com.ku771.pojo.Member;
import com.ku771.pojo.TradingCenter;
import com.ku771.service.TradingCenterService;
import com.ku771.util.CorsAccessControl;

/**
 * @title：交易控制中心
 * @Class AccountController
 * @description：加载交易列表，按条件查询，修改交易状态等
 * @author：Eric
 * @date：2018年12月12日
 */
@Controller
@RequestMapping("/tradingcenter/")
public class TradingCenterController {

	@Autowired
	private TradingCenterService tradingCenteService;
	
	/**   
	 * @title：获取交易记录
	 * @description 添加分页查询
	 * @author：Eric
	 * @date：2018年12月12日
	 * @return: ServerResponse
	*/
	//@RequestMapping(value = "check_member_num.do",method = RequestMethod.POST)
	@RequestMapping(value = "trading_list.do")
    @ResponseBody
	public JSONObject getTradingRecordList(Integer tradingType,Integer tradingStatus,HttpSession session, HttpServletResponse response,
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
		//获取交易记录信息
        return tradingCenteService.getTradingRecordList(tradingType,tradingStatus,currentUser.getMemberId(),pageNum,pageSize);
    }
	
	/**
	 * @title：修改交易记录信息(回调函数)
	 * @author：Eric
	 * @date：2018年12月12日
	 * @param：TradingCenter
	 * @return: ServerResponse
	 */
	@RequestMapping(value = "update_trading_record.do")
	@ResponseBody
	public ServerResponse<?> updateTradingRecord(HttpServletResponse response,
			HttpSession session) {
		// 解决跨域性问题
		CorsAccessControl.setHearder(response);

		// 获取session中的会员信息
		String token = (String) session.getAttribute("SESSION_KEY");
		Member currentUser = (Member) session.getAttribute(token);
		//获取刚新增的交易信息
		TradingCenter tc = (TradingCenter)session.getAttribute("TRADING_RECORD");
		if (currentUser == null) {
			return ServerResponse.createByErrorMessage("会员未登录");
		}
		// 修改交易记录
		return tradingCenteService.updateTradingRecord(tc);
	}
	
	/**
	 * @title：获取提款次数
	 * @author：Eric
	 * @date：2018年12月12日
	 * @return: ServerResponse
	 */
	@RequestMapping(value = "get_drawing_frequency.do")
	@ResponseBody
	public ServerResponse<?> getDrawingFrequency(HttpServletResponse response,HttpSession session) {
		// 解决跨域性问题
		CorsAccessControl.setHearder(response);

		// 获取session中的会员信息
		String token = (String) session.getAttribute("SESSION_KEY");
		Member currentUser = (Member) session.getAttribute(token);
		if (currentUser == null) {
			return ServerResponse.createByErrorMessage("会员未登录");
		}
		// 获取提款次数
		return tradingCenteService.getDrawingFrequency(currentUser.getMemberId());
	}
	
	/**
	 * @title：提款操作
	 * @author：Eric
	 * @date：2018年12月12日
	 * @param：TradingCenter
	 * @return: ServerResponse
	 */
	@RequestMapping(value = "add_drawing_record.do")
	@ResponseBody
	public ServerResponse<?> addDrawingRecord(TradingCenter tradingCenter,HttpServletResponse response,HttpSession session) {
		// 解决跨域性问题
		CorsAccessControl.setHearder(response);

		// 获取session中的会员信息
		String token = (String) session.getAttribute("SESSION_KEY");
		Member currentUser = (Member) session.getAttribute(token);
		if (currentUser == null) {
			return ServerResponse.createByErrorMessage("会员未登录");
		}
		// 新增提款记录
		tradingCenter.setMemberId(currentUser.getMemberId());
		return tradingCenteService.addDrawingRecord(tradingCenter);
	}
}
