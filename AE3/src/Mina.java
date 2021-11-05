	
public class Mina {
	private int stock;
	private static int totalExtraccion = 0;
	
	/*m�todo: Mina()
	Descripci�n: Constructor de la clase mina
	Par�metros de entrada: int stock
	Par�metros de salida: no*/
	public Mina(int stock) {
		this.stock = stock;
	}
	
	/*m�todo: getStock()
	Descripci�n: Devuelve el stock
	Par�metros de entrada: no
	Par�metros de salida: int stock*/
	public int getStock() {
		return stock;
	}
	
	/*m�todo: setStock()
	Descripci�n: Determina el stock de la mina
	Par�metros de entrada: int stock
	Par�metros de salida: no*/
	public void setStock(int stock) {
		this.stock = stock;
	}
	
	/*m�todo: restaStock()
	Descripci�n: resta la cantidad indicada al stock
	Par�metros de entrada: int resta
	Par�metros de salida: no*/
	public void restaStock(int resta) {
		stock -= resta;
	}
	
	/*m�todo: getTotalExtraccion()
	Descripci�n: Devuelve el total de la extracci�n
	Par�metros de entrada: no
	Par�metros de salida: int totalExtraccion*/
	public static int getTotalExtraccion() {
		return totalExtraccion;
	}
	
	/*m�todo: sumaTotalExtraccion()
	Descripci�n: Suma la cantidad indicada al total de la extracci�n
	Par�metros de entrada: int suma
	Par�metros de salida: no*/
	public static void sumaTotalExtraccion(int suma) {
		totalExtraccion += suma;
	}
}