package com.ku771.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.ku771.common.ServerResponse;
import com.ku771.mapper.AccountMapper;
import com.ku771.mapper.MemberMapper;
import com.ku771.mapper.TradingCenterMapper;
import com.ku771.pojo.Account;
import com.ku771.pojo.Member;
import com.ku771.pojo.TradingCenter;
import com.ku771.pojo.example.AccountExample;
import com.ku771.service.TradingCenterService;

@Service("tradingCenterService")
public class TradingCenterServiceImpl implements TradingCenterService {
	@Autowired
	private TradingCenterMapper tradingCenterMapper;
	
	@Autowired
	private MemberMapper memberMapper;

	@Autowired
	private AccountMapper accountMapper;
	
	//获取交易记录列表（分页查询）
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public JSONObject getTradingRecordList(Integer tradingType,Integer tradingStatus, Integer memberId, int pageNum, int pageSize) {
		//按条件查询时先查询总条数
        int total = 0;
        if(tradingType!=null || tradingStatus!=null){
        	total = tradingCenterMapper.getTotal(tradingType,tradingStatus,memberId);
        }
        // 开始分页
        PageHelper.startPage(pageNum,pageSize);
        //获取分页列表
        List<TradingCenter> tradingRecordList = new ArrayList<TradingCenter>();
        if(tradingType!=null || tradingStatus!=null){
        	if(total < pageSize){
        		PageHelper.startPage(1,pageSize);
        	}else{
            	//当前按条件做第一次查询时必须初始化分页条件
        		//总页数
        		int pages = 0;
        		if((total%pageSize) == 0){
            		pages = total/pageSize;
        		}else{
        			pages = (total/pageSize)+1;
        		}
        		//如果首次查询时参数中传递页码超过实际页码需重新修改
            	if(pageNum>pages){
            		PageHelper.startPage(pages,pageSize);
            	}
        	}
        	tradingRecordList = tradingCenterMapper.getTradingRecordList(tradingType,tradingStatus,memberId);
        }else{
        	tradingRecordList = tradingCenterMapper.getTradingRecordList(null,null,memberId);
        }

        PageInfo pageInfo = new PageInfo(tradingRecordList);
       
        List<TradingCenter> tradingRecordListVoList = Lists.newArrayList();
        //封装分页列表
        for (TradingCenter tradingCenter : tradingRecordList) {
        	tradingRecordListVoList.add(tradingCenter);
        }

        pageInfo.setList(tradingRecordListVoList);

        //格式化JSON
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("count", pageInfo.getTotal());
        jsonObject.put("code", 0);
        jsonObject.put("msg", "success");
        jsonObject.put("data", pageInfo.getList());

        return jsonObject;
	}
	
	
	//新增交易记录
	public int addTradingRecord(TradingCenter tradingCenter) {
		
		return tradingCenterMapper.addTradingRecord(tradingCenter);
	}

	//修改交易记录
	public ServerResponse<?> updateTradingRecord(TradingCenter tradingCenter) {
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateNowStr = sdf.format(date);
		
		int updateFlag = 0;
		String msg = "";
		/**
		 * 为存款交易时
		 */
		if(tradingCenter.getTradingType() == 0){
			//计算手续费
			double serviceCharge = tradingCenter.getTradingMoney()*0.05;
			//数据封装
			tradingCenter.setTradingStatus(1);
			tradingCenter.setTradingDate(dateNowStr);
			tradingCenter.setServiceCharge(serviceCharge);
			tradingCenter.setBalance(tradingCenter.getTradingMoney()-serviceCharge);
			
			updateFlag = tradingCenterMapper.updateTradingRecord(tradingCenter);
			if(updateFlag > 0){
				msg = "付款成功";
			}else{
				msg = "付款失败";
			}
		}
		
		/**
		 * 为提款交易时
		 */
		if(tradingCenter.getTradingType() == 1){
			//数据封装
			tradingCenter.setTradingStatus(1);
			tradingCenter.setTradingDate(dateNowStr);
			
			updateFlag = tradingCenterMapper.updateTradingRecord(tradingCenter);
			if(updateFlag > 0){
				msg = "提款成功";
			}else{
				msg = "提款失败";
			}
		}
		if(updateFlag > 0){
			return ServerResponse.createBySuccessMessage(msg);
		}
		return ServerResponse.createByErrorMessage(msg);
	}


	//获取提款次数
	public ServerResponse<?> getDrawingFrequency(Integer memberId) {
		int frequency = memberMapper.getDrawingFrequency(memberId);
		return ServerResponse.createBySuccess("提款次数", frequency);
	}


	//新增提款记录
	@Transactional(propagation = Propagation.REQUIRED,readOnly = false)
	public ServerResponse<?> addDrawingRecord(TradingCenter tradingCenter) {
		//初始化提款信息
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateNowStr = sdf.format(date);
		tradingCenter.setTradingDate(dateNowStr);
		tradingCenter.setTradingType(1);
		tradingCenter.setTradingContent("提款");
		//交易状态修改为处理中，钱款到达银行户头时回调函数设置状态完成
		tradingCenter.setTradingStatus(0);
		
		//获取账户总金额
		double totalMoney = accountMapper.getAccountTotalMoney(tradingCenter.getMemberId());
		//将余额转回主账户，并清空其他账户
		accountMapper.clearAccount(new Account(null,null,tradingCenter.getMemberId(),0));
		AccountExample mainAccount = accountMapper.getMainAccount(tradingCenter.getMemberId());
		//如果提款超过2次，手续费不为空时，账户余额应减去手续费
		double accountBanlace = totalMoney-tradingCenter.getTradingMoney();
		if(accountBanlace < 0){
			return ServerResponse.createByErrorMessage("账户余额不足");
		}
		if(tradingCenter.getServiceCharge() != 0.0){
			accountBanlace = totalMoney-(tradingCenter.getTradingMoney()+tradingCenter.getServiceCharge());
		}
		accountMapper.updateAccount(new Account(mainAccount.getMainId(),null,null,accountBanlace));

		//设置余额
		tradingCenter.setBalance(accountBanlace);
		
		//开始提款操作
		int addFlag = tradingCenterMapper.addTradingRecord(tradingCenter);
		if(addFlag > 0){
			//提款成功后累计提款次数
			int frequency = memberMapper.getDrawingFrequency(tradingCenter.getMemberId());
			Member member = new Member();
			member.setMemberId(tradingCenter.getMemberId());
			member.setDrwingFrequency(frequency+1);
			memberMapper.updateMember(member);
			return ServerResponse.createBySuccessMessage("提款处理中……");
		}
		return ServerResponse.createByErrorMessage("参数错误(0,-500)");
	}
}
