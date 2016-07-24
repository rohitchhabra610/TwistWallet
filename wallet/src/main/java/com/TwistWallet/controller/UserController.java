package com.TwistWallet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.TwistWallet.Exception.CustomGenericException;
import com.TwistWallet.service.UserService;
import com.TwistWallet.utils.ErrorCodes;
import com.TwistWallet.utils.TwistWalletRequest;
import com.TwistWallet.utils.TwistWalletResponse;

@Controller
public class UserController {

	@Autowired
	UserService userServiceImpl;
	
	@RequestMapping(value="/createUser",method=RequestMethod.POST,consumes="application/json",produces="application/json")
	public @ResponseBody TwistWalletResponse createUser(@RequestBody TwistWalletRequest request) throws Exception{
				TwistWalletResponse res = userServiceImpl.createUser(request);
				return  res;
	}
	@RequestMapping(value="/sendMail",method=RequestMethod.POST,consumes="application/json",produces="application/json")
	public @ResponseBody TwistWalletResponse sendMail(@RequestBody TwistWalletRequest request) throws Exception{
				TwistWalletResponse res = userServiceImpl.sendMail(request);
				return  res;
	}
	
	
}
