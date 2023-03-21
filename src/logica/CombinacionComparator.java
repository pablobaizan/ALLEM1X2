package logica;

import java.util.Comparator;

public class CombinacionComparator implements Comparator<Combinacion> {

	private String option;
	
	public CombinacionComparator(String option) {
		this.option = option;
	}

	@Override
	public int compare(Combinacion o1, Combinacion o2) {
		switch(this.option) {
			case "prob14":
				return Float.compare(o2.probabilidad14(), o1.probabilidad14());
			case "emar":
				float emar1 = o1.getEms()[0] + o1.getEms()[1] + o1.getEms()[2] + o1.getEms()[3]; 
				float emar2 = o2.getEms()[0] + o2.getEms()[1] + o2.getEms()[2] + o2.getEms()[3]; 
				return Float.compare(emar2, emar1);
			case "emt":
				float emt1 = o1.getEms()[0] + o1.getEms()[1] + o1.getEms()[2] + o1.getEms()[3] + o1.getEms()[4]; 
				float emt2 = o2.getEms()[0] + o2.getEms()[1] + o2.getEms()[2] + o2.getEms()[3] + o2.getEms()[4]; 
				return Float.compare(emt2, emt1);
			case "EM10":
				return Float.compare(o2.getEms()[0], o1.getEms()[0]);
			case "EM11":
				return Float.compare(o2.getEms()[1], o1.getEms()[1]);
			case "EM12":
				return Float.compare(o2.getEms()[2], o1.getEms()[2]);
			case "EM13":
				return Float.compare(o2.getEms()[3], o1.getEms()[3]);
			case "EM14":
				return Float.compare(o2.getEms()[4], o1.getEms()[4]);
			case "mad":
				return Float.compare(o2.getMad(), o1.getMad());
			case "kelly":
				return Float.compare(o2.getKelly(), o1.getKelly());
			case "tvm":
				return Float.compare(o2.getTvm(), o1.getTvm());
			default:
				throw new RuntimeException("Opción no válida " + this.option);
		}
	}
	
}
