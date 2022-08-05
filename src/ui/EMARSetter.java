package ui;

import principal.Launcher;

public class EMARSetter extends AbstractSetter {

	private double emar;
	
	public EMARSetter(Launcher launcher, String optionName) {
		super(launcher, optionName);
	}

	@Override
	public void set() {
		this.launcher.setEMAR(this.emar);
	}

	@Override
	public void load(String newParam) {
		double emar = 1.0;
		try {
			emar = Double.parseDouble(newParam);
			if(emar >= 0.0) {
				this.emar = emar;
			}
			else {
				this.paramFail(newParam);
			}
		}
		catch(Exception e) {
			this.paramFail(newParam);
		}
	}

}

