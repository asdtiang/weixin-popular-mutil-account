package weixin.popular.mutil.account.client.sesion;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
  WeiXinSession
 * @author asdtiang
 *
 */
public class WeiXinSession implements java.io.Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1807254037196830411L;
	private String toOpenId;
	/**
	 * from user cache key
	 */
	private String fromOpenId;
	private Map<String,Object> sessionParams;
	private long createTime;
	private long lastAccess;
	/**
	 * session name, use for route
	 */
	private String sessionName;
	
	public WeiXinSession(String fromOpenId,String toOpenId){
		this.toOpenId = toOpenId;
		this.fromOpenId = fromOpenId;
		sessionParams = new ConcurrentHashMap<String,Object>();
		createTime = System.currentTimeMillis();
		lastAccess= createTime;
	} 
	/**
	 * set session parameter
	 * @param key
	 * @param value
	 */
	public void setSessionParam(String key,Object value){
		sessionParams.put(key, value);
	}
	/**
	 * get parameter by key
	 * @param key
	 * @return store value by key if exist,null not exist
	 */
	public Object getSessionParam(String key){
		return sessionParams.get(key);
		
	}

	public Map<String, Object> getSessionParams() {
		return sessionParams;
	}

	public void setSessionParams(Map<String, Object> sessionParams) {
		this.sessionParams = sessionParams;
	}


	public String getToOpenId() {
		return toOpenId;
	}
	public String getFromOpenId() {
		return fromOpenId;
	}
	public long getCreateTime() {
		return createTime;
	}
	public void setCreateTime(long createTime) {
		this.createTime = createTime;
	}
	
	public String getSessionName() {
		return sessionName;
	}
	public void setSessionName(String sessionName) {
		this.sessionName = sessionName;
	}
	public long getLastAccess() {
		return lastAccess;
	}
	public void setLastAccess(long lastAccess) {
		this.lastAccess = lastAccess;
	}
	
}
