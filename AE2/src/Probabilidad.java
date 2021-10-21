import java.io.File;
import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class Probabilidad {
	
	/*metodo: main()
	Descripción: Aplicación principal
	Parámetros de entrada: String[] args
	Parámetros de salida: no*/
	public static void main(String[] args) throws IOException {
		double posicionTierra = 1;
		double velocidadTierra = 100;
		double posicion = Double.parseDouble(args[0]);
		double velocidad = Double.parseDouble(args[1]);
		double resultado, miliIni, miliFin, tiempo;
		String nom = args[2];
		
		miliIni = System.currentTimeMillis();
		for (int i = 0; i < (50 * 365 * 24 * 60 * 60); i++) {
		posicion = posicion + velocidad * i;
		posicionTierra = posicionTierra + velocidadTierra * i;
		}
		resultado = 100 * Math.random() * Math.pow( ((posicion-posicionTierra)/(posicion+posicionTierra)), 2);
		resultado = Math.round(resultado*100.0)/100.0;
		miliFin = System.currentTimeMillis();
		tiempo = (miliFin - miliIni)/1000;
		if(resultado > 10) {
			System.err.println("\n\nLA PROBABILIDAD DE '" + nom + "' ES " + resultado + "%\n\nALERTA MUNDIAL");
			System.err.println("Tiempo total de ejecución: " + tiempo + " segundos");
		} else {
			System.out.println("\n\nPROBABILIDAD DE " + resultado + "%\n\nNO HAY QUE PREOCUPARSE");
			System.out.println("Tiempo total de ejecución: " + tiempo + " segundos");
		}
		escribirProb(nom, resultado);
	}
	
	/*metodo: escribirProb()
	Descripción: Escribe la probabilidad del NEO en un fichero con el mismo nombre
	Parámetros de entrada: String nom, double prob
	Parámetros de salida: no*/
	public static void escribirProb(String nom, double prob) throws IOException {
		File file = new File(nom + ".txt");
		file.createNewFile();
		Files.write(Paths.get(file.getName()), String.valueOf(prob).getBytes(), StandardOpenOption.TRUNCATE_EXISTING);
	}
	
}
