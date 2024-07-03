package hunghhph44272.fpoly.duan1.Model;

public class KhachHang {
    private int ID_KH;
    private String TEN_KH;
    private String SDT;
    private String EMAIL_KH;
    private String MAT_KHAU_KH;

    public KhachHang() {
    }
    public KhachHang(String TEN_KH) {
        this.TEN_KH = TEN_KH;
    }

    public KhachHang(int ID_KH, String TEN_KH, String SDT, String EMAIL_KH, String MAT_KHAU_KH) {
        this.ID_KH = ID_KH;
        this.TEN_KH = TEN_KH;
        this.SDT = SDT;
        this.EMAIL_KH = EMAIL_KH;
        this.MAT_KHAU_KH = MAT_KHAU_KH;
    }

    public int getID_KH() {
        return ID_KH;
    }

    public void setID_KH(int ID_KH) {
        this.ID_KH = ID_KH;
    }

    public String getTEN_KH() {
        return TEN_KH;
    }

    public void setTEN_KH(String TEN_KH) {
        this.TEN_KH = TEN_KH;
    }

    public String getSDT() {
        return SDT;
    }

    public void setSDT(String SDT) {
        this.SDT = SDT;
    }

    public String getEMAIL_KH() {
        return EMAIL_KH;
    }

    public void setEMAIL_KH(String EMAIL_KH) {
        this.EMAIL_KH = EMAIL_KH;
    }

    public String getMAT_KHAU_KH() {
        return MAT_KHAU_KH;
    }

    public void setMAT_KHAU_KH(String MAT_KHAU_KH) {
        this.MAT_KHAU_KH = MAT_KHAU_KH;
    }
}
