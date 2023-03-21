package ui;

import principal.Launcher;

public class SortSetter extends AbstractSetter {

	private String option;
	
	public SortSetter(Launcher launcher, String optionName) {
		super(launcher, optionName);
	}

	@Override
	public void set() {
		if(this.option.equals("prob14") 
				|| this.option.equals("emar") 
				|| this.option.equals("emt") 
				|| this.option.equals("EM10")
				|| this.option.equals("EM11")
				|| this.option.equals("EM12")
				|| this.option.equals("EM13")
				|| this.option.equals("EM14")
				|| this.option.equals("mad")
				|| this.option.equals("kelly")
				|| this.option.equals("tvm")) {
			this.launcher.setSortOption(this.option);
		}
		else this.paramFail(this.option);
	}

	@Override
	public void load(String newParam) {
		this.option = newParam;
	}

}
