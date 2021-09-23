import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Ej7 {

	public static void main(String[] args) throws IOException {
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		String nom;
		double anyo;
		
		System.out.print("Introduce nombre: ");
		nom = br.readLine();
		System.out.print("Experiencia: ");
		anyo = Integer.parseInt(br.readLine());
		
		if(anyo < 1) {
			System.out.print(nom + "\tDesarrollador Junior L1\t15000-18000");
		} else if(anyo <= 3) {
			System.out.print(nom + "\tDesarrollador Junior L2\t18000-22000");
		} else if(anyo <= 5) {
			System.out.print(nom + "\tDesarrollador Senior L1\t22000-28000");
		} else if(anyo <= 8) {
			System.out.print(nom + "\tDesarrollador Senior L2\t28000-36000");
		} else {
			System.out.print(nom + "\tAnalista/Arquitecto\tSalario a convertir en base a rol");
		}
	}

}