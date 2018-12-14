package com.ku771.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ku771.common.Md5Util;
import com.ku771.common.ServerResponse;
import com.ku771.mapper.AccountMapper;
import com.ku771.mapper.IntelligenceLineMapper;
import com.ku771.mapper.MemberMapper;
import com.ku771.pojo.Account;
import com.ku771.pojo.Member;
import com.ku771.pojo.SonAccount;
import com.ku771.pojo.ThirdPartyInsert;
import com.ku771.pojo.example.MemberLoadExample;
import com.ku771.service.MemberService;
import com.ku771.util.StringUtil;

@Service("memberService")
public class MemberServiceImpl implements MemberService {
	
	@Autowired
	private MemberMapper memberMapper;

	@Autowired
	private AccountMapper accountMapper;

	@Autowired
	private IntelligenceLineMapper intelligenceLineMapper;
	
	// 发件人的 邮箱 和 密码（替换为自己的邮箱和密码）
    // PS: 某些邮箱服务器为了增加邮箱本身密码的安全性，给 SMTP 客户端设置了独立密码（有的邮箱称为“授权码”）,
    // 对于开启了独立密码的邮箱, 这里的邮箱密码必需使用这个独立密码（授权码）。
	public static String myEmailAccount = "1509825175@qq.com";
    public static String myEmailPassword = "tbhetokdqwfwhjih";   //成功开启POP3/SMTP服务,在第三方客户端登录时，请输入以下授权码：
 
    // 发件人邮箱的 SMTP 服务器地址, 必须准确, 不同邮件服务器地址不同, 一般(只是一般, 绝非绝对)格式为: smtp.xxx.com
    // QQ邮箱的 SMTP 服务器地址为: smtp.qq.com
    public static String myEmailSMTPHost = "smtp.qq.com";
 
    // 收件人邮箱（自己知道的有效邮箱）
    //public static String receiveMailAccount = "734741525@qq.com";
    
	//检测会员账号是否存在
	public ServerResponse<?> checkMemberNum(String memberNum) {
		//flag 0:不存在；1：存在
		int flag = memberMapper.checkMemberNum(memberNum);
		if(flag > 0){
			return ServerResponse.createByErrorMessage("不可用");
		}
		return ServerResponse.createBySuccessMessage("可注册");
	}

	//会员注册
	@Transactional(propagation = Propagation.REQUIRED,readOnly = false)
	public ServerResponse<?> register(Member member) {
		//使用MD5加密
		member.setPassword(Md5Util.md5EncodeUTF8(member.getPassword()));
		// 验证通过后新增会员信息
		int flagAdd = memberMapper.register(member);
		if(flagAdd > 0){
			//初始化会员账户（包括主账户和子账户）; 初始化账户接入信息
			//首先获取共有多少个账户
			List<SonAccount> saList = accountMapper.getAllAccount();
			int addFlag = 0;
			int addFlag1 = 0;
			//循环增加账户信息
			for(SonAccount sonAccount : saList){
				addFlag += accountMapper.addAccount(new Account(null,sonAccount.getSonId(),member.getMemberId(),0.00));
				addFlag1 += intelligenceLineMapper.addInsert(new ThirdPartyInsert(null,member.getMemberId(),sonAccount.getSonId(),0));
			}
			
			if(addFlag == saList.size() && addFlag1 == saList.size()){
				return ServerResponse.createBySuccessMessage("注册成功");
			}else{
				return ServerResponse.createByErrorMessage("参数错误(1,-500)");
			}
		}
		return ServerResponse.createByErrorMessage("参数错误(1,-500)");
	}

	//会员登录
	@SuppressWarnings("unchecked")
	public ServerResponse<Member> login(String memberNum, String password) {
		//加密后查询
		password = Md5Util.md5EncodeUTF8(password);
		
		Member member = memberMapper.login(memberNum, password);
		if(member == null){
			return ServerResponse.createByErrorMessage("账户或密码错误");
		}
		
		//不响应密码
		member.setPassword(StringUtils.EMPTY);
		return ServerResponse.createBySuccess("登录成功", member);
	}

	//新增提款密码
	public ServerResponse<?> addDrawingPassword(Member member) {
		//使用MD5加密
		member.setDrawingPassword(Md5Util.md5EncodeUTF8(member.getDrawingPassword()));
		int flagUpdate = memberMapper.updateMember(member);
		if(flagUpdate > 0){
			return ServerResponse.createBySuccessMessage("提款密码添加成功");
		}
		return ServerResponse.createByErrorMessage("提款密码添加失败");
	}

