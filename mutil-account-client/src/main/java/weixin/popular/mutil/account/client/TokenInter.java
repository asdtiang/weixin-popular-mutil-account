package weixin.popular.mutil.account.client;



public interface TokenInter {
	/**
	 * gen token info by weiXinId
	 * @param fromUser
	 * @return
	 */
	TokenRequest genTokenRequest(String weiXinId);
}
