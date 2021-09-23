import java.util.Iterator;

public class Ej4 {

	public static void main(String[] args) {
		double num = 1;
		for(int i = 1; i <= 15; i ++) {
			num *= i;
			System.out.println(num);
		}
		System.out.print(num);
	}

}