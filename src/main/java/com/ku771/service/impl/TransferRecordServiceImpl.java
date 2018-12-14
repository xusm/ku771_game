package com.ku771.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.ku771.mapper.TransferRecordMapper;
import com.ku771.pojo.example.TransferRecordExample;
import com.ku771.service.TransferRecordService;

@Service("transferRecordService")
public class TransferRecordServiceImpl implements TransferRecordService {
	
	@Autowired
	private TransferRecordMapper transferRecordMapper;

	//获取转账记录列表（分页查询）
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public JSONObject getAccountInfo(String startTurnDate, String endTurnDate,Integer turnInId,Integer memberId, int pageNum, int pageSize) {
		//按条件查询时先查询总条数
        int total = 0;
        if(startTurnDate!=null || endTurnDate!=null || turnInId != null){
        	total = transferRecordMapper.getTotal(startTurnDate,endTurnDate,turnInId,memberId);
        }
        // 开始分页
        PageHelper.startPage(pageNum,pageSize);
        //获取分页列表
        List<TransferRecordExample> transferRecordList = new ArrayList<TransferRecordExample>();
        if(startTurnDate!=null || endTurnDate!=null || turnInId != null){
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
        	transferRecordList = transferRecordMapper.getTransferRecordList(startTurnDate,endTurnDate,turnInId,memberId);
        }else{
        	transferRecordList = transferRecordMapper.getTransferRecordList(null,null,null,memberId);
        }

        PageInfo pageInfo = new PageInfo(transferRecordList);
       
        List<TransferRecordExample> transferRecordListVoList = Lists.newArrayList();
        //封装分页列表
        for (TransferRecordExample transferRecord : transferRecordList) {
        	transferRecordListVoList.add(transferRecord);
        }

        pageInfo.setList(transferRecordListVoList);

        //格式化JSON
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("count", pageInfo.getTotal());
        jsonObject.put("code", 0);
        jsonObject.put("msg", "success");
        jsonObject.put("data", pageInfo.getList());

        return jsonObject;
	}
}
