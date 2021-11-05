import java.util.Random;

public class Minero implements Runnable {
	private int bolsa;
	private int tiempoExtraccion = 1000;
	private Mina mina;
	
	/*método: Minero()
	Descripción: Constructor de la clase Minero
	Parámetros de entrada: Mina mina
	Parámetros de salida: int stock*/
	public Minero(Mina mina) {
		bolsa = 0;
		this.mina = mina;
	}
	
	/*método: extraerRecurso()
	Descripción: extrae recursos de la mina mientras hayan
	Parámetros de entrada: no
	Parámetros de salida: no*/
	public void extraerRecurso() throws InterruptedException {
		while(mina.getStock() > 0) {
			Random r = new Random();
			int extrae = r.nextInt(5) + 1;
			synchronized (mina) {
				if((mina.getStock() - extrae) >= 0) {
					mina.restaStock(extrae);
					Mina.sumaTotalExtraccion(extrae);
					bolsa += extrae;
					System.out.println(Thread.currentThread().getName() + " acaba de extraer " + extrae + ", ya ha extraído " + bolsa + " en total y en la mina queda " + mina.getStock() + " de oro");
					Thread.sleep(tiempoExtraccion);
				} else {
					System.err.println(Thread.currentThread().getName() + " no puede extraer " + extrae + " porque quedan " + mina.getStock());
				}
			}
		}
	}
	
	/*método: run()
	Descripción: Método que ejecuta el hilo
	Parámetros de entrada: no
	Parámetros de salida: no*/
	@Override
	public void run() {
		try {
			extraerRecurso();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}