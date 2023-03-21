package principal;

import java.util.HashSet;

import logica.Calc;
import logica.Config;
import logica.Percentage;
import ui.ISetter;
import ui.SetterFactory;

public class Launcher {

	private SetterFactory sFactory;
	private ISetter actual;
	private Config config;
	
	public Launcher() {
		this.sFactory = new SetterFactory(this);
		this.actual = null;
		this.config = new Config();
	}
	
	public void launch() throws Exception {
		System.out.println();
		System.out.println();
		System.out.println("Programa realizado por Pablo Baizán Fernández");
		System.out.println("Gestor de la peña quinielista Los Habaneros");
		System.out.println("Twitter: @loshabaneros1x2");
		System.out.println("Email: loshabaneros1x2@gmail.com");
		System.out.println();
		System.out.println();
		
		Calc calc = new Calc(this.config);
		
		System.out.println("Inicio del proceso.");
		
		calc.run();
		
		while(calc.getPool().size() < this.config.getHilos() && calc.getBusies() != 0) {
		}
		System.out.println("Ordenando columnas y preparando salida. Dependiendo del tamaño de la madre, ésto puede llevar unos instantes...");
		calc.print();
		System.out.println("Proceso finalizado.");
	}

	public void setSetters(String[] args, boolean unchecked) {
		HashSet<String> obligatorios = new HashSet<String>();
		obligatorios.add("-ap");
		obligatorios.add("-o");
		obligatorios.add("-ar");
		obligatorios.add("-r");
		obligatorios.add("-a");
		for(String s : args) {
			if(s.equals("-load")) {
				obligatorios.remove("-ap");
				obligatorios.remove("-ar");
				obligatorios.remove("-r");
				obligatorios.remove("-a");
			}
			obligatorios.remove(s);
			if(s.startsWith("-") && this.actual == null) {
				this.actual = this.sFactory.create(s.substring(1));
			}
			else if(this.actual != null) {
				this.actual.load(s);
				this.actual.set();
				this.actual = null;
			}
			else {
				throw new RuntimeException("Entrada de argumentos no v�lida.");
			}
		}
		if(!unchecked && obligatorios.size() != 0) {
			throw new RuntimeException("No se han introducido todos los par�metros obligatorios."
					+ " Falta(n) " + obligatorios.size());
		}
	}

	public void setApuestas(int apuestas) {
		this.config.setApuestasLAE(apuestas);
	}

	public void setPorcsLAE(Percentage porcsLAE) {
		this.config.setApostados(porcsLAE);
	}

	public void setPorcsReal(Percentage porcsReal) {
		this.config.setReales(porcsReal);
	}

	public void setFile(String fileMadre) {
		this.config.setFileName(fileMadre);
	}

	public void setFileOut(String fileOut) {
		this.config.setFileOut(fileOut);
	}
	
	public void setFileCSV(String fileCSV) {
		this.config.setFileCSV(fileCSV);
	}
	
	public void setFileSaved(String fileSaved) {
		this.config.setFileSaved(fileSaved);
	}
	
	public void setSortOption(String option) {
		this.config.setSortOption(option);
	}

	public void setHilos(int hilos) {
		this.config.setHilos(hilos);
	}
	
	public void setEMT(float emt) {
		this.config.setEMT(emt);
	}
	
	public void setEMAR(float emar) {
		this.config.setEMAR(emar);
	}
	
	public void setEMS(float[] ems) {
		this.config.setEMS(ems);
	}
	
	public void setTop(int top) {
		this.config.setTop(top);
	}
	
	public void setDistancia(int distancia) {
		this.config.setDistancia(distancia);
	}
	
	public void setReparto(float[] reparto) {
		this.config.setReparto(reparto);
	}

}
