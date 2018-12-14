package com.ku771.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ku771.pojo.TransferRecord;
import com.ku771.pojo.example.TransferRecordExample;

public interface TransferRecordMapper {
	int addTransferRecord(TransferRecord transferRecord);

	int getTotal(@Param("startTurnDate")String startTurnDate, @Param("endTurnDate")String endTurnDate, @Param("turnInId")Integer turnInId,@Param("memberId")Integer memberId);

	List<TransferRecordExample> getTransferRecordList(@Param("startTurnDate")String startTurnDate, @Param("endTurnDate")String endTurnDate, @Param("turnInId")Integer turnInId,@Param("memberId")Integer memberId);

}
