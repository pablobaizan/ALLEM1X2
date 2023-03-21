package ui;

import principal.Launcher;

public class EMSSetter extends AbstractSetter {

	private float[] ems;
	
	public EMSSetter(Launcher launcher, String optionName) {
		super(launcher, optionName);
	}

	@Override
	public void set() {
		this.launcher.setEMS(this.ems);
	}

	@Override
	public void load(String newParam) {
		String[] vals = newParam.split("/");
		if(vals.length != 5) this.paramFail(newParam);
		this.ems = new float[5];
		for(int i = 0; i < vals.length; i++) {
			try {
				float t = Float.parseFloat(vals[i]);
				if(t < 0.0) this.paramFail(newParam);
				this.ems[i] = t;
			}
			catch(Exception e) {
				this.paramFail(newParam);
				return;
			}
		}
	}

}

