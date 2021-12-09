package model;

public class TipoAtraccion extends ActivableItem {
    private int ID;
    private String name;

    public TipoAtraccion(int ID, String name, boolean active) {
        super(active);
        this.ID = ID;
        this.name = name;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
