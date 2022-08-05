package ui;

import logica.Percentage;
import principal.Launcher;

public class PercentageSetter extends AbstractSetter {

	private Percentage percentage;
	
	public PercentageSetter(Launcher launcher, String optionName) {
		super(launcher, optionName);
	}

	@Override
	public void set() {
		if(this.optionName.equals("a")) {
			this.launcher.setPorcsLAE(this.percentage);
		}
		else if(this.optionName.equals("r")) {
			this.launcher.setPorcsReal(this.percentage);
		}
		else {
			this.paramFail(this.percentage.getFileName());
		}
	}

	@Override
	public void load(String newParam) {
		this.percentage = new Percentage(newParam);
	}

}
