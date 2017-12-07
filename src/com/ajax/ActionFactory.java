package com.ajax;

public class ActionFactory {
	
	private ActionFactory() {
		
	}

	private static ActionFactory INSTANCE = null;
	
	
	public static ActionFactory getActionFactory() {
		if(INSTANCE==null) {
			synchronized(ActionFactory.class) {
				if(INSTANCE==null) {
					INSTANCE = new ActionFactory();
				}				
			}
		}
		return INSTANCE;
	}

	public Action getAction(String actionClassName) {
		Action action = null;
		try {
			action = (Action)Class.forName(actionClassName).newInstance();
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