	//检测提款密码是否正确
	public ServerResponse<?> checkDrawingPw(Integer memberId, String drawingPassword) {
		//加密后查询
		drawingPassword = Md5Util.md5EncodeUTF8(drawingPassword);
		int checkFlag = memberMapper.checkDrawingPw(memberId,drawingPassword);
		if(checkFlag > 0){
			return ServerResponse.createBySuccessMessage("提款密码正确");
		}
		return ServerResponse.createByErrorMessage("提款密码错误");
	}

	//根据会员ID获取会员资料
	public ServerResponse<?> getMemberInfo(Integer memberId) {
		MemberLoadExample member = memberMapper.getMemberInfo(memberId);
		return ServerResponse.createBySuccess(member);
	}

	//变更会员资料
	public ServerResponse<?> updateMember(Member member) {
		//检测是否需要修改登录密码
		if(member.getPassword() != null){
			if(StringUtil.isNotEmpty(member.getPassword())){
				//如果修改账户密码，检测新密码是否和旧密码相同
				member.setPassword(Md5Util.md5EncodeUTF8(member.getPassword()));
				int checkFlag = memberMapper.checkUpdatePassword(member.getMemberId(),member.getPassword());
				if(checkFlag > 0){
					return ServerResponse.createByErrorMessage("新账号密码不可和原账号密码相同");
				}
			}
		}
		//检测是否需要修改提款密码
		if(member.getDrawingPassword() != null){
			if(StringUtil.isNotEmpty(member.getDrawingPassword())){
				//如果修改提款密码，检测新密码是否和旧密码相同
				member.setDrawingPassword(Md5Util.md5EncodeUTF8(member.getDrawingPassword()));
				int checkFlag = memberMapper.checkUpdateDrawingPassword(member.getMemberId(),member.getDrawingPassword());
				if(checkFlag > 0){
					return ServerResponse.createByErrorMessage("新提款密码不可和原提款密码相同");
				}
			}
		}
		
		/**
		 * 检测账户密码和提款密码是否同时修改，若同时修改，在前端已判断账户密码不等于提款密码；后端也已判断两者密码均不于原密码相等
		 * 因此，在已有条件下，若账户密码和提款密码只有其中一个需要修改时，需判断新账户密码是否等于原提款密码或者新提款密码是否等于原账户密码
		 * 而在账户密码和提款密码同时需要修改的情况下，要忽略新账户密码等于原提款密码或新提款密码等于原账户密码
		 */
		//第一种情况，修改账户密码，不修改提款密码
		if(member.getPassword() == null && member.getDrawingPassword() != null){
			if(StringUtil.isNotEmpty(member.getPassword()) && StringUtil.isEmpty(member.getDrawingPassword())){
				//判断新账户密码是否等于原提款密码
				int checkFlag = memberMapper.checkNewPasswordWhetherEqualsOldDrawingPassword(member.getMemberId(),member.getPassword());
				if(checkFlag > 0){
					return ServerResponse.createByErrorMessage("新账户密码不可和原提款密码相同");
				}
			}
		}
		
		//第二种情况，修改提款密码，不修改账户密码
		if(member.getDrawingPassword() != null && member.getPassword() == null){
			if(StringUtil.isNotEmpty(member.getDrawingPassword()) && StringUtil.isEmpty(member.getPassword())){
				//判断新提款密码是否等于原账户密码
				int checkFlag = memberMapper.checkNewDrawingPasswordWhetherEqualsOldPassword(member.getMemberId(),member.getDrawingPassword());
				if(checkFlag > 0){
					return ServerResponse.createByErrorMessage("新提款密码不可和原账户密码相同");
				}
			}
		}
		
		//开始变更会员资料
		int updateFlag = memberMapper.updateMember(member);
		if(updateFlag > 0){
			return ServerResponse.createBySuccessMessage("修改成功");
		}
		return ServerResponse.createByErrorMessage("修改失败");
	}

