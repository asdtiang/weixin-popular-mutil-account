package weixin.popular.mutil.account.client;

public class TokenRequest {
	private String weiXinName;
	private String appId;
	private String appSecret;
	
	public String getAppId() {
		return appId;
	}
	public void setAppId(String appId) {
		this.appId = appId;
	}
	public String getAppSecret() {
		return appSecret;
	}
	public void setAppSecret(String appSecret) {
		this.appSecret = appSecret;
	}
	public String getWeiXinName() {
		return weiXinName;
	}
	public void setWeiXinName(String weiXinName) {
		this.weiXinName = weiXinName;
	}
}
