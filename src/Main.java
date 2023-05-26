
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * Clase que se encarga de comprobar los parámetros de entrada.
 *
 * Si no se ha introducido nada por parámetros, se carga la sintaxis que debe
 * seguir el usuario. Si se ha introducido -h se mostrará el fichero de ayuda.
 * Si se ha introducido -f nombre_fichero_configuracion.txt se cargará el
 * fichero de configuración. Si se ha introducido cualquier otro tipo de
 * parámetro, este devolverá un mensaje indicando la sintaxis que debe seguir el
 * usuario.
 *
 *
 */
public class Main {

    /**
     * @param args Los parámetros que se introducen al programa.
     */
    public static void main(String[] args) {

        // Si no hay nada escrito
        if (args.length == 0) {
            System.out.println("La sintaxis del programa debe ser:\n"
                    + "P1_si2020 [-f fichero] | [-h]\n"
                    + " El argumento asociado a -f es el fichero de configuracion\n"
                    + "El argumento -h indica ayuda  y hará que el programa informe al usuario\n"
                    + "  de cuáles son sus posibilidades respecto al contenido\n"
                    + "  y los parametros.");
        } else {

            // Si -h
            if (args[0].equals("-h") && args.length == 1) {
                /*
                BufferedReader objReader = null;

                // Vemos si el fichero ayuda.txt existe. Si existe, se muestra lo que contiene. Si no, salta la excepción.
                try {
                    String strCurrentLine;

                    objReader = new BufferedReader(new InputStreamReader(new FileInputStream("ayuda.txt"), StandardCharsets.UTF_8));

                    while ((strCurrentLine = objReader.readLine()) != null) {

                        System.out.println(strCurrentLine);
                    }

                    // Salta la excepción por no existir el fichero ayuda.txt
                } catch (IOException e) {
                    System.out.println("-------- ERROR --------");
                    System.out.println("No existe el fichero de ayuda.txt");
                    System.out.println("-----------------------");
                } finally {

                    try {
                        if (objReader != null) {
                            objReader.close();
                        }
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
                 */

                // Muestra la ayuda.
                MostrarAyuda();
            } else {

                // Si -f fichero
                if (args[0].equals("-f") && args.length == 2) {

                    String strCurrentLine; //e.printStackTrace();
                    Config c = new Config(args[1].toString());
                    c.leerFichero();

                    // Si es una sentencia no permitida    
                } else {
                    System.out.println("La sintaxis del programa debe ser:\n"
                            + "P1_si2020 [-f fichero] | [-h]\n"
                            + " El argumento asociado a -f es el fichero de configuracion\n"
                            + "El argumento -h indica ayuda  y hará que el programa informe al usuario\n"
                            + "  de cuáles son sus posibilidades respecto al contenido\n"
                            + "  y los parametros.");

                }
            }
        }

        System.out.println("Fin de la ejecución");

    }

