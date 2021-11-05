import java.util.Random;

public class Minero implements Runnable {
	private int bolsa;
	private int tiempoExtraccion = 1000;
	private Mina mina;
	
	/*m�todo: Minero()
	Descripci�n: Constructor de la clase Minero
	Par�metros de entrada: Mina mina
	Par�metros de salida: int stock*/
	public Minero(Mina mina) {
		bolsa = 0;
		this.mina = mina;
	}
	
	/*m�todo: extraerRecurso()
	Descripci�n: extrae recursos de la mina mientras hayan
	Par�metros de entrada: no
	Par�metros de salida: no*/
	public void extraerRecurso() throws InterruptedException {
		while(mina.getStock() > 0) {
			Random r = new Random();
			int extrae = r.nextInt(5) + 1;
			synchronized (mina) {
				if((mina.getStock() - extrae) >= 0) {
					mina.restaStock(extrae);
					Mina.sumaTotalExtraccion(extrae);
					bolsa += extrae;
					System.out.println(Thread.currentThread().getName() + " acaba de extraer " + extrae + ", ya ha extra�do " + bolsa + " en total y en la mina queda " + mina.getStock() + " de oro");
					Thread.sleep(tiempoExtraccion);
				} else {
					System.err.println(Thread.currentThread().getName() + " no puede extraer " + extrae + " porque quedan " + mina.getStock());
				}
			}
		}
	}
	
	/*m�todo: run()
	Descripci�n: M�todo que ejecuta el hilo
	Par�metros de entrada: no
	Par�metros de salida: no*/
	@Override
	public void run() {
		try {
			extraerRecurso();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}