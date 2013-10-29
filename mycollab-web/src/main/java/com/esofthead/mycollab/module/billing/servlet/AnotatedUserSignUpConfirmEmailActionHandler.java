package com.esofthead.mycollab.module.billing.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;

import com.esofthead.mycollab.servlet.GenericServlet;

@Component("userconfirmsignupServlet")
public class AnotatedUserSignUpConfirmEmailActionHandler extends GenericServlet {

	@Override
	protected void onHandleRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
	}
}
