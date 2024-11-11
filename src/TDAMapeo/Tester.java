package TDAMapeo;

public class Tester {
	public static void main(String args[]) {
		for(int i=0; i<25;i++) {
			System.out.println("El primo siguiente del Numero "+i+" es "+NextPrimeFinder.nextPrime(i));
		}
	}
}
