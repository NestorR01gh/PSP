import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Principal {
	
	/*metodo: main()
	Descripción: Aplicación principal
	Parámetros de entrada: String[] args
	Parámetros de salida: no*/
	public static void main(String[] args) throws IOException {
		double miliIni = System.currentTimeMillis();
		File neosFile = new File("NEOs.txt");
		List<NEO> NEOs = NEO.getNEOs(neosFile);
		int cores = Runtime.getRuntime().availableProcessors();
		int cont = 0;
		List<String> nombres = new ArrayList<String>();
		double miliFin, tiempo;
		
		limpiarFicheros(NEOs);
		for(NEO neo : NEOs) {
			cont++;
			nombres.add(neo.getNombre());
			ejecutar(neo.getPosicion(), neo.getVelocidad(), neo.getNombre());
			if(cont == cores || NEOs.indexOf(neo) == NEOs.size()-1) {
				cont = 0;
				while(!ficherosCreados(nombres)) {
					
				}
				nombres.clear();
			}
		}
		miliFin = System.currentTimeMillis();
		tiempo = (miliFin - miliIni)/1000;
		System.out.println("\nTiempo total de la aplicación completa: " + tiempo + " segundos");
	}
	
	/*metodo: limpiarFicheros()
	Descripción: elimina los ficheros asociados a los NEOs
	Parámetros de entrada: List<NEO> NEOs
	Parámetros de salida: no*/
	public static void limpiarFicheros(List<NEO> NEOs) {
		for(NEO neo : NEOs) {
			File file = new File(neo.getNombre() + ".txt");
			if(file.exists()) {
				file.delete();
			}
		}
	}
	
	/*metodo: ficherosCreados()
	Descripción: Comprueba que los ficheros asociados a los NEOs estén creados
	Parámetros de entrada: List<String> ficheros
	Parámetros de salida: boolean ok*/
	public static boolean ficherosCreados(List<String> ficheros) {
		boolean ok = true;
		for(String fichero : ficheros) {
			File file = new File(fichero + ".txt");
			if(!file.exists()) {
				ok = false;
			}
		}
		return ok;
	}
	
	/*metodo: ejecutar()
	Descripción: Ejecuta la clase Probabilidad.java
	Parámetros de entrada: double pos, souble vel, String nom
	Parámetros de salida: no*/
	public static void ejecutar(double pos, double vel, String nom) throws IOException {
		File directorioEj = new File("...");
		String clase = "Probabilidad";
		String javaHome = System.getProperty("java.home");
		String javaBin = javaHome + File.separator + "bin" + File.separator + "java";
		String classpath = System.getProperty("java.class.path");
		String className = clase;
		List<String> command = new ArrayList<>();
		command.add(javaBin);
		command.add("-cp");
		command.add(classpath);
		command.add(className);
		command.add(String.valueOf(pos));
		command.add(String.valueOf(vel));
		command.add(nom);
		
		ProcessBuilder builder = new ProcessBuilder(command);
		builder.directory(directorioEj);
		Process p = builder.inheritIO().start();
	}
	
}