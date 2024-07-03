package hunghhph44272.fpoly.duan1.Model;

public class Admin {
    private int ID_ADMIN;
    private String TEN_AD;
    private String EMAIL_AD;
    private String MAT_KHAU_AD;

    public Admin() {
    }

    public Admin(int ID_ADMIN, String TEN_AD, String EMAIL_AD, String MAT_KHAU_AD) {
        this.ID_ADMIN = ID_ADMIN;
        this.TEN_AD = TEN_AD;
        this.EMAIL_AD = EMAIL_AD;
        this.MAT_KHAU_AD = MAT_KHAU_AD;
    }

    public int getID_ADMIN() {
        return ID_ADMIN;
    }

    public void setID_ADMIN(int ID_ADMIN) {
        this.ID_ADMIN = ID_ADMIN;
    }

    public String getTEN_AD() {
        return TEN_AD;
    }

    public void setTEN_AD(String TEN_AD) {
        this.TEN_AD = TEN_AD;
    }

    public String getEMAIL_AD() {
        return EMAIL_AD;
    }

    public void setEMAIL_AD(String EMAIL_AD) {
        this.EMAIL_AD = EMAIL_AD;
    }

    public String getMAT_KHAU_AD() {
        return MAT_KHAU_AD;
    }

    public void setMAT_KHAU_AD(String MAT_KHAU_AD) {
        this.MAT_KHAU_AD = MAT_KHAU_AD;
    }
}
