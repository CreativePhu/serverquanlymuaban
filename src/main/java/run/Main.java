package run;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import dao.LoaiSanPhamInf;
import dao.NhanVienInf;
import dao.QuyenInf;
import dao.SanPhamInf;
import dao.TaiKhoanInf;
import dao.implement.LoaiSanPhamImp;
import dao.implement.NhanVienImp;
import dao.implement.QuyenImp;
import dao.implement.SanPhamImp;
import dao.implement.TaikhoanImp;
import util.ConnectDB;

public class Main {
    public static void main(String[] args) throws RemoteException {
        Registry registry = LocateRegistry.createRegistry(1234);

        ConnectDB connectDB = new ConnectDB();

        NhanVienInf nhanVienInf = new NhanVienImp(connectDB.getEntityManager());
        QuyenInf quyenInf = new QuyenImp(connectDB.getEntityManager());
        TaiKhoanInf taiKhoanInf = new TaikhoanImp(connectDB.getEntityManager());
        LoaiSanPhamInf loaiSanPhamInf = new LoaiSanPhamImp(connectDB.getEntityManager());
        SanPhamInf sanPhamInf = new SanPhamImp(connectDB.getEntityManager());

        registry.rebind("nhanVienInf", nhanVienInf);
        registry.rebind("quyenInf", quyenInf);
        registry.rebind("taiKhoanInf", taiKhoanInf);
        registry.rebind("loaiSanPhamInf", loaiSanPhamInf);
        registry.rebind("sanPhamInf", sanPhamInf);

        System.out.println("Server ready");
    }
}
