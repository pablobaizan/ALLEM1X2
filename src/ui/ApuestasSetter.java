package ui;

import principal.Launcher;

public class ApuestasSetter extends AbstractSetter {

	private int aps;
	
	public ApuestasSetter(Launcher launcher, String optionName) {
		super(launcher, optionName);
	}

	@Override
	public void set() {
		this.launcher.setApuestas(this.aps);
	}

	@Override
	public void load(String newParam) {
		int aps = 0;
		try {
			aps = Integer.parseInt(newParam);
			if(aps <= 0) this.paramFail(newParam);
			else this.aps = aps;
		}
		catch(Exception e) {
			this.paramFail(newParam);
		}
	}

}
