package ui;

import principal.Launcher;

public class SetterFactory {
	
	private Launcher launcher;
	
	public SetterFactory(Launcher launcher) {
		this.launcher = launcher;
	}

	public ISetter create(String type) {
		switch(type) {	
			case "ap":
				return new ApuestasSetter(this.launcher, type);
			case "a":
				return new PercentageSetter(this.launcher, type);
			case "r":
				return new PercentageSetter(this.launcher, type);
			case "ar":
				return new FileSetter(this.launcher, type);
			case "o":
				return new FileSetter(this.launcher, type);
			case "csv":
				return new FileSetter(this.launcher, type);
			case "load":
				return new FileSetter(this.launcher, type);
			case "h":
				return new HilosSetter(this.launcher, type);
			case "rep":
				return new RepartoSetter(this.launcher, type);
			case "emt":
				return new EMTSetter(this.launcher, type);
			case "emar":
				return new EMARSetter(this.launcher, type);
			case "ems":
				return new EMSSetter(this.launcher, type);
			case "sort":
				return new SortSetter(this.launcher, type);
			case "top":
				return new TopSetter(this.launcher, type);
			case "d":
				return new DistanciaSetter(this.launcher, type);
			default: throw new RuntimeException("Parámetro no reconocido " + type);
		}
	}
}
