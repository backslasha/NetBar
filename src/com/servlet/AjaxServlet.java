package com.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ajax.AJAXFactory;
import com.ajax.AJAX;

/**
 * Servlet implementation class AjaxServlet
 */
@WebServlet("/AjaxServlet")
public class AjaxServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AjaxServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		response.setHeader("Content-type", "application/json;charset=UTF-8");  
		response.setCharacterEncoding("UTF-8");
		handleAction(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		response.setHeader("Content-type", "application/json;charset=UTF-8");  
		response.setCharacterEncoding("UTF-8");
		handleAction(request, response);
	}
	protected void handleAction(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String pathName = request.getServletPath();
		int index = pathName.indexOf(".");
		String actionName = pathName.substring(1, index);
		String actionClassName = this.getInitParameter(actionName);
		if(actionClassName!=null) {
			AJAX action =  AJAXFactory.getActionFactory().getAction(actionClassName);
			String responseText = action.ajax(request, response);
			response.getWriter().print(responseText);
			System.out.println(responseText);
		}else {
			response.getWriter().print("获取数据失败！");
		}
	}
}
