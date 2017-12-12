package com.ajax;

public class AJAXFactory {
	
	private AJAXFactory() {
		
	}

	private static AJAXFactory INSTANCE = null;
	
	
	public static AJAXFactory getActionFactory() {
		if(INSTANCE==null) {
			synchronized(AJAXFactory.class) {
				if(INSTANCE==null) {
					INSTANCE = new AJAXFactory();
				}				
			}
		}
		return INSTANCE;
	}

	public AJAX getAction(String actionClassName) {
		AJAX action = null;
		try {
			action = (AJAX)Class.forName(actionClassName).newInstance();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return action;
	}

}
