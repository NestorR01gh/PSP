import java.util.ArrayList;
import java.util.List;

public class Ej2b {

	public static void main(String[] args) {
		List<String> noms = new ArrayList();
		
		noms.add("Claudiu");
		noms.add("N�stor");
		noms.add("V�ctor");
		noms.add("Manel");
		noms.add("Ximo");
		noms.add("Josep");
		for(String nom : noms) {
			System.out.println(nom);
		}
	}

}