package weixin.popular.mutil.account.client.cache;

import weixin.popular.bean.Token;



public interface TokenCache {
	void setTokenCache(String weiXinName,Token token);
	void removeTokenCache(String weiXinName);
	/**
	 * get token by weiXinName,first form cache then get from weiXin api
	 * @param weiXinName
	 * @return
	 */
	String getToken(String weiXinName);
}
