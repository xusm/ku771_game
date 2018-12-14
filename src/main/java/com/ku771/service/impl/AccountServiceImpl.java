package com.ku771.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ku771.common.ServerResponse;
import com.ku771.mapper.AccountMapper;
import com.ku771.mapper.TransferRecordMapper;
import com.ku771.pojo.Account;
import com.ku771.pojo.TransferRecord;
import com.ku771.pojo.example.AccountExample;
import com.ku771.pojo.example.SonAccountLoadExample;
import com.ku771.service.AccountService;

@Service("accountService")
public class AccountServiceImpl implements AccountService {

	@Autowired
	private AccountMapper accountMapper;
	
	@Autowired
	private TransferRecordMapper transferRecordMapper;
	
	//获取账户信息
	public ServerResponse<?> getAccountInfo(Integer memberId) {
		List<AccountExample> accountList = accountMapper.getAccountInfo(memberId);
		return ServerResponse.createBySuccess(accountList);
	}

	//获取账户总金额
	public ServerResponse<?> getAccountTotalMoney(Integer memberId) {
		double totalMoney = accountMapper.getAccountTotalMoney(memberId);
		return ServerResponse.createBySuccess(totalMoney);
	}

	//转账操作
	@Transactional(propagation = Propagation.REQUIRED,readOnly = false)
	public ServerResponse<?> transferAccounts(Integer memberId,Integer turnOutId, Integer turnInId, double turnMoney) {
		//前端需判断转出账户金额是否充足，这里默认账户已有足够金额
		//转出账户和转入账户不能一样（由前端判断）
		//各账户之间相互转账的逻辑为账户A--主账户--账户B
		
		//获取转出账户信息
		AccountExample turnOutAccount = accountMapper.getTurnOutOrInAccount(turnOutId,memberId); 
		
		//获取转入账户信息
		AccountExample turnInAccount = accountMapper.getTurnOutOrInAccount(turnInId,memberId); 
		
		//获取主账户信息
		AccountExample mainAccount = accountMapper.getMainAccount(memberId);
		
		//减少转出账户
		int updateFlag1 = accountMapper.updateAccount(new Account(turnOutAccount.getMainId(),turnOutId,memberId,turnOutAccount.getBalance()-turnMoney));
		//增加转入账户
		int updateFlag2 = accountMapper.updateAccount(new Account(turnInAccount.getMainId(),turnInId,memberId,turnInAccount.getBalance()+turnMoney));

		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateNowStr = sdf.format(date);
		
		//判断转入转出账户是否为主账户（转入转出账户不能相等）
		//转出为主账户时，仅一条转账记录
		if(turnInId == mainAccount.getSonId() || turnOutId == mainAccount.getSonId()){
			//如果为主账户，直接转入
			//添加一条转账记录
			int addFlag = transferRecordMapper.addTransferRecord(new TransferRecord(null,memberId,turnOutId,turnInId,turnMoney,dateNowStr,null));
			if(updateFlag1+updateFlag2+addFlag == 3){
				return ServerResponse.createBySuccessMessage("转账成功");
			}else{
				return ServerResponse.createByErrorMessage("转账失败");
			}
		}else{
			//如果不为主账户，先转入主账户，由主账户转入指定账户,则需要添加两条转账记录
			//1.由转出账户转入到主账户
			int addFlag1 = transferRecordMapper.addTransferRecord(new TransferRecord(null,memberId,turnOutId,mainAccount.getSonId(),turnMoney,dateNowStr,null));
			//2.由主账户转入到指定账户
			int addFlag2 = transferRecordMapper.addTransferRecord(new TransferRecord(null,memberId,mainAccount.getSonId(),turnInId,turnMoney,dateNowStr,null));
			if(updateFlag1+updateFlag2+addFlag1+addFlag2 == 4){
				return ServerResponse.createBySuccessMessage("转账成功");
			}else{
				return ServerResponse.createByErrorMessage("转账失败");
			}
		}
	}

	//额度全部转回主账户
	@Transactional(propagation = Propagation.REQUIRED,readOnly = false)
	public ServerResponse<?> accountsReturn(Integer memberId) {
		//获取总金额
		double totalMoney = accountMapper.getAccountTotalMoney(memberId);
		//清空该会员账户金额
		int clearFlag = accountMapper.clearAccount(new Account(null,null,memberId,0));
		
		//总金额转回到主账户
		//获取主账户信息
		if(clearFlag > 0){
			AccountExample mainAccount = accountMapper.getMainAccount(memberId);
			int updateFlag = accountMapper.updateAccount(new Account(mainAccount.getMainId(),null,null,totalMoney));
			if(updateFlag > 0){
				return ServerResponse.createBySuccessMessage("转回主账户成功");
			}
		}
		return ServerResponse.createByErrorMessage("转回主账户失败");
	}
	
	//加载所有账户（包括ID和名称）
	public ServerResponse<?> loadAllAccount() {
		List<SonAccountLoadExample> accountList = accountMapper.loadAllAccount();
		return ServerResponse.createBySuccess(accountList);
	}
}
