package logica;

public class Combinacion {
	
	private int[] combinacion;
	private String name;
	private int hashCode;
	private double[] ems;
	private double p14;
	private Config config;
	
	public Combinacion(int[] combinacion, Config config) {
		this.combinacion = combinacion;
		this.hashCode = 0;
		this.config = config;
		this.p14 = 0.0;
	}
	
	public Combinacion(String combinacion, Config config) {
		this.name = combinacion;
		this.hashCode = 0;
		this.config = config;
		this.p14 = 0.0;
		int[] comb = new int[14];
		int cont = 0;
		for(Character ch : combinacion.toCharArray()) {
			if(ch.equals('X')) comb[cont] = 0;
			else if(ch.equals('1')) comb[cont] = 1;
			else comb[cont] = 2;
			cont++;
		}
		this.combinacion = comb;
	}
	
	public int comparacion(Combinacion c) {
		int cont = 0;
		for(int i = 0; i < combinacion.length; i++) {
			if(combinacion[i] == c.getCombinacion()[i]) cont++;
		}
		return cont;
	}
	
	public int tamanio() {
		return combinacion.length;
	}

	public double probabilidad14() {
		if(this.p14 != 0.0) return this.p14;
		double acum = 1.0;
		for(int i = 0; i < combinacion.length; i++) {
			acum *= this.config.getReales().getData()[i][combinacion[i]];
		}
		this.p14 = acum;
		return acum;
	}
	
	public int get(int i) {
		return combinacion[i];
	}
	
	public void set(int i, int signo) {
		combinacion[i] = signo;
	}

	public int[] getCombinacion() {
		return combinacion;
	}
	
	@Override
	public String toString() {
		if(this.name != null) return name;
		String retorno = "";
		for(int i : combinacion) {
			if(i == 0) retorno += "X";
			else retorno += i;
		}
		this.name = retorno;
		return retorno;
	}

	@Override
	public boolean equals(Object o) {
		if(!(o instanceof Combinacion)) return false;
		for(int i = 0; i < combinacion.length; i++) {
			if(combinacion[i] != ((Combinacion)o).get(i)) return false;
		}
		return true;
	}
	
	@Override
	public int hashCode() {
		if(hashCode != 0) return this.hashCode;
		int code = 0;
		for(int i = 0; i < combinacion.length; i++) {
			code += combinacion[i] * Math.pow(3, i);
		}
		this.hashCode = code;
		return code;
	}
	
	public double[] getProbabilidades() {
		double[][] p = this.config.getApostados().getData();
	    double[] probs = new double[5];
	    int[] signos = this.getCombinacion();
	    double p14 = 1.0;
	    for(int a = 0; a < 14; a++) {
	    	p14 *= p[a][signos[a]];
	    	double p13 = 1.0;
	    	for(int i = 0; i < 14; i++) {
	    		if(i != a) {
	    			p13 *= p[i][signos[i]];
	    		} 
	    		else {
	    			p13 *= (1.0 - p[i][signos[i]]);
	    		}
	    	}
	    	probs[3] += p13;
	    	for(int b = a + 1; b < 14; b++) {
	    		double p12 = 1.0;
	    		for(int i = 0; i < 14; i++) {
	    			if(i != a && i != b) {
	    				p12 *= p[i][signos[i]];
	    			}
	    			else {
	    				p12 *= (1.0 - p[i][signos[i]]);
	    			}
	    		}
	    		probs[2] += p12;
	    		for(int c = b + 1; c < 14; c++) {
	    			double p11 = 1.0;
	    			for(int i = 0; i < 14; i++) {
	    				if(i != a && i != b && i != c) {
	    					p11 *= p[i][signos[i]];
	    				}
	    				else {
	    					p11 *= (1.0 - p[i][signos[i]]);
	    				}
	    			}
	    			probs[1] += p11;
	    			for(int d = c + 1; d < 14; d++) {
	    				double p10 = 1.0;
	    				for(int i = 0; i < 14; i++) {
	    					if(i != a && i != b && i != c && i != d) {
	    						p10 *= p[i][signos[i]];
	    					}
	    					else {
	    						p10 *= (1.0 - p[i][signos[i]]);
	    					}
	    				}
	    				probs[0] += p10;
	    			}
	    		}
	    	}
	    }
	    probs[4] += p14;
	    return probs;
	}

	public double[] getEms() {
		return ems;
	}

	public void setEms(double[] ems) {
		this.ems = ems;
	}

	public void setP14(double p14) {
		this.p14 = p14;
	}

}
