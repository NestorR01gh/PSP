import java.io.Serializable;

@SuppressWarnings("serial")
public class Contrasenya implements Serializable {
	
	private String textoPlano;
	private String encriptada;
	
	/*mètode: getTextoPlano()	
	Descripció: Retorna el text plà
	Paràmetres d'entrada: no
	Paràmetres de salida: String textoPlano*/
	public String getTextoPlano() {
		return textoPlano;
	}
	
	/*mètode: setTextoPlano()	
	Descripció: Assigna un nou valor a textoPlano
	Paràmetres d'entrada: String textoPlano
	Paràmetres de salida: no*/
	public void setTextoPlano(String textoPlano) {
		this.textoPlano = textoPlano;
	}
	
	/*mètode: getEncriptada()	
	Descripció: Retorna el text encriptat
	Paràmetres d'entrada: no
	Paràmetres de salida: String encriptada*/
	public String getEncriptada() {
		return encriptada;
	}

	/*mètode: setEncriptada()	
	Descripció: Assigna un nou valor a encriptada
	Paràmetres d'entrada: String encriptada
	Paràmetres de salida: no*/
	public void setEncriptada(String encriptada) {
		this.encriptada = encriptada;
	}
	
}
