package cn.nahan.cd.weixin.wClient;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@ContextConfiguration(locations = "classpath:spring-jms-dev.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class JmsReceiveTest {
	
	
	@Test
	public void testSend(){
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	

}