package hunghhph44272.fpoly.duan1.Model;

public class LoaiCoffee {
    private int maLoai;
    private String tenLoai;

    public LoaiCoffee() {
    }

    public LoaiCoffee(int maLoai, String tenLoai) {
        this.maLoai = maLoai;
        this.tenLoai = tenLoai;
    }

    public int getMaLoai() {
        return maLoai;
    }

    public void setMaLoai(int maLoai) {
        this.maLoai = maLoai;
    }

    public String getTenLoai() {
        return tenLoai;
    }

    public void setTenLoai(String tenLoai) {
        this.tenLoai = tenLoai;
    }
}
