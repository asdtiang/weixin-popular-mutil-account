package weixin.popular.mutil.account.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import weixin.popular.api.MessageAPI;
import weixin.popular.bean.BaseResult;
import weixin.popular.bean.EventMessage;
import weixin.popular.bean.message.Message;
import weixin.popular.bean.message.TextMessage;
import weixin.popular.mutil.account.client.cache.TokenCache;
import weixin.popular.util.XMLConverUtil;


public class DefaultMessageEvent implements MessageEventInter{
	private static final Logger log = LoggerFactory.getLogger(DefaultMessageEvent.class);

	private TokenCache tokenCache;
	@Override
	public void onMessage(EventMessage eventMessage) {
		String receiveXml = XMLConverUtil.convertToXML(eventMessage);
		log.info("receive msg:\n"+receiveXml);
		log.info("\n msgType:"+eventMessage.getMsgType());
		String token = tokenCache.getToken(eventMessage.getToUserName());
		log.info("token from cache:"+token);
		Message returnMsg = new TextMessage(eventMessage.getFromUserName(), 
				"收到您的消息 "+eventMessage.getMsgType()+eventMessage.getContent());
		log.info("send msg to "+eventMessage.getFromUserName());
		BaseResult result= MessageAPI.messageCustomSend(token, returnMsg);
		log.info("send result-->"+"token:"+token+result.getErrcode()+result.getErrmsg());
		
	}
	public TokenCache getTokenCache() {
		return tokenCache;
	}
	public void setTokenCache(TokenCache tokenCache) {
		this.tokenCache = tokenCache;
	}
	

}
