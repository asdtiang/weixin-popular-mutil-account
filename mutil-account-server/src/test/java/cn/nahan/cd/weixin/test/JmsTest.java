package cn.nahan.cd.weixin.test;

import javax.jms.JMSException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import weixin.popular.mutil.account.server.jms.MessageSender;

@ContextConfiguration(locations = "classpath:spring-jms.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class JmsTest {
	
	@Autowired
	private MessageSender messageSender;
	
	@Test
	public void testSend(){
		try {
			messageSender.sendMessage("test Message",null);
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	

}
