import java.util.Random;

public class Test {
 public static void main(String[] args) {
	PercolationStats p = new PercolationStats(50,5000);
	System.out.println("Mean = "+p.mean());
	System.out.println("Standard Deviation = "+p.stddev());
	System.out.println("Confidence imterval [ "+p.confidenceLo()+" , "+p.confidenceHi()+" ]");
	}
 }

