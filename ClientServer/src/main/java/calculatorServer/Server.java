package calculatorServer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

/**
 * A TCP server that runs on port 9090. When a client connects, it sends the
 * client the current date and time, then closes the connection with that
 * client. Arguably just about the simplest server you can write.
 */
public class Server {
	/**
	 * Runs the server.
	 */
	public static void main(String[] args) throws IOException {
		ServerSocket listener = new ServerSocket(9090);
		System.out.println("server jestem");
		try {
			while (true) {
				Socket socket = listener.accept();
				try {
					BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
					String in = input.readLine();
					System.out.println(in);

					PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
					out.println(calculate(in));
				} finally {
					socket.close();
				}
			}
		} finally {
			listener.close();
		}
	}

	public static String calculate(String input) {
		
		input = input.replace(" ", "");
		String[] signs = {"+","-","*","/"};
		String sign = "";
		for(int a = 0; a<4; a++){
			if(input.contains(signs[a])){
				sign = signs[a];
			};
		//System.out.println(sign);
		}
		
//		String[] spited = input.split(" ");
		
		try{
			Integer x;
			x = Integer.parseInt(input.substring(0, input.indexOf(sign)));
			Integer y;
			y = Integer.parseInt(input.substring(input.indexOf(sign)+1, input.length()));
			
			Integer result = 0;
			Integer result2 = 0;
			switch (sign) {
			case "+":
				result = x + y;
				return result.toString();
			case "-":
				result = x - y;
				return result.toString();
			case "*":
				result = x * y;
				return result.toString();
			case "/":
				result = x / y;
				result2 = x % y;
				return result.toString() + "." + result2.toString() ;
		
			}
		}catch(NumberFormatException f ){
			System.out.println("Nieprawid³owe pytanie");
		
		
		}
		return "-1";
	}

}
