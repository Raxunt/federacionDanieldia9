package entidades;

import java.time.format.DateTimeFormatter;

public class Palmares<T extends Metal, S extends Participante> {

	private long idPalmares;
	private T tipoMedalla;
	private S tipoParticipante;
	private Prueba prueba;
	private String observaciones;

	public Palmares() {
		super();

	}

	public Palmares(long idPalmares, T tipoMedalla, S tipoParticipante, Prueba prueba, String observaciones) {
		super();
		this.idPalmares = idPalmares;
		this.tipoMedalla = tipoMedalla;
		this.tipoParticipante = tipoParticipante;
		this.prueba = prueba;
		this.observaciones = observaciones;
	}

	public long getIdPalmares() {
		return idPalmares;
	}

	public void setIdPalmares(long idPalmares) {
		this.idPalmares = idPalmares;
	}

	public T getTipoMedalla() {
		return tipoMedalla;
	}

	public void setTipoMedalla(T tipoMedalla) {
		this.tipoMedalla = tipoMedalla;
	}

	public S getTipoParticipante() {
		return tipoParticipante;
	}

	public void setTipoParticipante(S tipoParticipante) {
		this.tipoParticipante = tipoParticipante;
	}

	public Prueba getPrueba() {
		return prueba;
	}

	public void setPrueba(Prueba prueba) {
		this.prueba = prueba;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	/*public static <T extends Metal> void mostrarDatosMetal (T elem) {
		System.out.println("Las cotas del metal son:[" + elem.cotasPurezaEfectiva()[0] +" - " + elem);
	}*/

	
	
	public String toString() {
		String ret = "";
		ret += "ID:" + this.idPalmares + "|" + this.tipoMedalla.toString() + "|" + this.prueba.getFecha().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) + "|"
				+ this.getPrueba().getLugar().getNombre() + "|" + this.getTipoParticipante().dorsal + this.getTipoParticipante().calle + /**(getTipoParticipante(). ? "individual" : "equipos") +**/ "\n";
		/*if (this.tipoParticipante()) {
			ret += this.nombresGanadores();
		}
		if (this.cerrada()) {
			Resultado res = this.getResultado();
			Participante[] podio = res.getPodio();
			ret += "Primer puesto:" + podio[0].getId() + ", con el dorsal" + podio[0].getDorsal() + " por la calle "
					+ podio[0].getCalle() + " Oro#" + res.getPrimero().getId() + "\n";
			ret += "Segundo puesto:" + podio[1].getId() + ", con el dorsal" + podio[1].getDorsal() + " por la calle "
					+ podio[1].getCalle() + " Oro#" + res.getSegundo().getId() + "\n";
			ret += "Tercer puesto:" + podio[2].getId() + ", con el dorsal" + podio[2].getDorsal() + " por la calle "
					+ podio[2].getCalle() + " Oro#" + res.getTercero().getId() + "\n";
		}*/
		return ret;
	}
	
	
	
	
	
	
	
}