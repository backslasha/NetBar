package com.ajax;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface AJAX {
	String ajax(HttpServletRequest request, HttpServletResponse response);
}
