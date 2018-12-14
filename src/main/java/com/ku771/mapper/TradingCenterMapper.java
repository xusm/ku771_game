package com.ku771.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ku771.pojo.TradingCenter;

public interface TradingCenterMapper {
	int addTradingRecord(TradingCenter tradingCenter);

	int updateTradingRecord(TradingCenter tradingCenter);

	int getTotal(@Param("tradingType")Integer tradingType,@Param("tradingStatus")Integer tradingStatus, @Param("memberId")Integer memberId);

	List<TradingCenter> getTradingRecordList(@Param("tradingType")Integer tradingType,@Param("tradingStatus")Integer tradingStatus, @Param("memberId")Integer memberId);

}
