package mdp.ingenieria.clinicagestion.model;

public class AmbulanciaMock {
	
	private boolean state = false;

	public AmbulanciaMock() {
		// TODO Auto-generated constructor stub
	}

	
	
	 public synchronized void solicitarAtencionDomicilio(){
        while (!state) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        
        state = true;
        notifyAll();
    }

    public synchronized void solicitarTraslado(){
        while (state) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        state = false;
        notifyAll();
    }

    public synchronized void solicitarMantenimiento(){
        while (state) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        this.state = false;
        notifyAll();
    }

    public synchronized void retornoAutomatico(){
    	this.state = true;
        notifyAll();
    }
}
