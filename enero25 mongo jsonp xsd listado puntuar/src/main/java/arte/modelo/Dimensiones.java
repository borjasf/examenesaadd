package arte.modelo;

public class Dimensiones {
double alto;
double ancho;
double profundidad;

public Dimensiones(double alto, double ancho, double profundidad) {
	this.alto = alto;
	this.ancho = ancho;
	this.profundidad = profundidad;
}
public Dimensiones() {
	
}
public double getAlto() {
	return alto;
}
public void setAlto(double alto) {
	this.alto = alto;
}
public double getAncho() {
	return ancho;
}
public void setAncho(double ancho) {
	this.ancho = ancho;
}
public double getProfundidad() {
	return profundidad;
}
public void setProfundidad(double profundidad) {
	this.profundidad = profundidad;
}
 
}