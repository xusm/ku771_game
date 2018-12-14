package com.ku771.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.alibaba.fastjson.JSONObject;
import com.alipay.api.internal.util.AlipaySignature;
import com.google.gson.Gson;
import com.ku771.config.AlipayConfig;
import com.ku771.pojo.Alipay;
import com.ku771.pojo.Member;
import com.ku771.pojo.TradingCenter;
import com.ku771.service.TradingCenterService;
import com.ku771.util.CorsAccessControl;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.UUID;

/**   
 * @title：
 * @class：AliPayController   
 * @description：   
 * @author：Eric
 * @date：2018年12月12日
 * @param： 
 * @return:
*/

@Controller
@RequestMapping(value = "/alipay")
public class AlipayController {
	
	@Autowired 
	private TradingCenterService tradingCenteService;
	
	private static final Logger logger = LoggerFactory.getLogger(AlipayController.class);
    /**
     * 支付网站扫码支付接口-统一下单支付接口
     * @return
     * @throws AlipayApiException
     */
	@RequestMapping(value = "pay.do")
	@ResponseBody
    public String alipayPay(double tradingMoney,HttpServletResponse response,HttpSession session) throws AlipayApiException {
		// 解决跨域性问题
		CorsAccessControl.setHearder(response);

		// 获取session中的会员信息
		String token = (String) session.getAttribute("SESSION_KEY");
		Member currentUser = (Member) session.getAttribute(token);

		if (currentUser == null) {
			return "会员未登录";
		}
		//首先新增交易未完成的交易记录
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateNowStr = sdf.format(date);
		TradingCenter tc = new TradingCenter();
		tc.setMemberId(currentUser.getMemberId());
		tc.setTradingDate(dateNowStr);
		tc.setTradingType(0);
		tc.setTradingContent("在线支付宝");
		tc.setTradingMoney(tradingMoney);
		tc.setTradingStatus(0);
		int addFlag = tradingCenteService.addTradingRecord(tc);
		//保存新增的交易信息到session中
		session.setAttribute("TRADING_RECORD",tc);
		
		if(addFlag <= 0){
			return "参数错误(1，-500)";
		}
		
        //这个需要从前端端传过来，这里为了测试就从后台写死了
        Alipay vo = new Alipay();
        vo.setOut_trade_no(UUID.randomUUID().toString().replace("-", ""));
        vo.setTotal_amount(String.valueOf(tradingMoney));
        vo.setSubject("nelson-test-title");
        vo.setProduct_code("FAST_INSTANT_TRADE_PAY"); //这个是固定的
        String json = new Gson().toJson(vo);
 
        AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.gatewayUrl,AlipayConfig.app_id,AlipayConfig.merchant_private_key,AlipayConfig.param_type,AlipayConfig.charset,AlipayConfig.alipay_public_key,AlipayConfig.sign_type);
        // 设置请求参数
        AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
        alipayRequest.setReturnUrl(AlipayConfig.return_url);
        alipayRequest.setNotifyUrl(AlipayConfig.notify_url);
        alipayRequest.setBizContent(json);
        String result = alipayClient.pageExecute(alipayRequest).getBody();
        logger.info("result: {}", result);
        return result; //这里生成一个表单，会自动提交
    }
 
    /**
     * 支付宝服务器异步通知页面
     * @param request
     * @param out_trade_no 商户订单号
     * @param trade_no 支付宝交易凭证号
     * @param trade_status 交易状态
     * @return
     * @throws AlipayApiException
     */
    @RequestMapping(value = "notify.do")
	@ResponseBody
    public String alipayNotify(HttpServletRequest request, String out_trade_no, String trade_no, String trade_status) throws AlipayApiException {
        Map<String, String> params = getParamsMap(request);
        logger.info("notify params: {}", JSONObject.toJSON(params));
        // 验证签名
        boolean signVerified = AlipaySignature.rsaCheckV1(params, AlipayConfig.alipay_public_key, AlipayConfig.charset, AlipayConfig.sign_type);
        logger.info("notify signVerified: {}", signVerified);
        if (signVerified) {
            //处理你的业务逻辑，更细订单状态等
            return ("success");
        } else {
            logger.info("验证失败,不去更新状态");
            return ("fail");
        }
    }
 
    /**
     * 支付宝服务器同步通知页面
     * @param request
     * @param out_trade_no 商户订单号
     * @param trade_no 支付宝交易凭证号
     * @param total_amount 交易状态
     * @return
     * @throws AlipayApiException
     */
    @RequestMapping(value = "return.do")
	@ResponseBody
    public String alipayReturn(HttpServletRequest request, String out_trade_no,String trade_no,String total_amount) throws AlipayApiException {
        Map<String, String> params = getParamsMap(request);
        logger.info("return params: {}", JSONObject.toJSON(params));
 
        // 验证签名
        boolean signVerified = AlipaySignature.rsaCheckV1(params, AlipayConfig.alipay_public_key, AlipayConfig.charset, AlipayConfig.sign_type);
        logger.info("return signVerified: {}", signVerified);
 
        if (signVerified) {
            return ("success");
        } else {
            logger.info("验证失败,不去更新状态");
            return ("fail");
        }
    }
 
    @SuppressWarnings("rawtypes")
	private Map<String, String> getParamsMap(HttpServletRequest request) {
        Map<String,String> params = new HashMap<>();
        Map requestParams = request.getParameterMap();
        for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
            String name = (String) iter.next();
            String[] values = (String[]) requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i]
                        : valueStr + values[i] + ",";
            }
            //乱码解决，这段代码在出现乱码时使用
            try {
                valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
                params.put(name, valueStr);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return params;
    }
 
}