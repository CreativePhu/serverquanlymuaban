package mythread;
import dao.implement.QuyenImp;
import model.Quyen;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;

public class ClientHandler implements Runnable {
    private Socket socket;
    private QuyenImp quyenImp = new QuyenImp();


    public ClientHandler(Socket socket) {
        this.socket = socket;
    }


    @Override
    public void run() {
        try {
            System.out.println("Client " + socket.getInetAddress().getHostAddress() + " is connected");
            ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            while (true) {
                Object object = objectInputStream.readObject();
                if(object instanceof String){
                    String message = (String) object;
                    if(message.equalsIgnoreCase("layDanhSachQuyen")){
                        List<Quyen> dsQuyen = quyenImp.layDanhSachQuyen();
                        System.out.println(dsQuyen.size());
                        objectOutputStream.writeObject(dsQuyen);
                        objectOutputStream.flush();
                    }
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}
