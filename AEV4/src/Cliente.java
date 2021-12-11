import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;

public class Cliente {
	
	/*mètode: main()	
	Descripció: Executa l'aplicació client
	Paràmetres d'entrada: String[] args
	Paràmetres de salida: no*/
	public static void main(String[] args) throws IOException, InterruptedException, ClassNotFoundException {
		Thread.sleep(3000);
		System.out.println("CLIENTE >>> Arranca cliente");
		System.out.println("CLIENTE >>> Conexion al servidor");
		Socket socket = new Socket("localhost", 1234);
		
		System.out.println("CLIENTE >>> Recibe el objeto contraseña");
		ObjectInputStream inObj = new ObjectInputStream(socket.getInputStream());
		Contrasenya pass = (Contrasenya) inObj.readObject();
		
		System.out.println("CLIENTE >>> Rellena el objeto contraseña con el texto plano");
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.print("Texto plano: ");
		pass.setTextoPlano(br.readLine());
		
		System.out.println("CLIENTE >>> Reenvía el objeto contraseña con el texto plano");
		ObjectOutputStream outObj = new ObjectOutputStream(socket.getOutputStream());
		outObj.writeObject(pass);
		
		System.out.println("CLIENTE >>> Elige el método de encriptación");
		PrintWriter pw = new PrintWriter(socket.getOutputStream());
		System.out.print("Encriptación simple(1) | Encriptación MD5(2): ");
		pw.print(br.readLine() + "\n");
		pw.flush();
		
		System.out.println("CLIENTE >>> Recibe el objeto contraseña con la contraseña encriptada");
		inObj = new ObjectInputStream(socket.getInputStream());
		pass = (Contrasenya) inObj.readObject();
		
		System.out.println("El texto plano es: " + pass.getTextoPlano() + "\nLa contraseña encriptada es: " + pass.getEncriptada());
		socket.close();
	}


}