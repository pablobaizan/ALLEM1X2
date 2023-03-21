package logica;

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
			Integer[][] all = new Integer[5][];
			all[0] = new Integer[16016];
			all[1] = new Integer[2912];
			all[2] = new Integer[364];
			all[3] = new Integer[28];
			all[4] = new Integer[1];
			todosLosPremios(this.combinacion, all);
			float[] ems = new float[5];
			float mad = 0.0f;
			float pa = 0.0f;
			for(int i = 0; i < 5; i++) {
				for(Integer p : all[i]) {
					float[] premio = this.calc.getMem().get(p);
					if(premio == null) {
						byte[] a = CombinacionTool.getArray(p);
						float[] probs = CombinacionTool.getProbabilidades(a, this.config);
						float[] acertantes = new float[5];
						premio = new float[6];
						premio[5] = CombinacionTool.getProbabilidad14(a, this.config);
						for(int j = 0; j < 5; j++) {
							acertantes[j] = (int)(probs[j] * this.calc.getConfig().getApuestasLAE());
							if(acertantes[j] == 0) acertantes[j] += 1;
							premio[j] = (this.calc.getConfig().getApuestasLAE() 
//									* this.calc.getConfig().getImporte() 
									* this.calc.getConfig().getReparto()[j]) / acertantes[j];	
						}
						this.calc.getMem().put(p, premio);
					}
					float val = premio[i] * premio[5];
					if(premio[i] > 1.0) {
						mad += (premio[5] * val);
					}
					pa += premio[5];
					ems[i] += val;
				}
			}
			float emt = ems[0] + ems[1] + ems[2] + ems[3] + ems[4];
			this.combinacion.setKelly((emt - 1.0f) / (emt / pa - 1.0f));
			this.combinacion.setTvm(
					CombinacionTool.getProbabilidadApostada14(this.combinacion.getCombinacion(), this.config) 
					* this.calc.getConfig().getApuestasLAE()
					);
			this.combinacion.setEms(ems);
			this.combinacion.setMad(mad);
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
	
	public void todosLosPremios(Combinacion obj, Integer[][] all) {
		byte[] arr = obj.getCombinacion();
		Integer a4 = CombinacionTool.getHashCode(arr);
		all[4][0] = a4;
		int[] cont = new int[] {0, 0, 0, 0};
		for(int a = 0; a < 14; a++) {
			for(byte i = 0; i < 3; i++) {
				if(arr[a] != i) {
					Integer a3 = CombinacionTool.newHashCode(a4, a, arr[a], i);
					all[3][cont[3]++] = a3;
					for(int b = a + 1; b < 14; b++) {
						for(byte j = 0; j < 3; j++) {
							if(arr[b] != j) {
								Integer a2 = CombinacionTool.newHashCode(a3, b, arr[b], j);
								all[2][cont[2]++] = a2;
								for(int c = b + 1; c < 14; c++) {
									for(byte k = 0; k < 3; k++) {
										if(arr[c] != k) {
											Integer a1 = CombinacionTool.newHashCode(a2, c, arr[c], k);
											all[1][cont[1]++] = a1;
											for(int d = c + 1; d < 14; d++) {
												for(byte l = 0; l < 3; l++) {
													if(arr[d] != l) {
														Integer a0 = CombinacionTool.newHashCode(a1, d, arr[d], l);
														all[0][cont[0]++] = a0;
													}
												}
											}
										}
									}
								}
							}
						}
					}
				}
			}
		}
	}
	
//	public void todosLosPremios(Combinacion obj, List<List<byte[]>> all) {
//		byte[] arr = obj.getCombinacion().clone();
//		all.get(4).add(arr);
//		for(int a = 0; a < 14; a++) {
//			for(byte i = 0; i < 3; i++) {
//				if(arr[a] != i) {
//					byte[] arr_i = arr.clone();
//					arr_i[a] = i;
//					all.get(3).add(arr_i);
//					for(int b = a + 1; b < 14; b++) {
//						for(byte j = 0; j < 3; j++) {
//							if(arr_i[b] != j) {
//								byte[] arr_j = arr_i.clone();
//								arr_j[b] = j;
//								all.get(2).add(arr_j);
//								for(int c = b + 1; c < 14; c++) {
//									for(byte k = 0; k < 3; k++) {
//										if(arr_j[c] != k) {
//											byte[] arr_k = arr_j.clone();
//											arr_k[c] = k;
//											all.get(1).add(arr_k);
//											for(int d = c + 1; d < 14; d++) {
//												for(byte l = 0; l < 3; l++) {
//													if(arr_k[d] != l) {
//														byte[] arr_l = arr_k.clone();
//														arr_l[d] = l;
//														all.get(0).add(arr_l);
//													}
//												}
//											}
//										}
//									}
//								}
//							}
//						}
//					}
//				}
//			}
//		}
//	}
	
//	public void todosLosPremios(Combinacion obj, Combinacion c, int pivot, int com, List<HashSet<Combinacion>> all) {
//		for(int i = pivot; i < 14; i++) {
//			for(int j = 0; j < 3; j++) {
//				int[] vs = c.getCombinacion().clone();
//				vs[i] = j;
//				Combinacion nc = new Combinacion(vs, this.config);
//				int comunes = com;
//				if(obj.get(i) != j) {
//					comunes -= 1;
//				}
//				if(comunes >= 10) {
//					boolean inserted = all.get(comunes - 10).add(nc);
//					if(inserted && comunes > 10) {
//						todosLosPremios(obj, nc, i + 1, comunes, all);
//					}
//				}
//				else return;
//			}
//		}
//	}
	
//	private float[] getParametrosLoglogistica(List<float[]> lista, float pAt) {
//        float[] out = new float[2];
//        lista.sort((a, b) -> {return float.compare(a[0], b[0]);});
//        float alfa = 0.0;
//        float beta = 0.0;
//        float sx = 0.0;
//        float sx2 = 0.0;
//        float sy = 0.0;
//        float sxy = 0.0;
//        float np = 0.0;
//        float pA = 0.0;
//        for(int i = 0; i < lista.size(); i++) {
//          if(lista.get(i)[0] > 0.0) {
//            float x = Math.log(lista.get(i)[0]);
//            pA += lista.get(i)[1];
//            float y = Math.log(1.0 / (pA / pAt) - 1.0);
//            if(!float.isNaN(y) && !float.isNaN(x)) {
//            	sx += x;
//                sx2 += (x * x);
//                sy += y;
//                sxy += (x * y);
//                np += 1.0;	
//            }
//          }
//        }
//        float m = (np * sxy - sx * sy) / (np * sx2 - sx * sx);
//        float b = (sy * sx2 - sx * sxy) / (np * sx2 - sx * sx);
//        beta = (-1.0) * m;
//        alfa = Math.exp(b / beta);
//        out[0] = alfa;
//        out[1] = beta;
//        return out;
//      }
	
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
