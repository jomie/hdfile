package org.niko.utils;

import java.util.Map;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class UserAuthorityInterceptor extends AbstractInterceptor{

	private static final long serialVersionUID = 3064430118345133265L;

	@SuppressWarnings("rawtypes")
	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		Map session = invocation.getInvocationContext().getSession();
		String username = (String)session.get("username");
		String adminname = (String)session.get("adminName");
		if (null != username && !"".equals(username)) {
			return invocation.invoke();
		} else if (null != adminname && !adminname.equals("")) {
			return invocation.invoke();
		} else {
			return Action.LOGIN;
		}
	}

}
