package hunghhph44272.fpoly.duan1.Model;

public class LichSuDonHang {
    private int id_lichsu;
    private int id_cart;
    private int phone;
    private String type;
    private String name;
    private String address;
    private String time;
    private  String contten;
    private String status;
    private double sum;
    private String size;

    public LichSuDonHang() {
    }

    public LichSuDonHang(int id_lichsu, int id_cart, int phone, String type, String name, String address, String time, String contten, String status, double sum, String size) {
        this.id_lichsu = id_lichsu;
        this.id_cart = id_cart;
        this.phone = phone;
        this.type = type;
        this.name = name;
        this.address = address;
        this.time = time;
        this.contten = contten;
        this.status = status;
        this.sum = sum;
        this.size = size;
    }

    public int getId_lichsu() {
        return id_lichsu;
    }

    public void setId_lichsu(int id_lichsu) {
        this.id_lichsu = id_lichsu;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }
}
