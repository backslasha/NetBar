package com.listener;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * Application Lifecycle Listener implementation class FinishListener
 *
 */
@WebListener
public class FinishListener implements HttpSessionListener {

    /**
     * Default constructor. 
     */
    public FinishListener() {
        // TODO Auto-generated constructor stub
    }

	/**
     * @see HttpSessionListener#sessionCreated(HttpSessionEvent)
     */
    public void sessionCreated(HttpSessionEvent se)  { 
    	String sessionid = se.getSession().getId();  
    	System.out.println("-------------------------------------");
    	System.out.println("|--- session created:"+sessionid+"-----|");
    	System.out.println("-------------------------------------");
    }

	/**
     * @see HttpSessionListener#sessionDestroyed(HttpSessionEvent)
     */
    public void sessionDestroyed(HttpSessionEvent se)  { 
    	String sessionid = se.getSession().getId();  
    	System.out.println("-------------------------------------");
    	System.out.println("|--- session destroyed:"+sessionid+"-----|");
    	System.out.println("-------------------------------------");
     }
	
}
