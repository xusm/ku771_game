package com.ku771.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ku771.pojo.Account;
import com.ku771.pojo.SonAccount;
import com.ku771.pojo.example.AccountExample;
import com.ku771.pojo.example.SonAccountLoadExample;

public interface AccountMapper {

	List<SonAccount> getAllAccount();

	int addAccount(Account account);

	List<AccountExample> getAccountInfo(@Param("memberId")Integer memberId);

	double getAccountTotalMoney(@Param("memberId")Integer memberId);

	AccountExample getMainAccount(@Param("memberId")Integer memberId);

	AccountExample getTurnOutOrInAccount(@Param("sonId")Integer sonId, @Param("memberId")Integer memberId);

	int updateAccount(Account account);

	List<SonAccountLoadExample> loadAllAccount();

	int clearAccount(Account account);

	int updateAccountBySonIdAndMemberId(Account account);

}
