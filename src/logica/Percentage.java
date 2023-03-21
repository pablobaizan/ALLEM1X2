package logica;

import java.io.BufferedReader;
import java.io.FileReader;

public class Percentage {

	private float[][] data;
	private String fileName;
	
	public Percentage(String fileName) {
		this.fileName = fileName;
		this.load(this.fileName);
		this.check();
	}
	
	public void load(String fileName) {
		float[][] porcs = new float[14][3];
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(fileName));
			int cont = 0;
			while(br.ready()) {
				String line = br.readLine();
				String[] data = line.split("\t");
				porcs[cont][0] = Float.parseFloat(data[1].replace(',', '.')) / 100.0f;
				porcs[cont][1] = Float.parseFloat(data[0].replace(',', '.')) / 100.0f;
				porcs[cont][2] = Float.parseFloat(data[2].replace(',', '.')) / 100.0f;
				cont++;
			}
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.data = porcs;
	}
	
	public boolean check() {
		if(this.data.length != 14)
			throw new RuntimeException("Porcentajes con número incorrecto de partidos.");
		for(int i = 0; i < 14; i++) {
			if(this.data[i].length != 3)
				throw new RuntimeException("Error en el fichero de porcentajes.");
			float toCheck = 0;
			for(int j = 0; j < 3; j++) {
				toCheck += this.data[i][j];
			}
			if(Math.abs(1.0 - toCheck) > 0.01) {
				throw new RuntimeException("Los datos de los porcentajes no se han podido"
						+ " cargar correctamente debido a un desequilibrio"
						+ " en la asignación de porcentajes del fichero origen.");
			}
		}
		return true;
	}

	public float[][] getData() {
		return data;
	}

	public String getFileName() {
		return fileName;
	}
}
