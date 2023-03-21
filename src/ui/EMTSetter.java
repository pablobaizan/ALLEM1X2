package ui;

import principal.Launcher;

public class EMTSetter extends AbstractSetter {

	private float emt;
	
	public EMTSetter(Launcher launcher, String optionName) {
		super(launcher, optionName);
	}

	@Override
	public void set() {
		this.launcher.setEMT(this.emt);
	}

	@Override
	public void load(String newParam) {
		float emt = 1.0f;
		try {
			emt = Float.parseFloat(newParam);
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