    /**
     * Método que muestra la ayuda del programa tras tener '-h' como argumento.
     */
    private static void MostrarAyuda() {
        System.out.println(
                "Debes introducir por parametros '-f' seguido de un fichero de configuración con el siguiente formato: ");
        System.out.println("El fichero de configuración es un fichero tipo TXT, que tendrá una instrucción, orden o\r\n"
                + "comando por cada una de las líneas del fichero. Las líneas del fichero pueden ser de uno\r\n"
                + "de estos cuatro tipos:\r\n"
                + "• Tipo 1. Línea vacía. Se trata de una línea en blanco, es decir, que no tiene ningún\r\n"
                + "	carácter. Obviamente, este tipo se usará para formatear el fichero de configuración\r\n"
                + "	y facilitar su lectura por parte de los usuarios.\r\n"
                + "• Tipo 2. Comentario. Estas líneas han de comenzar en la primera posición con el\r\n"
                + "	carácter “#” y luego puede haber cualquier cosa hasta el final de la línea que será\r\n"
                + "	interpretado como comentario y no tendrá efecto sobre el programa.\r\n"
                + "• Tipo 3. Banderas. Estas líneas comenzarán con el símbolo “@” y necesariamente\r\n"
                + "	tendrán un espacio en blanco a continuación y luego debe venir uno de los\r\n"
                + "	siguientes comandos:\r\n" + "		o @ salidapantalla ON | OFF\r\n"
                + "		Si está en ON el resultado de la codificación o decodificación\r\n"
                + "		saldrá por pantalla.\r\n"
                + "		Si está en OFF, no se hará eco a pantalla de ningún resultado.\r\n"
                + "		o @ salidafichero ON | OFF\r\n"
                + "		Si está en ON el resultado de la codificación o decodificación se\r\n"
                + "		escribirá en el fichero de salida que esté seleccionado en ese\r\n"
                + "		momento. Si en el fichero de configuración no aparece en ningún\r\n"
                + "		comando, se usará el fichero “salida.txt”.\r\n"
                + "		Si está en OFF, no se escribirá nada en el fichero de salida.\r\n"
                + "		o @ codifica ON | OFF\r\n"
                + "		Si está en ON, indica que los siguientes algoritmos que aparezcan\r\n"
                + "		en el fichero de configuración, hasta que haya una bandera que\r\n"
                + "		indique lo contrario, han de utilizarse en formato codificación y si\r\n"
                + "		está en OFF para decodificar.\r\n" + "		o @ traza ON | OFF\r\n"
                + "		Si está en ON, el programa irá mostrando en pantalla información\r\n"
                + "		relativa a la entrada que se está produciendo desde el fichero de\r\n"
                + "		configuración. Si está en OFF, solo mostrará los textos en claro y\r\n"
                + "		los textos cifrados en pantalla (si procede).\r\n"
                + "	• Tipo 4. Comando. Estas líneas comenzarán con el símbolo “&” y necesariamente\r\n"
                + "	tendrán un espacio en blanco a continuación y luego debe venir uno de los\r\n"
                + "	siguientes comandos:\r\n" + "		o & ficheroentrada <fichero>\r\n"
                + "		<fichero> será el fichero de entada de donde se leerá el texto de\r\n"
                + "		entrada al algoritmo de cifrado. Si dicho fichero no existe se\r\n"
                + "		indicará mediante un mensaje de error, pero el programa seguirá\r\n"
                + "		ejecutándose.\r\n"
                + "		Sólo se tendrán en cuenta los primeros 1000 caracteres en el caso\r\n"
                + "		de que el fichero sea de un tamaño mayor.\r\n"
                + "		Los símbolos de salto de línea y retorno de carro, también se\r\n"
                + "		eliminan, es decir, no son tenidos en cuenta.\r\n" + "		o & ficherosalida <fichero>\r\n"
                + "		<fichero> será el fichero de salida donde se escribirá la salida del\r\n"
                + "		programa si procede. En el caso de que dicho fichero exista se\r\n"
                + "		borrará su contenido y se escribirá con el nuevo contenido. En este\r\n"
                + "		fichero sólo se escribe la salida del cifrado o descifrado.\r\n"
                + "		o & clave <letras>\r\n"
                + "		En <letras> está la ristra de caracteres de la “a” a la “z” (excluida\r\n"
                + "		la “ñ”) que se usará como clave primaria. Por defecto, antes de que\r\n"
                + "		aparezca este comando la clave primaria será “merida”.\r\n"
                + "		Las letras mayúsculas serán reemplazadas por sus\r\n"
                + "		correspondientes letras minúsculas.\r\n"
                + "		La cantidad de <letras> debe ser mayor o igual a 2. Si el número\r\n"
                + "		de letras válidas es inferior la clave no se cambia.\r\n" + "		o & autoclave\r\n"
                + "		Producirá la ejecución del proceso de cifrado o descifrado del\r\n"
                + "		algoritmo “autoclave” (según esté elegido en base a la bandera \r\n"
                + "		Codifica). Tomando los datos de entrada del fichero de entrada, y\r\n"
                + "		poniendo los datos de salida en el fichero oportuno.\r\n"
                + "		o & formateaentrada <fichero>\r\n"
                + "		En este caso el programa tomará el fichero <fichero> como un\r\n"
                + "		TXT y escribirá en el fichero que esté elegido como fichero de\r\n"
                + "		salida, todos los caracteres de la “a” a la “z” (excluida la “ñ”) y\r\n"
                + "		eliminará el resto de símbolos. Las letras mayúsculas serán\r\n"
                + "		reemplazadas por letras minúsculas. Por ejemplo:\r\n"
                + "	• Fichero entrada: España es una Nación completa.\r\n"
                + "	• Fichero salida: espaaesunanacincompleta\r\n"
                + "	Además, si el tamaño del fichero es mayor de 1000 caracteres, sólo\r\n"
                + "	se procesarán los 1000 primeros.\r\n" + "	Condiciones a tener en cuenta en el fichero:\r\n"
                + "	• Los comandos y banderas pueden venir en cualquier orden el fichero. En cada\r\n"
                + "	instante el programa se ejecutará para cada bandera según el último valor\r\n" + "	leído.\r\n"
                + "	• Cada comando válido deberá ser ejecutado inmediatamente después de su\r\n"
                + "	lectura del fichero de configuración.\r\n"
                + "	• La configuración inicial del programa, y previa a la lectura de la primera línea\r\n"
                + "	del fichero de configuración, será:\r\n"
                + "		o Codificar en ON, Salida Pantalla en ON, Salida Fichero ON, Traza en\r\n"
                + "		ON, el fichero de entrada es “entrada.txt”, el fichero de salida es\r\n"
                + "		“salida.txt”,\r\n" + "		o clave es: “merida”\r\n"
                + "	• En el caso de que el fichero de configuración tenga alguna línea de las no\r\n"
                + "	previstas (tipo 1… tipo 4) o bien siendo de las previstas no esté completa o\r\n"
                + "	no sea sintácticamente correcta, dicha línea no se ejecutará y se informará en\r\n"
                + "	pantalla de dicho hecho, si estuviera la traza en ON.\r\n");

    }

}
