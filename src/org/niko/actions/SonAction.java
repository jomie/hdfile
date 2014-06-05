package org.niko.actions;

public class SonAction extends BaseAction {

	
	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		new SonAction().doCommon();
		
	}

	@Override
	void init() {
		// TODO Auto-generated method stub
		System.out.println("son init.");
	}

	@Override
	boolean valiReq() throws Exception {
		// TODO Auto-generated method stub
		System.out.println("son valiReq.");
		return true;
	}

}
