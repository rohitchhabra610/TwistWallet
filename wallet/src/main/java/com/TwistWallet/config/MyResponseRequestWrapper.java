package com.TwistWallet.config;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

public class MyResponseRequestWrapper extends HttpServletResponseWrapper{
    public MyResponseRequestWrapper(HttpServletResponse response) {
        super(response);
    }
}
