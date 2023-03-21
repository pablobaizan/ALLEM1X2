package logica;

public final class CombinacionTool {
	
	public static final int[][] base = {
		{0, 1, 2},
		{0, 3, 6},
		{0, 9, 18},
		{0, 27, 54},
		{0, 81, 162},
		{0, 243, 486},
		{0, 729, 1458},
		{0, 2187, 4374},
		{0, 6561, 13122},
		{0, 19683, 39366},
		{0, 59049, 118098},
		{0, 177147, 354294},
		{0, 531441, 1062882},
		{0, 1594323, 3188646}
	};

	public static float[] getProbabilidades(byte[] signos, Config config) {
		float[][] p = config.getApostados().getData();
	    float[] probs = new float[5];
	    float p14 = 1.0f;
	    for(int a = 0; a < 14; a++) {
	    	p14 *= p[a][signos[a]];
	    	float p13 = 1.0f;
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
	    		float p12 = 1.0f;
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
	    			float p11 = 1.0f;
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
	    				float p10 = 1.0f;
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
	
	public static String getName(byte[] combinacion) {
		String retorno = "";
		for(int i : combinacion) {
			if(i == 0) retorno += "X";
			else retorno += i;
		}
		return retorno;
	}
	
	public static byte[] getArray(String combinacion) {
		byte[] retorno = new byte[14];
		for(int n = 0; n < 14; n++) {
			if(combinacion.charAt(n) == 'X') retorno[n] = 0;
			else if(combinacion.charAt(n) == '1') retorno[n] = 1;
			else if(combinacion.charAt(n) == '2') retorno[n] = 2;
			else {
				throw new RuntimeException("Error en la columna. Signo no admitido " + combinacion.charAt(n));
			}
		}
		return retorno;
	}
	
	public static float getProbabilidad14(byte[] combinacion, Config config) {
		float acum = 1.0f;
		for(int i = 0; i < combinacion.length; i++) {
			acum *= config.getReales().getData()[i][combinacion[i]];
		}
		return acum;
	}
	
	public static float getProbabilidadApostada14(byte[] combinacion, Config config) {
		float acum = 1.0f;
		for(int i = 0; i < combinacion.length; i++) {
			acum *= config.getApostados().getData()[i][combinacion[i]];
		}
		return acum;
	}
	
	public static int getHashCode(byte[] array) {
		int code = 0;
		for(byte i = 0; i < 14; i++) {
			code += (base[i][array[i]]);
		}
		return code;
	}
	
	public static int newHashCode(Integer hash, int pos, byte sub_value, byte add_value) {
		return hash - base[pos][sub_value] + base[pos][add_value];
	}
	
	public static byte[] getArray(Integer hash) {
		int h = hash.intValue();
		byte[] ret = new byte[14];   
		byte index = 0;    
		while(h > 0){    
		   ret[index++] = (byte)(h % 3);    
		   h /= 3;    
		}
		return ret;
	}
	
}
