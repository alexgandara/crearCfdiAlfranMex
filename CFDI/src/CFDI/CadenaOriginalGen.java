package CFDI;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.apache.commons.ssl.Base64;
import org.apache.commons.ssl.PKCS8Key;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.security.KeyFactory;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.Signature;
import java.security.spec.PKCS8EncodedKeySpec;



public class CadenaOriginalGen {

	public static void main(String[] args) throws TransformerException, IOException, NoSuchAlgorithmException {

		String _doc="FAC-AMMTY18-000002";

		// cargar el archivo XSLT
		File xslt = new File("C:/fg/conector_qr/data/RAM0603016C6/cadenaoriginal_3_3.xslt/cadenaoriginal_3_3.xslt");
		StreamSource sourceXSL = new StreamSource(xslt);

		// cargar el CFDI
		File cfdi = new File("c:/fg/conector_qr/data/RAM0603016C6/02_xmls_sin_firma/RAM0603016C6-FAC-AMMTY18-000019.xml");
	//	File cfdi = new File("c:/fg/conector_qr/data/RAM0603016C6/02_xmls_sin_firma/CFDI_sin_timbrar.xml");
		
		
		StreamSource sourceXML = new StreamSource(cfdi);

		// crear el procesador XSLT que nos ayudará a generar la cadena original
		// con base en las reglas del archivo XSLT
		TransformerFactory tFactory = TransformerFactory.newInstance();
		Transformer transformer = tFactory.newTransformer(sourceXSL);

		String _cadena="";

		// aplicar las reglas del XSLT con los datos del CFDI y escribir el resultado en output
		StringWriter outWriter = new StringWriter();
		transformer.transform(sourceXML, new StreamResult(outWriter));
		StringBuffer sb = outWriter.getBuffer(); 
		_cadena = sb.toString();

		// E:\conector_qr\data\RAM0603016C6\23_cadena_original
		File archivo_cadena=new File("C:/FG/conector_qr/data/RAM0603016C6/23_cadena_original/RAM0603016C6-FAC-AMMTY18-000016.txt");
		archivo_cadena.delete();
		FileWriter chanel_write=new FileWriter(archivo_cadena,true);
		chanel_write.write(_cadena);
		chanel_write.close();

		System.out.println("Cadena Original: "+_cadena);

		String _sha1="";
		_sha1=sha1(_cadena);
		File archivo_sha1=new File("C:/FG/conector_qr/data/RAM0603016C6/24_sha1/RAM0603016C6-FAC-AMMTY18-000016.txt");
		archivo_sha1.delete();
		FileWriter chanel_write_sha1=new FileWriter(archivo_sha1,true);
		chanel_write_sha1.write(_sha1);
		chanel_write_sha1.close();

		
		
		System.out.println("SHA1           : "+_sha1);
		
		
		////////////////////////////////////////////////////////
		
	//	cfdi.appendChild( comprobante );
	//	//XML Terminado sin TIMBRAR
	//	int n = new Random().nextInt(50) + 1;
	//	//NumeroRandom para crear archivo temporal y poder hacer CadenaOriginal
	//	File xslt = new File("Timbrado/cadenaoriginal-3_3xslt/cadenaoriginal_3_3.xslt");
	//	StreamSource sourceXSL = new StreamSource(xslt);
	//	//XSLT para formar CadenaOriginal
	//	DOMSource source = new DOMSource(cfdi);
	//	FileWriter writer = new FileWriter(new File(Funcion.rutaArchivos + "Temp/temp" + n + ".xml"));
	//	StreamResult sourceXML = new StreamResult(writer);
	//	TransformerFactory transformerFactory = TransformerFactory.newInstance();
	//	Transformer transformer = transformerFactory.newTransformer();
	//	transformer.transform(source, sourceXML);
		
	//	//CFDI Temporal
	//	ByteArrayOutputStream bos=new ByteArrayOutputStream();
	//	StreamResult cadenaOriginal = new StreamResult(bos);
	//	File xmlFile = new File("E:/conector_qr/data/RAM0603016C6/02_xmls_sin_firma/RAM0603016C6-FAC-AMMTY18-000002.xml");
	//	StreamSource sourceXML2 = new StreamSource(xmlFile);
	//	TransformerFactory tFactory = TransformerFactory.newInstance();
	//	Transformer transformer2 = tFactory.newTransformer(sourceXSL);
	//	transformer2.transform(sourceXML2, cadenaOriginal);
	
		/*
		//Cadena Original
		File keyFile = new File("LlavePkcs8PF.key.pem");
		String p4ss="12345678a";
	//	StringBuffer bos = new StringBuffer();
		InputStream keyFileInput = new FileInputStream(keyFile);
		PKCS8Key pkcs8 = new PKCS8Key(keyFileInput, p4ss.toCharArray());
		KeyFactory privateKeyFactory = KeyFactory.getInstance("RSA");
		PKCS8EncodedKeySpec pkcs8Encoded = new PKCS8EncodedKeySpec(pkcs8.getDecryptedBytes());
		PrivateKey privateKey = privateKeyFactory.generatePrivate(pkcs8Encoded);
		Signature signature = Signature.getInstance("SHA256withRSA");
		signature.initSign(privateKey);
		byte[] cadenaOriginalArray = bos.toByteArray();
		signature.update(cadenaOriginalArray);
		String firma = new String(Base64.encodeBase64(signature.sign()), "UTF-8");
		System.out.println("------------------ Cadena Original: " + bos.toString());
		System.out.println("---------------------------- Firma: " + firma);
		
		*/
		///////////////////////////////////////////////////////
		
	
	}




	static String sha1(String input) throws NoSuchAlgorithmException {
		MessageDigest mDigest = MessageDigest.getInstance("SHA1");
		byte[] result = mDigest.digest(input.getBytes());
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < result.length; i++) {
			sb.append(Integer.toString((result[i] & 0xff) + 0x100, 16).substring(1));
		}

		return sb.toString();
	}

}