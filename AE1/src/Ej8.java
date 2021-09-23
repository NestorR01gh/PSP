import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Ej8 {
	
	public static boolean esPrimo(int num) {
		boolean ok = true;
		for(int i = 2; i < num; i++) {
			if(num % i == 0) {
				ok = false;
			}
		}
		return ok;
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		int n1, n2;
		double miliIni, miliFin, tiempo;
		
		miliIni = System.currentTimeMillis();
		System.out.print("Introduce número 1: ");
		n1 = Integer.parseInt(br.readLine());
		System.out.print("Introduce número 2: ");
		n2 = Integer.parseInt(br.readLine());
		if(n2 < n1){
			int nTemp = n1;
			n1 = n2;
			n2 = nTemp;
		}
		while(n1 <= n2) {
			System.out.print(n1);
			if(esPrimo(n1)) {
				System.out.println(" | Es primo");
			} else {
				System.out.println(" | No es primo");
			}
			n1++;
		}
		miliFin = System.currentTimeMillis();
		tiempo = (miliFin - miliIni);
		System.out.print("Tiempo total de ejecución: " + tiempo + " segundos");
	}

}