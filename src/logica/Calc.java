package logica;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Semaphore;

public class Calc extends Thread {
	
	private Config config;
	private Solution solution;
	private ConcurrentHashMap<String, double[]> mem;
	private Pool pool;
	private Semaphore busy;
	private Counter busies;
	
	public Calc(Config config) {
		this.config = config;
		this.busy = new Semaphore(1);
		this.solution = new Solution();
		this.pool = new Pool(this, this.config);
		this.mem = new ConcurrentHashMap<String, double[]>();
		this.busies = new Counter();
	}

	public void run() {
		try {
			if(this.config.getFileSaved() != null) {
				BufferedReader br =  new BufferedReader(new FileReader(this.config.getFileSaved()));
				int cont = 0;
				while(br.ready()) {
					cont++;
					if(cont == 1) {
						br.readLine();
						continue;
					}
					String line = br.readLine();
					String[] pieces = line.split("\t");
					Combinacion c = new Combinacion(pieces[0], this.config);
					c.setP14(Double.parseDouble(pieces[1]));
					c.setEms(new double[] {
							Double.parseDouble(pieces[2]), 
							Double.parseDouble(pieces[3]), 
							Double.parseDouble(pieces[4]), 
							Double.parseDouble(pieces[5]), 
							Double.parseDouble(pieces[6])
							});
					if(this.checkEM(c.getEms())) this.solution.add(c);
				}
				br.close();
			}
			else {
				BufferedReader br = new BufferedReader(new FileReader(this.config.getFileName()));
				while(br.ready()) {
					String str = br.readLine();
					Combinacion obj = new Combinacion(str, this.config);
					Worker w = null;
					do {
						w = this.pool.take();
					}
					while(w == null);
					w.setCombinacion(obj);
					w.start();
				}
				br.close();	
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void printAll(Worker w) {
		try {
			this.busy.acquire();
			if(checkEM(w.getCombinacion().getEms())) {
				this.getSolution().add(w.getCombinacion());
				this.consolePrint(true, w.getCombinacion());
			}
			else {
				this.consolePrint(false, w.getCombinacion());
			}	
		}
		catch(InterruptedException e) {
			e.printStackTrace();
		}
		finally {
			this.busy.release();
		}
	}
	
	public void consolePrint(boolean aceptada, Combinacion c) {
		String str = "";
		double emar = c.getEms()[0] + c.getEms()[1] + c.getEms()[2] + c.getEms()[3];
		if(aceptada) {
			str += "Aceptada ";
		}
		else {
			str += "----- Rechazada ";
		}
		str += c.toString() 
			+ " / EM Total -> " + (int)((emar + c.getEms()[4]) * 1000.0) / 1000.0 
			+ " / EM arrastres -> " + (int)(emar * 1000.0) / 1000.0 
			+ " / EMs categorías 10 a 14 -> " 
			+ (int)(c.getEms()[0] * 1000.0) / 1000.0 
			+ " -- " + (int)(c.getEms()[1] * 1000.0) / 1000.0 
			+ " -- " + (int)(c.getEms()[2] * 1000.0) / 1000.0 
			+ " -- " + (int)(c.getEms()[3] * 1000.0) / 1000.0 
			+ " -- " + (int)(c.getEms()[4] * 1000.0) / 1000.0;
		System.out.println(str);
	}
	
	public boolean checkEM(double[] ems) {
		double emt = 0.0;
		for(int i = 0; i < 5; i++) {
			if(this.getConfig().getEMS()[i] > ems[i]) return false;
			emt += ems[i];
		}
		if(emt < this.getConfig().getEMT()) return false;
		if(emt - ems[4] < this.getConfig().getEMAR()) return false;
		return true;
	}
	
	public void print() {
		try {
			CombinacionComparator cc = new CombinacionComparator(this.config.getSortOption());
			List<Combinacion> listaOrdenada = new ArrayList<Combinacion>();
			listaOrdenada.addAll(this.solution.getSet());
			listaOrdenada.sort(cc);
			BufferedWriter bw = new BufferedWriter(new FileWriter(this.config.getFileOut()));
			int top = this.config.getTop();
			int cont = 0;
			List<Combinacion> temp = new ArrayList<Combinacion>();
			for(Combinacion c : listaOrdenada) {
				boolean mantieneDistancia = true;
				if(temp.size() == 0) {
					temp.add(c);
				}
				else {
					for(Combinacion t : temp) {
						if(14 - t.comparacion(c) < this.config.getDistancia()) {
							mantieneDistancia = false;
							break;
						}
					}
				}
				if(mantieneDistancia) {
					cont++;
					temp.add(c);
					bw.write(c.toString());
					bw.newLine();
				}
				if(cont >= top) break;
			}
			if(this.config.getFileCSV() != null) {
				BufferedWriter bwCSV = new BufferedWriter(new FileWriter(this.config.getFileCSV()));
				bwCSV.write("Columna" + "\t" + "Prob14" + "\t" + "EM10" + "\t" + "EM11" + "\t" + "EM12" + "\t" + "EM13" + "\t" + "EM14");
				bwCSV.newLine();
				for(Combinacion c : listaOrdenada) {
					bwCSV.write(c.toString());
					bwCSV.write("\t" + c.probabilidad14());
					bwCSV.write("\t" + c.getEms()[0]);
					bwCSV.write("\t" + c.getEms()[1]);
					bwCSV.write("\t" + c.getEms()[2]);
					bwCSV.write("\t" + c.getEms()[3]);
					bwCSV.write("\t" + c.getEms()[4]);
					bwCSV.newLine();
				}	
				bwCSV.close();
			}
			bw.close();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

	public ConcurrentHashMap<String, double[]> getMem() {
		return mem;
	}

	public Config getConfig() {
		return config;
	}

	public HashSet<Combinacion> getSolution() {
		return solution.getSet();
	}

	public Pool getPool() {
		return pool;
	}
	
	public void increaseBusies() {
		this.busies.increaseBusies();
	}
	
	public void decreaseBusies() {
		this.busies.decreaseBusies();
	}

	public int getBusies() {
		return busies.getBusies();
	}
	
}
