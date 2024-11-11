package boludeo;

public class principal {
	public static void main(String args[]) {
		System.out.println(calculo(1));
	}
	
	public static float calculo(int i){
		float a=i;
		for(int j=0; j<=31; j++) {
			a=a*1.11f;
		}
		return a;
	}
}
