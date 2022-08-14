package logica;

import java.util.concurrent.Semaphore;

public class Counter {

	private int busies;
	private Semaphore semaphore;
	
	public Counter() {
		this.busies = 0;
		this.semaphore = new Semaphore(1);
	}
	
	public void increaseBusies() {
		try {
			this.semaphore.acquire();
			this.busies++;
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			this.semaphore.release();
		}
	}
	
	public void decreaseBusies() {
		try {
			this.semaphore.acquire();
			this.busies--;
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			this.semaphore.release();
		}
	}

	public int getBusies() {
		return busies;
	}
}
