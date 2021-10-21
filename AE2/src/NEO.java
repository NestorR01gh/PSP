import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

public class NEO {
	private String nombre;
	private double posicion;
	private double velocidad;
	
	public NEO(String nom, double pos, double	 vel) {
		nombre = nom;
		posicion = pos;
		velocidad = vel;
	}
	
	/*metodo: getNombre()
	Descripción: Devuelve el nombre del NEO
	Parámetros de entrada: no
	Parámetros de salida: String nombre*/
	public String getNombre() {
		return nombre;
	}
	
	/*metodo: getPosicion()
	Descripción: Devuelve la posición del NEO
	Parámetros de entrada: no
	Parámetros de salida: double posicion*/
	public double getPosicion() {
		return posicion;
	}
	
	/*metodo: getVelocidad()
	Descripción: Devuelve la velocidad del NEO
	Parámetros de entrada: no
	Parámetros de salida: double velocidad*/
	public double getVelocidad() {
		return velocidad;
	}
	
	/*metodo: getNEOs()
	Descripción: Devuelve una lista de NEOs a partir de los datos del fichero
	Parámetros de entrada: File file
	Parámetros de salida: List<NEO> NEOs*/
	public static List<NEO> getNEOs(File file) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(file));
		String linea;
		List<NEO> NEOs = new ArrayList<NEO>();
		
		while((linea = br.readLine()) != null) {
			NEO neo;
			String[] params = linea.split(",");
			
			neo = new NEO(params[0], Double.parseDouble(params[1]), Double.parseDouble(params[2]));
			NEOs.add(neo);
		}
		br.close();
		return NEOs;
	}
}