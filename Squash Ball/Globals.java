class Globals {
	public static int WIDTH = 600;
	public static int HEIGHT = 500;
	
	public static void delay(int ms) {
		try {
			Thread.sleep(ms);
		}
		catch(InterruptedException e) {
			
		}
	}
}
