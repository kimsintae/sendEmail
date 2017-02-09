package email;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;


public class SendEmail {
	
	
    
    public static void main(String[] args){
        try{
            new SendEmail();
            System.out.println("전송 되었습니다.");
        }catch(Exception e){
        	System.out.println("전송 실패하였습니다.");
            e.printStackTrace();
        }
    }
	

    public SendEmail() throws Exception{    	
    	//https://www.tutorialspoint.com/javamail_api/javamail_api_smtp_servers.htm
    	
    	/* 
    	 * -네이버-
    	 * POP 서버명 : pop.naver.com
		   SMTP 서버명 : smtp.naver.com
		   POP 포트 : 995, 보안연결(SSL) 필요
		   SMTP 포트 : 465, 보안 연결(SSL) 필요

    	 -gmail-
    	   TLS/STARTTLS용 포트: 587
    	   SSL용 포트: 465
    	 */
    	
    	
        Properties props = new Properties();
        
        //네이버 : smtp.naver.com
        //구글 : smtp.gmail.com
	    props.put("mail.smtp.host", "smtp.gmail.com");			
        props.put("mail.smtp.port", "465");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.debug", "true");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.socketFactory.fallback", "false");
         
        //이메일 사용자 인증
        Authenticator auth = new Authenticator(){
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("kimstcool01", "adh59fls");
            }
        };
        
      
        //세션 생성은 인증과 프로퍼티 인자값을 통해 생성
        Session session = Session.getDefaultInstance(props,auth);
         
        MimeMessage message = new MimeMessage(session);
        message.setSender(new InternetAddress("kimstcool01@gmail.com"));//보내는사람
        message.setSubject("test !");//제목
        message.setText("This is mailing system test");//본문
        
        // text/html 로 컨텐트타입을 설정하면 html코드가 내장되어 보내집니다.
        //message.setContent("test", "text/html; charset=UTF-8");
        
        message.setRecipient(Message.RecipientType.TO, new InternetAddress("kimstcool01@naver.com"));//받는사람
         
        Transport.send(message);//전송
    }


}
