package logica;

public class Combinacion {
	
	private byte[] combinacion;
	private String name;
	private int hashCode;
	private float[] probs;
	private float[] ems;
	private float p14;
	private Config config;
	private float mad;
	private float kelly;
	private float tvm;
	
	public Combinacion(byte[] combinacion, Config config) {
		this.combinacion = combinacion;
		this.hashCode = -1;
		this.config = config;
		this.p14 = 0.0f;
	}
	
	public Combinacion(String combinacion, Config config) {
		this.name = combinacion;
		this.hashCode = -1;
		this.config = config;
		this.p14 = 0.0f;
		byte[] comb = new byte[14];
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

	public float probabilidad14() {
		if(this.p14 != 0.0) return this.p14;
		this.p14 = CombinacionTool.getProbabilidad14(this.combinacion, this.config);
		return this.p14;
	}
	
	public int get(int i) {
		return combinacion[i];
	}
	
	public void set(byte i, byte signo) {
		combinacion[i] = signo;
	}

	public byte[] getCombinacion() {
		return combinacion;
	}
	
	@Override
	public String toString() {
		if(this.name != null) return name;
		this.name = CombinacionTool.getName(this.combinacion);
		return this.name;
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
		if(hashCode != -1) return this.hashCode;
		this.hashCode = CombinacionTool.getHashCode(this.combinacion);
		return this.hashCode;
	}
	
	public float[] getProbabilidades() {
		if(this.probs != null) return probs;
		this.probs = CombinacionTool.getProbabilidades(this.combinacion, this.config);
		return this.probs;
	}

	public float[] getEms() {
		return ems;
	}

	public void setEms(float[] ems) {
		this.ems = ems;
	}

	public void setP14(float p14) {
		this.p14 = p14;
	}

	public float getMad() {
		return mad;
	}

	public void setMad(float eProb) {
		this.mad = eProb;
	}

	public float getKelly() {
		return kelly;
	}

	public void setKelly(float kelly) {
		this.kelly = kelly;
	}

	public float getTvm() {
		return tvm;
	}

	public void setTvm(float tvm) {
		this.tvm = tvm;
	}

}
