import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class ClientMainTest {
	public static void main(String args[]){
		ApplicationContext context = new ClassPathXmlApplicationContext(
				"spring-jms-client.xml");
		try {
			Thread.sleep(Long.MAX_VALUE);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
