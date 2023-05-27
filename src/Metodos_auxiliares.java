
import java.util.HashSet;
import java.util.Set;

/**
 * Clase que contiene métodos auxiliares para ayudar a los algoritmos.
 *
 */
public class Metodos_auxiliares {

    /**
     * Método que indica si hay repetidos o no.
     *
     * @param checkString String que queremos comprobar.
     * @return True si hay repetidos y False si no.
     */
    public static boolean Contiene_repetidos(String checkString) {
        // result flag
        boolean foundDuplicate = false;
        // get string length
        int stringLength = checkString.length();
        // create a set for all found characters (max size is number
        // of characters in the string to check
        Set<Character> characters = new HashSet<>(stringLength);
        // loop all characters in string
        for (int i = 0; i < stringLength; i++) {
            // construct a object (may be use internal JDK cache)
            Character c = Character.valueOf(checkString.charAt(i));
            // check if character is already found
            if (characters.contains(c)) {
                // yes, set result to TRUE
                foundDuplicate = true;
                // break the loop
                break;
            } else {
                // not found, add char to set
                characters.add(c);
            }
        }
        return foundDuplicate;
    }

    /**
     * Método que trunca texto.
     *
     * @param texto Texto a truncar.
     * @param longitud Longitud a truncar.
     * @return Texto truncado.
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
     * Método que comprueba si la clave Playfair es válida o no.
     *
     * @param clave Clave Playfair.
     * @return True si es válida y False si no.
     */
    public static boolean Comprobar_clave_playfair(String clave) {
        char[] alfabeto_base = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'ñ', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '.', ';'};
        char[] array_clave = clave.toCharArray();

        boolean valido_final = true;
        boolean valido = false;
        for (int i = 0; i < array_clave.length; i++) {
            valido = false;
            for (int b = 0; b < alfabeto_base.length && !valido; b++) {
                if (array_clave[i] == alfabeto_base[b]) {
                    valido = true;
                }
            }
            if (!valido) {
                System.out.println("El símbolo: " + array_clave[i] + " no es válido");
                valido_final = false;
            }
        }

        return valido_final;
    }

    /**
     * Método que formatea un texto quitando caracteres especiales y espacios.
     *
     * @param texto Texto a formatear.
     * @return Texto formateado.
     */
    public String formatear(String texto) {
        String texto_formateado = "";
        texto_formateado = texto.replaceAll("\\s", ""); // Eliminamos los espacios

        texto_formateado = texto_formateado.replaceAll("[^a-zA-Z]", "");
        texto_formateado = texto_formateado.toLowerCase();
        return texto_formateado;
    }

    /**
     * Método que formatea un texto quitando caracteres especiales excepto la ñ.
     *
     * @param texto Texto a formatear.
     * @return Texto formateado.
     */
    public String formatear_playfair(String texto) {
        String texto_formateado = "";
        texto_formateado = texto.replaceAll("\\s", ""); // Eliminamos los espacios

        texto_formateado = texto_formateado.replaceAll("[^{ñ}a-z]", "");
        texto_formateado = texto_formateado.toLowerCase();
        return texto_formateado;
    }

    /**
     * Método que formatea un texto quitando caracteres especiales excepto ñ y
     * vocales acentuadas.
     *
     * @param texto Texto a formatear.
     * @return Texto formateado.
     */
    public String formatear_2(String texto) {
        String texto_formateado = "";

        texto_formateado = texto.toLowerCase(); // Pasamos el texto a minúscula
        texto_formateado = texto_formateado.replaceAll("[^[ñ][áéíóú]a-zA-Z\\s]", ""); // Eliminamos todos los caracteres que no sean ñ, letras minúsculas, letras mayúsculas y letras con tilde.

        // Sustituimos las vocales acentuadas con sus respectivas no acentuadas.
        //texto_formateado = texto_formateado.replace("ñ", "n");
        texto_formateado = texto_formateado.replace("á", "a");
        texto_formateado = texto_formateado.replace("é", "e");
        texto_formateado = texto_formateado.replace("í", "i");
        texto_formateado = texto_formateado.replace("ó", "o");
        texto_formateado = texto_formateado.replace("ú", "u");

        return texto_formateado;
    }

    /**
     * Método que rota un array derecha o izquierda.
     *
     * @param data Array a rotar.
     * @param n Número de rotaciones.
     * @return Array rotado.
     */
    public char[] rotateArray(char[] data, int n) {
        if (n < 0) // rotating left?
        {
            n = -n % data.length; // convert to +ve number specifying how 
            // many positions left to rotate & mod
            n = data.length - n;  // rotate left by n = rotate right by length - n
        }
        char[] result = new char[data.length];
        for (int i = 0; i < data.length; i++) {
            result[(i + n) % data.length] = data[i];
        }
        return result;
    }

    /**
     * Método que comprueba si un string es un valor numérico o no.
     *
     * @param strNum String a pasar.
     * @return True si es numérico y False si no.
     */
    public boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {

            int d = Integer.parseInt(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

}
