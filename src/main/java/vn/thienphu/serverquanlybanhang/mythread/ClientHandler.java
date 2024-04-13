package vn.thienphu.serverquanlybanhang.mythread;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class ClientHandler implements Runnable {
	private Socket socket;
	
	
	public ClientHandler(Socket socket) {
		this.socket = socket;
	}


	@Override
	public void run() {
		try {
			BufferedReader read = new BufferedReader(
					new InputStreamReader(socket.getInputStream()));
			BufferedWriter write = new BufferedWriter(
					new OutputStreamWriter(socket.getOutputStream()));
			
			while(true) {
				
				String request = read.readLine();
				System.out.println("Client " + socket.getLocalAddress().getHostAddress() + " " + request);
				
				String newResponse = request.toUpperCase();
				
				write.append(newResponse);
				write.newLine();
				write.flush();
				
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
