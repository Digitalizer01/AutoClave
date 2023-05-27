
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;
import java.nio.charset.StandardCharsets;

/**
 * Clase encargada del cifrado y descifrado Vatsayayana.
 *
 */
public class Vatsayayana {

    /**
     * Método que realiza el cifrado o descifrado Vatsayayana.
     *
     * @param ficheroentrada Fichero entrada.
     * @param ficherosalida Fichero salida.
     * @param alfabeto_Vatsayana Alfabeto Vatsayana.
     * @param traza Boolean traza.
     * @param codifica Boolean codifica.
     */
    public void Comando_vatsayayana(String ficheroentrada, String ficherosalida, String alfabeto_Vatsayana, Boolean traza, Boolean codifica) {
        BufferedReader objecto_lector_vatsayayana = null;

        // Se comprueba si existe el fichero de entrada a formatear.
        // Si existe, se formatea y se guarda la salida (si salidafichero es true) en el fichero de salida indicado por ficherosalida.
        // Si no existe, salta la excepción.
        try {
            objecto_lector_vatsayayana = new BufferedReader(new InputStreamReader(new FileInputStream(ficheroentrada), "UTF-8"));

            String linea = "";
            String linea_siguiente = "";

            // Se leen todas las líneas que pueda tener el fichero de entrada.
            while ((linea_siguiente = objecto_lector_vatsayayana.readLine()) != null) {
                linea = linea + linea_siguiente;
            }

            // Si el fichero no está en blanco.
            if (linea != null) {
                // Se cogen solo los primeros 1000 caracteres del fichero.
                Metodos_auxiliares metodos = new Metodos_auxiliares();
                linea = metodos.Truncar_Texto(linea, 1000);

                char[] alfabeto_parte_1 = new char[14];
                char[] alfabeto_parte_2 = new char[14];

                /*

                                                         parte 1: abcdefghijklmn
                                                         parte 2: ñopqrstuvwxyz=

                 */
                for (int i = 0; i <= 13; i++) {
                    alfabeto_parte_1[i] = alfabeto_Vatsayana.charAt(i);
                }

                for (int i = 14; i <= 27; i++) {
                    alfabeto_parte_2[i - 14] = alfabeto_Vatsayana.charAt(i);
                }

                for (int i = 0; i < alfabeto_parte_1.length; i++) {
                    if (alfabeto_parte_1[i] == '=') {
                        alfabeto_parte_1[i] = ' ';
                    }

                    if (alfabeto_parte_2[i] == '=') {
                        alfabeto_parte_2[i] = ' ';
                    }
                }

                String alfabeto_parte_1_string = new String(alfabeto_parte_1);
                String alfabeto_parte_2_string = new String(alfabeto_parte_2);

                if (traza) {

                    System.out.println("Parte 1 del alfabeto: " + alfabeto_parte_1_string);
                    System.out.println("Parte 2 del alfabeto: " + alfabeto_parte_2_string);
                }
                char[] linea_char_array = new char[linea.length()];

                for (int i = 0; i < linea.length(); i++) {
                    for (int j = 0; j < 14; j++) {
                        if (linea.charAt(i) == alfabeto_parte_1[j]) {
                            linea_char_array[i] = alfabeto_parte_2[j];
                        }
                        if (linea.charAt(i) == alfabeto_parte_2[j]) {
                            linea_char_array[i] = alfabeto_parte_1[j];
                        }
                    }
                }

                String linea_final = new String(linea_char_array);

                File archivo = new File(ficherosalida);
                BufferedWriter bw;
               // CharsetEncoder encoder = Charset.forName("UTF-8").newEncoder();

                bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(archivo), "UTF-8" ));
                if (traza == true) {

                    if (codifica) {
                        System.out.println("Vamos a cifrar con Vatsayayana");
                        System.out.println("Fichero a traducir: " + linea + "\n"
                                + "Texto en claro: " + linea + "\n"
                                + "Texto cifrado:" + linea_final);
                    } else {

                        System.out.println("Vamos a cifrar con Vatsayayana");
                        System.out.println("Fichero a traducir: " + linea + "\n"
                                + "Texto en claro: " + linea + "\n"
                                + "Texto descifrado:" + linea_final);
                    }
                }

                // Se escribe el texto formateado en el fichero de salida.
                bw.write(linea_final);
                bw.close();

                // Si el fichero está en blanco.
            } else {

                File archivo = new File(ficherosalida);
                BufferedWriter bw;

                bw = new BufferedWriter(new OutputStreamWriter(
                        new FileOutputStream(archivo), "UTF-8"
                ));
                //     bw = new BufferedWriter(new FileWriter(archivo, StandardCharsets.UTF_8));
                if (traza == true) {
                    if (codifica) {
                        System.out.println("Fichero fuente antes de formatear:\n"
                                + "Fichero a traducir:\n"
                                + "Texto en claro:\n"
                                + "Texto cifrado:");

                    } else {
                        System.out.println("Fichero fuente antes de formatear:\n"
                                + "Fichero a traducir:\n"
                                + "Texto en claro:\n"
                                + "Texto descifrado:");
                    }

                }
                bw.close();
            }

