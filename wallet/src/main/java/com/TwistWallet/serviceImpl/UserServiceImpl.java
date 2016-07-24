package com.TwistWallet.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.TwistWallet.Entity.LoginEntity;
import com.TwistWallet.Entity.UserEntity;
import com.TwistWallet.Exception.CustomGenericException;
import com.TwistWallet.beans.Mailer;
import com.TwistWallet.dao.BaseDao;
import com.TwistWallet.dto.Login;
import com.TwistWallet.dto.MailUser;
import com.TwistWallet.dto.User;
import com.TwistWallet.service.LoginSignupService;
import com.TwistWallet.service.UserService;
import com.TwistWallet.utils.ErrorCodes;
import com.TwistWallet.utils.Response;
import com.TwistWallet.utils.TwistWalletRequest;
import com.TwistWallet.utils.TwistWalletResponse;
import com.TwistWallet.utils.TwistWalletUtils;

@Service("userService")
public class UserServiceImpl implements UserService{
	
	@Autowired
	private Mailer mailer;
	
	@Autowired
	BaseDao baseDaoImpl;
	
	@Autowired
	LoginSignupService loginSignupService;
	
	@Override
	public TwistWalletResponse createUser(TwistWalletRequest request) throws Exception {
		TwistWalletResponse response = new TwistWalletResponse();
		UserEntity userEntity = new UserEntity();
		LoginEntity loginEntity = new LoginEntity();
		if(request.getUser().getEmailAddress() !=null && request.getUser().getFirstName()!=null && 
				request.getUser().getLastName() != null){
		userEntity.setEmailAddress(request.getUser().getEmailAddress());
		response=loginSignupService.duplicateEmail(request);
		if(response.getResultCode() !=1){
			return response;
		}
		response = new TwistWalletResponse();
		userEntity.setFirstName(request.getUser().getFirstName());
		userEntity.setLastName(request.getUser().getLastName());
		userEntity.setMobileNumber(request.getUser().getMobileNumber());
		userEntity.setNewUser(true);
		//userEntity.setPassword(request.getUser().getPassword());
		userEntity.setAdmin(request.getUser().isAdmin());
		String genPassword = TwistWalletUtils.getPassword(request.getUser().getEmailAddress());
		userEntity = (UserEntity) baseDaoImpl.save(userEntity);
		
		loginEntity.setPassword(genPassword);
		Login login = new Login();
		login.setPassword(genPassword);
		request.setLogin(login);
		
		loginEntity.setUser(userEntity);
		loginEntity = (LoginEntity) baseDaoImpl.save(loginEntity);
		
		Login loginE=new Login();
		loginE.setLoginId(loginEntity.getLoginId());
		response.setLogin(loginE);
		
		User user = new User();
		user.setUserId(userEntity.getUserId());
		user.setEmailAddress(userEntity.getEmailAddress());
		user.setFirstName(userEntity.getFirstName());
		user.setLastName(userEntity.getLastName());
		user.setAdmin(userEntity.getAdmin());
		user.setNewUser(userEntity.getNewUser());
		response.setUser(user);
		
		MailUser mailUser = new MailUser();
		mailUser.setEmailAddress(userEntity.getEmailAddress());
		mailUser.setSubject("TwistWallet-Registration");
		mailUser.setTemplateName("TwistwalletWelcomeTemplet.html");
		mailUser.setUserName(userEntity.getFirstName());
		mailUser.setUserActivationCode(genPassword);
		request.setMailUser(mailUser);
		//sendMail(request);
		response.setResultCode(Response.SUCCESS.getResultCode());
		response.setResultDesc(Response.SUCCESS.getDesc());
		return response;
		}
		response.setResultCode(ErrorCodes.INVALID_REQUEST.getResultCode());
		response.setResultDesc(ErrorCodes.INVALID_REQUEST.getDesc());
		return response;
	}
	
	@Override
	public TwistWalletResponse sendMail(TwistWalletRequest request) throws Exception {	
		/*MailUser mailUser = new MailUser();
		mailUser.setEmailAddress("rohitchhh@gmail.com");
		mailUser.setSubject("TwistWallet-Registration");
		mailUser.setTemplateName("mailTemplet.html");
		mailUser.setUserName("Rohit");
		mailUser.setUserActivationCode("abcdefgh134@26");
		request.setMailUser(mailUser);*/
		if(request.getUser().getUserId()==4){
			throw new Exception("4");
			/*throw new CustomGenericException("E888", "This is custom message");*/
			}
		mailer.sendMail(request.getMailUser());
		
		return null;
	}
}
