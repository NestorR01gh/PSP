
public class Ventilador {
	private boolean enMarcha = false;
	private int tiempo = 2000;
	private Mina mina;
	
	/*método: Ventilador()
	Descripción: Constructor de la clase Ventilador
	Parámetros de entrada: Mina mina
	Parámetros de salida: no*/
	public Ventilador(Mina mina) {
		this.mina = mina;
	}

	/*método: encenderVentilador()
	Descripción: Enciende el ventilador si está apagado y notifica al otro hilo, si no, espera
	Parámetros de entrada: no
	Parámetros de salida: no*/
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
	
	/*método: apagarVentilador()
	Descripción: Apaga el ventilador si está encendido y notifica al otro hilo, si no, espera
	Parámetros de entrada: no
	Parámetros de salida: no*/
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
