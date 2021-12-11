import java.io.Serializable;

@SuppressWarnings("serial")
public class Contrasenya implements Serializable {
	
	private String textoPlano;
	private String encriptada;
	
	/*m�tode: getTextoPlano()	
	Descripci�: Retorna el text pl�
	Par�metres d'entrada: no
	Par�metres de salida: String textoPlano*/
	public String getTextoPlano() {
		return textoPlano;
	}
	
	/*m�tode: setTextoPlano()	
	Descripci�: Assigna un nou valor a textoPlano
	Par�metres d'entrada: String textoPlano
	Par�metres de salida: no*/
	public void setTextoPlano(String textoPlano) {
		this.textoPlano = textoPlano;
	}
	
	/*m�tode: getEncriptada()	
	Descripci�: Retorna el text encriptat
	Par�metres d'entrada: no
	Par�metres de salida: String encriptada*/
	public String getEncriptada() {
		return encriptada;
	}

	/*m�tode: setEncriptada()	
	Descripci�: Assigna un nou valor a encriptada
	Par�metres d'entrada: String encriptada
	Par�metres de salida: no*/
	public void setEncriptada(String encriptada) {
		this.encriptada = encriptada;
	}
	
}
