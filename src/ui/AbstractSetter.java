package ui;

import principal.Launcher;

public abstract class AbstractSetter implements ISetter {
	
	protected Launcher launcher;
	protected String optionName;
	
	public AbstractSetter(Launcher launcher, String optionName) {
		this.launcher = launcher;
		this.optionName = optionName;
	}

	public abstract void load(String newParam);
	
	protected void paramFail(String param) {
		throw new RuntimeException("Ha habido un error de introducci�n del par�metro "
				+ param + " de la opci�n " + this.optionName);
	}
	

}
