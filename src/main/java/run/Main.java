package run;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import dao.NhanVienInf;
import dao.QuyenInf;
import dao.TaiKhoanInf;
import dao.implement.NhanVienImp;
import dao.implement.QuyenImp;
import dao.implement.TaikhoanImp;
import util.ConnectDB;

public class Main {
    public static void main(String[] args) throws RemoteException {
        Registry registry = LocateRegistry.createRegistry(1234);

        ConnectDB connectDB = new ConnectDB();

        NhanVienInf nhanVienInf = new NhanVienImp(connectDB.getEntityManager());
        QuyenInf quyenInf = new QuyenImp(connectDB.getEntityManager());
        TaiKhoanInf taiKhoanInf = new TaikhoanImp(connectDB.getEntityManager());

        registry.rebind("nhanVienInf", nhanVienInf);
        registry.rebind("quyenInf", quyenInf);
        registry.rebind("taiKhoanInf", taiKhoanInf);

        System.out.println("Server ready");
    }
}
