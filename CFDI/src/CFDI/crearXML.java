package CFDI;

import java.io.File;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.soap.util.mime.ByteArrayDataSource;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

// alex

public class crearXML {

	public static String[] myParam = new String[300];

	public static String[] myArrayCab = new String[1];
	public static int _counterCab = 1;

	public static String[] myArrayCab_Aca = new String[1];
	public static int _counterCab_aca = 1;

	public static String[] myArrayLey = new String[100];
	public static int _counterley = 1;

	public static String[] myArrayDet = new String[100];
	public static int _counterDet = 1;

	public static String[] myArrayRel = new String[100];
	public static int _counterRel = 1;
	public static int _counterLey = 1;

	public static String $PATH = "";

	public static String $FILE_NAME = "";

	// RUTAS Y ARCHIVOS
	public static String $PATH_ARCHIVOS_PLANOS = "";
	public static String $PATH_SIN_FIRMA = "";
	public static String $PATH_CON_FIRMA = "";
	public static String $FILE_NAME_XML = "";
	public static String $FILE_NAME_CANTIDAD_LETRAS = "";
	public static String $FILE_NAME_LOGS = "";
	public static String $PATH_RESPUESTAS = "";
	public static String $PATH_PDFS = "";
	public static String $PATH_TICKETS = "";
	public static String $PATH_RESPUESTAS_STATUS = "";
	public static String $PATH_CERTIFICADOS = "";
	public static String $PATH_CANTIDAD_LETRAS = "";
	public static String $PATH_QR = "";
	public static String $PATH_417 = "";
	public static String $PATH_LOGS = "";
	public static String $PATH_CADENA_ORIGINAL = "";

	public static String $FILE_PATH_NAME_QR = "";
	public static String $FILE_PATH_NAME_417 = "";

	// DATOS DEL EMISOR
	public static String $RUC = "";
	public static String $RAZON_SOCIAL = "";
	public static String $CODIGO_POSTAL = "";
	public static String $DIRECCION = "";
	public static String $DISTRITO = "";
	public static String $DEPARTAMENTO = "";
	public static String $CIUDAD = "";
	public static String $UBIGEO = "";
	public static String $PAIS = "";
	public static String $NOMBRE_COMERCIAL = "";
	public static String $LUGAR_EXPEDICION = "";
	public static String $TIPO_DE_CAMBIO = "";

	// DATOS DE LA LLAVE
	public static String $KEYSTORE = "";
	public static String $PASSWORD_KEYSTORE = "";
	public static String $PASSWORD_CERTIFICADO = "";
	public static String $ALIAS_CERTIFICADO = "";
	public static String $NUMERO_CERTIFICADO;

	public static double $SUM_IGV = 0;

	public static String $SERIE = "";
	public static String $NUMERO = "";
	public static String $TIPO_DOCUMENTO = "";
	public static String $FORMA_DE_PAGO = "";
	public static String $METODO_DE_PAGO = "";
	public static String $CONDICIONES_DE_PAGO = "";
	public static String $TIPO_DE_COMPROBANTE = "";
	public static String $UUID_NC = "";

	// declaracion de objeto tipo cabecera
	public static cabecera myCabecera = new cabecera();
	public static aca myAca = new aca();
	public static ley[] myLey = new ley[100];
	public static detalle[] myDetalle = new detalle[100];
	public static documentos_relacionados[] mydocumentos_relacionados = new documentos_relacionados[100];
	public static String _letra_numero;

	public static String $FILE_PATH_NAME_XML = "";
	public static String $FILE_PATH_NAME_HASH = "";
	public static String $PATH_HASH = "";
	public static String $PORCENTAJE_IGV = "";

	public static String $HORA = "00:00:00";
	public static String $CORREO = "";
	public static String $ORDEN_DE_COMPRA = "0000000000";

	// String $PATH_XMLS_SIN_FIRMA

