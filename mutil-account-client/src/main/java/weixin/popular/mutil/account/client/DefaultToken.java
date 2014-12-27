package weixin.popular.mutil.account.client;


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
