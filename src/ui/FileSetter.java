package ui;

import principal.Launcher;

public class FileSetter extends AbstractSetter {

	private String fileName;
	
	public FileSetter(Launcher launcher, String optionName) {
		super(launcher, optionName);
	}

	@Override
	public void set() {
		if(this.optionName.equals("ar")) {
			this.launcher.setFile(fileName);
		}
		else if(this.optionName.equals("o")) {
			this.launcher.setFileOut(fileName);
		}
		else if(this.optionName.equals("csv")) {
			this.launcher.setFileCSV(fileName);
		}
		else if(this.optionName.equals("load")) {
			this.launcher.setFileSaved(fileName);
		}
		else this.paramFail(this.fileName);
	}

	@Override
	public void load(String newParam) {
		this.fileName = newParam;
	}

}
