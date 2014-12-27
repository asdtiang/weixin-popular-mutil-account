package weixin.popular.mutil.account.client;

import weixin.popular.bean.EventMessage;


public interface MessageEventInter {
	void onMessage(EventMessage eventMessage);
}
