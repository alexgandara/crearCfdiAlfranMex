package CFDI;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.GeneralSecurityException;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;


public class Prepara_Documento {
	
	// RUTAS Y ARCHIVOS
		public static String $PATH_ARCHIVOS_PLANOS="";
		public static String $PATH_SIN_FIRMA="";
		public static String $PATH_CON_FIRMA="";
		public static String $FILE_NAME_XML="";
		public static String $PATH_RESPUESTAS="";
		public static String $PATH_PDFS="";
		public static String $PATH_TICKETS="";
		public static String $PATH_RESPUESTAS_STATUS="";
		public static String $PATH_CERTIFICADOS="";
		
		
		// DATOS DEL EMISOR
		public static String $RUC="";
		public static String $RAZON_SOCIAL="";
		public static String $CODIGO_POSTAL="";
		public static String $DIRECCION="";
		public static String $CIUDAD="";
		public static String $PAIS="";
		
		// DATOS DE LA LLAVE
		public static String $KEYSTORE="";
		public static String $PASSWORD_KEYSTORE="";
		public static String $PASSWORD_CERTIFICADO="";
		public static String $ALIAS_CERTIFICADO="";
		

//	public static void firmar(String $PATH_XMLS_SIN_FIRMA, String $PATH_XMLS_CON_FIRMA, String $FILE_NAME, parametros misParametros, String $PATH_CADENA_ORIGINAL, String _ruc_receptor) throws GeneralSecurityException, IOException, TransformerException {
	public static void firmar(String $PATH_XMLS_SIN_FIRMA, String $PATH_XMLS_CON_FIRMA, String $FILE_NAME, parametros misParametros, String $PATH_CADENA_ORIGINAL, String _ruc_receptor, String _tipo_documento) throws GeneralSecurityException, IOException, TransformerException {
				
		
		$PATH_ARCHIVOS_PLANOS=misParametros.get_ruta_base();
		$PATH_SIN_FIRMA=misParametros.get_ruta_xml_sin_firma();
		$PATH_CON_FIRMA=misParametros.get_ruta_xml_con_firma();
		$PATH_RESPUESTAS=misParametros.get_ruta_respuestas_status();
		$PATH_PDFS=misParametros.get_ruta_pdfs();
		$PATH_TICKETS=misParametros.get_ruta_tickets();
		$PATH_RESPUESTAS_STATUS=misParametros.get_ruta_respuestas_status();
		$PATH_CERTIFICADOS=misParametros.get_ruta_certificados();
		
				
		$RUC=misParametros.get_ruc();
		$RAZON_SOCIAL=misParametros.get_razon_social();
		$CODIGO_POSTAL=misParametros.get_codigo_postal();
		$DIRECCION=misParametros.get_direccion();
		$CIUDAD=misParametros.get_ciudad();
		$PAIS=misParametros.get_pais();
		
		$KEYSTORE=misParametros.get_keystore();
		$PASSWORD_KEYSTORE=misParametros.get_password_keystore();
		$PASSWORD_CERTIFICADO=misParametros.get_password_certificado();
		$ALIAS_CERTIFICADO=misParametros.get_alias_certificado();
		
		
		
		

		// elavoracion de cadena original
		
		// cargar el archivo XSLT
	
		
		String _file_cadena="";
		
		
	//	if (_tipo_documento.equals("LOC")) {
			_file_cadena="C:/fg/conector_qr/data/RAM0603016C6/cadenaoriginal/cadenaoriginal_3_3.xslt";
			
	//	}
		
		
	//	if (_tipo_documento.equals("EXP")) {
	//		_file_cadena="C:/fg/conector_qr/data/RAM0603016C6/cadenaoriginal/ComercioExterior11.xslt";
			
			
			
	//	}

		System.out.println("Archivo para generar Cadena Original:"+_file_cadena);
		
		
//		File xslt = new File("C:/fg/conector_qr/data/RAM0603016C6/cadenaoriginal/cadenaoriginal_3_3.xslt");
//		StreamSource sourceXSL = new StreamSource(xslt);
	
		
		File xslt = new File(_file_cadena);
		StreamSource sourceXSL = new StreamSource(xslt);

		
		// cargar el CFDI
	//	File cfdi = new File("E:/conector_qr/data/RAM0603016C6/02_xmls_sin_firma/RAM0603016C6-FAC-AMMTY18-000002.xml");
		 File cfdi = new File($PATH_XMLS_SIN_FIRMA+$FILE_NAME+".xml");
	//	System.out.println("Archivo Xml    "+cfdi.toString());
		StreamSource sourceXML = new StreamSource(cfdi);

		TransformerFactory tFactory = TransformerFactory.newInstance();
		Transformer transformer = null;
		try {
			transformer = tFactory.newTransformer(sourceXSL);
		} catch (TransformerConfigurationException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}

		
		String cadenaOriginal="";

		// aplicar las reglas del XSLT con los datos del CFDI y escribir el resultado en output
		StringWriter outWriter = new StringWriter();
		try {
			transformer.transform(sourceXML, new StreamResult(outWriter));
		} catch (TransformerException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		StringBuffer sb = outWriter.getBuffer(); 
		cadenaOriginal = sb.toString();
		
		System.out.println("Cadena Original: "+cadenaOriginal);
		
			
		// escribe en texto la cadena original
		write_cadena_original(cadenaOriginal, $PATH_CADENA_ORIGINAL+$FILE_NAME+".txt");
		
		
		// leyendo lllave publica.
		
		String _clavePublica="";
		
		String _file_llave_publica="C:\\fg\\conector_qr\\data\\RAM0603016C6\\certificados\\cerB64.txt";
		
		if (_ruc_receptor.equals("TES030201001")) {
			_file_llave_publica="C:\\fg\\conector_qr\\data\\RAM0603016C6\\certificados\\cerB64_pruebas.txt";
			System.out.println("leyo la llave del de pruebas------------------------------------");
			
		}
		
	    StringBuilder sb_llave = new StringBuilder();

        try (BufferedReader br_llave = Files.newBufferedReader(Paths.get(_file_llave_publica))) {

            // read line by line
            String line;
            while ((line = br_llave.readLine()) != null) {
            	sb_llave.append(line);
            }
            _clavePublica=sb_llave.toString();
            
            System.out.println("Llave Publica:----> "+_clavePublica);
            
            
        } catch (IOException e) {
            System.err.format("IOException: %s%n", e);
        }
        
        
        System.out.println(sb);
	
		
		
		
		
		
		
		
		
		
		
		// _clavePublica="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";
		
		
		
		// Instantiate the document to be signed.
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		dbf.setNamespaceAware(true);
		try {
			Document doc = dbf.newDocumentBuilder().parse
			   (new FileInputStream($PATH_XMLS_SIN_FIRMA+$FILE_NAME+".xml"));
			
			Element element = (Element) doc.getElementsByTagName("cfdi:Comprobante").item(0);
	//		element.setAttribute("Sello","" );
			element.setAttribute("Certificado",_clavePublica);

			
			
			OutputStream os = new FileOutputStream($PATH_XMLS_CON_FIRMA+$FILE_NAME+".xml");
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
			
			System.out.println("El Archivo "+$FILE_NAME+" fue creado y firmado!");
			
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		
		
		 
		
		
		
		
		
		
	}
	
	
	


	public static String getFileContent(
			   FileInputStream fis
			    ) throws IOException
			 {
			   try( BufferedReader br =
			           new BufferedReader( new InputStreamReader(fis )))
			   {
			      StringBuilder sb = new StringBuilder();
			      String line;
			      while(( line = br.readLine()) != null ) {
			         sb.append( line );
			        // sb.append( '\n' );
			      }
			      return sb.toString();
			   }
			}
	
	
	
	private static void write_cadena_original(String txt_cadena_original, String cadena_original_file_name) {
		String raya="------------------------------------------------";
		  try {

			  
			  
			  System.out.println(raya);
			  	File archivo_hash=new File(cadena_original_file_name);
			  	archivo_hash.delete();
			//  File fileDir = new File("c:\\temp\\test.txt");
		         Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(archivo_hash), "UTF-8"));
		         out.append(txt_cadena_original);
		     //    out.append(txt_cadena_original).append("\r\n");
		        
		         out.flush();
		         out.close();		  
		
			//  File archivo_hash=new File(cadena_original_file_name);
//			  archivo_hash.delete();
//			  FileWriter chanel_write=new FileWriter(archivo_hash,true);
//			  chanel_write.write(txt_cadena_original);
//			  chanel_write.close();
			  
			  
		  } catch (Exception e) {
		  		e.printStackTrace();
	    	}
	}


}