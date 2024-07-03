package hunghhph44272.fpoly.duan1.Model;

public class Coffee{
    private int id;
    private String type;
    private String img;
    private String name;
    private String des;
    private int price;
    private String size;

    public Coffee() {
    }

    public Coffee(int id, String type, String img, String name, String des, int price,String size) {
        this.id = id;
        this.type = type;
        this.img = img;
        this.name = name;
        this.des = des;
        this.price = price;
        this.size = size;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
