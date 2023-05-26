
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.nio.charset.StandardCharsets;

/**
 *
 * Clase que se encarga del tratado del fichero de configuración y realiza todas
 * las acciones que este dicta.
 *
 *
 */
public class Config {

    private String fichero_configuracion;
    private boolean salidapantalla;
    private boolean salidafichero;
    private boolean codifica;
    private boolean traza;
    private String ficheroentrada;
    private String ficherosalida;
    private String clave;
    private String clave_larga;
    private String formateaentrada;

    /**
     * Constructor por defecto de la clase.
     */
    public Config() {
        fichero_configuracion = "";
        salidapantalla = true;
        salidafichero = true;
        codifica = true;
        traza = true;
        ficheroentrada = "";
        ficherosalida = "salida.txt";
        clave = "merida";
        clave_larga = "";
        formateaentrada = "";
    }

    /**
     * Constructor parametrizado que da valores por defecto excepto al
     * fichero_configuracion.
     *
     * @param fichero_configuracion1 Parámetro que contiene el nombre del
     * fichero de configuración.
     *
     */
    public Config(String fichero_configuracion1) {
        fichero_configuracion = fichero_configuracion1;
        salidapantalla = true;
        salidafichero = true;
        codifica = true;
        traza = true;
        ficheroentrada = "";
        ficherosalida = "salida.txt";
        clave = "merida";
        clave_larga = "";
        formateaentrada = "";
    }

    /**
     * Constructor parametrizado que indica el valor de todos los atributos de
     * la clase por parámetros.
     *
     * @param fichero_configuracion Indica el nombre del fichero de
     * configuración.
     * @param salidapantalla Indica con ON u OFF si debe existir salida de datos
     * por pantalla.
     * @param salidafichero Indica con ON u OFF si la salida debe escribirse en
     * el fichero de salida.
     * @param codifica Indica con ON u OFF si el método Autoclave debe realizar
     * una codificación (ON) o debe realizar una decodificación (OFF).
     * @param traza Indica con ON u OFF si la traza del programa debe mostrarse
     * por pantalla o no.
     * @param ficheroentrada Indica el nombre del fichero de entrada que se
     * usará para realizar las operaciones oportunas con Autoclave.
     * @param ficherosalida Indica el nombre del fichero de salida donde se
     * guardará la salida del programa.
     * @param clave Indica la rista de caracteres de la 'a' a la 'z' (sin ñ) que
     * se usará como clave primaria.
     * @param clave_larga Indica la clave resultante del truncamiento del texto
     * en claro formateado más la clave (todo con la longitud del string del
     * texto anteriormente mencionado).
     * @param formateaentrada Indica que debe formatearse el texto que indique y
     * volcar la salida en el fichero indicado por ficherosalida.
     */
    public Config(String fichero_configuracion, boolean salidapantalla, boolean salidafichero, boolean codifica, boolean traza, String ficheroentrada, String ficherosalida, String clave, String clave_larga, String formateaentrada) {
        this.fichero_configuracion = fichero_configuracion;
        this.salidapantalla = salidapantalla;
        this.salidafichero = salidafichero;
        this.codifica = codifica;
        this.traza = traza;
        this.ficheroentrada = ficheroentrada;
        this.ficherosalida = ficherosalida;
        this.clave = clave;
        this.clave_larga = clave_larga;
        this.formateaentrada = formateaentrada;
    }

