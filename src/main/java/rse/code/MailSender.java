package rse.code;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Properties;
import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;

@Path("/send/mail")
public class MailSender {
	
	private static String sendermail = "anonymous.rse@gmail.com";
	private static String password = "rsegmail95";
	
	private static String receivermail_default = "rafael.eusebio95@gmail.com";
	private static String subject_default = "Test Email";
	private static String text_default = "Just a default text.\nYou are probably not surprised with that";
	
	@GET
	@Path("/{to}/{subject}/{message}")
	public void setEmail(@PathParam("to") String toAddress, @PathParam("subject") String subject, @PathParam("message") String message  ) {
		/*
		 * Should have some code here
		 */
	}
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public void receiveMessage( @FormParam("receivermail") String senderMail, @FormParam("subject") String subject, @FormParam("text") String text ) {
		
	}
	

	public static void  sendmail(String receivermail, String subject, String text) throws MessagingException {
		Stateful stateobj = new Stateful();
		
		try {
			stateobj.next("Starting the try statement", true);
			
			Properties prop = new Properties();
			prop.put("mail.smtp.auth", true);
			prop.put("mail.smtp.starttls.enable", "true");
			prop.put("mail.smtp.host", "smtp.gmail.com");
			prop.put("mail.smtp.port", "587");
			
			stateobj.next("Configured the prop object", true);
			
			Authenticator auth = new Authenticator() {
				@Override
			    protected PasswordAuthentication getPasswordAuthentication() {
			        return new PasswordAuthentication(sendermail, password);
			    }
			};
			
			stateobj.next("configured the authenticator", true);
			
			Session session = Session.getInstance(prop, auth);
						
			stateobj.next("created the session", true);
			
			
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(sendermail));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(receivermail));
			message.setSubject(subject);
			message.setText(text);
			
			stateobj.next("configure the message object", true);

			Transport.send(message);
			
			stateobj.next("Sent the message", true);

		}
		catch(Exception err) {
			
			System.out.println("Erro!!");
			System.out.println("Method's state is: " + stateobj.getState() + ".\n");

			System.out.println("error: " + err.getMessage());
			/*
			String[] msgs = err.getMessage().split("534-5.7.14");
			for(String msg : msgs) {
				System.out.print(msg);
			}
			*/
		}
	}
	public static void main(String[] args) throws MessagingException, AddressException {
		try {
			sendmail(receivermail_default, subject_default, text_default );
		}
		catch(Exception error) {
			System.out.println(
							   "An error has occourred.\n" 
		                       + 
					           "Error message: " 
		                       + 
					           error.getMessage()
		                       );
		}
		
		
	}
}
