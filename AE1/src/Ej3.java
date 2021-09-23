import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Ej3 {

	public static int sumaPares(int tope) {
		int num = 0;
		for(int i = 2; i <= tope; i+=2) {
			num+=i;
		}
		return num;
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		int num;
		
		System.out.print("Introduce número: ");
		num = Integer.parseInt(br.readLine());
		System.out.print("Resultado: " + sumaPares(num));
	}

}
