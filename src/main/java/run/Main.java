package run;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import mythread.ClientHandler;

public class Main {
    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(1234)) {
            ExecutorService executorService = Executors.newCachedThreadPool();
            while (true) {

                System.out.println("Đang chờ kết nối");
                Socket socketClient = serverSocket.accept();
                System.out.println("client " + socketClient.getInetAddress().getHostAddress() + " is connect");

                ClientHandler clientHandler = new ClientHandler(socketClient);
                executorService.execute(clientHandler);
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
