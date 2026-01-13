package ajedrez.servicio.test;

import java.io.FileNotFoundException;

import javax.json.bind.JsonbException;

import ajedrez.modelo.TipoFinalizacion;
import ajedrez.servicio.IServicioPartida;
import repositorio.EntidadNoEncontrada;
import repositorio.RepositorioException;
import servicio.FactoriaServicios;

/**
 * Programa para poblar la base de datos con partida m.
 */
public class Programa {
	
    public static void main(String[] args) throws JsonbException, FileNotFoundException {
        
    	IServicioPartida servicio = FactoriaServicios.getServicio(IServicioPartida.class);
        
        try {
            System.out.println("=== Creando partidas ===");
            
            // crear partida
            String p1 = servicio.crearPartida(1, "torneo_0004", "jugador_001", "jugador_002", 10.0, 3.0);
            servicio.registrarMovimiento(p1, "e4", "e5");            
            servicio.registrarMovimiento(p1, "Ac4", "Cc6");
            servicio.registrarMovimiento(p1, "Dh5", "Ac5");
            servicio.registrarMovimiento(p1, "Dxf7++", null);            
            servicio.establecerResultadoPartida(p1, "jugador_001", TipoFinalizacion.JAQUE_MATE);
            
            System.out.println(servicio.recuperarPartidas());
            System.out.println("=== final ===");
            
            
            // JSONB
            servicio.añadirJson("id");
        } catch (RepositorioException e) {
            System.err.println("Error de repositorio: " + e.getMessage());
            e.printStackTrace();
        } catch (EntidadNoEncontrada e) {
            System.err.println("Entidad no encontrada: " + e.getMessage());
            e.printStackTrace();
        }
    }
}