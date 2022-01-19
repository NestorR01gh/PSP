import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

public class GestorHTTP implements HttpHandler {
	private int tempActual = 15;
	private int tempTermostato = 15;

	@Override
	public void handle(HttpExchange httpExchange) throws IOException {
		System.out.println("Llega petición");
		String requestParamValue = null;
		int postrequestParamValue = -1000;
		if ("GET".equals(httpExchange.getRequestMethod())) {
			requestParamValue = handleGetRequest(httpExchange);
			handleGETResponse(httpExchange, requestParamValue);
		} else if ("POST".equals(httpExchange.getRequestMethod())) {
			try {
				postrequestParamValue = handlePostRequest(httpExchange);
			} catch (IOException | MessagingException e1) {
				e1.printStackTrace();
			}
			try {
				handlePOSTResponse(httpExchange, postrequestParamValue);
			} catch (IOException | InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	private String handleGetRequest(HttpExchange httpExchange) {
		return httpExchange.getRequestURI().toString().split("\\?")[1];
	}

	private void handleGETResponse(HttpExchange httpExchange, String requestParamValue) throws IOException {
		if (requestParamValue.equals("temperaturaActual")) {
			OutputStream outputStream = httpExchange.getResponseBody();
			String htmlResponse = "<html><body><p>Temperatura actual: " + tempActual + "</p><p>Temperatura termostato: "
					+ tempTermostato + "</p></body></html>";
			httpExchange.sendResponseHeaders(200, htmlResponse.length());
			outputStream.write(htmlResponse.getBytes());
			outputStream.flush();
			outputStream.close();
		}
	}

	private int handlePostRequest(HttpExchange httpExchange) throws IOException, MessagingException {
		InputStream inputStream = httpExchange.getRequestBody();
		InputStreamReader isr = new InputStreamReader(inputStream);
		BufferedReader br = new BufferedReader(isr);
		String linea, set, averia, postRequest = "";
		int temp = -1000;
		while ((linea = br.readLine()) != null) {
			postRequest += linea + "\n";
		}
		System.out.println(postRequest);
		set = postRequest.split("=")[0];
		if (set.equals("setTemperatura")) {
			temp = Integer.parseInt(postRequest.split("=")[1].replace("\n", ""));
		}
		averia = postRequest.split(":")[0];
		if (averia.equals("notificarAveria")) {
			String mailRem = postRequest.split(";")[0].split("=")[0].split(":")[1];
			String mail = postRequest.split(";")[0].split("=")[1];
			String passRem = postRequest.split(";")[1].split("=")[0];
			String pass = postRequest.split(";")[1].split("=")[1];
			System.out.println(mail + " " + pass);
			if (mailRem.equals("email_remitente") && passRem.equals("pass_remitente") && !mail.equals(null)
					&& !pass.equals(null)) {
				System.out.println("Envía mail");
				String asunto = "AVERIA";
				String mensaje = "Ha habido una avería en una estufa";
				String host = "smtp.gmail.com";
				String port = "587";
				String[] mails = { "mantenimientoinvernalia@gmail.com", "megustaelfresquito@gmail.com" };
				String[] anexo = { "pdf.pdf", "imagen.png" };
				try {
					envioMail(mensaje, asunto, mail, pass, host, port, mails, anexo);
					}
					catch(Exception e) {
					  e.printStackTrace();
					}
			}
		}
		return temp;
	}

	private void regularTemperatura() throws InterruptedException {
		while (tempActual != tempTermostato) {
			tempActual++;
			Thread.sleep(5000);
		}
	}
	private void handlePOSTResponse(HttpExchange httpExchange, int requestParamValue)
			throws IOException, InterruptedException {
		OutputStream outputStream = httpExchange.getResponseBody();
		String htmlResponse = "";
		if (requestParamValue != -1000) {
			tempTermostato = requestParamValue;
			htmlResponse = "Se ha asignado " + requestParamValue + " como temperatura";
		}
		System.out.println(htmlResponse);
		httpExchange.sendResponseHeaders(200, htmlResponse.length());
		outputStream.write(htmlResponse.getBytes());
		outputStream.flush();
		outputStream.close();
		regularTemperatura();
	}

	public static void envioMail(String mensaje, String asunto, String email_remitente, String email_remitente_pass,
			String host_email, String port_email, String[] email_destino, String[] anexo)
			throws UnsupportedEncodingException, MessagingException {
		Properties props = System.getProperties();
		props.put("mail.smtp.host", host_email);
		props.put("mail.smtp.user", email_remitente);
		props.put("mail.smtp.clave", email_remitente_pass);
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.port", port_email);
		Session session = Session.getDefaultInstance(props);
		MimeMessage message = new MimeMessage(session);
		message.setFrom(new InternetAddress(email_remitente));
		message.addRecipients(Message.RecipientType.TO, email_destino[0]);
		message.addRecipients(RecipientType.CC, email_destino[1]);
		message.setSubject(asunto);
		BodyPart messageBodyPart1 = new MimeBodyPart();
		messageBodyPart1.setText(mensaje);
		BodyPart messageBodyPart2 = new MimeBodyPart();
		DataSource src = new FileDataSource(anexo[0]);
		messageBodyPart2.setDataHandler(new DataHandler((javax.activation.DataSource) src));
		messageBodyPart2.setFileName(anexo[0]);
		BodyPart messageBodyPart3 = new MimeBodyPart();
		DataSource src2 = new FileDataSource(anexo[1]);
		messageBodyPart3.setDataHandler(new DataHandler((javax.activation.DataSource) src2));
		messageBodyPart3.setFileName(anexo[1]);
		Multipart multipart = new MimeMultipart();
		multipart.addBodyPart(messageBodyPart1);
		multipart.addBodyPart(messageBodyPart2);
		multipart.addBodyPart(messageBodyPart3);
		message.setContent(multipart);
		Transport transport = session.getTransport("smtp");
		transport.connect(host_email, email_remitente, email_remitente_pass);
		transport.sendMessage(message, message.getAllRecipients());
		transport.close();
	}

}