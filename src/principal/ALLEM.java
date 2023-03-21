package principal;

public class ALLEM {

	public static void main(String[] args) throws Exception {
		long t0 = System.currentTimeMillis();
		Launcher launcher = new Launcher();
		String[] defaults = new String[] {
				"-rep", "0.09/0.075/0.075/0.075/0.16"
				, "-ems", "0.0/0.0/0.0/0.0/0.0"
				, "-emar", "0.0"
				, "-emt", "1.0"
				, "-sort", "prob14"
				, "-h", "8"
				, "-d", "1"
				, "-top", "0"};
//		String[] args2 = new String[] {
//				"-ar", "C:/Users/pablo/Desktop/Jornada_Quiniela/Pruebas/j58_320.txt"
//				, "-o", "C:/Users/pablo/Desktop/Jornada_Quiniela/Pruebas/allem_test.txt"
//				, "-r", "C:/Users/pablo/Desktop/Jornada_Quiniela/Pruebas/reales_LH_j58.txt"
//				, "-a", "C:/Users/pablo/Desktop/Jornada_Quiniela/Pruebas/apostados_LH_j58.txt"
//				, "-ap", "3000000"
//				, "-h", "8"
//				};
		launcher.setSetters(defaults, true);
		launcher.setSetters(args, false);
		launcher.launch();
		long t1 = System.currentTimeMillis();
		System.out.println((t1 - t0) / 1000.0);
	}

}
