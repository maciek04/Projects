package calculatorServer;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

/**
 * Trivial client for the date server.
 */
public class Client {

    /**
     * Runs the client as an application.  First it displays a dialog
     * box asking for the IP address or hostname of a host running
     * the date server, then connects to it and displays the date that
     * it serves.
     */
    public static void main(String[] args) throws IOException {

    	System.out.println("***********************");
    	System.out.println("****  Kalkulator ******");
    	System.out.println("***********************");
    	System.out.println("");
    	System.out.println("Podaj dzia³anie:");
    	
    	
    	boolean run = true;
    	while(run){
    	
    	Scanner scanner = new Scanner(System.in);
    	String in =scanner.nextLine();
    	if(in == "exit"){
    		System.out.print("koniec");
    		run = false;
    	}else{
    		
	        Socket s = new Socket("localhost", 9090);
	        PrintWriter out = new PrintWriter(s.getOutputStream(), true);
	        String task = in;
	        out.println(task);
	        //System.out.println(task);
	        BufferedReader input = new BufferedReader(new InputStreamReader(s.getInputStream()));
	        String answer = input.readLine();
	        System.out.println(answer);
    	}
    	}
        System.exit(0);
    }
    
    
    
}