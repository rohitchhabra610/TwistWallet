package com.TwistWallet.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import com.TwistWallet.Exception.CustomGenericException;
import com.TwistWallet.utils.ErrorCodes;
import com.TwistWallet.utils.TwistWalletResponse;

@Controller
public class ExceptionController {

	@ExceptionHandler(Exception.class)
	public /*@ResponseBody TwistWalletResponse*/ String systemException(Exception ex) {
		System.out.println("imnn");
		TwistWalletResponse response = new TwistWalletResponse();	
		response.setResultDesc(ex.getMessage());
		response.setErrorCode(ErrorCodes.SYSTEM_EXCEPTION.getResultCode());
		response.setErrorMsg(ErrorCodes.SYSTEM_EXCEPTION.getDesc());
		//return response;
		return "hbvh";
	}
	
	@ExceptionHandler(CustomGenericException.class)
	public @ResponseBody TwistWalletResponse CustomGenericException(CustomGenericException ex) {
		TwistWalletResponse response = new TwistWalletResponse();	
		response.setResultDesc(ex.getMessage());
		response.setErrorCode(ErrorCodes.CUSTOM_EXCEPTION.getResultCode());
		response.setErrorMsg(ErrorCodes.CUSTOM_EXCEPTION.getDesc());
		return response;
	}
}
