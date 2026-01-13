package ejercicio1;

import java.io.File;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;

public class ProgramaEjercicio1 {

    public static void modificarTablon(Document documento) {
        // 1. Obtener todas las entradas
        NodeList entrys = documento.getElementsByTagName("entry");

        // 2. Recorrer las entradas
        for (int i = 0; i < entrys.getLength(); i++) {
            Element entry = (Element) entrys.item(i);

            // 3. Buscar la etiqueta <link> y <summary> dentro de esta entrada
            NodeList links = entry.getElementsByTagName("link");
            NodeList summaries = entry.getElementsByTagName("summary");

            // Solo procesamos si existen ambos elementos
            if (links.getLength() > 0 && summaries.getLength() > 0) {
                Element linkNode = (Element) links.item(0);
                Element summaryNode = (Element) summaries.item(0);

                // 4. Obtener el atributo href (NO el TextContent)
                String url = linkNode.getAttribute("href");

                if (!url.isEmpty()) {
                    // 5. Crear el nuevo elemento <a>
                    Element elementA = documento.createElement("a");
                    elementA.setAttribute("href", url);
                    // Le ponemos texto al enlace para que no esté vacío
                    elementA.setTextContent("Enlace al PDF");

                    // 6. Añadir un espacio en blanco (opcional, para estética)
                    summaryNode.appendChild(documento.createTextNode(" - "));
                    
                    // 7. Añadir el elemento <a> al final del summary
                    summaryNode.appendChild(elementA);
                    
                    System.out.println("Modificado entry con ID: " + 
                        entry.getElementsByTagName("id").item(0).getTextContent());
                }
            }
        }
    }

    public static void guardarDocumento(Document documento, String rutaFichero) {
        try {
            TransformerFactory transFactory = TransformerFactory.newInstance();
            Transformer transformer = transFactory.newTransformer();
            
            // Configuración para identar el XML de salida (opcional pero recomendado)
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            
            DOMSource source = new DOMSource(documento);
            StreamResult result = new StreamResult(new File(rutaFichero));
            
            transformer.transform(source, result);
            System.out.println("Fichero guardado en: " + rutaFichero);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    
    public static void validarDocumento(String nombreFichero, String ficheroEsquema) {
        try {
            // 1. Crear la factoría
            SchemaFactory factoriaSchema = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);

            // 2. Construye el esquema
            Schema esquema = factoriaSchema.newSchema(new File(ficheroEsquema));

            // 3. Solicita al esquema la construcción de un validador
            Validator validador = esquema.newValidator();

            // 4. Registra el manejador de eventos de error (Tu código)
            validador.setErrorHandler(new DefaultHandler() {
                @Override
                public void error(SAXParseException e) throws SAXException {
                    System.out.println("Error de validación: " + e.getMessage());
                    // throw e; -> Si quisieras detener la validación al primer error
                }
            });

            // 5. Solicita la validación del fichero XML 
            validador.validate(new StreamSource(new File(nombreFichero)));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception {

        // --- CARGA DEL DOM ---
        DocumentBuilderFactory factoriaDOM = DocumentBuilderFactory.newInstance();
        DocumentBuilder analizador = factoriaDOM.newDocumentBuilder();
        // Asegúrate de que la ruta es correcta en tu proyecto
        Document documento = analizador.parse("xml/tablon.xml"); 

        // --- XPATH (Solo para mostrar por consola, como pedía el ejercicio original) ---
        XPathFactory factoria = XPathFactory.newInstance();
        XPath xpath = factoria.newXPath();
        XPathExpression consulta = xpath.compile("//entry[summary]");
        NodeList resultado = (NodeList) consulta.evaluate(documento, XPathConstants.NODESET);

        System.out.println("--- Entradas con summary encontradas (XPath) ---");
        for (int i = 0; i < resultado.getLength(); i++) {
            // Nota: Imprimimos el ID o Title para identificarlo, no todo el nodo
            Element entry = (Element) resultado.item(i);
            String id = entry.getElementsByTagName("id").item(0).getTextContent();
            System.out.println("Entry ID: " + id);
        }
        System.out.println("----------------------------------------------");

        // --- PROCESAMIENTO DOM ---
        modificarTablon(documento);

        // --- GUARDAR EL RESULTADO ---
        guardarDocumento(documento, "xml/tablon-2.xml");
        
        // validar esquema
        validarDocumento("xml/tablon-2.xml", "xml/tablon.xsd");
        
        
    }
}