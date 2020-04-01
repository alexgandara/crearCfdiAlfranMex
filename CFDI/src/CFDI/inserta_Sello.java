package CFDI;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

public class inserta_Sello {

	public static void main(String[] args) throws FileNotFoundException, IOException, TransformerException {
		
		String file_xml = args[0];
		
		String file_sello =  args[1];
		
//		file_xml="C:\\fg\\conector_qr\\data\\RAM0603016C6\\03_xmls_con_firma\\RAM0603016C6-FAC-AMMTY18-000019.xml";
		
		String sello="no esta el sello";
		
	    StringBuilder sb = new StringBuilder();

        try (BufferedReader br = Files.newBufferedReader(Paths.get(file_sello))) {

            // read line by line
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
            sello=sb.toString();
            
            System.out.println("Sello Digital: "+sello);
            
            
        } catch (IOException e) {
            System.err.format("IOException: %s%n", e);
        }
        
        
        System.out.println(sb);
		
		
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		dbf.setNamespaceAware(true);
		try {
			Document doc = dbf.newDocumentBuilder().parse
			   (new FileInputStream(file_xml));
			
			Element element = (Element) doc.getElementsByTagName("cfdi:Comprobante").item(0);
			element.setAttribute("Sello",sello );
	//		element.setAttribute("Certificado",_clavePublica);

			
			
			OutputStream os = new FileOutputStream(file_xml);
			TransformerFactory tf = TransformerFactory.newInstance();
			Transformer trans = null;
			try {
				trans = tf.newTransformer();
			} catch (TransformerConfigurationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			doc.setXmlStandalone(true);
			trans.transform(new DOMSource(doc), new StreamResult(os));
			
		//	comprimirXML.comprimir($PATH_XMLS_CON_FIRMA+$FILE_NAME+".zip",$PATH_XMLS_CON_FIRMA+$FILE_NAME+".xml",$FILE_NAME+".xml");
			
			System.out.println("El Archivo "+file_xml+" se le agrego el sello !");
			
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		
		
	}

}
