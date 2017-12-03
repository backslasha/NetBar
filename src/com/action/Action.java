package com.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Action {
	String url(HttpServletRequest request, HttpServletResponse response);
}
