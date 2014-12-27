package weixin.popular.mutil.account.client.listener;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.ObjectMessage;
import javax.jms.TextMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import weixin.popular.bean.EventMessage;
import weixin.popular.mutil.account.client.MessageEventInter;
import weixin.popular.mutil.account.client.sesion.WeiXinContent;
import weixin.popular.mutil.account.client.sesion.WeiXinSession;

public class WeiXinMessageListener implements WeiXinMessageListenerInter {

	private MessageEventInter messageEventInter;
	private WeiXinContent weiXinContent;
	private static final Logger log = LoggerFactory
			.getLogger(WeiXinMessageListener.class);

	@Override
	public void onMessage(Message message) {
		if (message instanceof TextMessage) {
			try {
				String msgText = ((TextMessage) message).getText();
				log.info("About to process message: " + msgText);

			} catch (JMSException jmsEx_p) {
				String errMsg = "An error occurred extracting message";
				log.error(errMsg, jmsEx_p);
			}
		}
		if (message instanceof ObjectMessage) {
			try {
				ObjectMessage objectMessage = (ObjectMessage) message;
				Object object = objectMessage.getObject();
				if (object instanceof EventMessage) {
					EventMessage eventMessage = (EventMessage) objectMessage
							.getObject();
					WeiXinSession session = weiXinContent.getWeiXinSession(
							eventMessage.getFromUserName(),
							eventMessage.getToUserName());
					if (session == null) {
						session = new WeiXinSession(
								eventMessage.getFromUserName(),
								eventMessage.getToUserName());
						weiXinContent.addOrUpdateWeiXinSession(session);
					}else{
						session.setLastAccess(System.currentTimeMillis());
					}
					messageEventInter.onMessage(eventMessage);
				} else {
					log.warn("receive msg is not EventMessage type!-->"
							+ objectMessage.getObject().toString());
				}

			} catch (JMSException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			log.info(message.getClass().getName() + message.toString());
		}
	}

	public MessageEventInter getMessageEventInter() {
		return messageEventInter;
	}

	public void setMessageEventInter(MessageEventInter messageEventInter) {
		this.messageEventInter = messageEventInter;
	}

	public WeiXinContent getWeiXinContent() {
		return weiXinContent;
	}

	public void setWeiXinContent(WeiXinContent weiXinContent) {
		this.weiXinContent = weiXinContent;
	}

}