    /**
     * Método que lee el fichero de configuración que indique
     * fichero_configuración.
     *
     * Realiza los siguientes pasos:
     *
     * - Lee la línea. En caso de estar en blanco o comenzar por el carácter #,
     * la descarta y pasa a la siguiente. En caso de no estar en esos casos, la
     * trata.
     *
     * - Si la línea comienza por @, es una bandera. Lee lo que existe después.
     * Si contiene las palabras 'salidapantalla', 'salidafichero', 'codifica' o
     * 'traza' seguido de 'ON' u 'OFF', se trata. De lo contrario, se descarta.
     *
     * - Si la línea comienza por aspersan, es un comando. Lee lo que existe después.
     * Si es 'autoclave', se ejecuta el método con el mismo nombre. Si es
     * 'ficheroentrada', 'ficherosalida', 'clave' o 'formateaentrada', seguido
     * de un fichero TXT, se trata. De lo contrario, se descarta.
     *
     */
    public void leerFichero() {
        BufferedReader objeto_lector_fichero_config = null;

        // Tratamos de leer el fichero de configuración indicado por fichero_configuracion. Si no salta la excepción.
        try {
            String strCurrentLine;

            objeto_lector_fichero_config = new BufferedReader(new InputStreamReader(new FileInputStream(fichero_configuracion), StandardCharsets.UTF_8));

            while ((strCurrentLine = objeto_lector_fichero_config.readLine()) != null) {
                if (strCurrentLine.isEmpty() || (strCurrentLine.charAt(0) == '#')) {
                    // No hacemos nada puesto que estamos ante una línea inútil.
                } else {

                    StringTokenizer Tok = new StringTokenizer(strCurrentLine, " ");
                    while (Tok.hasMoreTokens()) {
                        String s = Tok.nextToken();
                        if (Tok.hasMoreTokens()) {
                            switch (s) {
                                // En caso de parámetro:
                                case "&":
                                    // tipoDato contiene lo que hay después de &
                                    String tipoComando = Tok.nextToken();
                                    tipoComando = formatear(tipoComando);
                                    switch (tipoComando) {
                                        // En caso de que tipoComando sea igual a ficheroentrada
                                        case "ficheroentrada":
                                            if (Tok.hasMoreTokens()) {
                                                String fichero = Tok.nextToken();

                                                BufferedReader objeto_lector_2 = null;

                                                // Tratamos de leer el fichero indicado por fichero. Si no salta la excepción, ficheroentrada se iguala a fichero.
                                                try {
                                                    objeto_lector_2 = new BufferedReader(new InputStreamReader(new FileInputStream(fichero), StandardCharsets.UTF_8));
                                                    ficheroentrada = fichero;

                                                    // El fichero que fichero indica no existe.
                                                } catch (IOException e) {
                                                    if (traza == true) {
                                                        System.out.println("-------- ERROR --------");
                                                        System.out.println("No existe el fichero de entrada: " + fichero);
                                                        System.out.println("-----------------------");
                                                    }
                                                } finally {

                                                    try {
                                                        if (objeto_lector_2 != null) {
                                                            objeto_lector_2.close();
                                                        }
                                                    } catch (IOException ex) {
                                                        ex.printStackTrace();
                                                    }
                                                }

                                            }
                                            break;

                                        // En caso de que tipoComando sea igual a ficherosalida    
                                        case "ficherosalida":
                                            if (Tok.hasMoreTokens()) {
                                                String fichero = Tok.nextToken();
                                                ficherosalida = fichero;
                                                BufferedReader objeto_lector_2 = null;

                                                // Tratamos de leer el fichero indicado por fichero para saber si existe o no. Si no existe, salta a la excepción.
                                                try {
                                                    objeto_lector_2 = new BufferedReader(new InputStreamReader(new FileInputStream(fichero), StandardCharsets.UTF_8));

                                                    if (traza == true) {
                                                        System.out.println("-------- ERROR --------");
                                                        System.out.println("Existe el fichero de salida: " + fichero + " sera reescrito");
                                                        System.out.println("-----------------------");
                                                    }

                                                    // El fichero que fichero indica no existe. Será creado.    
                                                } catch (IOException e) {
                                                    if (traza == true) {
                                                        System.out.println("-------- ERROR --------");
                                                        System.out.println("No existe el fichero de salida: " + fichero + " sera creado");
                                                        System.out.println("-----------------------");
                                                    }
                                                    File archivo = new File(fichero);
                                                    BufferedWriter bw;

                                                    bw = new BufferedWriter(new FileWriter(archivo));
                                                    bw.write("");

                                                    bw.close();

                                                } finally {

                                                    try {
                                                        if (objeto_lector_2 != null) {
                                                            objeto_lector_2.close();
                                                        }
                                                    } catch (IOException ex) {
                                                        ex.printStackTrace();
                                                    }
                                                }

                                            }
                                            break;

                                        // En caso de que tipoComando sea igual a clave   
                                        case "clave":
                                            if (Tok.hasMoreTokens()) {
                                                String clave_config = Tok.nextToken();
                                                if (traza == true) {
                                                    System.out.println("Clave que viene: " + clave_config);
                                                }
                                                // Formateamos la clave.
                                                clave_config = formatear(clave_config);

                                                // Si la clave contiene dos caracteres o más, se acepta. Si no, se descarta.
                                                if (clave_config.length() >= 2) {
                                                    if (traza == true) {
                                                        System.out.println("Cargamos la nueva clave: " + clave_config);
                                                    }
                                                    clave = clave_config;
                                                } else {
                                                    if (traza == true) {
                                                        System.out.println("No cambiamos la clave, pues es demasiado corta: " + clave_config);
                                                    }
                                                }
                                            }
                                            break;

                                        // En caso de que tipoComando sea igual a formateaentrada   
                                        case "formateaentrada":
                                            if (Tok.hasMoreTokens()) {
                                                String fichero = Tok.nextToken();

                                                BufferedReader objecto_lector_2 = null;

                                                // Se comprueba si existe el fichero de entrada a formatear.
                                                // Si existe, se formatea y se guarda la salida (si salidafichero es true) en el fichero de salida indicado por ficherosalida.
                                                // Si no existe, salta la excepción.
                                                try {
                                                    objecto_lector_2 = new BufferedReader(new InputStreamReader(new FileInputStream(fichero), StandardCharsets.UTF_8));

                                                    String linea = "";
                                                    String linea_siguiente = "";

                                                    // Se leen todas las líneas que pueda tener el fichero de entrada.
                                                    while ((linea_siguiente = objecto_lector_2.readLine()) != null) {
                                                        linea = linea + linea_siguiente;
                                                    }

                                                    // Si el fichero no está en blanco.
                                                    if (linea != null) {
                                                        // Se cogen solo los primeros 1000 caracteres del fichero.
                                                        linea = Truncar_Texto(linea, 1000);

                                                        File archivo = new File(ficherosalida);
                                                        BufferedWriter bw;

                                                        bw = new BufferedWriter(new FileWriter(archivo));
                                                        if (salidapantalla == true) {
                                                            System.out.println("Fichero fuente antes de formatear: " + linea + "\n"
                                                                    + "Vamos a formatear el fichero de entrada con los caracteres validos\n"
                                                                    + "Tamaño del fichero:" + linea.length() + " caracteres\n"
                                                                    + "Fichero formateado:" + formatear(linea));
                                                        }

                                                        // Se escribe el texto formateado en el fichero de salida.
                                                        bw.write(formatear(linea));
                                                        bw.close();

                                                        // Si el fichero está en blanco.
                                                    } else {

                                                        File archivo = new File(ficherosalida);
                                                        BufferedWriter bw;

                                                        bw = new BufferedWriter(new FileWriter(archivo));
                                                        if (salidapantalla == true) {
                                                            System.out.println("Fichero fuente antes de formatear: " + "\n"
                                                                    + "Vamos a formatear el fichero de entrada con los caracteres validos\n"
                                                                    + "Tamaño del fichero:" + " caracteres\n"
                                                                    + "Fichero formateado:");
                                                        }
                                                        bw.close();
                                                    }

                                                    // Salta la excepción si no existe el fichero de entrada a formatear.
                                                } catch (IOException e) {
                                                    if (traza == true) {
                                                        System.out.println("-------- ERROR --------");
                                                        System.out.println("No Existe el fichero de entrada a formatear: " + fichero);
                                                        System.out.println("-----------------------");
                                                    }
                                                } finally {

                                                    try {
                                                        if (objecto_lector_2 != null) {
                                                            objecto_lector_2.close();
                                                        }
                                                    } catch (IOException ex) {
                                                        ex.printStackTrace();
                                                    }
                                                }

                                            }

                                            break;

                                        // En caso de que tipoComando sea igual a autoclave
                                        case "autoclave":

                                            // Si se trata de codificar el texto que existe en fichero de entrada.
                                            if (codifica == true) {
                                                BufferedReader objeto_lector_2 = null;

                                                // Tratamos de leer el fichero indicado por ficheroentrada. Si no salta la excepción.
                                                try {
                                                    objeto_lector_2 = new BufferedReader(new InputStreamReader(new FileInputStream(ficheroentrada), StandardCharsets.UTF_8));

                                                    String linea = "";
                                                    String linea_siguiente = "";
                                                    // Se leen todas las líneas que pueda tener el fichero de entrada.
                                                    while ((linea_siguiente = objeto_lector_2.readLine()) != null) {
                                                        linea = linea + linea_siguiente;
                                                    }

                                                    // Si el fichero no está en blanco.
                                                    if (linea != null) {
                                                        // Se cogen solo los primeros 1000 caracteres del fichero.
                                                        linea = Truncar_Texto(linea, 1000);

                                                        AutoclaveMetodo a = new AutoclaveMetodo();

                                                        if (salidapantalla == true) {
                                                            System.out.println("Vamos a cifrar con AutoClave");
                                                            System.out.println("Fichero a traducir:" + linea);
                                                            System.out.println("Texto cifrado es: " + linea);
                                                        }

                                                        linea = linea.replaceAll("[^a-z]", "");

                                                        linea = formatear(linea);
                                                        String combinacion_sin_truncar = (clave + linea);
                                                        String texto_truncado = a.Truncar_Texto(combinacion_sin_truncar, linea.length());
                                                        clave_larga = texto_truncado;
                                                        String texto_cifrado = a.cifrar_mensaje(linea, clave);
                                                        if (salidapantalla == true) {
                                                            System.out.println("Clave:          " + texto_truncado);
                                                            System.out.println("Texto  cifrado: " + texto_cifrado);
                                                        }

                                                        if (salidafichero == true) {
                                                            File archivo = new File(ficherosalida);
                                                            BufferedWriter bw;

                                                            bw = new BufferedWriter(new FileWriter(archivo));

                                                            bw.write(texto_cifrado);
                                                            bw.close();
                                                        }

                                                        // Si el fichero está en blanco.
                                                    } else {
                                                        if (salidapantalla == true) {

                                                            System.out.println("Vamos a cifrar con AutoClave");
                                                            System.out.println("Fichero a traducir:");
                                                            System.out.println("Texto cifrado es: ");
                                                        }

                                                        if (salidapantalla == true) {
                                                            System.out.println("Clave:          " + clave);
                                                            System.out.println("Texto  cifrado: ");
                                                        }

                                                        if (salidafichero == true) {
                                                            File archivo = new File(ficherosalida);
                                                            BufferedWriter bw;

                                                            bw = new BufferedWriter(new FileWriter(archivo));

                                                            bw.write("");
                                                            bw.close();
                                                        }

                                                    }

                                                    // Salta la excepción si no existe el fichero de entrada.
                                                } catch (IOException e) {
                                                    if (traza == true) {
                                                        System.out.println("-------- ERROR --------");
                                                        System.out.println("No existe el fichero de entrada a tratar: " + ficheroentrada);
                                                        System.out.println("-----------------------");
                                                    }
                                                } finally {

                                                    try {
                                                        if (objeto_lector_2 != null) {
                                                            objeto_lector_2.close();
                                                        }
                                                    } catch (IOException ex) {
                                                        ex.printStackTrace();
                                                    }
                                                }

                                                // Si se trata de decodificar el texto que existe en fichero de entrada.    
                                            } else {
                                                BufferedReader objeto_lector_2 = null;

                                                // Tratamos de leer el fichero indicado por ficheroentrada. Si no salta la excepción.
                                                try {
                                                    objeto_lector_2 = new BufferedReader(new InputStreamReader(new FileInputStream(ficheroentrada), StandardCharsets.UTF_8));

                                                    String linea = "";
                                                    String linea_siguiente = "";
                                                    // Se leen todas las líneas que pueda tener el fichero de entrada.
                                                    while ((linea_siguiente = objeto_lector_2.readLine()) != null) {
                                                        linea = linea + linea_siguiente;
                                                    }

                                                    // Si el fichero no está en blanco.
                                                    if (linea != null) {
                                                        AutoclaveMetodo a = new AutoclaveMetodo();

                                                        String combinacion_sin_truncar = (clave + linea);
                                                        String texto_truncado = clave_larga;

                                                        String texto_descifrado = a.descifrar_mensaje(linea, clave_larga);

                                                        if (salidapantalla == true) {

                                                            System.out.println("Vamos a descifrar con AutoClave");
                                                            System.out.println("Fichero a traducir:" + linea);
                                                            System.out.println("Texto en claro: " + linea);
                                                            System.out.println("Clave:          " + texto_truncado);
                                                            System.out.println("Texto  descifrado: " + texto_descifrado);
                                                        }
                                                        File archivo = new File(ficherosalida);
                                                        BufferedWriter bw;

                                                        bw = new BufferedWriter(new FileWriter(archivo));

                                                        bw.write(texto_descifrado);
                                                        bw.close();

                                                        // Si el fichero está en blanco.
                                                    } else {

                                                        String combinacion_sin_truncar = (clave + linea);
                                                        String texto_truncado = clave_larga;

                                                        if (salidapantalla == true) {

                                                            System.out.println("Vamos a descifrar con AutoClave");
                                                            System.out.println("Fichero a traducir:");
                                                            System.out.println("Texto en claro: ");
                                                            System.out.println("Clave:          " + clave);
                                                            System.out.println("Texto  descifrado: ");
                                                        }
                                                        File archivo = new File(ficherosalida);
                                                        BufferedWriter bw;

                                                        bw = new BufferedWriter(new FileWriter(archivo));

                                                        bw.write("");
                                                        bw.close();
                                                    }

                                                    // Salta la excepción si no existe el fichero de entrada.
                                                } catch (IOException e) {
                                                    if (traza == true) {
                                                        System.out.println("-------- ERROR --------");
                                                        System.out.println("No existe el fichero de entrada a formatear: " + ficheroentrada);
                                                        System.out.println("-----------------------");
                                                    }

                                                } finally {

                                                    try {
                                                        if (objeto_lector_2 != null) {
                                                            objeto_lector_2.close();
                                                        }
                                                    } catch (IOException ex) {
                                                        ex.printStackTrace();
                                                    }
                                                }
                                            }

                                            break;

                                        default:

                                            break;
                                    }

                                    break;

                                // En caso de bandera:
                                case "@":
                                    String tipoBandera = Tok.nextToken();
                                    tipoBandera = formatear(tipoBandera);
                                    switch (tipoBandera) {

                                        // En caso de que tipoBandera sea igual a salidapantalla
                                        case "salidapantalla":

                                            if (Tok.hasMoreTokens()) {
                                                String confirmacion = Tok.nextToken();
                                                confirmacion = formatear(confirmacion);
                                                if (confirmacion.equals("on")) {
                                                    salidapantalla = true;
                                                } else {
                                                    if (confirmacion.equals("off")) {
                                                        salidapantalla = false;
                                                    }
                                                }
                                            }

                                            break;

                                        // En caso de que tipoBandera sea igual a salidafichero
                                        case "salidafichero":
                                            if (Tok.hasMoreTokens()) {
                                                String confirmacion = Tok.nextToken();
                                                confirmacion = formatear(confirmacion);
                                                if (confirmacion.equals("on")) {
                                                    salidafichero = true;
                                                } else {
                                                    if (confirmacion.equals("off")) {
                                                        salidafichero = false;
                                                    }
                                                }
                                            }
                                            break;

                                        // En caso de que tipoBandera sea igual a codifica
                                        case "codifica":
                                            if (Tok.hasMoreTokens()) {
                                                String confirmacion = Tok.nextToken();
                                                confirmacion = formatear(confirmacion);
                                                if (confirmacion.equals("on")) {
                                                    codifica = true;
                                                } else {
                                                    if (confirmacion.equals("off")) {
                                                        codifica = false;
                                                    }
                                                }
                                            }
                                            break;

                                        // En caso de que tipoBandera sea igual a traza
                                        case "traza":
                                            if (Tok.hasMoreTokens()) {
                                                String confirmacion = Tok.nextToken();
                                                confirmacion = formatear(confirmacion);
                                                if (confirmacion.equals("on")) {
                                                    traza = true;
                                                } else {
                                                    if (confirmacion.equals("off")) {
                                                        traza = false;
                                                    }
                                                }
                                            }
                                            break;

                                    }
                                    break;

                            }
                        }

                    }

                }
            }

            // El fichero de configuración que indica fichero_configuracion no existe.   
        } catch (IOException e) {
            System.out.println("-------- ERROR --------");
            System.out.println("No existe el fichero de " + fichero_configuracion);
            System.out.println("-----------------------");
            //e.printStackTrace();

        } finally {

            try {
                if (objeto_lector_fichero_config != null) {
                    objeto_lector_fichero_config.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

    }

    /**
     * Método que formatea la entrada dada por parámetros eliminando los
     * espacios y los caracteres que no estén de la 'a' a la 'z' sin contar 'ñ'
     * y poniendo el resultado en minúscula.
     *
     * @param texto Parámetro que indica el que se introduce para su formateo.
     * @return Retorna el texto introducido por parámetro pero formateado.
     */
    public String formatear(String texto) {
        String texto_formateado = "";
        texto_formateado = texto.replaceAll("\\s", ""); // Eliminamos los espacios

        texto_formateado = texto_formateado.replaceAll("[^a-zA-Z]", "");
        texto_formateado = texto_formateado.toLowerCase();
        return texto_formateado;
    }

    /**
     * Método que trunca un String dado por parámatros tanto como indique la
     * longitud introducida por parámetros.
     *
     * @param texto Parámetro que indica el texto que va a truncarse.
     * @param longitud Parámetro que indica la longitud a la que el texto
     * introducido por parámetros será truncado.
     * @return Retorna el texto introducido por parámetros pero truncado hasta
     * la longitud indicada por parámetros.
     */
    public String Truncar_Texto(String texto, int longitud) {
        // Ensure String length is longer than requested size.
        if (texto.length() > longitud) {
            return texto.substring(0, longitud);
        } else {
            return texto;
        }
    }

    /**
     * Método que indica si salidapantalla es true o false.
     *
     * @return Retorna true o false.
     */
    public boolean isSalidapantalla() {
        return salidapantalla;
    }

    /**
     * Método que asigna un estado booleano a salidapantalla.
     *
     * @param salidapantalla Indica con ON u OFF si debe existir salida de datos
     * por pantalla.
     */
    public void setSalidapantalla(boolean salidapantalla) {
        this.salidapantalla = salidapantalla;
    }

    /**
     * Método que indica si salidafichero es true o false.
     *
     * @return Retorna true o false.
     */
    public boolean isSalidafichero() {
        return salidafichero;
    }

    /**
     * Método que asigna un estado booleano a salidafichero.
     *
     * @param salidafichero Indica con ON u OFF si la salida debe escribirse en
     * el fichero de salida.
     */
    public void setSalidafichero(boolean salidafichero) {
        this.salidafichero = salidafichero;
    }

    /**
     * Método que indica si codifica es true o false.
     *
     * @return Retorna true o false.
     */
    public boolean isCodifica() {
        return codifica;
    }

    /**
     * Método que asigna un estado booleano a codifica.
     *
     * @param codifica Indica con ON u OFF si el método Autoclave debe realizar
     * una codificación (ON) o debe realizar una decodificación (OFF).
     */
    public void setCodifica(boolean codifica) {
        this.codifica = codifica;
    }

    /**
     * Método que indica si traza es true o false.
     *
     * @return Retorna true o false.
     */
    public boolean isTraza() {
        return traza;
    }

    /**
     * Método que asigna un estado booleano a traza.
     *
     * @param traza Indica con ON u OFF si la traza del programa debe mostrarse
     * por pantalla o no.
     */
    public void setTraza(boolean traza) {
        this.traza = traza;
    }

    /**
     * Método que obtiene el nombre del fichero de entrada.
     *
     * @return Retorna el nombre del fichero de entrada.
     */
    public String getFicheroentrada() {
        return ficheroentrada;
    }

    /**
     * Método que asigna un nombre de fichero a ficheroentrada.
     *
     * @param ficheroentrada Indica el nombre del fichero de entrada que se
     * usará para realizar las operaciones oportunas con Autoclave.
     */
    public void setFicheroentrada(String ficheroentrada) {
        this.ficheroentrada = ficheroentrada;
    }

    /**
     * Método que asigna un nombre de fichero a ficherosalida.
     *
     * @return Retorna el nombre del fichero de salida.
     */
    public String getFicherosalida() {
        return ficherosalida;
    }

    /**
     * Método que asigna un nombre de fichero a ficherosalida.
     *
     * @param ficherosalida Indica el nombre del fichero de salida donde se
     * guardará la salida del programa.
     */
    public void setFicherosalida(String ficherosalida) {
        this.ficherosalida = ficherosalida;
    }

    /**
     * Método que obtiene la clave.
     *
     * @return Retorna la clave.
     */
    public String getClave() {
        return clave;
    }

    /**
     * Método que asigna la clave.
     *
     * @param clave Indica la rista de caracteres de la 'a' a la 'z' (sin ñ) que
     * se usará como clave primaria.
     */
    public void setClave(String clave) {
        this.clave = clave;
    }

    /**
     * Metodo que obtiene el nombre del fichero que se quiere formatear.
     *
     * @return Retorna el nombre del fichero que se quiere formatear.
     */
    public String getFormateaentrada() {
        return formateaentrada;
    }

    /**
     * Método que asigna un nombre de fichero a formateaentrada.
     *
     * @param formateaentrada Indica que debe formatearse el texto que indique y
     * volcar la salida en el fichero indicado por ficherosalida.
     */
    public void setFormateaentrada(String formateaentrada) {
        this.formateaentrada = formateaentrada;
    }

}
