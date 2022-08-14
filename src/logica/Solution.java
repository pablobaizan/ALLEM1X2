package logica;

import java.util.HashSet;
import java.util.concurrent.Semaphore;

public class Solution {

	private HashSet<Combinacion> set;
	private Semaphore busy;
	
	public Solution() {
		this.set = new HashSet<Combinacion>();
		this.busy = new Semaphore(1);
	}
	
	public void add(Combinacion c) {
		try {
			this.busy.acquire();
			this.set.add(c);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			this.busy.release();
		}
	}

	public HashSet<Combinacion> getSet() {
		return set;
	}
}
