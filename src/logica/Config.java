package logica;

public class Config {
	
	private String fileName;
	private String fileOut;
	private String fileCSV;
	private String fileSaved;
	private Percentage reales;
	private Percentage apostados;
	private String sortOption;
	private int apuestasLAE;
	private int hilos;
	private double emt;
	private double emar;
	private double[] ems;
	private double[] reparto;
	private int top;
	private int distancia;

	public Config() {

	}

	public String getFileName() {
		return fileName;
	}
	
	public String getFileOut() {
		return this.fileOut;
	}
	
	public String getFileCSV() {
		return this.fileCSV;
	}
	
	public String getFileSaved() {
		return this.fileSaved;
	}
	
	public void setFileOut(String fileOut) {
		this.fileOut = fileOut;
	}
	
	public void setFileCSV(String fileCSV) {
		this.fileCSV = fileCSV;
	}
	
	public void setFileSaved(String fileSaved) {
		this.fileSaved = fileSaved;
	}
	
	public void setSortOption(String option) {
		this.sortOption = option;
	}

	public Percentage getReales() {
		return reales;
	}

	public Percentage getApostados() {
		return apostados;
	}

	public int getApuestasLAE() {
		return apuestasLAE;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public void setReales(Percentage reales) {
		this.reales = reales;
	}

	public void setApostados(Percentage apostados) {
		this.apostados = apostados;
	}

	public void setApuestasLAE(int apuestasLAE) {
		this.apuestasLAE = apuestasLAE;
	}

	public int getHilos() {
		return this.hilos;
	}
	
	public int getTop() {
		return this.top;
	}
	
	public int getDistancia() {
		return this.distancia;
	}

	public void setHilos(int hilos) {
		this.hilos = hilos;
	}
	
	public void setTop(int top) {
		this.top = top;
	}
	
	public void setDistancia(int distancia) {
		this.distancia = distancia;
	}
	
	public void setEMT(double emt) {
		this.emt = emt;
	}
	
	public double getEMT() {
		return this.emt;
	}
	
	public void setEMAR(double emar) {
		this.emar = emar;
	}
	
	public double getEMAR() {
		return this.emar;
	}
	
	public void setEMS(double[] ems) {
		this.ems = ems;
	}
	
	public double[] getEMS() {
		return this.ems;
	}

	public double[] getReparto() {
		return reparto;
	}

	public void setReparto(double[] reparto) {
		this.reparto = reparto;
	}

	public String getSortOption() {
		return sortOption;
	}
	
}
