
public class Ventilador {
	private boolean enMarcha = false;
	private int tiempo = 2000;
	private Mina mina;
	
	/*m�todo: Ventilador()
	Descripci�n: Constructor de la clase Ventilador
	Par�metros de entrada: Mina mina
	Par�metros de salida: no*/
	public Ventilador(Mina mina) {
		this.mina = mina;
	}

	/*m�todo: encenderVentilador()
	Descripci�n: Enciende el ventilador si est� apagado y notifica al otro hilo, si no, espera
	Par�metros de entrada: no
	Par�metros de salida: no*/
	public void encenderVentilador() throws InterruptedException{
		while (mina.getStock() > 0) {
			synchronized (this) {
				while(enMarcha) {
					wait();
				}
				enMarcha = true;
				System.out.println("VENTILADOR PUESTO EN MARCHA");
				notify();
				Thread.sleep(tiempo);
			}
		}
	}
	
	/*m�todo: apagarVentilador()
	Descripci�n: Apaga el ventilador si est� encendido y notifica al otro hilo, si no, espera
	Par�metros de entrada: no
	Par�metros de salida: no*/
	public void apagarVentilador() throws InterruptedException {
		while (mina.getStock() > 0) {
			synchronized (this) {
				while(!enMarcha) {
					wait();
				}
				enMarcha = false;
				System.out.println("VENTILADOR APAGADO");
				notify();
				Thread.sleep(tiempo);
			}
		}
	}

}
