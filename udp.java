import java.util.Scanner;
public class udp{  
   public static void main(String args[]){


Scanner scanner = new Scanner(System.in);
System.out.print("Enter Hexadecimal Digits:  ");
String hexnum = scanner.nextLine();
scanner.close();
int len = hexnum.length();
int n = 4;  
    int temp = 0, chars = len/n;  
        //Stores the array of string  
    String[] equalStr = new String [n];  

for(int i = 0; i < len; i = i+chars) {  
                //Dividing string in n equal part using substring()  
                String part = hexnum.substring(i, i+chars);  
                equalStr[temp] = part;  
                temp++;  
            }    
            int num = Integer.parseInt(equalStr[0],16);
            int num1 = Integer.parseInt(equalStr[1],16);
int num2 = Integer.parseInt(equalStr[2],16);
int num3 = num2-8;


System.out.println("Source port number is: "+num);
System.out.println("Destination port number is: "+num1);
System.out.println("Total length of the user datagram: "+num2);
System.out.println("length of the data: "+num3);

   }
}
