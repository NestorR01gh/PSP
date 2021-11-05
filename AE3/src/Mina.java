	
public class Mina {
	private int stock;
	private static int totalExtraccion = 0;
	
	/*método: Mina()
	Descripción: Constructor de la clase mina
	Parámetros de entrada: int stock
	Parámetros de salida: no*/
	public Mina(int stock) {
		this.stock = stock;
	}
	
	/*método: getStock()
	Descripción: Devuelve el stock
	Parámetros de entrada: no
	Parámetros de salida: int stock*/
	public int getStock() {
		return stock;
	}
	
	/*método: setStock()
	Descripción: Determina el stock de la mina
	Parámetros de entrada: int stock
	Parámetros de salida: no*/
	public void setStock(int stock) {
		this.stock = stock;
	}
	
	/*método: restaStock()
	Descripción: resta la cantidad indicada al stock
	Parámetros de entrada: int resta
	Parámetros de salida: no*/
	public void restaStock(int resta) {
		stock -= resta;
	}
	
	/*método: getTotalExtraccion()
	Descripción: Devuelve el total de la extracción
	Parámetros de entrada: no
	Parámetros de salida: int totalExtraccion*/
	public static int getTotalExtraccion() {
		return totalExtraccion;
	}
	
	/*método: sumaTotalExtraccion()
	Descripción: Suma la cantidad indicada al total de la extracción
	Parámetros de entrada: int suma
	Parámetros de salida: no*/
	public static void sumaTotalExtraccion(int suma) {
		totalExtraccion += suma;
	}
}