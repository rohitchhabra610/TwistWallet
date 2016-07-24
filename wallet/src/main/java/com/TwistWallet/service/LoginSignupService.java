package com.TwistWallet.service;

import com.TwistWallet.utils.TwistWalletRequest;
import com.TwistWallet.utils.TwistWalletResponse;

public interface LoginSignupService {

	TwistWalletResponse login(TwistWalletRequest request) throws Exception;
	TwistWalletResponse reset(TwistWalletRequest request)throws Exception;
	TwistWalletResponse duplicateEmail(TwistWalletRequest request)throws Exception;
	TwistWalletResponse forgotPassword(TwistWalletRequest request)throws Exception;
	TwistWalletResponse googleLoginSignUp(TwistWalletRequest request)throws Exception;
}
