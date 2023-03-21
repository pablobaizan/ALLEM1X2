package ui;

import principal.Launcher;

public class RepartoSetter extends AbstractSetter {

	private float[] reparto;
	
	public RepartoSetter(Launcher launcher, String optionName) {
		super(launcher, optionName);
	}

	@Override
	public void set() {
		this.launcher.setReparto(this.reparto);
	}

	@Override
	public void load(String newParam) {
		String[] vals = newParam.split("/");
		if(vals.length != 5) this.paramFail(newParam);
		this.reparto = new float[5];
		for(int i = 0; i < vals.length; i++) {
			try {
				float t = Float.parseFloat(vals[i]);
				if(t < 0.0) this.paramFail(newParam);
				this.reparto[i] = t;
			}
			catch(Exception e) {
				this.paramFail(newParam);
				return;
			}
		}
	}

}