	//邮箱验证并发送验证链接
	public ServerResponse<?> checkEmail(Integer memberId,String email) throws Exception {
        // 1. 创建参数配置, 用于连接邮件服务器的参数配置
        Properties props = new Properties();                    // 参数配置
        props.setProperty("mail.transport.protocol", "smtp");   // 使用的协议（JavaMail规范要求）
        props.setProperty("mail.smtp.host", myEmailSMTPHost);   // 发件人的邮箱的 SMTP 服务器地址
        props.setProperty("mail.smtp.auth", "true");            // 需要请求认证
        
        
        // PS: 某些邮箱服务器要求 SMTP 连接需要使用 SSL 安全认证 (为了提高安全性, 邮箱支持SSL连接, 也可以自己开启),
        //     如果无法连接邮件服务器, 仔细查看控制台打印的 log, 如果有有类似 “连接失败, 要求 SSL 安全连接” 等错误,
        //     取消下面 /* ... */ 之间的注释代码, 开启 SSL 安全连接。
        
        // SMTP 服务器的端口 (非 SSL 连接的端口一般默认为 25, 可以不添加, 如果开启了 SSL 连接,
        //                  需要改为对应邮箱的 SMTP 服务器的端口, 具体可查看对应邮箱服务的帮助,
        //                  QQ邮箱的SMTP(SLL)端口为465或587, 其他邮箱自行去查看)
        final String smtpPort = "465";
        props.put("mail.smtp.ssl.enable", "true");//QQ邮箱的SSL加密
        props.setProperty("mail.smtp.port", smtpPort);
        props.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.setProperty("mail.smtp.socketFactory.fallback", "false");
        props.setProperty("mail.smtp.socketFactory.port", smtpPort);
        
 
        // 2. 根据配置创建会话对象, 用于和邮件服务器交互
        Session session = Session.getInstance(props);
        // 设置为debug模式, 可以查看详细的发送 log
        session.setDebug(true);
 
        // 3. 创建一封邮件
        MimeMessage message = createMimeMessage(session, myEmailAccount, email,memberId);
 
        // 4. 根据 Session 获取邮件传输对象
        Transport transport = session.getTransport();
 
        // 5. 使用 邮箱账号 和 密码 连接邮件服务器, 这里认证的邮箱必须与 message 中的发件人邮箱一致, 否则报错
        //
        //    PS_01: 如果连接服务器失败, 都会在控制台输出相应失败原因的log。
        //    仔细查看失败原因, 有些邮箱服务器会返回错误码或查看错误类型的链接,
        //    根据给出的错误类型到对应邮件服务器的帮助网站上查看具体失败原因。
        //
        //    PS_02: 连接失败的原因通常为以下几点, 仔细检查代码:
        //           (1) 邮箱没有开启 SMTP 服务;
        //           (2) 邮箱密码错误, 例如某些邮箱开启了独立密码;
        //           (3) 邮箱服务器要求必须要使用 SSL 安全连接;
        //           (4) 请求过于频繁或其他原因, 被邮件服务器拒绝服务;
        //           (5) 如果以上几点都确定无误, 到邮件服务器网站查找帮助。
        //
        transport.connect(myEmailAccount, myEmailPassword);
 
        // 6. 发送邮件, 发到所有的收件地址, message.getAllRecipients() 获取到的是在创建邮件对象时添加的所有收件人, 抄送人, 密送人
        transport.sendMessage(message, message.getAllRecipients());
 
        // 7. 关闭连接
        transport.close();
        return null;
	}
	    
    /**
     * 创建一封只包含文本的简单邮件
     *
     * @param session     和服务器交互的会话
     * @param sendMail    发件人邮箱
     * @param receiveMail 收件人邮箱
     * @return
     * @throws Exception
     */
    public static MimeMessage createMimeMessage(Session session, String sendMail, String receiveMail,Integer memberId) throws Exception {
        // 1. 创建一封邮件
        MimeMessage message = new MimeMessage(session);
 
        // 2. From: 发件人
        message.setFrom(new InternetAddress(sendMail, "KU游娱乐", "UTF-8"));
 
        // 3. To: 收件人（可以增加多个收件人、抄送、密送）
        message.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(receiveMail, "XX用户", "UTF-8"));
 
        // 4. Subject: 邮件主题
        message.setSubject("会员电子邮箱验证信", "UTF-8");
 
        // 5. Content: 邮件正文（a标签的href要修改为指定的服务器下的验证地址）
        String t = "http://ku11.net/Member/EmailVerify?z=8cd2eb1f5bd8fb1122fb650375d66196ff3a5144d6e535bca8df025e012d9da47ced31e360aec54afdfb1d53cefd8e5ec1c9c29db6c1efc0e5ffc0f37ff53d75";
        String href = "http://localhost:8080/member/update_email.do?email="+receiveMail+"&memberId="+memberId;
        message.setContent("尊敬的会员您好:<br>请点击下面的链接进行电子信箱认证：<br><a href='"+href+"'>"+t+"</a><br><br>客服中心敬上！", "text/html;charset=UTF-8");
            // 6. 设置发件时间
        message.setSentDate(new Date());
 
        // 7. 保存设置
        message.saveChanges();
 
        return message;
    }

	//邮箱验证验证后更新会员邮件
	public ServerResponse<?> updateEmail(Integer memberId,String email) {
		int updateFlag = memberMapper.updateEmail(memberId,email);
		if(updateFlag > 0){
			return ServerResponse.createBySuccessMessage("success");
		}
		return ServerResponse.createByErrorMessage("fail");
	} 
    
}
