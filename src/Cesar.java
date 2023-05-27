
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

/**
 * Clase que se encarga del cifrado y descifrado de César.
 *
 */
public class Cesar {

    /**
     * Método que carga la clave César.
     *
     * @param ficheroentrada Fichero de entrada.
     * @param traza Boolean traza.
     * @return Clave.
     *
     */
    public int Comando_cargaclavecesar(String ficheroentrada, Boolean traza) {
        String fichero = ficheroentrada;
        Metodos_auxiliares metodos = new Metodos_auxiliares();
        int clave_cesar = 0;

        BufferedReader objecto_lector_2 = null;

        try {
            objecto_lector_2 = new BufferedReader(new InputStreamReader(new FileInputStream(fichero), StandardCharsets.UTF_8));

            String linea = "";
            String linea_siguiente = "";

            // Se leen todas las líneas que pueda tener el fichero de entrada.
            linea = objecto_lector_2.readLine();
            // Si el fichero no está en blanco.
            if (linea != null) {
                // Se cogen solo los primeros 1000 caracteres del fichero.
                linea = metodos.Truncar_Texto(linea, 1000);

                if (metodos.isNumeric(linea)) {
                    clave_cesar = Integer.parseInt(linea);
                    if (traza == true) {
                        System.out.println("Cargamos la nueva clave César del fichero " + ficheroentrada + ": " + clave_cesar);
                    }
                } else {
                    if (traza) {
                        System.out.println("Clave César incorrecta: " + linea);
                    }
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
        return clave_cesar;
    }

    /**
     * Método que guarda la clave pasada en un tokenizer.
     *
     * @param clave Clave César.
     * @param traza Boolean traza.
     * @return Clave.
     */
    public int Comando_clavecesar(String clave, Boolean traza) {
        Metodos_auxiliares metodos = new Metodos_auxiliares();
        int clave_cesar = 0;
        if (metodos.isNumeric(clave)) {
            if (Integer.parseInt(clave) > 0) {
                if (traza) {
                    System.out.println("Nueva clave César: " + clave);
                }
                clave_cesar = Integer.parseInt(clave);
            }
        } else {
            if (traza) {
                System.out.println("Clave César inválida: " + clave + ". Debe ser un número positivo mayor que 0.");
            }
        }
        return clave_cesar;
    }

    /**
     * Método que cifra con el algoritmo de César.
     *
     * @param ficheroentrada Fichero de entrada.
     * @param ficherosalida Fichero de salida.
     * @param clave_cesar Clave César.
     * @param traza Boolean traza.
     * @throws IOException
     */
    public void Comando_cesar_cifrado(String ficheroentrada, String ficherosalida, int clave_cesar, Boolean traza) throws IOException {
        char[] alfabeto_base = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'ñ', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
        Metodos_auxiliares metodos = new Metodos_auxiliares();

        char[] alfabeto_nuevo = metodos.rotateArray(alfabeto_base, (-clave_cesar));
        BufferedReader objecto_lector_2 = null;

        try {
            objecto_lector_2 = new BufferedReader(new InputStreamReader(new FileInputStream(ficheroentrada), StandardCharsets.UTF_8));

            String linea = "";

            // Se leen todas las líneas que pueda tener el fichero de entrada.
            linea = objecto_lector_2.readLine();
            // Si el fichero no está en blanco.
            if (linea != null) {
                // Se cogen solo los primeros 1000 caracteres del fichero.
                linea = metodos.Truncar_Texto(linea, 1000);

                char[] texto_original = linea.toCharArray();
                String texto_cifrado = "";

                // Recorremos el texto original char por char.
                // 
                for (int i = 0; i < texto_original.length; i++) {

                    if (texto_original[i] == ' ') {
                        texto_cifrado = texto_cifrado + ' ';
                    }
                    for (int j = 0; j < alfabeto_base.length; j++) {
                        if (texto_original[i] == alfabeto_base[j]) {
                            texto_cifrado = texto_cifrado + alfabeto_nuevo[j];
                        }
                    }

                }

                File archivo_cesar_cifrado = new File(ficherosalida);
                BufferedWriter bw_cesar_cifrado;

                bw_cesar_cifrado = new BufferedWriter(new FileWriter(archivo_cesar_cifrado, StandardCharsets.UTF_8));
                bw_cesar_cifrado.write(texto_cifrado);
                bw_cesar_cifrado.close();

                if (traza == true) {
                    System.out.println("El texto cifrado con César está en " + ficherosalida + " y es " + texto_cifrado);
                }

                // Si el fichero está en blanco.
            }
            // Salta la excepción si no existe el fichero de entrada a formatear.
        } catch (IOException e) {
            if (traza == true) {
                System.out.println("-------- ERROR --------");
                System.out.println("No Existe el fichero de entrada de donde se coge el alfabeto: " + ficherosalida);
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

    /**
     * Método que descifra con el algoritmo de César.
     *
     * @param ficheroentrada Fichero de entrada.
     * @param ficherosalida Fichero de salida.
     * @param clave_cesar Clave César.
     * @param traza Boolean traza.
     * @throws IOException
     */
    public void Comando_cesar_descifrado(String ficheroentrada, String ficherosalida, int clave_cesar, Boolean traza) throws IOException {
        char[] alfabeto_base = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'ñ', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
        Metodos_auxiliares metodos = new Metodos_auxiliares();

        char[] alfabeto_nuevo = metodos.rotateArray(alfabeto_base, (clave_cesar));
        BufferedReader objecto_lector_2 = null;

        try {
            objecto_lector_2 = new BufferedReader(new InputStreamReader(new FileInputStream(ficheroentrada), StandardCharsets.UTF_8));

            String linea = "";

            // Se leen todas las líneas que pueda tener el fichero de entrada.
            linea = objecto_lector_2.readLine();
            // Si el fichero no está en blanco.
            if (linea != null) {
                // Se cogen solo los primeros 1000 caracteres del fichero.
                linea = metodos.Truncar_Texto(linea, 1000);

                char[] texto_original = linea.toCharArray();
                String texto_descifrado = "";

                // Recorremos el texto original char por char.
                // 
                for (int i = 0; i < texto_original.length; i++) {

                    if (texto_original[i] == ' ') {
                        texto_descifrado = texto_descifrado + ' ';
                    }
                    for (int j = 0; j < alfabeto_base.length; j++) {
                        if (texto_original[i] == alfabeto_base[j]) {
                            texto_descifrado = texto_descifrado + alfabeto_nuevo[j];
                        }
                    }

                }

                File archivo_cesar_descifrado = new File(ficherosalida);
                BufferedWriter bw_cesar_descifrado;

                bw_cesar_descifrado = new BufferedWriter(new FileWriter(archivo_cesar_descifrado, StandardCharsets.UTF_8));
                bw_cesar_descifrado.write(texto_descifrado);
                bw_cesar_descifrado.close();

                if (true == true) {
                    System.out.println("El texto descifrado con César está en " + ficherosalida + " y es " + texto_descifrado);
                }

                // Si el fichero está en blanco.
            }
            // Salta la excepción si no existe el fichero de entrada a formatear.
        } catch (IOException e) {
            if (traza == true) {
                System.out.println("-------- ERROR --------");
                System.out.println("No Existe el fichero de entrada de donde se coge el alfabeto: " + ficherosalida);
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

    /**
     * Método que genera una clave César aleatoria.
     *
     * @param ficherosalida Fichero de salida.
     * @param traza Boolean traza.
     * @throws IOException
     */
    public void Comando_generaclavecesar(String ficherosalida, Boolean traza) throws IOException {
        int aleatorio = (int) (Math.random() * (26 - 1 + 26) + 1);
        if (traza) {
            System.out.println("Nueva clave de César aleatoria: " + aleatorio);
        }

        File archivo_genera_clave_cesar = new File(ficherosalida);
        BufferedWriter bw_genera_clave_cesar;

        bw_genera_clave_cesar = new BufferedWriter(new FileWriter(archivo_genera_clave_cesar, StandardCharsets.UTF_8));
        bw_genera_clave_cesar.write(aleatorio);
        bw_genera_clave_cesar.close();
    }

}
