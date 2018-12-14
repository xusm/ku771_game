package com.ku771.service.impl;

import java.math.BigInteger;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ku771.common.ServerResponse;
import com.ku771.mapper.BankCardMapper;
import com.ku771.pojo.BankCard;
import com.ku771.service.BankCardService;

@Service("bankCardService")
public class BankCardServiceImpl implements BankCardService {
	
	@Autowired
	private BankCardMapper bankCardMapper;
	
	//银行卡新增
	public ServerResponse<?> addBankCard(BankCard bankCard) {
		int flagAdd = bankCardMapper.addBankCard(bankCard);
		if(flagAdd > 0){
			return ServerResponse.createBySuccessMessage("银行卡新增成功");
		}
		return ServerResponse.createByErrorMessage("银行卡新增失败");
	}

	//获取会员银行卡信息
	public ServerResponse<?> getBankCardInfoByMenumberId(Integer memberId) {
		List<BankCard> bankCardList = bankCardMapper.getBankCardInfoByMenumberId(memberId);
		return ServerResponse.createBySuccess(bankCardList);
	}

	//银行卡新增前检验该账号是否重复
	public ServerResponse<?> checkCardNumber(BigInteger bankCardNum) {
		int checkFlag = bankCardMapper.checkCardNumber(bankCardNum);
		if(checkFlag > 0){
			return ServerResponse.createByErrorMessage("账号已存在");
		}
		return ServerResponse.createBySuccessMessage("账号可用");
	}

}
