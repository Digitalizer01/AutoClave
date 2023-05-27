
/**
 * Clase que se encarga de realizar las operaciones de Autoclave.
 *
 */
public class AutoclaveMetodo {

    private String textoClaro;
    private String clave;

    /**
     * Constructor por defecto de la clase.
     */
    public AutoclaveMetodo() {
        textoClaro = "";
        clave = "merida";
    }

    /**
     * Constructor parametrizado de la clase.
     * 
     * @param texto_claro Parámetro que indica el texto claro que se usará para realizar las pertinentes operaciones. 
     * @param clave Parámetro que indica la clave primaria con la que se trabajará.
     */
    public AutoclaveMetodo(String texto_claro, String clave) {
        this.textoClaro = texto_claro;
        this.clave = clave;
    }

    /**
     * Método que cifra un texto claro mediante una clave, ambos dados por parámetros.
     * 
     * @param texto_claro Parámetro que indica el texto que va a cifrarse.
     * @param clave Parámetro que indica la clave que va a usarse para la realización del cifrado.
     * @return Retorna el texto cifrado.
     */
    public String cifrar_mensaje(String texto_claro, String clave) {

        String combinacion_sin_truncar = (clave + texto_claro);
        String texto_truncado = Truncar_Texto(combinacion_sin_truncar, texto_claro.length());

        char[] array_texto_claro = texto_claro.toCharArray();
        char[] array_cifrado = texto_truncado.toCharArray();

        for (int i = 0; i < array_cifrado.length; i++) {

            char c1 = array_texto_claro[i];
            char c2 = array_cifrado[i];

            int numero = ((c1 - 97) + (c2 - 97)) % 26;
            char num = (char) (numero + 97);

            array_cifrado[i] = num;

        }

        return String.valueOf(array_cifrado);
    }

    /**
     * Método que descifra un texto claro mediante una clave, ambos dados por parámetros.
     * 
     * @param texto_cifrado Parámetro que indica el texto que va a descifrarse.
     * @param clave Parámetro que indica la clave que va a usarse para la realización del descifrado.
     * @return Retorna el texto descifrado.
     */
    public String descifrar_mensaje(String texto_cifrado, String clave) {

        char[] array_texto_cifrado = texto_cifrado.toCharArray();
        char[] array_clave = clave.toCharArray();
        char[] array_descifrado = new char[array_texto_cifrado.length];

        for (int i = 0; i < array_descifrado.length; i++) {

            char c1 = array_texto_cifrado[i];
            char c2 = array_clave[i];

            int numero = (((c1 + 97) - (c2 + 97)) % 26) + 97;

            if (numero < 97) {
                numero = numero + 26;
            }

            char num = (char) (numero);

            array_descifrado[i] = num;

        }

        return String.valueOf(array_descifrado);
    }

    /**
     * Método que trunca un String dado por parámatros tanto como indique la longitud introducida por parámetros.
     * 
     * @param texto Parámetro que indica el texto que va a truncarse.
     * @param longitud Parámetro que indica la longitud a la que el texto introducido por parámetros será truncado.
     * @return Retorna el texto introducido por parámetros pero truncado hasta la longitud indicada por parámetros.
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
     * Método que devuelve el texto claro.
     * 
     * @return Retorna el texto en claro.
     */
    public String getTextoClaro() {
        return textoClaro;
    }

    /**
     * Método que asigna el texto claro.
     * 
     * @param textoClaro Parámetro que indica el texto claro que se usará para realizar las pertinentes operaciones. 
     */
    public void setTextoClaro(String textoClaro) {
        this.textoClaro = textoClaro;
    }

    /**
     * Método que devuelve la clave.
     *
     * @return Retorna la clave.
     */
    public String getClave() {
        return clave;
    }

    /**
     * Método que asigna la clave.
     *
     * @param clave Parámetro que indica la clave primaria con la que se trabajará.
     */
    public void setClave(String clave) {
        this.clave = clave;
    }

}
