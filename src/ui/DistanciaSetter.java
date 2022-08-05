package ui;

import principal.Launcher;

public class DistanciaSetter extends AbstractSetter {

	private int distancia;
	
	public DistanciaSetter(Launcher launcher, String optionName) {
		super(launcher, optionName);
	}

	@Override
	public void set() {
		this.launcher.setDistancia(this.distancia);
	}

	@Override
	public void load(String newParam) {
		int d = 1;
		try {
			d = Integer.parseInt(newParam);
			if(d >= 1) this.distancia = d;
			else this.paramFail(newParam);
		}
		catch(Exception e) {
			this.paramFail(newParam);
		}
	}

}
