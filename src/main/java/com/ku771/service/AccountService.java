package com.ku771.service;

import com.ku771.common.ServerResponse;

public interface AccountService {

	ServerResponse<?> getAccountInfo(Integer memberId);

	ServerResponse<?> getAccountTotalMoney(Integer memberId);

	ServerResponse<?> transferAccounts(Integer memberId, Integer turnOutId,Integer turnInId, double turnMoney);

	ServerResponse<?> accountsReturn(Integer memberId);

	ServerResponse<?> loadAllAccount();
}
