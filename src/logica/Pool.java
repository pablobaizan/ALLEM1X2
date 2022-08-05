package logica;

import java.util.concurrent.ConcurrentLinkedQueue;

public class Pool {

	private ConcurrentLinkedQueue<Worker> libres;
	private Calc calc;
	private Config config;
	
	public Pool(Calc calc, Config config) {
		this.calc = calc;
		this.config = config;
		this.libres = new ConcurrentLinkedQueue<Worker>();
		for(int i = 0; i < this.config.getHilos(); i++) {
			this.libres.add(new Worker(this.calc, this.config, i));
		}
	}
	
	public boolean create(Worker old) {
		return this.libres.add(new Worker(this.calc, this.config, old.getNumber()));
	}
	
	public Worker take() {
		Worker toUse = this.libres.poll();
		return toUse;
	}
	
	public int size() {
		return this.libres.size();
	}
}
