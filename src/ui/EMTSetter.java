package ui;

import principal.Launcher;

public class EMTSetter extends AbstractSetter {

	private double emt;
	
	public EMTSetter(Launcher launcher, String optionName) {
		super(launcher, optionName);
	}

	@Override
	public void set() {
		this.launcher.setEMT(this.emt);
	}

	@Override
	public void load(String newParam) {
		double emt = 1.0;
		try {
			emt = Double.parseDouble(newParam);
			if(emt >= 0.0) {
				this.emt = emt;
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

