package com.ku771.service;

import com.alibaba.fastjson.JSONObject;

public interface TransferRecordService {

	JSONObject getAccountInfo(String turnDate, String endTurnDate, Integer turnInId,Integer memberId, int pageNum,int pageSize);

}
