package ui;

import principal.Launcher;

public class HilosSetter extends AbstractSetter {

	private int hilos;
	
	public HilosSetter(Launcher launcher, String optionName) {
		super(launcher, optionName);
	}

	@Override
	public void set() {
		this.launcher.setHilos(this.hilos);
	}

	@Override
	public void load(String newParam) {
		int d = -1;
		try {
			d = Integer.parseInt(newParam);
			if(d >= 1) this.hilos = d;
			else this.paramFail(newParam);
		}
		catch(Exception e) {
			this.paramFail(newParam);
		}
	}

}
