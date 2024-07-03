package hunghhph44272.fpoly.duan1.Model;

public class Gio_Hang {
    private int idCart;
    private int idCoffee;
    private int quanti;
    private double sum;
    private  String username;

    private String size;

    private String loai;

    public Gio_Hang() {
    }

    public Gio_Hang(int idCart, int idCoffee, int quanti, double sum, String username,String size,String loai) {
        this.idCart = idCart;
        this.idCoffee = idCoffee;
        this.quanti = quanti;
        this.sum = sum;
        this.username = username;
        this.size = size;
        this.loai = loai;
    }

    public String getLoai() {
        return loai;
    }

    public void setLoai(String loai) {
        this.loai = loai;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public int getIdCart() {
        return idCart;
    }

    public void setIdCart(int idCart) {
        this.idCart = idCart;
    }

    public int getIdCoffee() {
        return idCoffee;
    }

    public void setIdCoffee(int idCoffee) {
        this.idCoffee = idCoffee;
    }

    public int getQuanti() {
        return quanti;
    }

    public void setQuanti(int quanti) {
        this.quanti = quanti;
    }

    public double getSum() {
        return sum;
    }

    public void setSum(double sum) {
        this.sum = sum;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
