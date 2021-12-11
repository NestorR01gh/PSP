import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {
	
	/*mètode: main()
	Descripció: Executa l'aplicació servidor
	Paràmetres d'entrada: String[] args
	Paràmetres de salida: no*/
	@SuppressWarnings("resource")
	public static void main(String[] args) throws IOException {
		System.err.println("SERVIDOR >>> Arranca el servidor, espera peticion");
		ServerSocket socketEscucha = null;
		try {
			socketEscucha = new ServerSocket(1234);
		} catch (IOException e) {
			System.err.println("SERVIDOR >>> Socket error");
			return;
		}
		int nHilo = 1;
		while (true) {
			Socket conexion = socketEscucha.accept();
			System.err.println("SERVIDOR >>> Conexion recibida");
			Peticion p = new Peticion(conexion);
			Thread hilo = new Thread(p);
			hilo.setName("Hilo: " + nHilo);
			hilo.start();
			nHilo++;
		}
	}
}