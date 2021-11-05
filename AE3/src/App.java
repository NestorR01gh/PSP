import java.util.ArrayList;
import java.util.List;

public class App {
	
	/*m�todo: main()
	Descripci�n: Ejecuta el programa principal
	Par�metros de entrada: String[] args
	Par�metros de salida: no*/
	public static void main(String[] args) throws InterruptedException {
		int inicioMina = 50;
		List<Thread> ts = new ArrayList<Thread>();
		Mina mina = new Mina(inicioMina);
		Ventilador vent = new Ventilador(mina);
		Thread tEncender = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					vent.encenderVentilador();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		Thread tApagar = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					vent.apagarVentilador();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		
		tEncender.start();
		tApagar.start();
		for (int i = 0; i < 10; i++) {
			ts.add(new Thread(new Minero(mina)));
			ts.get(i).setName("Minero " + (i + 1));
			ts.get(i).start();
		}
		while (mina.getStock() > 0) {
			Thread.sleep(2000);
		}
		tEncender.join();
		tApagar.join();
		System.out.println("La mina ten�a " + inicioMina + " de oro y se ha extra�do " + Mina.getTotalExtraccion() + " de oro");
	}

}