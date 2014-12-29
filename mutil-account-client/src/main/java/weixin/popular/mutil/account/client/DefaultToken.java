package weixin.popular.mutil.account.client;

/**
 * 单个账号支持
 * @author abel.lee
 *2014年12月29日 下午4:24:51
 */
public class DefaultToken implements TokenInter{
	
	private TokenRequest tokenRequest;

	@Override
	public TokenRequest genTokenRequest(String fromUser) {
		return tokenRequest;
	}

	public TokenRequest getTokenRequest() {
		return tokenRequest;
	}

	public void setTokenRequest(TokenRequest tokenRequest) {
		this.tokenRequest = tokenRequest;
	}
	

}
