package model;


import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

import persistence.commons.DAOFactory;

public class PerfilUsuario extends ActivableItem{

	private double presupuesto;

	private double tiempoDisponible;
	private String nombre;
	private TipoAtraccion tipoDeAtraccion;
	private Itinerario itinerario;
	private String username;
	private String password;
	private boolean isAdmin;
	private Integer Id;

	public PerfilUsuario(Integer Id, String nombre, double presupuesto, int tiempoDisponible,
						 TipoAtraccion tipoDeAtraccion, String username,
			String password, boolean isAdmin, boolean active) {
		this( nombre,  presupuesto,  tiempoDisponible, tipoDeAtraccion,  username, password,  isAdmin,  active);
		this.Id = Id;
	}

	public PerfilUsuario(String nombre, Double presupuesto, int tiempoDisponible, TipoAtraccion tipoDeAtraccion, String username,
			String password, Boolean isAdmin, boolean active) {
		super(active);
		this.nombre = nombre;
		this.presupuesto = presupuesto;
		this.tiempoDisponible = tiempoDisponible;
		this.tipoDeAtraccion = tipoDeAtraccion;
		this.isAdmin = isAdmin;
		this.username = username;
		this.password = password;
		this.itinerario = new Itinerario();
	}
	public PerfilUsuario(String nombre, double presupuesto, int tiempoDisponible, TipoAtraccion tipoDeAtraccion, int id, List<Facturable> listaDeItinerario, String username,
			String password, boolean isAdmin, boolean active) {

		this( nombre,  presupuesto,  tiempoDisponible, tipoDeAtraccion,  username, password,  isAdmin,  active);
		this.Id = id;
		this.itinerario = new Itinerario (listaDeItinerario);
	}

	public Integer getId() {
		return Id;
	}

	public void setId(Integer id) {
		Id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isAdmin() {
		return isAdmin;
	}

	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

	@Override
	public String toString() {
		return "Nombre: " + nombre+ "\nMonedas Disponibles: " + presupuesto + "\nTiempo Disponible: " + tiempoDisponible
				+ "\nTipo de atraccion: " + tipoDeAtraccion + " ";
	}

	public double getPresupuesto() {

		return presupuesto;
	}

	public double getTiempoDisponible() {

		return tiempoDisponible;
	}

	public TipoAtraccion getTipoDeAtraccion() {

		return tipoDeAtraccion;
	}

	public String getNombre() {

		return nombre;
	}
	
	public boolean auth(String password) {
		return this.password.equals(password);
	}

	public void reservarTiempoYdinero(Facturable atraccion) {
		this.tiempoDisponible -= atraccion.obtenerTiempoTotal();
		this.presupuesto -= atraccion.obtenerCostoTotal();
	}

	public boolean tieneTiempoYdinero() {

		return this.tiempoDisponible > 0 && this.presupuesto > 0 ;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		PerfilUsuario that = (PerfilUsuario) o;
		return Double.compare(that.getPresupuesto(), getPresupuesto()) == 0 && Double.compare(that.getTiempoDisponible(), getTiempoDisponible()) == 0 && getNombre().equals(that.getNombre()) && getTipoDeAtraccion() == that.getTipoDeAtraccion();
	}

	@Override
	public int hashCode() {
		return Objects.hash(getPresupuesto(), getTiempoDisponible(), getNombre(), getTipoDeAtraccion());
	}

	public void agregarAtraccion(Facturable atraccion) throws SQLException {
		this.itinerario.agregarAtraccion(atraccion);
		atraccion.restarCupo();
		this.reservarTiempoYdinero(atraccion);
	}

	public boolean puedeComprar(Facturable atraccion) {
		return  !this.itinerario.poseeAtraccion(atraccion)
				&& atraccion.obtenerCostoTotal() <= this.presupuesto
				&& atraccion.obtenerTiempoTotal() <= this.tiempoDisponible
				&& atraccion.hayCupo();
	}

	public Itinerario getItinerario(){
		return  this.itinerario;
	}
	
	public boolean isValid() {
		return validate().isEmpty();
	}
	public void update() throws SQLException {
		DAOFactory.getUsuarioDAO().updateUsuarios(Id, this.presupuesto, this.tiempoDisponible);
		this.itinerario.update(this.Id);
	}
	
	public HashMap<String, String> validate(){
		HashMap<String, String> errors = new HashMap<String, String>();
		
		if(username.isBlank()) errors.put("name", "El nombre es requerido");	
		if(password.isBlank()) errors.put("password", "La contraseña es requerida");
		else if(password.length() < 6) errors.put("password", "La contraseña debe tener al menos 6 caracteres");
		if(presupuesto < 0) errors.put("price", "El dinero debe ser positivo");
		if(tiempoDisponible <=0) errors.put("tiempoDisponible", "el tiempo disponible del usuario debe ser mayor o igual a 1");
		System.out.println(errors);
		return errors;
	}

}
