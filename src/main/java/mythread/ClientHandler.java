//package mythread;
//
//import dao.implement.NhanVienImp;
//import dao.implement.QuyenImp;
//import dao.implement.TaikhoanImp;
//import model.NhanVien;
//import model.Quyen;
//import model.TaiKhoan;
//
//import java.io.IOException;
//import java.io.ObjectInputStream;
//import java.io.ObjectOutputStream;
//import java.net.Socket;
//import java.util.List;
//
//public class ClientHandler implements Runnable {
//    private Socket socket;
//    private QuyenImp quyenImp = new QuyenImp();
//    private NhanVienImp nhanVienImp = new NhanVienImp();
//    private TaikhoanImp taikhoanImp = new TaikhoanImp();
//
//
//    public ClientHandler(Socket socket) {
//        this.socket = socket;
//    }
//
//
//    @Override
//    public void run() {
//        try {
//            System.out.println("Client " + socket.getInetAddress().getHostAddress() + " is connected");
//            ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
//            ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
//            while (true) {
//                Object object = objectInputStream.readObject();
//                if (object instanceof String) {
//                    String message = (String) object;
//                    if (message.equalsIgnoreCase("layDanhSachQuyen")) {
//                        List<Quyen> dsQuyen = quyenImp.layDanhSachQuyen();
//                        objectOutputStream.writeObject(dsQuyen);
//                    }
//                    if (message.equalsIgnoreCase("layDanhSachNhanVien")) {
//                        List<NhanVien> dsNhanVien = nhanVienImp.layDanhSachNhanVien();
//                        objectOutputStream.writeObject(dsNhanVien);
//                    }
//                    if (message.equalsIgnoreCase("layDanhSachTaiKhoan")) {
//                        List<TaiKhoan> dsTaiKhoan = taikhoanImp.layDanhSachTaiKhoan();
//                        objectOutputStream.writeObject(dsTaiKhoan);
//                    }
//                    if (message.equalsIgnoreCase("exit")) {
//                        break;
//                    }
//                    objectOutputStream.flush();
//                }
//            }
//        } catch (IOException | ClassNotFoundException e) {
//            e.printStackTrace();
//        }
//    }
//
//}
