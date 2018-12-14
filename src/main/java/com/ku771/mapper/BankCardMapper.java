package com.ku771.mapper;

import java.math.BigInteger;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ku771.pojo.BankCard;

public interface BankCardMapper {

	int addBankCard(BankCard bankCard);

	List<BankCard> getBankCardInfoByMenumberId(@Param("memberId")Integer memberId);

	int checkCardNumber(@Param("bankCardNum")BigInteger bankCardNum);

}
