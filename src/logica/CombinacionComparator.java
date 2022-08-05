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
				return Double.compare(o2.probabilidad14(), o1.probabilidad14());
			case "emar":
				double emar1 = o1.getEms()[0] + o1.getEms()[1] + o1.getEms()[2] + o1.getEms()[3]; 
				double emar2 = o2.getEms()[0] + o2.getEms()[1] + o2.getEms()[2] + o2.getEms()[3]; 
				return Double.compare(emar2, emar1);
			case "emt":
				double emt1 = o1.getEms()[0] + o1.getEms()[1] + o1.getEms()[2] + o1.getEms()[3] + o1.getEms()[4]; 
				double emt2 = o2.getEms()[0] + o2.getEms()[1] + o2.getEms()[2] + o2.getEms()[3] + o2.getEms()[4]; 
				return Double.compare(emt2, emt1);
			case "EM10":
				return Double.compare(o2.getEms()[0], o1.getEms()[0]);
			case "EM11":
				return Double.compare(o2.getEms()[1], o1.getEms()[1]);
			case "EM12":
				return Double.compare(o2.getEms()[2], o1.getEms()[2]);
			case "EM13":
				return Double.compare(o2.getEms()[3], o1.getEms()[3]);
			case "EM14":
				return Double.compare(o2.getEms()[4], o1.getEms()[4]);
			default:
				throw new RuntimeException("Opción no válida " + this.option);
		}
	}
	
}
