package ui;

import principal.Launcher;

public class TopSetter extends AbstractSetter {

	private int top;
	
	public TopSetter(Launcher launcher, String optionName) {
		super(launcher, optionName);
	}

	@Override
	public void set() {
		this.launcher.setTop(this.top);
	}

	@Override
	public void load(String newParam) {
		int top;
		try {
			top = Integer.parseInt(newParam);
			if(top >= 1) {
				this.top = top;
			}
			else if(top == 0) {
				this.top = Integer.MAX_VALUE;
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

