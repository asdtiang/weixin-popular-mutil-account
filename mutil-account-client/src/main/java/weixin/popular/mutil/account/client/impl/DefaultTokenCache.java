package weixin.popular.mutil.account.client.impl;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import weixin.popular.api.TokenAPI;
import weixin.popular.bean.Token;
import weixin.popular.mutil.account.client.TokenInter;
import weixin.popular.mutil.account.client.TokenRequest;
import weixin.popular.mutil.account.client.cache.TokenCache;

public class DefaultTokenCache implements TokenCache{
	private TokenInter tokenInter;
	private long expiresTime = 7000000;
    private static Map<String,Token> cacheMap  = new ConcurrentHashMap<String,Token>();
	@Override
	public void setTokenCache(String weiXinName, Token token) {
		cacheMap.put(weiXinName, token);
	}

	@Override
	public void removeTokenCache(String weiXinName) {
		cacheMap.remove(weiXinName);		
	}

	@Override
	public String getToken(String weiXinName) {
		String tokenStr = "";
		Token token = null;
		if(weiXinName!=null){
			token = cacheMap.get(weiXinName);
			if(token!=null){
				long delayTime = (System.currentTimeMillis()-token.getGenTime());
				if(delayTime>expiresTime){
					TokenRequest tokenRequest = tokenInter.genTokenRequest(weiXinName);
					token = TokenAPI.token(tokenRequest.getAppId(), tokenRequest.getAppSecret());
					cacheMap.put(weiXinName, token);
				}
				tokenStr = token.getAccess_token();
			}else{
				TokenRequest tokenRequest = tokenInter.genTokenRequest(weiXinName);
				token = TokenAPI.token(tokenRequest.getAppId(), tokenRequest.getAppSecret());
				cacheMap.put(weiXinName, token);
				tokenStr = token.getAccess_token();
			}
			
		}
		return tokenStr;
	}

	public TokenInter getTokenInter() {
		return tokenInter;
	}

	public void setTokenInter(TokenInter tokenInter) {
		this.tokenInter = tokenInter;
	}

	public long getExpiresTime() {
		return expiresTime;
	}

	public void setExpiresTime(long expiresTime) {
		this.expiresTime = expiresTime;
	}
	
}
