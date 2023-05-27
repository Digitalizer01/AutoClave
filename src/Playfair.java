
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
 * Clase encargada del cifrado y descifrado Playfair.
 *
 */
public class Playfair {

    /**
     * Método encargado del cifrado de Playfair.
     *
     * @param ficheroentrada Fichero de entrada.
     * @param ficherosalida Fichero de salida.
     * @param clave Clave Playfair.
     * @param traza Boolean traza.
     */
    public void Comando_playfair_cifrar(String ficheroentrada, String ficherosalida, String clave, Boolean traza) {
        Metodos_auxiliares metodos = new Metodos_auxiliares();
        if (metodos.Contiene_repetidos(clave) || !metodos.Comprobar_clave_playfair(clave)) {
            if (traza) {
                System.out.println("Clave playfair propuesta:[" + clave + "] no es válida");
            }
        } else {
            if (traza) {
                System.out.println("Clave para Playfair:[" + clave + "] es valida");
            }
            BufferedReader objecto_lector_playfair = null;

            try {
                objecto_lector_playfair = new BufferedReader(new InputStreamReader(new FileInputStream(ficheroentrada), "UTF-8"));

                String linea = "";

                linea = objecto_lector_playfair.readLine();

                // Si el fichero no está en blanco.
                if (linea != null) {
                    char[] clave_array = clave.toCharArray();
                    char[] alfabeto_base = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'ñ', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '=', '.', ';'};

                    char[][] matriz_abecedario = new char[5][6];

                    int indice = 0;
                    boolean enc = false;

                    for (int i = 0; i < clave_array.length; i++) {

                        for (int j = 0; j < alfabeto_base.length && !enc; j++) {

                            if (clave_array[i] == alfabeto_base[j]) {
                                alfabeto_base[j] = '0';
                                enc = true;
                            }

                        }

                        enc = false;
                    }

                    indice = 0;
                    enc = false;
                    for (int i = 0; i < matriz_abecedario.length; i++) {

                        for (int j = 0; j < matriz_abecedario[0].length; j++) {
                            enc = false;
                            if (indice < clave_array.length) {
                                matriz_abecedario[i][j] = clave_array[indice];
                                indice++;
                            } else {

                                for (int z = 0; z < alfabeto_base.length && !enc; z++) {
                                    if (alfabeto_base[z] != '0') {
                                        matriz_abecedario[i][j] = alfabeto_base[z];
                                        alfabeto_base[z] = '0';
                                        enc = true;
                                    }
                                }

                            }

                        }

                    }

                    File archivo = new File(ficherosalida);
                    BufferedWriter bw;
                    CharsetEncoder encoder = Charset.forName("UTF-8").newEncoder();

                    bw = new BufferedWriter(new OutputStreamWriter(
                            new FileOutputStream(archivo), "UTF-8"
                    ));

                    // bw = new BufferedWriter(new FileWriter(archivo, StandardCharsets.UTF_8));
                    if (traza == true) {
                        System.out.println("==================================");
                        System.out.println("Tabla Playfair construida");
                        System.out.println();

                        for (int i = 0; i < matriz_abecedario.length; i++) {
                            for (int j = 0; j < matriz_abecedario[0].length; j++) {
                                if (matriz_abecedario[i][j] == '=') {
                                    System.out.print("  ");
                                } else {
                                    System.out.print(matriz_abecedario[i][j] + " ");
                                }
                            }
                            System.out.println();
                        }

                        System.out.println();
                        System.out.println("==================================");

                        System.out.println("Salida fase I de:[" + linea + "]:");
                    }
                    // Hay que dividir el string en trozos de dos en dos.
                    char[] linea_array = linea.toCharArray();
                    String linea_separada = "";

                    for (int i = 0; i < linea_array.length; i = i + 2) {

                        if ((i + 1) < linea_array.length) {

                            if (linea_array[i] == linea_array[i + 1]) {
                                int aleatorio = (int) (Math.random() * (1 - 0 + 1) + 0);
                                // char[]

                                if (aleatorio == 1) {
                                    linea_separada = linea_separada + linea_array[i] + '.' + " ";
                                } else {
                                    linea_separada = linea_separada + linea_array[i] + ';' + " ";
                                }

                                aleatorio = (int) (Math.random() * (1 - 0 + 1) + 0);
                                // char[]

                                if (aleatorio == 1) {
                                    linea_separada = linea_separada + linea_array[i] + '.' + " ";
                                } else {
                                    linea_separada = linea_separada + linea_array[i] + ';' + " ";
                                }

                            } else {

                                if (linea_array[i] == ' ' || linea_array[i + 1] == ' ') {
                                    if (linea_array[i] == ' ') {
                                        linea_separada = linea_separada + '=' + linea_array[i + 1] + " ";
                                    } else {
                                        linea_separada = linea_separada + linea_array[i] + '=' + " ";
                                    }
                                } else {
                                    linea_separada = linea_separada + linea_array[i] + linea_array[i + 1] + " ";
                                }
                            }
                        } else {
                            if (linea_array[i] == ' ') {
                                linea_separada = linea_separada + '=' + " ";
                            } else {

                                int aleatorio = (int) (Math.random() * (1 - 0 + 1) + 0);

                                if (aleatorio == 1) {
                                    linea_separada = linea_separada + linea_array[i] + '.' + " ";
                                } else {
                                    linea_separada = linea_separada + linea_array[i] + ';' + " ";
                                }

                            }
                        }

                    }

                    if (traza) {
                        System.out.println(linea_separada);
                    }

                    // Ahora vamos a separar el string linea_separada mediante un split gracias a los espacios
                    String[] linea_separada_split = linea_separada.split(" ");
                    
                    
                    

                    /* Debemos comprobar ahora los siguientes casos:
                                                                
                                                            1 - El par de caracteres está en la misma columna
                                                            2 - El par de caracteres está en la misma fila
                                                            3 - Ninguna de las dos anteriores
                                                            
                     */
                    String texto_cifrado = "";
                    for (int i = 0; i < linea_separada_split.length; i++) {
                        char[] linea_separada_split_array = linea_separada_split[i].toCharArray();
                        
                        try {
                                                    
                        //System.out.println("Longitud de linea_separada_split_array "+ linea_separada_split_array.toString().length());
                        int pos_x_1 = 0;
                        int pos_y_1 = 0;
                        int pos_x_2 = 0;
                        int pos_y_2 = 0;

                        for (int j = 0; j < matriz_abecedario.length; j++) {
                            for (int z = 0; z < matriz_abecedario[0].length; z++) {
                               
                                if (matriz_abecedario[j][z] == linea_separada_split_array[0]) {
                                    pos_x_1 = j;
                                    pos_y_1 = z;
                                }
                                if (matriz_abecedario[j][z] == linea_separada_split_array[1]) {
                                    pos_x_2 = j;
                                    pos_y_2 = z;
                                }
                            }
                        }

                        // Comprobamos si el par está en la misma columna
                        if (pos_y_1 == pos_y_2) {

                            int pos_x_1_nuevo;
                            int pos_x_2_nuevo;

                            if (pos_x_1 == 4) {
                                pos_x_1_nuevo = 0;

                            } else {
                                pos_x_1_nuevo = pos_x_1 + 1;
                            }

                            if (pos_x_2 == 4) {
                                pos_x_2_nuevo = 0;

                            } else {
                                pos_x_2_nuevo = pos_x_2 + 1;
                            }

                            char char_1_nuevo = matriz_abecedario[pos_x_1_nuevo][pos_y_1];
                            char char_2_nuevo = matriz_abecedario[pos_x_2_nuevo][pos_y_2];

                            char char_1_antiguo = matriz_abecedario[pos_x_1][pos_y_1];
                            char char_2_antiguo = matriz_abecedario[pos_x_2][pos_y_2];

                            texto_cifrado = texto_cifrado + char_1_nuevo + char_2_nuevo;

                            if (char_1_nuevo == '=') {
                                char_1_nuevo = ' ';
                            }
                            if (char_2_nuevo == '=') {
                                char_2_nuevo = ' ';
                            }

                            if (char_1_antiguo == '=') {
                                char_1_antiguo = ' ';
                            }
                            if (char_2_antiguo == '=') {
                                char_2_antiguo = ' ';
                            }

                            if (traza) {
                                System.out.println("Buscamos [" + char_1_antiguo + char_2_antiguo + "] en la tabla. Se cambia por [" + char_1_nuevo + char_2_nuevo + "]");
                            }

                        } else {
                            // Comprobamos si el par está en la misma fila
                            if (pos_x_1 == pos_x_2) {

                                int pos_y_1_nuevo;
                                int pos_y_2_nuevo;

                                if (pos_y_1 == 5) {
                                    pos_y_1_nuevo = 0;
                                } else {
                                    pos_y_1_nuevo = pos_y_1 + 1;
                                }

                                if (pos_y_2 == 5) {
                                    pos_y_2_nuevo = 0;
                                } else {
                                    pos_y_2_nuevo = pos_y_2 + 1;
                                }

                                char char_1_nuevo = matriz_abecedario[pos_x_1][pos_y_1_nuevo];
                                char char_2_nuevo = matriz_abecedario[pos_x_2][pos_y_2_nuevo];

                                char char_1_antiguo = matriz_abecedario[pos_x_1][pos_y_1];
                                char char_2_antiguo = matriz_abecedario[pos_x_2][pos_y_2];

                                texto_cifrado = texto_cifrado + char_1_nuevo + char_2_nuevo;

                                if (char_1_nuevo == '=') {
                                    char_1_nuevo = ' ';
                                }
                                if (char_2_nuevo == '=') {
                                    char_2_nuevo = ' ';
                                }

                                if (char_1_antiguo == '=') {
                                    char_1_antiguo = ' ';
                                }
                                if (char_2_antiguo == '=') {
                                    char_2_antiguo = ' ';
                                }

                                if (traza) {
                                    System.out.println("Buscamos [" + char_1_antiguo + char_2_antiguo + "] en la tabla. Se cambia por [" + char_1_nuevo + char_2_nuevo + "]");
                                }

                            } // No están ni en la misma fila ni en la misma columna
                            else {

                                char char_1_nuevo = matriz_abecedario[pos_x_2][pos_y_1];
                                char char_2_nuevo = matriz_abecedario[pos_x_1][pos_y_2];

                                char char_1_antiguo = matriz_abecedario[pos_x_1][pos_y_1];
                                char char_2_antiguo = matriz_abecedario[pos_x_2][pos_y_2];

                                texto_cifrado = texto_cifrado + char_1_nuevo + char_2_nuevo;

                                if (char_1_nuevo == '=') {
                                    char_1_nuevo = ' ';
                                }
                                if (char_2_nuevo == '=') {
                                    char_2_nuevo = ' ';
                                }

                                if (char_1_antiguo == '=') {
                                    char_1_antiguo = ' ';
                                }
                                if (char_2_antiguo == '=') {
                                    char_2_antiguo = ' ';
                                }

                                if (traza) {
                                    System.out.println("Buscamos [" + char_1_antiguo + char_2_antiguo + "] en la tabla. Se cambia por [" + char_1_nuevo + char_2_nuevo + "]");
                                }
                            }
                        }

                        } catch (Exception e) {
                            texto_cifrado = texto_cifrado + "  ";
                            i=i+1;
                        }

                    }

                    if (traza) {
                        System.out.println("Texto cifrado: " + texto_cifrado);
                    }

                    bw.write(texto_cifrado);
                    bw.close();

                    // Si el fichero está en blanco.
                } else {

                    File archivo = new File(ficherosalida);
                    BufferedWriter bw;
                    CharsetEncoder encoder = Charset.forName("UTF-8").newEncoder();

                    bw = new BufferedWriter(new OutputStreamWriter(
                            new FileOutputStream(archivo), "UTF-8"
                    ));

                    //  bw = new BufferedWriter(new FileWriter(archivo, StandardCharsets.UTF_8));
                    if (traza == true) {
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
                    System.out.println("No Existe el fichero de entrada a formatear: " + ficheroentrada);
                    System.out.println("-----------------------");
                }
            } finally {

                try {
                    if (objecto_lector_playfair != null) {
                        objecto_lector_playfair.close();
                    }
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }

        }

    }

    /**
     *
     * Método encargado del descifrado de Playfair.
     *
     * @param ficheroentrada Fichero de entrada.
     * @param ficherosalida Fichero de salida.
     * @param clave Clave Playfair.
     * @param traza Boolean traza.
     */
    public void Comando_playfair_descifrar(String ficheroentrada, String ficherosalida, String clave, Boolean traza) {
        Metodos_auxiliares metodos = new Metodos_auxiliares();
        if (metodos.Contiene_repetidos(clave) || !metodos.Comprobar_clave_playfair(clave)) {
            if (traza) {
                System.out.println("Clave playfair propuesta:[" + clave + "] no es válida");
            }
        } else {
            if (traza) {
                System.out.println("Clave para Playfair:[" + clave + "] es valida");
            }
            BufferedReader objecto_lector_playfair = null;

            try {
                objecto_lector_playfair = new BufferedReader(new InputStreamReader(new FileInputStream(ficheroentrada), "UTF-8"));

                String linea = "";

                linea = objecto_lector_playfair.readLine();

                // Si el fichero no está en blanco.
                if (linea != null) {
                    char[] clave_array = clave.toCharArray();
                    char[] alfabeto_base = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'ñ', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '=', '.', ';'};

                    char[][] matriz_abecedario = new char[5][6];

                    int indice = 0;
                    boolean enc = false;

                    for (int i = 0; i < clave_array.length; i++) {

                        for (int j = 0; j < alfabeto_base.length && !enc; j++) {

                            if (clave_array[i] == alfabeto_base[j]) {
                                alfabeto_base[j] = '0';
                                enc = true;
                            }

                        }

                        enc = false;
                    }

                    indice = 0;
                    enc = false;
                    for (int i = 0; i < matriz_abecedario.length; i++) {

                        for (int j = 0; j < matriz_abecedario[0].length; j++) {
                            enc = false;
                            if (indice < clave_array.length) {
                                matriz_abecedario[i][j] = clave_array[indice];
                                indice++;
                            } else {

                                for (int z = 0; z < alfabeto_base.length && !enc; z++) {
                                    if (alfabeto_base[z] != '0') {
                                        matriz_abecedario[i][j] = alfabeto_base[z];
                                        alfabeto_base[z] = '0';
                                        enc = true;
                                    }
                                }

                            }

                        }

                    }

                    File archivo = new File(ficherosalida);
                    BufferedWriter bw;
                    CharsetEncoder encoder = Charset.forName("UTF-8").newEncoder();

                    bw = new BufferedWriter(new OutputStreamWriter(
                            new FileOutputStream(archivo), encoder
                    ));

                    //  bw = new BufferedWriter(new FileWriter(archivo, StandardCharsets.UTF_8));
                    if (traza == true) {
                        System.out.println("==================================");
                        System.out.println("Tabla Playfair construida");
                        System.out.println();

                        for (int i = 0; i < matriz_abecedario.length; i++) {
                            for (int j = 0; j < matriz_abecedario[0].length; j++) {
                                if (matriz_abecedario[i][j] == '=') {
                                    System.out.print("  ");
                                } else {
                                    System.out.print(matriz_abecedario[i][j] + " ");
                                }
                            }
                            System.out.println();
                        }

                        System.out.println();
                        System.out.println("==================================");
                    }
                    if (traza) {
                        System.out.println("Salida fase I de:[" + linea + "]:");
                    }

                    // Hay que dividir el string en trozos de dos en dos.
                    char[] linea_array = linea.toCharArray();
                    String linea_separada = "";

                    for (int i = 0; i < linea_array.length; i = i + 2) {

                        if ((i + 1) < linea_array.length) {

                            if (linea_array[i] == linea_array[i + 1]) {
                                int aleatorio = (int) (Math.random() * (1 - 0 + 1) + 0);

                                if (aleatorio == 1) {
                                    linea_separada = linea_separada + linea_array[i] + '.' + " ";
                                } else {
                                    linea_separada = linea_separada + linea_array[i] + ';' + " ";
                                }

                            } else {

                                if (linea_array[i] == ' ' || linea_array[i + 1] == ' ') {
                                    if (linea_array[i] == ' ') {
                                        linea_separada = linea_separada + '=' + linea_array[i + 1] + " ";
                                    } else {
                                        linea_separada = linea_separada + linea_array[i] + '=' + " ";
                                    }
                                } else {
                                    linea_separada = linea_separada + linea_array[i] + linea_array[i + 1] + " ";
                                }
                            }
                        } else {
                            if (linea_array[i] == ' ') {
                                linea_separada = linea_separada + '=' + " ";
                            } else {

                                int aleatorio = (int) (Math.random() * (1 - 0 + 1) + 0);

                                if (aleatorio == 1) {
                                    linea_separada = linea_separada + linea_array[i] + '.' + " ";
                                } else {
                                    linea_separada = linea_separada + linea_array[i] + ';' + " ";
                                }

                            }
                        }

                    }
                    if (traza) {
                        System.out.println(linea_separada);
                    }

                    // Ahora vamos a separar el string linea_separada mediante un split gracias a los espacios
                    String[] linea_separada_split = linea_separada.split(" ");

                    /* Debemos comprobar ahora los siguientes casos:
                                                                
                                                            1 - El par de caracteres está en la misma columna
                                                            2 - El par de caracteres está en la misma fila
                                                            3 - Ninguna de las dos anteriores
                                                            
                     */
                    String texto_cifrado = "";
                    for (int i = 0; i < linea_separada_split.length; i++) {
                        char[] linea_separada_split_array = linea_separada_split[i].toCharArray();
                        
                        try {
                        int pos_x_1 = 0;
                        int pos_y_1 = 0;
                        int pos_x_2 = 0;
                        int pos_y_2 = 0;

                        for (int j = 0; j < matriz_abecedario.length; j++) {
                            for (int z = 0; z < matriz_abecedario[0].length; z++) {
                                if (matriz_abecedario[j][z] == linea_separada_split_array[0]) {
                                    pos_x_1 = j;
                                    pos_y_1 = z;
                                }
                                if (matriz_abecedario[j][z] == linea_separada_split_array[1]) {
                                    pos_x_2 = j;
                                    pos_y_2 = z;
                                }
                            }
                        }

                        // Comprobamos si el par está en la misma columna
                        if (pos_y_1 == pos_y_2) {

                            int pos_x_1_nuevo;
                            int pos_x_2_nuevo;

                            if (pos_x_1 == 0) {
                                pos_x_1_nuevo = 4;

                            } else {
                                pos_x_1_nuevo = pos_x_1 - 1;
                            }

                            if (pos_x_2 == 0) {
                                pos_x_2_nuevo = 4;

                            } else {
                                pos_x_2_nuevo = pos_x_2 - 1;
                            }

                            char char_1_nuevo = matriz_abecedario[pos_x_1_nuevo][pos_y_1];
                            char char_2_nuevo = matriz_abecedario[pos_x_2_nuevo][pos_y_2];

                            char char_1_antiguo = matriz_abecedario[pos_x_1][pos_y_1];
                            char char_2_antiguo = matriz_abecedario[pos_x_2][pos_y_2];

                            texto_cifrado = texto_cifrado + char_1_nuevo + char_2_nuevo;

                            if (char_1_nuevo == '=') {
                                char_1_nuevo = ' ';
                            }
                            if (char_2_nuevo == '=') {
                                char_2_nuevo = ' ';
                            }
                            if (char_1_antiguo == '=') {
                                char_1_antiguo = ' ';
                            }
                            if (char_2_antiguo == '=') {
                                char_2_antiguo = ' ';
                            }
                            if (traza) {
                                System.out.println("Buscamos [" + char_1_antiguo + char_2_antiguo + "] en la tabla. Se cambia por [" + char_1_nuevo + char_2_nuevo + "]");
                            }

                        } else {
                            // Comprobamos si el par está en la misma fila
                            if (pos_x_1 == pos_x_2) {

                                int pos_y_1_nuevo;
                                int pos_y_2_nuevo;

                                if (pos_y_1 == 0) {
                                    pos_y_1_nuevo = 5;
                                } else {
                                    pos_y_1_nuevo = pos_y_1 - 1;
                                }

                                if (pos_y_2 == 0) {
                                    pos_y_2_nuevo = 5;
                                } else {
                                    pos_y_2_nuevo = pos_y_2 - 1;
                                }

                                char char_1_nuevo = matriz_abecedario[pos_x_1][pos_y_1_nuevo];
                                char char_2_nuevo = matriz_abecedario[pos_x_2][pos_y_2_nuevo];

                                char char_1_antiguo = matriz_abecedario[pos_x_1][pos_y_1];
                                char char_2_antiguo = matriz_abecedario[pos_x_2][pos_y_2];

                                texto_cifrado = texto_cifrado + char_1_nuevo + char_2_nuevo;

                                if (char_1_nuevo == '=') {
                                    char_1_nuevo = ' ';
                                }
                                if (char_2_nuevo == '=') {
                                    char_2_nuevo = ' ';
                                }
                                if (char_1_antiguo == '=') {
                                    char_1_antiguo = ' ';
                                }
                                if (char_2_antiguo == '=') {
                                    char_2_antiguo = ' ';
                                }
                                if (traza) {
                                    System.out.println("Buscamos [" + char_1_antiguo + char_2_antiguo + "] en la tabla. Se cambia por [" + char_1_nuevo + char_2_nuevo + "]");
                                }

                            } // No están ni en la misma fila ni en la misma columna
                            else {

                                char char_1_nuevo = matriz_abecedario[pos_x_2][pos_y_1];
                                char char_2_nuevo = matriz_abecedario[pos_x_1][pos_y_2];

                                char char_1_antiguo = matriz_abecedario[pos_x_1][pos_y_1];
                                char char_2_antiguo = matriz_abecedario[pos_x_2][pos_y_2];

                                texto_cifrado = texto_cifrado + char_1_nuevo + char_2_nuevo;

                                if (char_1_nuevo == '=') {
                                    char_1_nuevo = ' ';
                                }
                                if (char_2_nuevo == '=') {
                                    char_2_nuevo = ' ';
                                }

                                if (char_1_antiguo == '=') {
                                    char_1_antiguo = ' ';
                                }
                                if (char_2_antiguo == '=') {
                                    char_2_antiguo = ' ';
                                }
                                if (traza) {
                                    System.out.println("Buscamos [" + char_1_antiguo + char_2_antiguo + "] en la tabla. Se cambia por [" + char_1_nuevo + char_2_nuevo + "]");
                                }

                            }
                        }

                        } catch (Exception e) {
                            texto_cifrado = texto_cifrado + " ";
                            i=i+1;
                        }
                        
                    }

                    texto_cifrado = texto_cifrado.replaceAll("=", " "); // Eliminamos los espacios
                    texto_cifrado = metodos.formatear_2(texto_cifrado);
                    if (traza) {
                        System.out.println("Texto descifrado: " + texto_cifrado);
                    }

                    // Se escribe el texto formateado en el fichero de salida.
                    bw.write(texto_cifrado);
                    bw.close();

                    // Si el fichero está en blanco.
                } else {

                    File archivo = new File(ficherosalida);
                    BufferedWriter bw;
                    CharsetEncoder encoder = Charset.forName("UTF-8").newEncoder();

                    bw = new BufferedWriter(new OutputStreamWriter(
                            new FileOutputStream(archivo), "UTF-8"
                    ));

                    // bw = new BufferedWriter(new FileWriter(archivo, StandardCharsets.UTF_8));
                    if (traza == true) {
                        System.out.println("Texto descifrado:");
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
                    if (objecto_lector_playfair != null) {
                        objecto_lector_playfair.close();
                    }
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }

        }

    }

}
