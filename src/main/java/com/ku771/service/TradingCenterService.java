package com.ku771.service;

import com.alibaba.fastjson.JSONObject;
import com.ku771.common.ServerResponse;
import com.ku771.pojo.TradingCenter;

public interface TradingCenterService {
	
	int addTradingRecord(TradingCenter tradingCenter);

	ServerResponse<?> updateTradingRecord(TradingCenter tc);

	JSONObject getTradingRecordList(Integer tradingType, Integer tradingStatus,Integer memberId, int pageNum, int pageSize);

	ServerResponse<?> getDrawingFrequency(Integer memberId);
	
	ServerResponse<?> addDrawingRecord(TradingCenter tradingCenter);
}
