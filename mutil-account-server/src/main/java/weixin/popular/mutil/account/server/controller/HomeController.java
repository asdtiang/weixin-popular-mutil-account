package weixin.popular.mutil.account.server.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

import javax.jms.JMSException;
import javax.servlet.ServletInputStream;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import weixin.popular.bean.EventMessage;
import weixin.popular.mutil.account.server.jms.MessageSenderInter;
import weixin.popular.util.XMLConverUtil;

@Controller
@RequestMapping("")
public class HomeController {
	private static final Logger log = LoggerFactory.getLogger(HomeController.class);
    @Autowired
	private MessageSenderInter messageSenderInter;
	
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public Object index() {
		return "test";
	}
	@RequestMapping(value = "/weixinCall")
	public void receiveMsg(HttpServletRequest request, HttpServletResponse response) throws IOException {
		ServletInputStream inputStream = request.getInputStream();
		ServletOutputStream outputStream = response.getOutputStream();
		String signature = request.getParameter("signature");
		String timestamp = request.getParameter("timestamp");
		String nonce = request.getParameter("nonce");
		String echostr = request.getParameter("echostr");
		//首次请求申请验证,返回echostr
		if(echostr!=null){
			outputStreamWrite(outputStream,echostr);
			return;
		}
		
//		//验证请求签名
//		if(!signature.equals(SignatureUtil.generateEventMessageSignature(token,timestamp,nonce))){
//			System.out.println("The request signature is invalid");
//			return;
//		}
		
		if(inputStream!=null){
			String xml = StreamUtils.copyToString(inputStream,Charset.forName("utf-8"));
			System.out.println("xml: "+xml);
			if(null!=xml){
				//转换XML
				EventMessage eventMessage = XMLConverUtil.convertToObject(EventMessage.class,xml);
				try {
					Map<String,String> properties = new HashMap<String,String>();
					properties.put("signature",signature);
					properties.put("timestamp",timestamp);
					properties.put("nonce",nonce);
					properties.put("echostr",echostr);
					messageSenderInter.sendMessage(eventMessage,properties);
				} catch (JMSException e) {
					log.error("receive msg send to jms error" + eventMessage.toString(),e);
				}
			}else{
				log.warn("receive xml is blank!!!!!");
			}
			
		}
		outputStreamWrite(outputStream,"");
	}
	/**
	 * 数据流输出
	 * @param outputStream
	 * @param text
	 * @return
	 */
	private boolean outputStreamWrite(OutputStream outputStream,String text){
		try {
			outputStream.write(text.getBytes("utf-8"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
	}

}
