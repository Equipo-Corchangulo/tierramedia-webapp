package model;

import java.util.HashMap;

public class TipoAtraccion extends ActivableItem {
    private int ID;
    private String name;

    public TipoAtraccion(int ID, String name, boolean active) {
        super(active);
        this.ID = ID;
        this.name = name;
    }

    public TipoAtraccion(String nombre) {
        super(true);
        this.name = nombre;
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
    
    public boolean isValid() {
		return validate().isEmpty();
	}
	
	public HashMap<String, String> validate(){
		HashMap<String, String> errors = new HashMap<String, String>();
		if(this.name.isBlank()) errors.put("nombre", "El nombre es requerido");
		System.out.println(errors);
		return errors;
	}
}
