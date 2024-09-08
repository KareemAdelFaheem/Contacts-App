package Model;

public class ListItem {
    private String Name;
    private String number;
    private int id;

    public ListItem(int id,String name, String number) {
        Name = name;
        this.number = number;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
