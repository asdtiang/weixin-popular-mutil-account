微信多帐号支持
==============

微信公众平台Java SDK,多帐户支持,通过jms实现
基于 https://github.com/liyiorg/weixin-popular 项目更改


简介:
==============
weixin-jms 包括微信公众平台基础API,提供便捷的API调用接口,消息接收后通过jms(activemq)异步处理,方便在多个微信公众号和单个公众号之间切换。
示例可以w-client sping-jms-client 
<bean id="messageEventInter" class="cn.nahan.cd.weixin.client.DefaultMessageEvent">
		<property name="tokenCache" ref="tokenCache" />
	</bean>
    <!-- 根据微信公众号获取token，token会缓存起来 ,超时后自动获取新的token-->
	<bean id="tokenInter" class="cn.nahan.cd.weixin.client.DefaultToken">
		<property name="tokenRequest" ref="tokenRequest" />
	</bean>
	<bean id="tokenRequest" class="cn.nahan.cd.weixin.bean.TokenRequest">
		<property name="weiXinName" value="weiXinName" />
		<property name="appId" value="appId" />
		<property name="appSecret" value="appSecret" />
	</bean>
发送消息的地方注入tokenCache（有待改进）。
用户消息事件处理：DefaultMessageEvent实现这个方法的onEvent方法。

项目介绍:
==============
mutil-account-server:
接收微信消息，地址类似：http://localhost/weixinCall.do。所有要接入的微信公众号，订阅号地址都填写一致。
处理流程，先放到jms队列，然后由mutil-account-client消费。

mutil-account-client
业务对接需要实现这个jar里的接口WeiXinMessageListenerInter，TokenInter。