            // Salta la excepción si no existe el fichero de entrada a formatear.
        } catch (IOException e) {
            if (traza == true) {
                System.out.println("-------- ERROR --------");
                System.out.println("No Existe el fichero de entrada: " + ficheroentrada);
                System.out.println("-----------------------");
            }
        } finally {

            try {
                if (objecto_lector_vatsayayana != null) {
                    objecto_lector_vatsayayana.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

    }

    /**
     * Método que carga un alfabeto dado por parámetros.
     *
     * @param alfabeto Alfabeto a ingresar.
     * @param traza Boolean traza.
     * @return Alfabeto Vatsayayana.
     */
    public String Comando_alfabetovatsyayana(String alfabeto, Boolean traza) {
        Metodos_auxiliares metodos = new Metodos_auxiliares();
        String alfabeto_Vatsayana = null;
        if (!metodos.Contiene_repetidos(alfabeto) && alfabeto.length() == 28) {
            alfabeto_Vatsayana = alfabeto;

            if (traza) {
                System.out.println("Nuevo alfabeto de Vatsyayana: " + alfabeto.substring(0, 14));
                System.out.println("                              " + alfabeto.substring(14, 28));
            }
        } else {
            System.out.println("Alfabeto Vatsyayana incorrecto: " + alfabeto);
        }

        return alfabeto_Vatsayana;
    }

    /**
     * Método que genera un alfabeto aleatorio y lo carga.
     *
     * @param ficherosalida Fichero salida.
     * @param traza Boolean traza.
     * @throws IOException
     */
    public void Comando_generaalfabetovatsyayana(String ficherosalida, Boolean traza) throws IOException {
        String alfabeto_predeterminado = "abcdefghijklmnñopqrstuvwxyz=";
        char[] alfabeto_predeterminado_array = new char[14];
        char[] alfabeto_aleatorio = new char[28];
        int cont_letras = 0;

        while (cont_letras <= 27) {

            int posicion_aleatoria = (int) ((Math.random() * (28 - 0)) + 0);

            if (alfabeto_aleatorio[posicion_aleatoria] == 0) {
                alfabeto_aleatorio[posicion_aleatoria] = alfabeto_predeterminado.charAt(cont_letras);
                cont_letras++;
            }
        }

        String alfabeto_aleatorio_string = new String(alfabeto_aleatorio);
        if (traza) {
            System.out.println("Nuevo alfabeto aleatorio: " + alfabeto_aleatorio_string);
        }

        File archivo_generaalfabetovatsyayana = new File(ficherosalida);
        BufferedWriter bw_generaalfabetovatsyayana;

        bw_generaalfabetovatsyayana = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream(archivo_generaalfabetovatsyayana), "UTF-8"
        ));
        bw_generaalfabetovatsyayana.write(alfabeto_aleatorio_string);
        bw_generaalfabetovatsyayana.close();

    }

    /**
     * Método que carga el alfabeto que esté en ficheroentrada.
     *
     * @param ficheroentrada Fichero entrada.
     * @param traza Boolean traza.
     * @return Alfabeto Vatsayayana.
     */
    public String Comando_cargaalfabetovatsyayana(String ficheroentrada, Boolean traza) {
        String fichero = ficheroentrada;
        String alfabeto_Vatsayana = null;
        BufferedReader objecto_lector_2 = null;

        try {
            objecto_lector_2 = new BufferedReader(new InputStreamReader(new FileInputStream(fichero), "UTF-8"));

            String linea = "";
            String linea_siguiente = "";

            // Se leen todas las líneas que pueda tener el fichero de entrada.
            linea = objecto_lector_2.readLine();
            // Si el fichero no está en blanco.
            if (linea != null) {
                // Se cogen solo los primeros 1000 caracteres del fichero.
                Metodos_auxiliares metodos = new Metodos_auxiliares();
                linea = metodos.Truncar_Texto(linea, 1000);

                if (!metodos.Contiene_repetidos(linea) && linea.length() == 28) {
                    alfabeto_Vatsayana = linea;
                    if (traza == true) {
                        System.out.println("Cargamos el nuevo alfabeto Vatsayayana del fichero " + ficheroentrada + ": " + linea);
                    }
                } else {
                    System.out.println("Alfabeto Vatsyayana incorrecto: " + linea);
                }

                // Si el fichero está en blanco.
            }
            // Salta la excepción si no existe el fichero de entrada a formatear.
        } catch (IOException e) {
            if (traza == true) {
                System.out.println("-------- ERROR --------");
                System.out.println("No Existe el fichero de entrada de donde se coge el alfabeto: " + fichero);
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
        return alfabeto_Vatsayana;
    }

}
