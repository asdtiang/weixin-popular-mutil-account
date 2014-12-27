package weixin.popular.mutil.account.client.sesion;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import weixin.popular.bean.EventMessage;

public class DefaultWeiXinContent implements WeiXinContent{
	private Map<String,WeiXinSession> sessions = new ConcurrentHashMap<String,WeiXinSession>();
	private long sessionTimeout=3600000;
	private static final Logger log = LoggerFactory.getLogger(DefaultWeiXinContent.class);
	private long scanTimeoutInterval = 300000;
	public void init(){
		new Thread(new Runnable(){
			@Override
			public void run() {
				log.info("-------------->weiXin sesion timeout thread scan start!!!");
				while(true){
					try {
						Thread.sleep(scanTimeoutInterval);
					} catch (InterruptedException e) {
						log.error("scansession timeout thread sleep error!! ",e);
					}
					long now = System.currentTimeMillis();
					List<String> removeKeys = new ArrayList<String>();
					for(WeiXinSession session:sessions.values()){
						if((now-session.getLastAccess())>sessionTimeout){
							removeKeys.add(session.getFromOpenId()+session.getToOpenId());
						}
					}
					for(String key:removeKeys){
						sessions.remove(key);
						log.info("remove session from sessions :" + key);
					}
				}
			}
		}).start();
	}
	

	@Override
	public void setSessionTimeout(long timemill) {
		if(timemill<1200000){
			log.warn("session timeout must bigger than "+1200000);
		}else{
			sessionTimeout = timemill;	
		}
	}

	@Override
	public WeiXinContent getContent() {
		return this;
	}


	@Override
	public long getSessionTimeout() {
		return sessionTimeout;
	}


	@Override
	public void addOrUpdateWeiXinSession(WeiXinSession session) {
		if(session!=null){
			if(session.getFromOpenId()!=null){
				sessions.put(session.getFromOpenId()+session.getToOpenId(), session);
			}else{
				log.warn("add  weixin session's fromName is null");
			}
		}else{
			log.warn("add  weixin session is null");
		}
	}

	@Override
	public WeiXinSession getWeiXinSession(String fromOpenId, String toUserOpenId) {
		return sessions.get(fromOpenId +toUserOpenId);
	}

	@Override
	public void removeWeiXinSession(String fromOpenId, String toUserOpenId) {
		if(null!=fromOpenId&&toUserOpenId!=null){
			sessions.remove(fromOpenId +toUserOpenId);
			log.info("remove weixin session from content, fromOpenId is "+fromOpenId+" toUserOpenId is "+toUserOpenId);
		}else{
			log.warn("remove weixin session fail fromOpenId is " + fromOpenId +"  toUserOpenId "+toUserOpenId);
		}
		
	}


	public long getScanTimeoutInterval() {
		return scanTimeoutInterval;
	}


	public void setScanTimeoutInterval(long scanTimeoutInterval) {
		this.scanTimeoutInterval = scanTimeoutInterval;
	}


	@Override
	public WeiXinSession getWeiXinSession(EventMessage eventMessage) {
		WeiXinSession session = null;
		if(eventMessage!=null&&eventMessage.getFromUserName()!=null){
			session =  this.getWeiXinSession(eventMessage.getFromUserName(),eventMessage.getToUserName());
		}else{
			log.warn("getWeiXinSession error -->"+eventMessage);
		}
		return session;
	}
	
}