	public static void c_XML(DataHandler dh_det, DataHandler dh_cab,
			DataHandler dh_aca, DataHandler dh_ley, DataHandler dh_rel,
			String $FILE_NAME, parametros misParametros, String _firma_conecta)
			throws Exception {

		$FILE_NAME = misParametros.get_file_name();

		$PATH_ARCHIVOS_PLANOS = misParametros.get_ruta_base();
		$PATH_SIN_FIRMA = misParametros.get_ruta_xml_sin_firma();
		$PATH_CON_FIRMA = misParametros.get_ruta_xml_con_firma();
		$PATH_RESPUESTAS = misParametros.get_ruta_respuestas_status();
		$PATH_PDFS = misParametros.get_ruta_pdfs();
		$PATH_TICKETS = misParametros.get_ruta_tickets();
		$PATH_RESPUESTAS_STATUS = misParametros.get_ruta_respuestas_status();
		$PATH_CERTIFICADOS = misParametros.get_ruta_certificados();
		$PATH_CANTIDAD_LETRAS = misParametros.get_ruta_cantidad_en_letras();
		$PATH_QR = misParametros.get_ruta_qr();
		$PATH_417 = misParametros.get_ruta_417();
		$PATH_LOGS = misParametros.get_ruta_logs();
		$PATH_CADENA_ORIGINAL = misParametros.get_ruta_cadena_original();
		$PORCENTAJE_IGV = misParametros.get_porcentaje_igv();
		$NUMERO_CERTIFICADO = misParametros.get_numero_certificado();

		$RUC = misParametros.get_ruc();
		$RAZON_SOCIAL = misParametros.get_razon_social();
		$CODIGO_POSTAL = misParametros.get_codigo_postal();
		$DIRECCION = misParametros.get_direccion();
		$DISTRITO = misParametros.get_distrito();
		$DEPARTAMENTO = misParametros.get_departamento();
		$CIUDAD = misParametros.get_ciudad();
		$UBIGEO = misParametros.get_codigo_postal();

		$PAIS = misParametros.get_pais();
		$NOMBRE_COMERCIAL = misParametros.get_nombre_comercial();

		$KEYSTORE = misParametros.get_keystore();
		$PASSWORD_KEYSTORE = misParametros.get_password_keystore();
		$PASSWORD_CERTIFICADO = misParametros.get_password_certificado();
		$ALIAS_CERTIFICADO = misParametros.get_alias_certificado();

		$FILE_NAME_XML = $PATH_SIN_FIRMA + $FILE_NAME + ".xml";
		$FILE_NAME_CANTIDAD_LETRAS = $PATH_CANTIDAD_LETRAS + $FILE_NAME
				+ ".cnt";

		$FILE_NAME_LOGS = $PATH_LOGS + $FILE_NAME + ".TXT";

		System.out.println("Conector Factura Global Ver 2.0");
		System.out
				.println($RUC + " " + $RAZON_SOCIAL + " " + $NOMBRE_COMERCIAL);
		System.out.println("Version XML 2.1");

		// PrintStream console = System.out;

		File file_log = new File($FILE_NAME_LOGS);

		// if file doesnt exists, then create it
		if (!file_log.exists()) {
			file_log.createNewFile();
		}

		System.out.println("");

		System.out.println("");
		System.out
				.println($RUC + " " + $RAZON_SOCIAL + " " + $NOMBRE_COMERCIAL);
		System.out.println("");
		System.out.println("Version XML 2.1");
		System.out.println("");

		System.out.println("DOCUMENTO: " + $FILE_NAME);
		System.out.println("");
		System.out.println("");

		$SERIE = $FILE_NAME.substring(17, 24);
		// $TIPO_DOCUMENTO=$FILE_NAME.substring(21,29);
		int _tam = $FILE_NAME.length();
		$NUMERO = $FILE_NAME.substring(27, _tam);

		System.out.println("SERIE            :" + $SERIE);
		System.out.println("NUMERO           :" + $NUMERO);

		String _cadena = "";
		String _car = "";
		int _num = 0;

		// leemos el archivo plano cabecera

		readPlainTextCab(dh_cab);

		// separa los campos
		int _tam_cabecera = myArrayCab[0].length();
		int _tam_detalle;

		for (int i = 0; i < _tam_cabecera; i++) {
			_car = myArrayCab[0].substring(i, i + 1);

			if (!"|".equals(_car)) {

				_cadena = _cadena + _car;
				// System.out.println(_car);

			} else {

				_num++;

				if (_num == 1) {
					myCabecera.set_tipo_op(_cadena);
				}
				if (_num == 2) {
					myCabecera.set_fecha(_cadena);
				}
				if (_num == 3) {
					myCabecera.set_dom_fiscal(_cadena);
				}
				if (_num == 4) {
					myCabecera.set_ident(_cadena);
				}
				if (_num == 5) {
					myCabecera.set_num_ident(_cadena);
				}
				if (_num == 6) {
					myCabecera.set_nombre(_cadena);
				}
				if (_num == 7) {
					myCabecera.set_moneda(_cadena);
				}
				if (_num == 8) {
					myCabecera.set_desc_glo(Double.parseDouble(_cadena));
				}
				if (_num == 9) {
					myCabecera.set_sum_cargos(Double.parseDouble(_cadena));
				}
				if (_num == 10) {
					myCabecera.set_tot_desc(Double.parseDouble(_cadena));
				}
				if (_num == 11) {
					myCabecera.set_tot_vta_gra(Double.parseDouble(_cadena));
				}
				if (_num == 12) {
					myCabecera.set_tot_vta_in(Double.parseDouble(_cadena));
				}
				if (_num == 13) {
					myCabecera.set_tot_vta_exo(Double.parseDouble(_cadena));
				}
				if (_num == 14) {
					myCabecera.set_sum_igv(Double.parseDouble(_cadena));
				}
				if (_num == 15) {
					myCabecera.set_sum_isc(Double.parseDouble(_cadena));
				}
				if (_num == 16) {
					myCabecera.set_sum_otros(Double.parseDouble(_cadena));
				}
				if (_num == 17) {
					myCabecera.set_importe_tot(Double.parseDouble(_cadena));
				}

				if (_num == 20) {
					myCabecera.set_iva_ret(Double.parseDouble(_cadena));
				}

				
				_cadena = "";

			}
		}

		// myCabecera.set_tipo_comprobante($TIPO_DOCUMENTO);

		/*
		 * System.out.println("Cabecera-> Tipo de Operacion:        "+myCabecera.
		 * get_tipo_op());
		 * System.out.println("Cabecera-> Tipo de Comprobante       "
		 * +myCabecera.get_tipo_comprobante());
		 * System.out.println("Cabecera-> Fecha de Emision:         "
		 * +myCabecera.get_fecha());
		 * System.out.println("Cabecera-> Tipo de Dom Fiscal:       "
		 * +myCabecera.get_dom_fiscal());
		 * System.out.println("Cabecera-> Tipo de Identificacion:   "
		 * +myCabecera.get_ident());
		 * System.out.println("Cabecera-> Numero de Identidad:      "
		 * +myCabecera.get_num_ident());
		 * System.out.println("Cabecera-> Nombre:                   "
		 * +myCabecera.get_nombre());
		 * System.out.println("Cabecera-> Moneda:                   "
		 * +myCabecera.get_moneda());
		 * System.out.println("Cabecera-> Descuento Global:         "
		 * +myCabecera.get_desc_glo());
		 * System.out.println("Cabecera-> Suma de Cargos:           "
		 * +myCabecera.get_sum_cargos());
		 * System.out.println("Cabecera-> Total Descuentos:         "
		 * +myCabecera.get_tot_desc());
		 * System.out.println("Cabecera-> Total Vta Gravada:        "
		 * +myCabecera.get_tot_vta_gra());
		 * System.out.println("Cabecera-> Total Vta Inafectadas:    "
		 * +myCabecera.get_tot_vta_in());
		 * System.out.println("Cabecera-> Total Vta Exonerada:      "
		 * +myCabecera.get_tot_vta_exo());
		 * System.out.println("Cabecera-> Total Suma IGV:           "
		 * +myCabecera.get_sum_igv());
		 * System.out.println("Cabecera-> Total Suma ISC:           "
		 * +myCabecera.get_sum_isc());
		 * System.out.println("Cabecera-> Total Suma otros:         "
		 * +myCabecera.get_sum_otros());
		 * System.out.println("Cabecera-> Importe Total:            "
		 * +myCabecera.get_importe_tot());
		 */

		readPlainTextAca(dh_aca);

		// separa los campos
		int _tam_cabecera_aca = myArrayCab_Aca[0].length();
		_num = 0;

		for (int i = 0; i < _tam_cabecera_aca; i++) {
			_car = myArrayCab_Aca[0].substring(i, i + 1);

			if (!"|".equals(_car)) {

				_cadena = _cadena + _car;
				// System.out.println(_car);

			} else {

				_num++;
				// System.out.println(_cadena);
				if (_num == 1) {
					myCabecera.set_uso_cfdi(_cadena);
				}
				/*
				 * if (_num==2)
				 * {myAca.set_mtoBaseImponiblePercepcion(Double.parseDouble
				 * (_cadena));} if (_num==3)
				 * {myAca.set_mtoPercepcion(Double.parseDouble(_cadena));} if
				 * (_num==4)
				 * {myAca.set_mtoTotalIncPercepcion(Double.parseDouble(
				 * _cadena));} if (_num==5)
				 * {myAca.set_mtoOperGratuitas(Double.parseDouble(_cadena));} if
				 * (_num==6)
				 * {myAca.set_mtoTotalAnticipo(Double.parseDouble(_cadena));} if
				 * (_num==7) {myAca.set_codPaisCliente(_cadena);} if (_num==8)
				 * {myAca.set_codUbigeoCliente(_cadena);} if (_num==9)
				 * {myAca.set_desDireccionCliente(_cadena);} if (_num==10)
				 * {myAca.set_codPaisEntrega(_cadena);} if (_num==11)
				 * {myAca.set_codUbigeoEntrega(_cadena);}
				 */

				// if (_num==12) {myAca.set_desDireccionEntrega(_cadena);}
				if (_num == 12) {
					myAca.set_guia(_cadena);
				}

				// if (_num==13) {myAca.set_fecVencimiento(_cadena);}
				if (_num == 14) {
					myAca.set_tipo_documento(_cadena);
				}
				if (_num == 15) {
					myAca.set_calle(_cadena);
				}
				if (_num == 16) {
					myAca.set_colonia(_cadena);
				}
				if (_num == 17) {
					myAca.set_estado(_cadena);
				}
				if (_num == 18) {
					myAca.set_pais(_cadena);
				}
				if (_num == 19) {
					myAca.set_codigo_postal(_cadena);
				}
				// if (_num==20) {myAca.set_tipo_operacion(_cadena);}
				if (_num == 20) {
					myAca.set_clavedepedimento(_cadena);
				}
				if (_num == 21) {
					myAca.set_certificadoorigen(_cadena);
				}
				if (_num == 22) {
					myAca.set_incoterm(_cadena);
				}
				if (_num == 23) {
					myAca.set_subdivision(_cadena);
				}
				if (_num == 24) {
					myAca.set_tipo_operacion(_cadena);
				}
				if (_num == 25) {
					myAca.set_NumRegIdTrib(_cadena);
				}
				// System.out.println(_num+" "+_cadena);
				_cadena = "";

			}
		}

		/*
		 * System.out.println("Aca-> C�digo de r�gimen de percepci�n:       "+myAca
		 * .get_codRegPercepcion());
		 * System.out.println("Aca-> Base imponible de percepci�n:          "
		 * +myAca.get_mtoBaseImponiblePercepcion());
		 * System.out.println("Aca-> Monto de la percepci�n:                "
		 * +myAca.get_mtoPercepcion());
		 * System.out.println("Aca-> Monto total incluido la percepci�n:    "
		 * +myAca.get_mtoTotalIncPercepcion());
		 * System.out.println("Aca-> Total valor de venta - Op. gratuitas:  "
		 * +myAca.get_mtoOperGratuitas());
		 * System.out.println("Aca-> Total Anticipos:                       "
		 * +myAca.get_mtoTotalAnticipo());
		 * System.out.println("Aca-> Direcci�n del cliente (C�d de pa�s):   "
		 * +myAca.get_codPaisCliente());
		 * System.out.println("Aca-> Direcci�n del cliente (C�d de ubigeo): "
		 * +myAca.get_codUbigeoCliente());
		 * System.out.println("Aca-> Dir. cliente (Dir completa):           "
		 * +myAca.get_desDireccionCliente()); //
		 * System.out.println("Aca-> C�d de pa�s en que se entrega el bien: "
		 * +myAca.get_codPaisEntrega());
		 * System.out.println("Aca-> Guia:                                  "
		 * +myAca.get_guia()); //
		 * System.out.println("Aca-> C�d ubigeo donde se entrega el bien:   "
		 * +myAca.get_codUbigeoEntrega());
		 * System.out.println("Aca-> Orden de Compre:					     "
		 * +myAca.get_OC());
		 * System.out.println("Aca-> Dir completa deonde se entrega el bien:"
		 * +myAca.get_desDireccionEntrega());
		 * System.out.println("Aca-> Fecha de vencimiento:                  "
		 * +myAca.get_fecVencimiento());
		 */

		// leemos el arcivo plano detalle
		// readPlainTextDet($PATH_ARCHIVOS_PLANOS+$FILE_NAME+".det",dh_det);
		readPlainTextDet(dh_det);

		_counterDet = _counterDet - 1;

		for (int i = 1; i < _counterDet; i++) {
			myDetalle[i] = new detalle();
			_cadena = "";
			_num = 0;

			_tam_detalle = myArrayDet[i].length();
			for (int x = 0; x < _tam_detalle; x++) {
				_car = myArrayDet[i].substring(x, x + 1);

				if (!"|".equals(_car)) {
					_cadena = _cadena + _car;
					// System.out.println(_cadena);

				} else {
					_num++;
					// System.out.println(" i="+i+"   x  "+x+" num="+_num+"   "+_cadena+" tam "+_tam_detalle);
					if (_num == 1) {
						myDetalle[i].set_unidad_med(_cadena);
					}
					if (_num == 2) {
						myDetalle[i].set_cantidad(Double.parseDouble(_cadena));
					}
					if (_num == 3) {
						myDetalle[i].set_producto(_cadena);
					}
					if (_num == 4) {
						myDetalle[i].set_cod_sunat(_cadena);
					}
					if (_num == 5) {
						myDetalle[i].set_descrip(_cadena);
						myDetalle[i].set_descrip(myDetalle[i].get_descrip()
								.replace("&", "\n"));
					}
					if (_num == 6) {
						myDetalle[i]
								.set_valor_unit(Double.parseDouble(_cadena));
					}
					if (_num == 7) {
						myCabecera.set_desc_detalle(myCabecera
								.get_desc_detalle()
								+ myDetalle[i].get_desc_unit());
						myDetalle[i].set_desc_unit(Double.parseDouble(_cadena));
					}
					if (_num == 8) {
						myDetalle[i].set_igv_unit(Double.parseDouble(_cadena));
					}
					if (_num == 9) {
						myDetalle[i].set_afec_igv(_cadena);
					}
					if (_num == 10) {
						myDetalle[i].set_isc_unit(Double.parseDouble(_cadena));
					}
					if (_num == 11) {
						myDetalle[i].set_tipo_isc(_cadena);
					}
					if (_num == 12) {
						myDetalle[i].set_precio_unit(Double
								.parseDouble(_cadena));
					}
					if (_num == 13) {
						myDetalle[i].set_valor_tot(Double.parseDouble(_cadena));
					}
					if (_num == 14) {
						myDetalle[i].set_extra(_cadena);
					}
					if (_num == 15) {
						myDetalle[i].set_fraccion(_cadena);
					}
					if (_num == 16) {
						myDetalle[i].set_cantidad_expo(Double
								.parseDouble(_cadena));
					}
					if (_num == 17) {
						myDetalle[i].set_valor_unit_expo(Double
								.parseDouble(_cadena));
					}
					if (_num == 18) {
						myDetalle[i].set_iva_ret(Double.parseDouble(_cadena));
					}

					_cadena = "";
				}
			}

			// System.out.println(" ");
			// System.out.println("Detalle-> Unidad de Medida:             "+myDetalle[i].get_unidad_med());
			// System.out.println("Detalle-> Cantidad:                     "+myDetalle[i].get_cantidad());
			// System.out.println("Detalle-> Producto:                     "+myDetalle[i].get_producto());
			// System.out.println("Detalle-> Codigo Sunat:                 "+myDetalle[i].get_cod_sunat());
			// System.out.println("Detalle-> Codigo Descripcion:           "+myDetalle[i].get_descrip());
			// System.out.println("Detalle-> Valor Unidad:                 "+myDetalle[i].get_valor_unit());
			// System.out.println("Detalle-> Descuento de Unidad:          "+myDetalle[i].get_desc_unit());
			// System.out.println("Detalle-> Descuento Acumulado:          "+myCabecera.get_desc_detalle());
			// System.out.println("Detalle-> Monto de IGV por Unidad:      "+myDetalle[i].get_igv_unit());
			// System.out.println("Detalle-> Tipo de Afectacion IGV:       "+myDetalle[i].get_afec_igv());
			// System.out.println("Detalle-> Monto de ISC por Unidad:      "+myDetalle[i].get_isc_unit());
			// System.out.println("Detalle-> Tipo de Afectacion ISC:       "+myDetalle[i].get_tipo_isc());
			// System.out.println("Detalle-> Precio por Unidad:            "+myDetalle[i].get_precio_unit());
			// System.out.println("Detalle-> Valor Total:                  "+myDetalle[i].get_valor_tot());

			// System.out.println("Nombre del Archivo                      "+$FILE_NAME);

			myCabecera.set_desc_detalle(myCabecera.get_desc_detalle()
					+ myDetalle[i].get_desc_unit());

			// $SUM_IGV=$SUM_IGV+myDetalle[i].get_igv_unit();

			// leemos el arcivo plano relacionados

			readPlainTextRel(dh_rel);

			_counterRel = _counterRel - 1;

			for (int r = 1; r < _counterRel; r++) {
				mydocumentos_relacionados[r] = new documentos_relacionados();
				_cadena = "";
				_num = 0;

				_tam_detalle = myArrayRel[r].length();
				for (int x = 0; x < _tam_detalle; x++) {
					_car = myArrayRel[r].substring(x, x + 1);

					if (!"|".equals(_car)) {
						_cadena = _cadena + _car;
					} else {
						_num++;

						// public String _indDocRelacionado; // este se llena
						// con 99 para indicar que otros documentos
						// public String _tipDocRelacionado; // este se llena
						// con 99 para indicar que otros documentos
						// public String _numDocRelacionado; // aqui va el valor
						// que varia o el dato que queremos mostrar en el xml
						// public String _tipDocEmisor; // vacio
						// public String _numDocEmisor; // vacio
						// public String _mtoDocRelacionado; //0.00

						if (_num == 1) {
							mydocumentos_relacionados[r]
									.set_indDocRelacionado(_cadena);
						}
						if (_num == 2) {
							mydocumentos_relacionados[r]
									.set_tipDocRelacionado(_cadena);
						}
						if (_num == 3) {
							mydocumentos_relacionados[r]
									.set_numDocRelacionado(_cadena);
						}
						if (_num == 4) {
							mydocumentos_relacionados[r]
									.set_tipDocEmisor(_cadena);
						}
						if (_num == 5) {
							mydocumentos_relacionados[r]
									.set_numDocEmisor(_cadena);
						}
						if (_num == 6) {
							mydocumentos_relacionados[r]
									.set_mtoDocRelacionado(_cadena);
						}

						_cadena = "";
					}
				}

				// System.out.println("Documento Relacionado-> Ind:"+mydocumentos_relacionados[r].get_indDocRelacionado());
				// System.out.println("Documento Relacionado-> Tipo:"+mydocumentos_relacionados[r].get_tipDocRelacionado());
				// System.out.println("Documento Relacionado-> Num:"+mydocumentos_relacionados[r].get_numDocRelacionado());

			}

			// leemos el arcivo plano ley

			readPlainTextLey(dh_ley);

			_counterLey = _counterLey - 1;

			for (int l = 1; l < _counterLey; l++) {
				myLey[l] = new ley();
				_cadena = "";
				_num = 0;

				_tam_detalle = myArrayLey[l].length();
				for (int x = 0; x < _tam_detalle; x++) {
					_car = myArrayLey[l].substring(x, x + 1);

					if (!"|".equals(_car)) {
						_cadena = _cadena + _car;
					} else {
						_num++;

						if (_num == 1) {
							myLey[l].set_codLeyenda(_cadena);
						}
						if (_num == 2) {
							myLey[l].set_desLeyenda(_cadena);
						}

						_cadena = "";
					}
				}

				// System.out.println("Codigo de Leyenda->:"+myLey[l].get_codLeyenda());
				// System.out.println("Descripcion Leyenda->:"+myLey[l].get_desLeyenda());

				if (myLey[l].get_codLeyenda().equals("1012")) {
					$CORREO = myLey[l].get_desLeyenda();

				}

				if (myLey[l].get_codLeyenda().equals("1013")) {
					$HORA = myLey[l].get_desLeyenda();

				}

				if (myLey[l].get_codLeyenda().equals("1015")) {
					$ORDEN_DE_COMPRA = myLey[l].get_desLeyenda();

				}

				if (myLey[l].get_codLeyenda().equals("1016")) {
					$FORMA_DE_PAGO = myLey[l].get_desLeyenda();

				}

				if (myLey[l].get_codLeyenda().equals("1017")) {
					$LUGAR_EXPEDICION = myLey[l].get_desLeyenda();

				}

				if (myLey[l].get_codLeyenda().equals("1018")) {
					$METODO_DE_PAGO = myLey[l].get_desLeyenda();

				}

				if (myLey[l].get_codLeyenda().equals("1019")) {
					$CONDICIONES_DE_PAGO = myLey[l].get_desLeyenda();

				}

				if (myLey[l].get_codLeyenda().equals("1020")) {
					$TIPO_DE_CAMBIO = myLey[l].get_desLeyenda();

				}

				if (myLey[l].get_codLeyenda().equals("1021")) {
					$TIPO_DE_COMPROBANTE = myLey[l].get_desLeyenda();

				}

				if (myLey[l].get_codLeyenda().equals("1114")) {
					$UUID_NC = myLey[l].get_desLeyenda();

				}

			}

			$PATH_HASH = misParametros.get_ruta_hash();

			$FILE_PATH_NAME_XML = $PATH_CON_FIRMA + $FILE_NAME + ".xml";
			$FILE_PATH_NAME_HASH = $PATH_HASH + $FILE_NAME + ".hash";
			$FILE_PATH_NAME_QR = $PATH_QR + $FILE_NAME + ".png";
			$FILE_PATH_NAME_417 = $PATH_417 + $FILE_NAME + ".png";

		}

		$SUM_IGV = myCabecera.get_sum_igv();

		writeXML($FILE_NAME_XML);

		/*
		 * System.out.println("...."); for (int _xx=0;_xx<30; _xx++) {
		 * Thread.sleep(1000); System.out.println(_xx);
		 * 
		 * }
		 */

		// Prepara_Documento.firmar($PATH_SIN_FIRMA,$PATH_CON_FIRMA,$FILE_NAME,misParametros,$PATH_CADENA_ORIGINAL,
		// myCabecera.get_num_ident() );
		Prepara_Documento.firmar($PATH_SIN_FIRMA, $PATH_CON_FIRMA, $FILE_NAME,
				misParametros, $PATH_CADENA_ORIGINAL,
				myCabecera.get_num_ident(), myAca.get_tipo_documento());

		myParam[0] = "";

		readParam("path.fg");
		String _path = myParam[0];

		int _tam_path = myParam[0].length();

		if (_tam_path > 0) {
			_path = myParam[0];
		} else {
			_path = ".";
		}

		misParametros.set_ruta_path(_path);

		myParam[0] = "";

		// String _llave_encontrada="La Llave no fue Encontrada...!!!";
		boolean permiso = false;

		for (int _x = 0; _x < 300; _x++) {
			myParam[_x] = "";
		}

		// String
		// _ruta_param=_path+_win+"data"+_win+$RUC+_win+"certificados"+_lin+$RUC+"-llaves.txt";
		// readParam(_ruta_param);

		// for (int _x=0;_x<300;_x++) {

		// if (myParam[_x].equals(_llave)) {
		// _llave_encontrada=myParam[_x];

		permiso = true;

		// break;

		// }
		// }

		if (permiso) {
			// System.out.println("La Llave SI fue Encontrada...!!! :");
		} else {

			// System.out.println("La Llave NO fue Encontrada...!!! :");

			// System.out.println("Comuniquese con Factura Global SAC    www.factura.global");
			// email.main("alejandro.gandara33@gmail.com", $FILE_PATH_NAME_XML);
			// TimeUnit.SECONDS.sleep(10);
		}

		if (permiso) {

			// CreateSignature.main($KEYSTORE, $PASSWORD_KEYSTORE,
			// $PASSWORD_CERTIFICADO, $ALIAS_CERTIFICADO, $PATH_SIN_FIRMA,
			// $PATH_CON_FIRMA, $FILE_NAME );

			// public static void main(String _keystore_file,
			// String _keystore_password,
			// String _private_key_password,
			// String _private_key_alias,
			// String _xml_origen,
			// String _xml_destino) throws Exception {

			// Firma_Documento.firmar($PATH_SIN_FIRMA,$PATH_CON_FIRMA,$FILE_NAME,misParametros);

			// String _contenido_qr =
			// $RUC+"|"+$TIPO_DOCUMENTO+"|"+$SERIE+"|"+$NUMERO+"|"+$SUM_IGV+"|"+
			// myCabecera.get_importe_tot()+"|"+myCabecera.get_fecha()+"|"+
			// myCabecera.get_ident()+"|"+myCabecera.get_num_ident();

			// codigoQR.get($FILE_PATH_NAME_XML,$FILE_PATH_NAME_QR,_contenido_qr);
			// codigo417.get($FILE_PATH_NAME_XML,$FILE_PATH_NAME_417,_contenido_qr);
			// codigoHash.get($FILE_PATH_NAME_XML,$FILE_PATH_NAME_HASH);

			if (_firma_conecta.equals("C")) {
				// System.out.println("CONEXION A SUNAT...");
				// H_main.conectar($FILE_NAME,misParametros);
			}

		}

	}

