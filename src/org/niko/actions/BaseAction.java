package org.niko.actions;

import com.opensymphony.xwork2.ActionSupport;

public abstract class BaseAction extends ActionSupport {
	
	final boolean doCommon() throws Exception { 
		
		init();
		if ( ! valiReq() )
			return false;
//		doAction();
		return true;
	}
	
	abstract void init();
	abstract boolean valiReq() throws Exception;
//	abstract void doAction();
}
