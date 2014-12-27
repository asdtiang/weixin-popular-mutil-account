package weixin.popular.mutil.account.client.sesion;

import weixin.popular.bean.EventMessage;

public interface WeiXinContent {
	/**
	 * get session by fromName
	 * @param fromName
	 * @return
	 */
	WeiXinSession getWeiXinSession(String fromOpenId,String toUserOpenId);
	
	WeiXinSession getWeiXinSession(EventMessage eventMessage);
	/**
	 * remove session from content
	 * @param fromName
	 */
	void removeWeiXinSession(String fromOpenId,String toUserOpenId);
	/**
	 * add session to content
	 * @param session
	 */
	
	void addOrUpdateWeiXinSession(WeiXinSession session);
	/**
	 * set session timeout
	 * @param timemill
	 */
	void setSessionTimeout(long timemill);
	/**
	 * get session timeout
	 * @return time mill
	 */
	long getSessionTimeout();
	/**
	 * get WeiXinContent impl class
	 * @return
	 */
	WeiXinContent getContent();
}
