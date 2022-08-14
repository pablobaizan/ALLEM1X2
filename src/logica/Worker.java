package logica;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Worker extends Thread {
	
	private Calc calc;
	private Combinacion combinacion;
	private int number;
	private Config config;

	public Worker(Calc calc, Config config, int number) {
		this.calc = calc;
		this.number = number;
		this.config = config;
	}
	
	public void run() {
		this.calc.increaseBusies();
		try {
			List<HashSet<Combinacion>> all = new ArrayList<HashSet<Combinacion>>();
			List<List<double[]>> premios = new ArrayList<List<double[]>>();
			for(int i = 0; i < 5; i++) {
				all.add(new HashSet<Combinacion>());
				premios.add(new ArrayList<double[]>());
			}
			todosLosPremios(this.combinacion, this.combinacion, 0, all);
			double[] ems = new double[5];
			for(int i = 0; i < 5; i++) {
				for(Combinacion p : all.get(i)) {
					double[] probs = this.calc.getMem().get(p.toString());
					if(probs == null) {
						probs = p.getProbabilidades();	
						this.calc.getMem().put(p.toString(), probs);
					}
					double probT = p.probabilidad14();
					double acertantes = (int)(probs[i] * this.calc.getConfig().getApuestasLAE());
					if(acertantes == 0) acertantes += 1;
					double premio = (this.calc.getConfig().getApuestasLAE() 
//							* this.calc.getConfig().getImporte() 
							* this.calc.getConfig().getReparto()[i]) / acertantes;
					premios.get(i).add(new double[] {premio, probT});
				}
				for(double[] p : premios.get(i)) {
					double val = p[0] * p[1];
					ems[i] += val;
				}
			}
			this.combinacion.setEms(ems);
			this.calc.printAll(this);
			boolean added = false;
			while(!added) {
				added = this.calc.getPool().create(this);	
			}
		}
		catch(Exception e) {
			e.printStackTrace();
			System.out.println("Tratando de recuperar hilo (" + this.number + ")...");
			Worker w = new Worker(this.calc, this.config, this.number);
			w.setCombinacion(this.combinacion);
			w.start();
			System.out.println("Relanzado hilo (" + this.number + ").");
		}
		finally {
			this.calc.decreaseBusies();
		}
	}
	
	public void todosLosPremios(Combinacion obj, Combinacion c, int pivot, List<HashSet<Combinacion>> all) {
		for(int i = pivot; i < 14; i++) {
			for(int j = 0; j < 3; j++) {
				int[] vs = c.getCombinacion().clone();
				vs[i] = j;
				Combinacion nc = new Combinacion(vs, this.config);
				int comunes = obj.comparacion(nc);
				if(comunes >= 10) {
					boolean inserted = all.get(comunes - 10).add(nc);
					if(inserted) todosLosPremios(obj, nc, i + 1, all);
				}
				else return;
			}
		}
	}
	
	public int hashCode() {
		return this.number;
	}
	
	public boolean equals(Object o) {
		if(!(o instanceof Worker)) return false;
		Worker w = (Worker)o;
		if(w.getNumber() == this.number) return true;
		return false;
	}

	public Combinacion getCombinacion() {
		return combinacion;
	}

	public void setCombinacion(Combinacion combinacion) {
		this.combinacion = combinacion;
	}

	public int getNumber() {
		return number;
	}
}
