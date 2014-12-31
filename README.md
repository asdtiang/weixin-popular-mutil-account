微信多帐号支持
==============

微信公众平台Java SDK,多帐户支持,通过jms实现
基于 https://github.com/liyiorg/weixin-popular 项目更改


![image](https://github.com/asdtiang/weixin-popular-mutil-account/tree/master/mutil-account-server/image/w-jms.png)
用户发送的消息首先到weiXin服务器，weiXin服务器发送消息到我们的指定地址，由w-server接收，w-server接收到消息后发送消息到jms队列并返回空字符串给weiXin
    服务器，其它的webApp异步监听jms队列。
发送消息
当消息被webApp接收到后，处理业务逻辑，完成后同步发送消息给用户。
组件说明：
W-server：负责接收微信消息，并且异步发送消息到jms队列，注意暴露地址需要80端口（微信api要求）。
Jms:负责消息异步处理和缓存。
webApp：具体的业务组件，可以使用同一个w-server配置不同的queue，实现多个业务组件。

通过以上架构后,本地只对接jms服务器后,即可实现本地开发,不用去配置网络,直接可以本地调试,同时也实现多帐户.

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


