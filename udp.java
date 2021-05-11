import java.util.Scanner; 

public class udp
{ 
	public static void main(String args[]){


 	Scanner scanner = new Scanner(System.in); 

	System.out.print("Enter any hexadecimal number: "); 
	String sourceNum = scanner.nextLine(); 

	scanner.close(); 

//converting hex to decimal by passing base 16 

	int num = Integer.parseInt(sourceNum,16); 

	System.out.println("Source Port Number: "+num); } 
}