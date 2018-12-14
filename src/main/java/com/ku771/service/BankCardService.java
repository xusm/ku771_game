package com.ku771.service;

import java.math.BigInteger;

import com.ku771.common.ServerResponse;
import com.ku771.pojo.BankCard;

public interface BankCardService {

	ServerResponse<?> addBankCard(BankCard bankCard);

	ServerResponse<?> getBankCardInfoByMenumberId(Integer memberId);

	ServerResponse<?> checkCardNumber(BigInteger bankCardNum);

}
