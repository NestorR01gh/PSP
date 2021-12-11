import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.Socket;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Peticion implements Runnable {
	BufferedReader bfr;
	PrintWriter pw;
	Socket socket;

	public Peticion(Socket socket) {
		this.socket = socket;
	}

	/*
	 * m�tode: encriptaSimple() Descripci�: Encripta l'string passat com a par�metre
	 * canviant-lo per el seguent caracter ASCII i si no es imprimible el canvia per
	 * '*' Par�metres d'entrada: String plano Par�metres de salida: String pass
	 */
	public String encriptaSimple(String plano) {
		char[] ctres = plano.toCharArray();
		String pass = "";

		for (char c : ctres) {
			int ascii = (int) c;
			if (ascii < 33 || ascii > 125) {
				pass += "*";
			} else {
				ascii += 1;
				pass += (char) ascii;
			}
		}
		return pass;
	}

	public String getMetodo(int num) {
		if (num == 1) {
			return "Simple";
		} else if (num == 2) {
			return "MD5";
		} else {
			return "No existe";
		}
	}

	/*
	 * m�tode: encriptaMD5() Descripci�: Encripta l'string passat com a par�metre
	 * amb el m�tode MD5 Par�metres d'entrada: String plano Par�metres de salida:
	 * String pass
	 */
	public String encriptaMD5(String plano) throws UnsupportedEncodingException, NoSuchAlgorithmException {
		byte[] bytes = plano.getBytes("UTF-8");
		MessageDigest md = MessageDigest.getInstance("MD5");
		byte[] codificacion = md.digest(bytes);
		String pass = "";
		for (byte b : codificacion) {
			pass += b;
		}
		return pass;
	}

	/*
	 * m�tode: run() Descripci�: Executa el servidor Par�metres d'entrada: no
	 * Par�metres de salida: no
	 */
	public void run() {
		System.err.println(Thread.currentThread().getName());
		try {
			System.err.println("SERVIDOR (" + Thread.currentThread().getName() + ") >>> Se crea el objeto contrase�a y se env�a al cliente");
			ObjectOutputStream outObj = new ObjectOutputStream(socket.getOutputStream());
			Contrasenya contra = new Contrasenya();
			outObj.writeObject(contra);

			ObjectInputStream inObj = new ObjectInputStream(socket.getInputStream());
			System.err.println("SERVIDOR (" + Thread.currentThread().getName() + ") >>> Recibe el objeto contrase�a con texto plano");
			contra = (Contrasenya) inObj.readObject();

			System.err.println("SERVIDOR (" + Thread.currentThread().getName() + ") >>> Pregunta el m�todo de encriptaci�n");
			InputStream is = socket.getInputStream();
			InputStreamReader isr = new InputStreamReader(is);
			BufferedReader bf = new BufferedReader(isr);

			int metodo = Integer.parseInt(bf.readLine());
			System.err.println("SERVIDOR (" + Thread.currentThread().getName() + ") >>> Recibe el m�todo de encriptaci�n (" + getMetodo(metodo) + ") y encripta");
			if (metodo == 2) {
				contra.setEncriptada(encriptaMD5(contra.getTextoPlano()));
			} else {
				contra.setEncriptada(encriptaSimple(contra.getTextoPlano()));
			}

			System.err.println("SERVIDOR (" + Thread.currentThread().getName() + ") >>> Env�a el objeto contrase�a con la contrase�a encriptada");
			outObj = new ObjectOutputStream(socket.getOutputStream());
			outObj.writeObject(contra);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
	}

}
