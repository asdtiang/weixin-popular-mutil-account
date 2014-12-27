package weixin.popular.mutil.account.server.jms;

import java.io.Serializable;
import java.util.Map;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Queue;
import javax.jms.Session;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;


@Service
public class MessageSender implements  MessageSenderInter{
	@Autowired
	private Queue queue;
	@Autowired
	private JmsTemplate jmsTemplate;
	private static final Logger log = LoggerFactory.getLogger(MessageSender.class);

	public void sendMessage(final Serializable object,final Map<String,String> properties) throws JMSException {
		log.info("send message:"+object.toString());
		jmsTemplate.send(queue,new MessageCreator(){
			@Override
			public Message createMessage(Session session) throws JMSException {
				Message msg = session.createObjectMessage(object);
				if(null!=properties){
					for(String key:properties.keySet()){
						msg.setStringProperty(key, properties.get(key));
					}
				}
				return msg;
			}
		});
	}

	public Queue getQueue() {
		return queue;
	}

	public void setQueue(Queue queue) {
		this.queue = queue;
	}

	public JmsTemplate getJmsTemplate() {
		return jmsTemplate;
	}

	public void setJmsTemplate(JmsTemplate jmsTemplate) {
		this.jmsTemplate = jmsTemplate;
	}


}
