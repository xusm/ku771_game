package com.ku771.controller;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ku771.common.ServerResponse;
import com.ku771.pojo.Member;
import com.ku771.service.AccountService;
import com.ku771.util.CorsAccessControl;

/**
 * @title：账户控制中心
 * @Class AccountController
 * @description： 账户转入转出，智能线路跳转等操作
 * @author：Eric
 * @date：2018年12月9日
 */
@Controller
@RequestMapping("/account/")
public class AccountController {

	@Autowired
	private AccountService accountService;

	/**
	 * @title：获取各账户信息
	 * @description 包括账户名称、余额、状态等信息
	 * @author：Eric
	 * @date：2018年12月9日
	 * @return: ServerResponse
	 */
	// @RequestMapping(value = "check_member_num.do",method =
	// RequestMethod.POST)
	@RequestMapping(value = "get_account_info.do")
	@ResponseBody
	public ServerResponse<?> getAccountInfo(HttpServletResponse response,
			HttpSession session) {
		// 解决跨域性问题
		CorsAccessControl.setHearder(response);

		// 获取session中的会员信息
		String token = (String) session.getAttribute("SESSION_KEY");
		Member currentUser = (Member) session.getAttribute(token);

		if (currentUser == null) {
			return ServerResponse.createByErrorMessage("会员未登录");
		}
		// 获取账户信息
		return accountService.getAccountInfo(currentUser.getMemberId());
	}

	/**
	 * @title：获取各账户总金额
	 * @author：Eric
	 * @date：2018年12月9日
	 * @return: ServerResponse
	 */
	@RequestMapping(value = "get_account_total_money.do")
	@ResponseBody
	public ServerResponse<?> getAccountTotalMoney(HttpServletResponse response,
			HttpSession session) {
		// 解决跨域性问题
		CorsAccessControl.setHearder(response);

		// 获取session中的会员信息
		String token = (String) session.getAttribute("SESSION_KEY");
		Member currentUser = (Member) session.getAttribute(token);

		if (currentUser == null) {
			return ServerResponse.createByErrorMessage("会员未登录");
		}
		// 获取账户信息
		return accountService.getAccountTotalMoney(currentUser.getMemberId());
	}

	/**
	 * @title：平台转账
	 * @author：Eric
	 * @date：2018年12月10日
	 * @param： 
	 *         turnOutId(转出账户ID--对应sonId)，turnInId(转入账户ID--对应sonId)，turnMoney(转入金额
	 *         )
	 * @return: ServerResponse
	 */
	@RequestMapping(value = "transfer_accounts.do")
	@ResponseBody
	public ServerResponse<?> transferAccounts(Integer turnOutId,
			Integer turnInId, double turnMoney, HttpServletResponse response,
			HttpSession session) {
		// 解决跨域性问题
		CorsAccessControl.setHearder(response);

		// 获取session中的会员信息
		String token = (String) session.getAttribute("SESSION_KEY");
		Member currentUser = (Member) session.getAttribute(token);

		if (currentUser == null) {
			return ServerResponse.createByErrorMessage("会员未登录");
		}
		// 开始转账操作
		return accountService.transferAccounts(currentUser.getMemberId(),
				turnOutId, turnInId, turnMoney);
	}

	/**
	 * @title：额度全部转回主账户
	 * @author：Eric
	 * @date：2018年12月11日
	 * @return: ServerResponse
	 */
	@RequestMapping(value = "accounts_return.do")
	@ResponseBody
	public ServerResponse<?> accountsReturn(HttpServletResponse response,
			HttpSession session) {
		// 解决跨域性问题
		CorsAccessControl.setHearder(response);

		// 获取session中的会员信息
		String token = (String) session.getAttribute("SESSION_KEY");
		Member currentUser = (Member) session.getAttribute(token);

		if (currentUser == null) {
			return ServerResponse.createByErrorMessage("会员未登录");
		}
		// 开始操作（额度全部转回主账户）
		return accountService.accountsReturn(currentUser.getMemberId());
	}

	/**
	 * @title：加载所有账户（用于加载下拉框）
	 * @author：Eric
	 * @date：2018年12月11日
	 * @return: ServerResponse
	 */
	@RequestMapping(value = "load_all_account.do")
	@ResponseBody
	public ServerResponse<?> loadAllAccount(HttpServletResponse response,
			HttpSession session) {
		// 解决跨域性问题
		CorsAccessControl.setHearder(response);

		// 获取session中的会员信息
		String token = (String) session.getAttribute("SESSION_KEY");
		Member currentUser = (Member) session.getAttribute(token);

		if (currentUser == null) {
			return ServerResponse.createByErrorMessage("会员未登录");
		}
		// 加载所有账户
		return accountService.loadAllAccount();
	}

}
