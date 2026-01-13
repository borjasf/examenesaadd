package tareas.servicio.test;

import java.io.File;
import java.time.LocalDate;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;

import tareas.modelo.Estado;
import tareas.modelo.Tarea;

public class Ejercicio2 {
	public static void main(String[] args) {

		try {
			JAXBContext contexto = JAXBContext.newInstance(Tarea.class);
			Marshaller marshaller = contexto.createMarshaller();
			
			marshaller.setProperty("jaxb.formatted.output", true);
			Tarea tarea = new Tarea("ir al medico","dentista",LocalDate.now(),Estado.EN_ESPERA);
			marshaller.marshal(tarea, new File("xml/tarea.xml"));
			SchemaFactory factoriaSchema =
					SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
					// Construye el esquema
					Schema esquema = factoriaSchema.newSchema(new File("xml/tarea.xsd"));
					// Solicita al esquema la construcción de un validador
					Validator validador = esquema.newValidator();
					// Registra el manejador de eventos de error
					validador.setErrorHandler(new DefaultHandler() {
					public void error(SAXParseException e) throws SAXException {
					System.out.println("Error: " + e.getMessage());
					// throw e; -> para detener la validación
					}
					});
					// Solicita la validación del fichero XML
					validador.validate(new StreamSource("xml/tarea.xml"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	}
}
