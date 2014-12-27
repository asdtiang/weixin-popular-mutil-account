package weixin.popular.mutil.account.server.jms;

import java.io.Serializable;
import java.util.Map;

import javax.jms.JMSException;


public interface MessageSenderInter {
	 void sendMessage(Serializable object ,Map<String,String> properties) throws JMSException ;
}
