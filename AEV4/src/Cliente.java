import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;

public class Cliente {
	
	/*m�tode: main()	
	Descripci�: Executa l'aplicaci� client
	Par�metres d'entrada: String[] args
	Par�metres de salida: no*/
	public static void main(String[] args) throws IOException, InterruptedException, ClassNotFoundException {
		Thread.sleep(3000);
		System.out.println("CLIENTE >>> Arranca cliente");
		System.out.println("CLIENTE >>> Conexion al servidor");
		Socket socket = new Socket("localhost", 1234);
		
		System.out.println("CLIENTE >>> Recibe el objeto contrase�a");
		ObjectInputStream inObj = new ObjectInputStream(socket.getInputStream());
		Contrasenya pass = (Contrasenya) inObj.readObject();
		
		System.out.println("CLIENTE >>> Rellena el objeto contrase�a con el texto plano");
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.print("Texto plano: ");
		pass.setTextoPlano(br.readLine());
		
		System.out.println("CLIENTE >>> Reenv�a el objeto contrase�a con el texto plano");
		ObjectOutputStream outObj = new ObjectOutputStream(socket.getOutputStream());
		outObj.writeObject(pass);
		
		System.out.println("CLIENTE >>> Elige el m�todo de encriptaci�n");
		PrintWriter pw = new PrintWriter(socket.getOutputStream());
		System.out.print("Encriptaci�n simple(1) | Encriptaci�n MD5(2): ");
		pw.print(br.readLine() + "\n");
		pw.flush();
		
		System.out.println("CLIENTE >>> Recibe el objeto contrase�a con la contrase�a encriptada");
		inObj = new ObjectInputStream(socket.getInputStream());
		pass = (Contrasenya) inObj.readObject();
		
		System.out.println("El texto plano es: " + pass.getTextoPlano() + "\nLa contrase�a encriptada es: " + pass.getEncriptada());
		socket.close();
	}


}