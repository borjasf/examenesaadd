package arte.modelo;

public class Puntuacion {
String usuario;
double nota_tecnica;
double nota_artistica;
String comentario;
public Puntuacion(String usuario, double nota_tecnica, double nota_artistica, String comentario) {
	this.usuario = usuario;
	this.nota_tecnica = nota_tecnica;
	this.nota_artistica = nota_artistica;
	this.comentario = comentario;
}
public Puntuacion() {
	
}
public String getUsuario() {
	return usuario;
}
public void setUsuario(String usuario) {
	this.usuario = usuario;
}
public double getNota_tecnica() {
	return nota_tecnica;
}
public void setNota_tecnica(double nota_tecnica) {
	this.nota_tecnica = nota_tecnica;
}
public double getNota_artistica() {
	return nota_artistica;
}
public void setNota_artistica(double nota_artistica) {
	this.nota_artistica = nota_artistica;
}
public String getComentario() {
	return comentario;
}
public void setComentario(String comentario) {
	this.comentario = comentario;
}

}
