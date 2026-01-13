package tablones.servicio.test;

import java.time.LocalDateTime;
import java.util.List;

import repositorio.EntidadNoEncontrada;
import repositorio.RepositorioException;
import tablones.modelo.Anuncio;
import tablones.servicio.IServicioTablones;
import tablones.servicio.ServicioTablones;
import tablones.servicio.TablonResumen;

/**
 * Programa para poblar la base de datos con tablones y anuncios de prueba.
 */
public class Programa {
	
    public static void main(String[] args) {
        
        IServicioTablones servicio = new ServicioTablones();
        
        try {
            System.out.println("=== Creando tablones ===");
            
            // Crear tablón 1: Noticias Universidad
            String idTablon1 = servicio.crearTablon("Noticias Universidad");
            System.out.println("Tablón 1 creado/recuperado con ID: " + idTablon1);
            
            // Crear tablón 2: Ofertas de Empleo
            String idTablon2 = servicio.crearTablon("Ofertas de Empleo");
            System.out.println("Tablón 2 creado/recuperado con ID: " + idTablon2);
            
            System.out.println("\n=== Añadiendo anuncios al tablón 1 (Noticias Universidad) ===");
            
            // Añadir anuncios al tablón 1
            Integer idAnuncio1 = servicio.añadirMensaje(idTablon1, 
                "Apertura de matrículas", 
                "Se abre el periodo de matriculación para el curso 2025-2026", 
                "https://universidad.es/matriculas");
            System.out.println("Anuncio 1 creado con ID: " + idAnuncio1);
            
            Integer idAnuncio2 = servicio.añadirMensaje(idTablon1, 
                "Conferencia de Inteligencia Artificial", 
                "El próximo viernes tendrá lugar una conferencia sobre IA en el Aula Magna", 
                "https://universidad.es/eventos/ia");
            System.out.println("Anuncio 2 creado con ID: " + idAnuncio2);
            
            Integer idAnuncio3 = servicio.añadirMensaje(idTablon1, 
                "Cierre por vacaciones", 
                "La universidad permanecerá cerrada del 23 de diciembre al 7 de enero", 
                null); // Enlace opcional nulo
            System.out.println("Anuncio 3 creado con ID: " + idAnuncio3);
            
            System.out.println("\n=== Añadiendo anuncios al tablón 2 (Ofertas de Empleo) ===");
            
            // Añadir anuncios al tablón 2
            Integer idAnuncio4 = servicio.añadirMensaje(idTablon2, 
                "Desarrollador Java Senior", 
                "Empresa tecnológica busca desarrollador Java con experiencia en Spring Boot", 
                "https://empleo.com/java-senior");
            System.out.println("Anuncio 4 creado con ID: " + idAnuncio4);
            
            Integer idAnuncio5 = servicio.añadirMensaje(idTablon2, 
                "Prácticas en Inteligencia Artificial", 
                "Oportunidad de prácticas en departamento de IA. Se requiere conocimientos de Python", 
                "https://empleo.com/practicas-ia");
            System.out.println("Anuncio 5 creado con ID: " + idAnuncio5);
            
            Integer idAnuncio6 = servicio.añadirMensaje(idTablon2, 
                "Analista de Datos Junior", 
                "Buscamos analista de datos para incorporación inmediata", 
                "https://empleo.com/analista-datos");
            System.out.println("Anuncio 6 creado con ID: " + idAnuncio6);
            
            System.out.println("\n=== Listado de tablones creados ===");
            
            // Obtener listado de tablones
            List<TablonResumen> tablones = servicio.getListadoResumen();
            for (TablonResumen resumen : tablones) {
                System.out.println("ID: " + resumen.getId() + " - Nombre: " + resumen.getTitulo());
            }
            
            System.out.println("\n=== Probando recuperar mensajes ===");
            
            // Probar recuperar mensajes con filtro de texto
            System.out.println("\nMensajes del tablón 1 que contienen 'Inteligencia':");
            List<Anuncio> mensajesIA = servicio.getMensajes(idTablon1, "Inteligencia", null);
            for (Anuncio a : mensajesIA) {
                System.out.println("  - " + a.getTitulo());
            }
            
            // Probar recuperar mensajes con filtro de fecha
            System.out.println("\nMensajes del tablón 2 publicados a partir de hoy:");
            List<Anuncio> mensajesHoy = servicio.getMensajes(idTablon2, null, LocalDateTime.now().minusDays(1));
            for (Anuncio a : mensajesHoy) {
                System.out.println("  - " + a.getTitulo());
            }
            
            // Probar recuperar todos los mensajes (sin filtros)
            System.out.println("\nTodos los mensajes del tablón 1:");
            List<Anuncio> todosMensajes = servicio.getMensajes(idTablon1, null, null);
            for (Anuncio a : todosMensajes) {
                System.out.println("  - " + a.getTitulo() + " | " + a.getResumen());
            }
            
            System.out.println("\n=== Programa finalizado correctamente ===");
            
        } catch (RepositorioException e) {
            System.err.println("Error de repositorio: " + e.getMessage());
            e.printStackTrace();
        } catch (EntidadNoEncontrada e) {
            System.err.println("Entidad no encontrada: " + e.getMessage());
            e.printStackTrace();
        }
    }
}