	public static void separaCab() {
		// Str1.length()

	}

	public static void readPlainTextCab(DataHandler dh) throws Exception {
		Object content = dh.getContent();

		BufferedReader br = null;

		try {

			String sCurrentLine;
			br = new BufferedReader(
					new InputStreamReader((InputStream) content));

			while ((sCurrentLine = br.readLine()) != null) {
				myArrayCab[0] = sCurrentLine;

			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null)
					br.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}

	}

	public static void readPlainTextAca(DataHandler dh) throws Exception {
		Object content = dh.getContent();

		BufferedReader br = null;

		try {

			String sCurrentLine;
			br = new BufferedReader(
					new InputStreamReader((InputStream) content));

			while ((sCurrentLine = br.readLine()) != null) {
				myArrayCab_Aca[0] = sCurrentLine;

			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null)
					br.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}

	}

	public static void readPlainTextDet(DataHandler dh) throws Exception {
		Object content = dh.getContent();

		BufferedReader br = null;

		try {

			String sCurrentLine;
			br = new BufferedReader(
					new InputStreamReader((InputStream) content));

			while ((sCurrentLine = br.readLine()) != null) {

				myArrayDet[_counterDet] = sCurrentLine;
				_counterDet = _counterDet + 1;

			}
			_counterDet = _counterDet + 1;

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null)
					br.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}

	}

	public static void readPlainTextRel(DataHandler dh) throws Exception {
		Object content = dh.getContent();

		BufferedReader br = null;

		_counterRel = 1;

		try {

			String sCurrentLine;
			br = new BufferedReader(
					new InputStreamReader((InputStream) content));

			while ((sCurrentLine = br.readLine()) != null) {

				myArrayRel[_counterRel] = sCurrentLine;
				_counterRel = _counterRel + 1;

			}
			_counterRel = _counterRel + 1;

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null)
					br.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}

	}

	public static void readPlainTextLey(DataHandler dh) throws Exception {
		Object content = dh.getContent();

		BufferedReader br = null;

		_counterLey = 1;

		try {

			String sCurrentLine;
			br = new BufferedReader(
					new InputStreamReader((InputStream) content));

			while ((sCurrentLine = br.readLine()) != null) {

				myArrayLey[_counterLey] = sCurrentLine;
				_counterLey = _counterLey + 1;

			}
			_counterLey = _counterLey + 1;

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null)
					br.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}

	}

	public static double round(double value, int places) {
		if (places < 0)
			throw new IllegalArgumentException();

		long factor = (long) Math.pow(10, places);
		value = value * factor;
		long tmp = Math.round(value);
		return (double) tmp / factor;
	}

	public static void writeXML(String $FILE_NAME) throws Exception {

		DocumentBuilderFactory documentBuilderFctory = DocumentBuilderFactory
				.newInstance();
		DocumentBuilder documentBuilder = documentBuilderFctory
				.newDocumentBuilder();

		Document document = documentBuilder.newDocument();

		// StringWriter sw = new StringWriter();
		// StreamResult result = new StreamResult(sw);
		// doc.setXmlStandalone(true);
		// DOMSource source = new DOMSource(doc);
		// trans.transform(source, result);
		// String xmlString = sw.toString();

		// agregamos la nombre pricipal dentro de este iran todos los elementos
		Element element = document.createElement("cfdi:Comprobante");
		document.appendChild(element);

		// definicion de variables

		// System.out.println("");
		// System.out.println("RESUMEN DE DOCUMENTO");
		// System.out.println("");

		double _base_gravable = myCabecera.get_tot_vta_gra();
		double _base_exonerada = myCabecera.get_tot_vta_exo();
		double _base_inafecta = myCabecera.get_tot_vta_in();

		double _base = _base_gravable + _base_exonerada + _base_inafecta;

		// System.out.println("Base Gravable___________"+Formato.neto(_base_gravable));
		// System.out.println("Base Exoneradas_________"+Formato.neto(_base_exonerada));
		// System.out.println("Base Inafecta___________"+Formato.neto(_base_inafecta));
		// System.out.println("BASE TOTAL______________"+Formato.neto(_base));
		// System.out.println("");
		double _igv = myCabecera.get_sum_igv();
		double _isc = myCabecera.get_sum_isc();
		double _iva_ret = myCabecera.get_iva_ret();
		double _impuestos = _igv + _isc;

		System.out.println("IGV_____________________"+Formato.neto(_igv));
		System.out.println("ISC_____________________"+Formato.neto(_isc));
		System.out.println("IVA RET_________________"+Formato.neto(_iva_ret));
		// System.out.println("TOTAL IMPUESTOS_________"+Formato.neto(_impuestos));
		// System.out.println("Porcentaje de IGV_______        "+$PORCENTAJE_IGV+"%");

		// System.out.println("");

		double _descuento_global = myCabecera.get_tot_desc();
		double _descuento_detalle = myCabecera.get_desc_detalle();
		double _descuentos = _descuento_global + _descuento_detalle;

		// System.out.println("Descuentos Globales_____"+Formato.neto(_descuento_global));
		// System.out.println("Descuento en Detalle____"+Formato.neto(_descuento_detalle));
		// System.out.println("TOTAL DESCUENTOS________"+Formato.neto(_descuentos));
		// System.out.println("");
		double _gratuito = myAca.get_mtoOperGratuitas();

		// System.out.println("Trans. Gratuitas________"+Formato.neto(_gratuito));
		// System.out.println("");
		double _anticipos = 0;

		// System.out.println("Anticipos_______________"+Formato.neto(_anticipos));
		// System.out.println("");

		double _otros_cargos = 0;

		// System.out.println("Otros Cargos____________"+Formato.neto(_otros_cargos));

		// System.out.println("");
		// System.out.println("");
		double _neto = 0;

		if (myAca.get_tipo_documento().equals("LOC")) {
			_neto = _base - _descuentos - _anticipos + _impuestos-_iva_ret;
		} else {
			_neto = _base - _descuentos - _anticipos;
			// +_impuestos;
		}

		System.out.println("--NETO____________________"+Formato.dinero(_neto));

		// System.out.println("");
		// System.out.println("Lineas en Detalle_______"+Formato.neto(_counterDet));

		// System.out.println("");

		// xmlns="urn:oasis:names:specification:ubl:schema:xsd:Invoice-2"
		Attr attr_xmlns = document.createAttribute("xmlns:xsi");
		attr_xmlns.setValue("http://www.w3.org/2001/XMLSchema-instance");
		element.setAttributeNode(attr_xmlns);

		// xmlns:cfdi="urn:oasis:names:specification:ubl:schema:xsd:Invoice-2"
		Attr attr_cfdi = document.createAttribute("xmlns:cfdi");
		attr_cfdi.setValue("http://www.sat.gob.mx/cfd/3");
		element.setAttributeNode(attr_cfdi);

		// xsi:schemaLocation
		Attr attr_schemaLocation = document
				.createAttribute("xsi:schemaLocation");
		attr_schemaLocation
				.setValue("http://www.sat.gob.mx/cfd/3 http://www.sat.gob.mx/sitio_internet/cfd/3/cfdv33.xsd");
		element.setAttributeNode(attr_schemaLocation);

		// xmlns:cac="urn:oasis:names:specification:ubl:schema:xsd:CommonAggregateComponents-2"
		// Attr attr_schemaLocation =
		// document.createAttribute("xsi:schemaLocation");
		// attr_schemaLocation.setValue("http://www.sat.gob.mx/cfd/3 http://www.sat.gob.mx/sitio_internet/cfd/3/cfdv33.xsd");
		// element.setAttributeNode(attr_schemaLocation);

		if (myAca.get_tipo_documento().equals("EXP")) {
			// xmlns:cce11="http://www.sat.gob.mx/ComercioExterior11"
			Attr attr_cce11 = document.createAttribute("xmlns:cce11");
			attr_cce11.setValue("http://www.sat.gob.mx/ComercioExterior11");
			element.setAttributeNode(attr_cce11);

		}

		// Version="3.3"
		Attr attr_Version = document.createAttribute("Version");
		attr_Version.setValue("3.3");
		element.setAttributeNode(attr_Version);

		// Serie="A"
		// Attr attr_Serie = document.createAttribute("Serie");
		// attr_Serie.setValue($SERIE);
		// element.setAttributeNode(attr_Serie);

		// Folio="2435"
		Attr attr_Folio = document.createAttribute("Folio");
		attr_Folio.setValue($SERIE + $NUMERO);
		element.setAttributeNode(attr_Folio);

		// Fecha="2018-02-09T10:36:36"
		Attr attr_Fecha = document.createAttribute("Fecha");
		attr_Fecha.setValue(myCabecera.get_fecha() + "T" + $HORA);
		element.setAttributeNode(attr_Fecha);

		// Sello
		Attr attr_Sello = document.createAttribute("Sello");
		attr_Sello.setValue("");
		element.setAttributeNode(attr_Sello);

		// Certificado
		Attr attr_Certificado = document.createAttribute("Certificado");
		attr_Certificado.setValue("");
		element.setAttributeNode(attr_Certificado);

		// FormaPago
		Attr attr_FormaPago = document.createAttribute("FormaPago");
		attr_FormaPago.setValue($FORMA_DE_PAGO);
		element.setAttributeNode(attr_FormaPago);

		// NoCertificado
		Attr attr_NoCertificado = document.createAttribute("NoCertificado");
		if (myCabecera.get_num_ident().equals("TES030201001")) {
			$NUMERO_CERTIFICADO = "20001000000300022824";
		}
		attr_NoCertificado.setValue($NUMERO_CERTIFICADO);
		element.setAttributeNode(attr_NoCertificado);

		// Certificado
		// Attr attr_Certificado = document.createAttribute("Certificado");
		// attr_Certificado.setValue("");
		// element.setAttributeNode(attr_Certificado);

		// CondicionesDePago
		Attr attr_CondicionesDePago = document
				.createAttribute("CondicionesDePago");
		attr_CondicionesDePago.setValue($CONDICIONES_DE_PAGO);
		element.setAttributeNode(attr_CondicionesDePago);

		// SubTotal
		Attr attr_SubTotal = document.createAttribute("SubTotal");
		attr_SubTotal.setValue("" + Formato._xml(_base));
		element.setAttributeNode(attr_SubTotal);

		// Descuentos
		Attr attr_Descuentos = document.createAttribute("Descuento");
		attr_Descuentos.setValue("0.00");
		element.setAttributeNode(attr_Descuentos);

		// Moneda
		Attr attr_Moneda = document.createAttribute("Moneda");
		attr_Moneda.setValue(myCabecera.get_moneda());
		element.setAttributeNode(attr_Moneda);

		// TipoCambio
		Attr attr_TipoCambio = document.createAttribute("TipoCambio");
		attr_TipoCambio.setValue($TIPO_DE_CAMBIO);
		element.setAttributeNode(attr_TipoCambio);

		// Total
		Attr attr_Total = document.createAttribute("Total");
		attr_Total.setValue("" + Formato._xml(_neto));
		element.setAttributeNode(attr_Total);

		// TipoDeComprobante
		Attr attr_TipoDeComprobante = document
				.createAttribute("TipoDeComprobante");
		attr_TipoDeComprobante.setValue($TIPO_DE_COMPROBANTE);
		element.setAttributeNode(attr_TipoDeComprobante);

		// MetodoPago
		Attr attr_MetodoPago = document.createAttribute("MetodoPago");
		attr_MetodoPago.setValue($METODO_DE_PAGO);
		element.setAttributeNode(attr_MetodoPago);

		// LugarExpedicion
		Attr attr_LugarExpedicion = document.createAttribute("LugarExpedicion");
		attr_LugarExpedicion.setValue($LUGAR_EXPEDICION);
		element.setAttributeNode(attr_LugarExpedicion);

		// xmlns:cfdi
		// Attr attr_xmlns_cfdi = document.createAttribute("xmlns:cfdi");
		// attr_xmlns_cfdi.setValue("http://www.sat.gob.mx/cfd/3");
		// element.setAttributeNode(attr_xmlns_cfdi);

		if ($UUID_NC.trim().length() > 0) {

			// $UUID_NC
			// cfdi:CfdiRelacionados
			Element CfdiRelacionados = document
					.createElement("cfdi:CfdiRelacionados");
			element.appendChild(CfdiRelacionados);

			// TipoRelacion
			Attr attr_TipoRelacion = document.createAttribute("TipoRelacion");
			attr_TipoRelacion.setValue("01");
			CfdiRelacionados.setAttributeNode(attr_TipoRelacion);

			// cfdi:CfdiRelacionado
			Element CfdiRelacionado = document
					.createElement("cfdi:CfdiRelacionado");
			CfdiRelacionados.appendChild(CfdiRelacionado);
			// UUID
			Attr attr_UUID = document.createAttribute("UUID");
			attr_UUID.setValue($UUID_NC);
			CfdiRelacionado.setAttributeNode(attr_UUID);

		}

		// cfdi:Emisor

		Element cfdi_Emisor = document.createElement("cfdi:Emisor");
		element.appendChild(cfdi_Emisor);

		// Rfc="RAM0603016C6"
		Attr attr_Rfc = document.createAttribute("Rfc");
		// temporal para pruebas
		// attr_Rfc.setValue("TEST010203001");
		if (myCabecera.get_num_ident().equals("TES030201001")) {
			$RUC = "TES030201001";
		}
		attr_Rfc.setValue($RUC);
		cfdi_Emisor.setAttributeNode(attr_Rfc);

		// Nombre="REFRACTARIOS ALFRAN MEXICO SA DE CV"
		Attr attr_Nombre = document.createAttribute("Nombre");
		attr_Nombre.setValue($RAZON_SOCIAL);
		cfdi_Emisor.setAttributeNode(attr_Nombre);

		// RegimenFiscal="601"
		Attr attr_RegimenFiscal = document.createAttribute("RegimenFiscal");
		attr_RegimenFiscal.setValue("601");
		cfdi_Emisor.setAttributeNode(attr_RegimenFiscal);

		// cfdi:Receptor

		Element cfdi_Receptor = document.createElement("cfdi:Receptor");
		element.appendChild(cfdi_Receptor);

		// Rfc="RAM0603016C6"
		Attr attr_Rfc_Receptor = document.createAttribute("Rfc");
		// TEST010203001
		// attr_Rfc_Receptor.setValue("TEST010203001");
		if ((myAca.get_tipo_documento().equals("EXP"))
				|| (myAca.get_NumRegIdTrib().trim().length() > 0)) {
			System.out.println("------->" + myAca.get_tipo_documento());
			attr_Rfc_Receptor.setValue("XEXX010101000");
			cfdi_Receptor.setAttributeNode(attr_Rfc_Receptor);
		} else {

			attr_Rfc_Receptor.setValue(myCabecera.get_num_ident());
			cfdi_Receptor.setAttributeNode(attr_Rfc_Receptor);
		}

		if (myAca.get_tipo_documento().equals("EXP")) {
			// ResidenciaFiscal="PAN"
			Attr attr_ResidenciaFiscal = document
					.createAttribute("ResidenciaFiscal");
			attr_ResidenciaFiscal.setValue(myAca.get_pais());
			cfdi_Receptor.setAttributeNode(attr_ResidenciaFiscal);

			// NumRegIdTrib="430850133290460"

			Attr attr_NumRegIdTrib = document.createAttribute("NumRegIdTrib");
			attr_NumRegIdTrib.setValue(myAca.get_NumRegIdTrib());
			cfdi_Receptor.setAttributeNode(attr_NumRegIdTrib);
		}

		// NumRegIdTrib
		if (myAca.get_NumRegIdTrib().trim().length() > 0) {
			// ResidenciaFiscal="PAN"
			Attr attr_ResidenciaFiscal = document
					.createAttribute("ResidenciaFiscal");
			attr_ResidenciaFiscal.setValue(myAca.get_pais());
			cfdi_Receptor.setAttributeNode(attr_ResidenciaFiscal);

			// NumRegIdTrib="430850133290460"

			Attr attr_NumRegIdTrib = document.createAttribute("NumRegIdTrib");
			attr_NumRegIdTrib.setValue(myAca.get_NumRegIdTrib());
			cfdi_Receptor.setAttributeNode(attr_NumRegIdTrib);
		}

		// Nombre="REFRACTARIOS ALFRAN MEXICO SA DE CV"
		Attr attr_Nombre_Receptor = document.createAttribute("Nombre");
		attr_Nombre_Receptor.setValue(myCabecera.get_nombre());
		cfdi_Receptor.setAttributeNode(attr_Nombre_Receptor);

		// UsoCFDI="G02"
		Attr attr_UsoCFDI = document.createAttribute("UsoCFDI");
		attr_UsoCFDI.setValue(myCabecera.get_uso_cfdi());
		cfdi_Receptor.setAttributeNode(attr_UsoCFDI);

		Element cfdi_Conceptos = document.createElement("cfdi:Conceptos");
		element.appendChild(cfdi_Conceptos);

		for (int linea = 1; linea < _counterDet; linea++) {
			// cfdi:Conceptos

			// cfdi:Concepto
			Element cfdi_Concepto = document.createElement("cfdi:Concepto");
			cfdi_Conceptos.appendChild(cfdi_Concepto);

			// ClaveUnidad="TNE"
			Attr attr_ClaveUnidad = document.createAttribute("ClaveUnidad");
			attr_ClaveUnidad.setValue(myDetalle[linea].get_unidad_med());
			cfdi_Concepto.setAttributeNode(attr_ClaveUnidad);

			// Unidad="ACT"
			// Attr attr_Unidad = document.createAttribute("Unidad");
			// attr_Unidad.setValue("");
			// cfdi_Concepto.setAttributeNode(attr_Unidad);

			// ClaveProdServ="30111503"
			Attr attr_ClaveProdServ = document.createAttribute("ClaveProdServ");
			// 84111506
			// attr_ClaveProdServ.setValue("84111506");
			attr_ClaveProdServ.setValue(myDetalle[linea].get_extra());
			cfdi_Concepto.setAttributeNode(attr_ClaveProdServ);

			// NoIdentificacion
			Attr attr_NoIdentificacion = document
					.createAttribute("NoIdentificacion");
			// 1234567890
			// attr_NoIdentificacion.setValue("1234567890");
			attr_NoIdentificacion.setValue(myDetalle[linea].get_producto());
			cfdi_Concepto.setAttributeNode(attr_NoIdentificacion);

			// Cantidad="3"
			Attr attr_Cantidad = document.createAttribute("Cantidad");
			// if (myDetalle[linea].get_cantidad_expo()>0) {
			// attr_Cantidad.setValue(""+myDetalle[linea].get_cantidad_expo());
			// } else {
			attr_Cantidad.setValue("" + myDetalle[linea].get_cantidad());
			// }

			cfdi_Concepto.setAttributeNode(attr_Cantidad);

			// Descripcion="DRYTECH 70 ALFRAN"
			Attr attr_Descripcion = document.createAttribute("Descripcion");
			attr_Descripcion.setValue(myDetalle[linea].get_descrip());
			cfdi_Concepto.setAttributeNode(attr_Descripcion);

			// ValorUnitario="1360"
			Attr attr_ValorUnitario = document.createAttribute("ValorUnitario");
			// if (myDetalle[linea].get_valor_unit_expo()>0) {
			// attr_ValorUnitario.setValue(""+myDetalle[linea].get_valor_unit_expo());
			// } else {
			attr_ValorUnitario.setValue("" + myDetalle[linea].get_valor_unit());
			// }

			cfdi_Concepto.setAttributeNode(attr_ValorUnitario);

			// Importe="4080"
			Attr attr_Importe = document.createAttribute("Importe");
			attr_Importe
					.setValue(""
							+ Formato._xml((myDetalle[linea].get_valor_unit() * myDetalle[linea]
									.get_cantidad())));
			cfdi_Concepto.setAttributeNode(attr_Importe);

			// Descuento
			Attr attr_Descuento = document.createAttribute("Descuento");
			attr_Descuento.setValue("" + myDetalle[linea].get_desc_unit());
			cfdi_Concepto.setAttributeNode(attr_Descuento);

			if (myAca.get_tipo_documento().equals("LOC")) {

				if (myDetalle[linea].get_igv_unit() > 0) {

					// cfdi:Impuestos
					Element cfdi_Impuestos = document
							.createElement("cfdi:Impuestos");
					cfdi_Concepto.appendChild(cfdi_Impuestos);


					// cfdi:Traslados
					Element cfdi_Traslados = document
							.createElement("cfdi:Traslados");
					cfdi_Impuestos.appendChild(cfdi_Traslados);

					// cfdi:Traslado
					Element cfdi_Traslado = document
							.createElement("cfdi:Traslado");
					cfdi_Traslados.appendChild(cfdi_Traslado);

					// Base="4080"
					Attr attr_Base = document.createAttribute("Base");
					attr_Base
							.setValue(""
									+ Formato._xml((myDetalle[linea]
											.get_valor_unit() * myDetalle[linea]
											.get_cantidad())));
					cfdi_Traslado.setAttributeNode(attr_Base);

					// Impuesto="002"
					Attr attr_Impuesto = document.createAttribute("Impuesto");
					attr_Impuesto.setValue("002");
					cfdi_Traslado.setAttributeNode(attr_Impuesto);

					// TipoFactor="Tasa"
					Attr attr_TipoFactor = document
							.createAttribute("TipoFactor");
					attr_TipoFactor.setValue("Tasa");
					cfdi_Traslado.setAttributeNode(attr_TipoFactor);

					// TasaOCuota="0.160000"
					Attr attr_TasaOCuota = document
							.createAttribute("TasaOCuota");
					attr_TasaOCuota.setValue("0.160000");
					cfdi_Traslado.setAttributeNode(attr_TasaOCuota);

					// Importe="652.800000"
					Attr attr_Importe_Traslado = document
							.createAttribute("Importe");
					attr_Importe_Traslado.setValue(""
							+ Formato._xml(myDetalle[linea].get_valor_unit()
									* myDetalle[linea].get_cantidad() * .16));

					cfdi_Traslado.setAttributeNode(attr_Importe_Traslado);

					if (myDetalle[linea].get_iva_ret()>0) {
						// cfdi:Retenciones
						Element cfdi_Retenciones = document.createElement("cfdi:Retenciones");
						cfdi_Impuestos.appendChild(cfdi_Retenciones);
						 
						// cfdi:Retencion
						Element cfdi_Retencion = document.createElement("cfdi:Retencion");
						cfdi_Retenciones.appendChild(cfdi_Retencion);
						
						// Base="4080"
						Attr attr_Base_Ret = document.createAttribute("Base");
						attr_Base_Ret.setValue(""+Formato._xml((myDetalle[linea].get_valor_unit()*myDetalle[linea].get_cantidad())));
						cfdi_Retencion.setAttributeNode(attr_Base_Ret);
						
						// Impuesto="002"
						Attr attr_Impuesto_Ret = document.createAttribute("Impuesto");
						attr_Impuesto_Ret.setValue("002");
						cfdi_Retencion.setAttributeNode(attr_Impuesto_Ret);
						 
						// TipoFactor="Tasa"
						Attr attr_TipoFactor_Ret =	document.createAttribute("TipoFactor");
						attr_TipoFactor_Ret.setValue("Tasa");
						cfdi_Retencion.setAttributeNode(attr_TipoFactor_Ret);
						
						// TasaOCuota="0.160000"
						Attr attr_TasaOCuota_Ret = document.createAttribute("TasaOCuota");
						attr_TasaOCuota_Ret.setValue("0.060000");
						cfdi_Retencion.setAttributeNode(attr_TasaOCuota_Ret);
						
						// Importe="652.800000"
						Attr attr_Importe_Traslado_Ret = document.createAttribute("Importe");
						attr_Importe_Traslado_Ret.setValue(""+(myDetalle[linea].get_valor_unit()*myDetalle[linea].get_cantidad()*.06));
						cfdi_Retencion.setAttributeNode(attr_Importe_Traslado_Ret);

						
						
						
						
						
					}
					






				}
				
				

				
			}

		}

		if (myAca.get_tipo_documento().equals("LOC") && _igv > 0) {

			// cfdi:Impuestos
			Element cfdi_Impuestos_Cabecera = document
					.createElement("cfdi:Impuestos");
			element.appendChild(cfdi_Impuestos_Cabecera);

			// TotalImpuestosTrasladados="1632.13"
			Attr attr_TotalImpuestosTrasladados = document
					.createAttribute("TotalImpuestosTrasladados");
			attr_TotalImpuestosTrasladados.setValue("" + Formato._xml(_igv));
			cfdi_Impuestos_Cabecera
					.setAttributeNode(attr_TotalImpuestosTrasladados);

			// TotalImpuestosRetenidos
			
			if (_iva_ret>0) {
				Attr attr_TotalImpuestosRetenidos = document.createAttribute("TotalImpuestosRetenidos");
				attr_TotalImpuestosRetenidos.setValue(""+_iva_ret);
				cfdi_Impuestos_Cabecera.setAttributeNode(attr_TotalImpuestosRetenidos);
				

				// cfdi:Retenciones
				Element cfdi_Retenciones_Cabecera =	document.createElement("cfdi:Retenciones");
				cfdi_Impuestos_Cabecera.appendChild(cfdi_Retenciones_Cabecera);


				// cfdi:Retencion
				Element cfdi_Retencion_Cabecera = document.createElement("cfdi:Retencion");
				cfdi_Retenciones_Cabecera.appendChild(cfdi_Retencion_Cabecera);

				
				// Impuesto="002"
				Attr attr_Impuesto_Cabecera_Ret = document.createAttribute("Impuesto");
				attr_Impuesto_Cabecera_Ret.setValue("002");
				cfdi_Retencion_Cabecera.setAttributeNode(attr_Impuesto_Cabecera_Ret);
				
				
				// Importe="1632.13"
				Attr attr_Importe_Cabecera_Ret = document.createAttribute("Importe");
				attr_Importe_Cabecera_Ret.setValue(""+_iva_ret);
				cfdi_Retencion_Cabecera.setAttributeNode(attr_Importe_Cabecera_Ret);

				
				
				
				
				
			}





				
				
			// cfdi:Traslados
			Element cfdi_Traslados_Cabecera = document
					.createElement("cfdi:Traslados");
			cfdi_Impuestos_Cabecera.appendChild(cfdi_Traslados_Cabecera);

			// cfdi:Traslado
			Element cfdi_Traslado_Cabecera = document
					.createElement("cfdi:Traslado");
			cfdi_Traslados_Cabecera.appendChild(cfdi_Traslado_Cabecera);

			// Impuesto="002"
			Attr attr_Impuesto_Cabecera = document.createAttribute("Impuesto");
			attr_Impuesto_Cabecera.setValue("002");
			cfdi_Traslado_Cabecera.setAttributeNode(attr_Impuesto_Cabecera);

			// TipoFactor="Tasa"
			Attr attr_TipoFactor_Cabecera = document
					.createAttribute("TipoFactor");
			attr_TipoFactor_Cabecera.setValue("Tasa");
			cfdi_Traslado_Cabecera.setAttributeNode(attr_TipoFactor_Cabecera);

			// TasaOCuota="0.160000"
			Attr attr_TasaOCuota_Cabecera = document
					.createAttribute("TasaOCuota");
			attr_TasaOCuota_Cabecera.setValue("0.160000");
			cfdi_Traslado_Cabecera.setAttributeNode(attr_TasaOCuota_Cabecera);

			// Importe="1632.13"
			Attr attr_Importe_Cabecera = document.createAttribute("Importe");
			attr_Importe_Cabecera.setValue("" + Formato._xml(_igv));
			cfdi_Traslado_Cabecera.setAttributeNode(attr_Importe_Cabecera);

		}

		// cfdi:Complemento
		Element cfdi_Complemento = document.createElement("cfdi:Complemento");
		element.appendChild(cfdi_Complemento);

		if (myAca.get_tipo_documento().equals("EXP")) {

			// cfdi:Complemento

			// Element cfdi_Complemento =
			// document.createElement("cfdi:Complemento");
			// element.appendChild(cfdi_Complemento);

			// cce11:ComercioExterior
			Element cfdi_ComercioExterior = document
					.createElement("cce11:ComercioExterior");
			cfdi_Complemento.appendChild(cfdi_ComercioExterior);

			// Version=1.1
			Attr attr_Version11 = document.createAttribute("Version");
			attr_Version11.setValue("1.1");
			cfdi_ComercioExterior.setAttributeNode(attr_Version11);

			// TipoOperacion="2"
			Attr attr_TipoOperacion = document.createAttribute("TipoOperacion");
			attr_TipoOperacion.setValue(myAca.get_tipo_operacion());
			cfdi_ComercioExterior.setAttributeNode(attr_TipoOperacion);

			// ClaveDePedimento="A1"
			Attr attr_ClaveDePedimento = document
					.createAttribute("ClaveDePedimento");
			attr_ClaveDePedimento.setValue(myAca.get_clavedepedimento());
			cfdi_ComercioExterior.setAttributeNode(attr_ClaveDePedimento);

			// CertificadoOrigen="1"
			Attr attr_CertificadoOrigen = document
					.createAttribute("CertificadoOrigen");
			attr_CertificadoOrigen.setValue(myAca.get_certificadoorigen());
			cfdi_ComercioExterior.setAttributeNode(attr_CertificadoOrigen);

			// Incoterm="EXW"
			Attr attr_Incoterm = document.createAttribute("Incoterm");
			attr_Incoterm.setValue(myAca.get_incoterm());
			cfdi_ComercioExterior.setAttributeNode(attr_Incoterm);

			// Subdivision="0"
			Attr attr_Subdivision = document.createAttribute("Subdivision");
			attr_Subdivision.setValue(myAca.get_subdivision());
			cfdi_ComercioExterior.setAttributeNode(attr_Subdivision);

			// TipoCambioUSD="18.9701"
			Attr attr_TipoCambioUSD = document.createAttribute("TipoCambioUSD");
			attr_TipoCambioUSD.setValue($TIPO_DE_CAMBIO);
			cfdi_ComercioExterior.setAttributeNode(attr_TipoCambioUSD);

			// TotalUSD="38400.00"
			Attr attr_TotalUSD = document.createAttribute("TotalUSD");
			attr_TotalUSD.setValue("" + Formato._xml(_neto));
			cfdi_ComercioExterior.setAttributeNode(attr_TotalUSD);

			// cce11:Emisor
			Element cfdi_Emisor11 = document.createElement("cce11:Emisor");
			cfdi_ComercioExterior.appendChild(cfdi_Emisor11);

			// cce11:Domicilio
			Element cfdi_Domicilio11 = document
					.createElement("cce11:Domicilio");
			cfdi_Emisor11.appendChild(cfdi_Domicilio11);

			// Calle="CALLE HERNAN SANTOS CANTU"
			Attr attr_Calle_emisor = document.createAttribute("Calle");
			attr_Calle_emisor.setValue("CALLE HERNAN SANTOS CANTU");
			cfdi_Domicilio11.setAttributeNode(attr_Calle_emisor);

			// NumeroExterior="228"
			Attr attr_NumeroExterior_emisor = document
					.createAttribute("NumeroExterior");
			attr_NumeroExterior_emisor.setValue("228");
			cfdi_Domicilio11.setAttributeNode(attr_NumeroExterior_emisor);

			// Localidad="10"
			Attr attr_localidad_emisor = document.createAttribute("Localidad");
			attr_localidad_emisor.setValue("10");
			cfdi_Domicilio11.setAttributeNode(attr_localidad_emisor);

			// Municipio="048"
			Attr attr_Municipio_emisor = document.createAttribute("Municipio");
			attr_Municipio_emisor.setValue("048");
			cfdi_Domicilio11.setAttributeNode(attr_Municipio_emisor);

			// Estado="NLE"
			Attr attr_Estado_emisor = document.createAttribute("Estado");
			attr_Estado_emisor.setValue("NLE");
			cfdi_Domicilio11.setAttributeNode(attr_Estado_emisor);

			// Pais="MEX"
			Attr attr_Pais_emisor = document.createAttribute("Pais");
			attr_Pais_emisor.setValue("MEX");
			cfdi_Domicilio11.setAttributeNode(attr_Pais_emisor);

			// CodigoPostal="66350"
			Attr attr_CodigoPostal_emisor = document
					.createAttribute("CodigoPostal");
			attr_CodigoPostal_emisor.setValue("66350");
			cfdi_Domicilio11.setAttributeNode(attr_CodigoPostal_emisor);

			// /

			// cce11:Receptor
			Element cfdi_Receptor11 = document.createElement("cce11:Receptor");
			cfdi_ComercioExterior.appendChild(cfdi_Receptor11);

			// cce11:Domicilio
			Element cfdi_Domicilio11_receptor = document
					.createElement("cce11:Domicilio");
			cfdi_Receptor11.appendChild(cfdi_Domicilio11_receptor);

			// Calle="CALLE HERNAN SANTOS CANTU"
			Attr attr_Calle_receptor = document.createAttribute("Calle");
			attr_Calle_receptor.setValue(myAca.get_calle());
			cfdi_Domicilio11_receptor.setAttributeNode(attr_Calle_receptor);

			// Colonia="228"
			Attr attr_colonia_receptor = document.createAttribute("Colonia");
			attr_colonia_receptor.setValue(myAca.get_colonia());
			cfdi_Domicilio11_receptor.setAttributeNode(attr_colonia_receptor);

			// Estado
			Attr attr_estado_receptor = document.createAttribute("Estado");
			attr_estado_receptor.setValue(myAca.get_estado());
			cfdi_Domicilio11_receptor.setAttributeNode(attr_estado_receptor);

			// Pais
			Attr attr_Pais_receptor = document.createAttribute("Pais");
			attr_Pais_receptor.setValue(myAca.get_pais());
			cfdi_Domicilio11_receptor.setAttributeNode(attr_Pais_receptor);

			// CodigoPostal
			Attr attr_CodigoPostal_receptor = document
					.createAttribute("CodigoPostal");
			attr_CodigoPostal_receptor.setValue(myAca.get_codigo_postal());
			cfdi_Domicilio11_receptor
					.setAttributeNode(attr_CodigoPostal_receptor);

			// cce11:Mercancias

			Element cfdi_Mercancias = document
					.createElement("cce11:Mercancias");
			cfdi_ComercioExterior.appendChild(cfdi_Mercancias);

			for (int linea = 1; linea < _counterDet; linea++) {
				// cfdi:Conceptos

				// cfdi:Concepto
				Element cfdi_Mercancia = document
						.createElement("cce11:Mercancia");
				cfdi_Mercancias.appendChild(cfdi_Mercancia);

				// NoIdentificacion
				Attr attr_NoIdentificacion_mer = document
						.createAttribute("NoIdentificacion");
				// 1234567890
				// attr_NoIdentificacion.setValue("1234567890");
				attr_NoIdentificacion_mer.setValue(myDetalle[linea]
						.get_producto());
				cfdi_Mercancia.setAttributeNode(attr_NoIdentificacion_mer);

				// FraccionArancelaria="38160002"
				Attr attr_FraccionArancelaria = document
						.createAttribute("FraccionArancelaria");
				attr_FraccionArancelaria.setValue(myDetalle[linea]
						.get_fraccion());
				cfdi_Mercancia.setAttributeNode(attr_FraccionArancelaria);

				// CantidadAduana="19200"
				Attr attr_CantidadAduana = document
						.createAttribute("CantidadAduana");
				if (myDetalle[linea].get_cantidad_expo() > 0) {
					attr_CantidadAduana.setValue(""
							+ myDetalle[linea].get_cantidad_expo());
				} else {
					attr_CantidadAduana.setValue(""
							+ myDetalle[linea].get_cantidad());
				}

				cfdi_Mercancia.setAttributeNode(attr_CantidadAduana);

				// UnidadAduana="01"
				Attr attr_Unidad_mer = document.createAttribute("UnidadAduana");
				attr_Unidad_mer.setValue("01");
				cfdi_Mercancia.setAttributeNode(attr_Unidad_mer);

				// ValorUnitarioAduana="2"
				Attr attr_ValorUnitarioAduana = document
						.createAttribute("ValorUnitarioAduana");
				if (myDetalle[linea].get_valor_unit_expo() > 0) {
					attr_ValorUnitarioAduana.setValue(""
							+ Formato._xml(myDetalle[linea]
									.get_valor_unit_expo()));
				} else {
					attr_ValorUnitarioAduana.setValue(""
							+ Formato._xml(myDetalle[linea].get_valor_unit()));
				}

				cfdi_Mercancia.setAttributeNode(attr_ValorUnitarioAduana);

				// ValorDolares="38400.00"
				Attr attr_ValorDolares = document
						.createAttribute("ValorDolares");
				attr_ValorDolares.setValue(""
						+ Formato._xml(myDetalle[linea].get_valor_unit()
								* myDetalle[linea].get_cantidad()));
				cfdi_Mercancia.setAttributeNode(attr_ValorDolares);

			}

		}

		// ///////////////////////////////////////////////////////////////////////////

		// / NODO NUMERO 2 FIRMA DIGITAL --
		// /Invoice/ext:UBLExtensions/ext:UBLExtension/ext:ExtensionContent/ds:Signature
		// /Invoice/cac:Signature

		TransformerFactory tf = TransformerFactory.newInstance();
		Transformer transformer = tf.newTransformer();

		// ////////////////////

		// ////////////////////
		// transformer.setOutputProperty(OutputKeys.ENCODING, "ISO-8859-1");
		// transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION,
		// "yes");

		// TransformerFactory transformerFactory =
		// TransformerFactory.newInstance();
		// Transformer transformer = transformerFactory.newTransformer();
		// StringWriter sw = new StringWriter();

		// StreamResult result = new StreamResult(sw);
		// doc.setXmlStandalone(true);
		// DOMSource source = new DOMSource(doc);
		// trans.transform(source, result);
		// String xmlString = sw.toString();

		document.setXmlStandalone(true);
		DOMSource source = new DOMSource(document);
		StreamResult streamResult = new StreamResult(new File($FILE_NAME_XML));
		transformer.transform(source, streamResult);

		// _letra_numero

	}

	public static void readParam(String _file_parametros) throws Exception {

		InputStream is_param = new FileInputStream(_file_parametros);
		DataSource ds_param = new ByteArrayDataSource(is_param,
				"application/octet-stream");
		DataHandler dhandler_param = new DataHandler(ds_param);

		Object content = dhandler_param.getContent();

		BufferedReader br = null;
		int _cont = 0;

		try {

			String sCurrentLine;
			br = new BufferedReader(
					new InputStreamReader((InputStream) content));

			while ((sCurrentLine = br.readLine()) != null) {

				myParam[_cont] = sCurrentLine;
				_cont++;

			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null)
					br.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}

	}

}