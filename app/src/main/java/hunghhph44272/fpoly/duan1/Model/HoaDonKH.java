package hunghhph44272.fpoly.duan1.Model;

public class HoaDonKH {
    private int id_hoadon;
    private int id_cart;
    private int phone;
    private String name;
    private String address;
    private String time;
    private  String contten;
    private String status;
    private double sum;

    public HoaDonKH() {
    }

    public HoaDonKH(int id_hoadon, int id_cart, int phone, String name, String address, String time, String contten, String status, double sum) {
        this.id_hoadon = id_hoadon;
        this.id_cart = id_cart;
        this.phone = phone;
        this.name = name;
        this.address = address;
        this.time = time;
        this.contten = contten;
        this.status = status;
        this.sum = sum;
    }

    public int getId_hoadon() {
        return id_hoadon;
    }

    public void setId_hoadon(int id_hoadon) {
        this.id_hoadon = id_hoadon;
    }

    public int getId_cart() {
        return id_cart;
    }

    public void setId_cart(int id_cart) {
        this.id_cart = id_cart;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getContten() {
        return contten;
    }

    public void setContten(String contten) {
        this.contten = contten;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public double getSum() {
        return sum;
    }

    public void setSum(double sum) {
        this.sum = sum;
    }
}
