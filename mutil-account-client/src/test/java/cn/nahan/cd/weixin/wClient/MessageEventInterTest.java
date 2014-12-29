package cn.nahan.cd.weixin.wClient;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class MessageEventInterTest {
	public static void main(String args[]){
		ApplicationContext context = new ClassPathXmlApplicationContext(
				"spring-jms-dev.xml");	
	}
}
