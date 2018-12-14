package com.ku771.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.ku771.mapper.MemberMapper;

@Component("taskCleanDrwingFrequency")
public class TaskCleanDrwingFrequency {
	
	@Autowired
	private MemberMapper memberMapper;
	
	/**
	 * 作业类--定时调度器
	 * 每日定时清空提款次数
	 */
	@Scheduled(cron = "0 32 17 * * ?")  
    public void cleanDrwingFrequence() {  
        System.out.println("定时任务进行中……");
        //开始重置提款次数
        int cleanFlag = memberMapper.resetDrwingFrequency();
        if(cleanFlag > 0){
        	System.out.println("提款次数重置成功");
        }else{
        	System.out.println("提款次数重置失败");
        }
        System.out.println("定时任务结束……");
    }  
}
