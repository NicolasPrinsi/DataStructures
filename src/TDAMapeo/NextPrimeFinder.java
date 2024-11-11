package TDAMapeo;

public class NextPrimeFinder {
	
	public static boolean isPrime(int i) {
		boolean toreturn=true;
		if(i<1)
			toreturn=false;
		else {
			if(i==1||i==2||i==3)
				toreturn = true;
			else {
				if(i%2==0||i%3==0)
					toreturn=false;
				for(int n=5; n*n<=i&&toreturn==true;i=n+6 ) {
					if(i%n==0||i%(n+2)==0)
						toreturn = false;
				}
			}
		}
		return toreturn;
	}
	
	public static int nextPrime(int i) {
		int toreturn=i+1;
			while (!NextPrimeFinder.isPrime(toreturn)) {
				toreturn++;
			}	
		return toreturn;
	}
}
