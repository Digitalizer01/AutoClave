## Index

## Table of Contents
1. [Introduction](#introduction)
2. [Classes](#classes)
   - [2.1. Main Class](#main-class)
   - [2.2. Config Class](#config-class)
   - [2.3. AutoclaveMetodo Class](#autoclavemetodo-class)
   - [2.4. Metodos_auxiliares Class](#metodos_auxiliares-class)
   - [2.5. Playfair Class](#playfair-class)
   - [2.6. Cesar Class](#caesar-class)
   - [2.7. Vatsayayana Class](#vatsayayana-class)
3. [Conclusions](#conclusions)

### 1. Introduction<a name="introduction"></a>

The motivation behind creating this program is the need to generate secure and reliable encrypted messages using the Vigenere table for secret communication. These messages can be both encrypted and decrypted based on the specific requirements. The program is implemented in the Java language, so familiarity with its syntax is necessary for modifications and understanding the steps indicated with comments for tracing purposes or in-depth study of its functionality.

This document aims to explain the underlying principles and the operation of this program for proper handling by the relevant personnel. We will delve into each class and explain the functionality of the methods and attributes they contain.

### 2. Classes<a name="classes"></a>

The classes that compose this program are: Main, AutoclaveMetodo, Metodos_auxiliares, Config, Playfair, Cesar, and Vatsayayana. Each class has a set of utilities which will be detailed below.

#### 2.1. Main Class<a name="main-class"></a>

This is the main class of the program. It handles the input arguments provided to the program via a .bat file located in the project's folder. The executable.bat file executes the program and contains the arguments processed by this class. The P1_si2020.bat file configures how the parameters are input and selects the .jar file that performs all the programming logic.

The introduction of parameters adds them to the `args` array. For example, if we input `-f config.txt`, the `args` array will be `args[0] = "-f"` and `args[1] = "config.txt"`.

The possible options are as follows:

- `-f config.txt`: Opens the config.txt file and performs all the steps indicated in the configuration file. The configuration file is not always named config.txt; it must match the actual file name.
- `-h`: Displays help related to the program.
- Other argument or no arguments provided: Displays a message indicating the syntax the user must follow to input the appropriate arguments.

Regarding the program's logic, it consists of two methods:

- `main`: Performs argument checking. It includes three nested if statements:
  - First if: Checks if an argument is provided. If nothing is provided, it displays the syntax to follow.
  - Second if: Checks if the argument is `-h`. In this case, the `MostrarAyuda` method is executed.
  - Third if: Checks if the argument is `-f config.txt`. If none of the previous conditions are met, it displays the syntax to follow.
- `MostrarAyuda`: Displays the program's help information to correctly input the arguments.

After the argument checking, the program proceeds to execute the instructions provided in the configuration file.

#### 2.2. Config Class<a name="config-class"></a>

This class is used to store and manage the configuration parameters read from the configuration file. It has several attributes:
- `fichero_configuracion`: Indicates the name of the configuration file.
- `salidapantalla`: Indicates whether there should be output data displayed on the screen (ON) or not (OFF).
- `salidafichero`: Indicates whether the output should be written to the output file (ON) or not (OFF).
- `codifica`: Indicates whether the Autoclave method should perform encoding (ON) or decoding (OFF).
- `traza`: Indicates whether the program trace should be shown on the screen (ON) or not (OFF).
- `ficheroentrada`: Indicates the name of the input file to be used for the operations with Autoclave.
- `ficherosalida`: Indicates the name of the output file where the program output will be saved.
- `clave`: Indicates the character string from 'a' to 'z' (excluding 'ñ') to be used as the primary key.
- `clave_larga`: Indicates the key resulting from truncating the formatted plaintext text plus the key (both with the length of the aforementioned text String).
- `formateaentrada`: Indicates whether the specified text should be formatted and the output dumped into the file specified by `ficherosalida`.

The Config class also has several constructors:
- Default Constructor: Initializes all the strings to "" and all the booleans to true. `ficherosalida` and `clave` are set to "salida.txt" and "merida" respectively.
- Parameterized Constructor (fichero_configuracion): Initializes all the data like the default constructor except for `fichero_configuracion`, which is set according to the parameter value.
- Parameterized Constructor: All the attributes are specified as parameters.

Additionally, this class contains a series of methods that are used for proper handling of the configuration file data:
- `leerFichero`: Reads the configuration file specified by `fichero_configuracion`. It performs the following steps in order:
  - Read a line. If it is blank or starts with the '#' character, discard it and move to the next line. Otherwise, process it.
  - If the line starts with '@', it is a flag. Read the content after the '@'. If it contains the words 'salidapantalla', 'salidafichero', 'codifica', or 'traza' followed by 'ON' or 'OFF', process it. Otherwise, discard it.
  - If the line starts with '&', it is a command. Read the content after the '&'.
    - If it is 'autoclave', execute the method with the same name.
    - If it is 'ficheroentrada', 'ficherosalida', 'clave', or 'formateaentrada', followed by a TXT file, process it. Otherwise, discard it.
  If an exception is thrown, it means that the configuration file does not exist or that a required file for a command does not exist.
- `autoclave`: Processes the 'autoclave' command. It reads the line and splits it into an array using whitespaces as the separator. If the array has two elements and the second element is 'ON' or 'OFF', assign the value to `codifica`. Otherwise, discard the command.
- `ficheroEntrada`: Processes the 'ficheroentrada' command. It reads the line and splits it into an array using whitespaces as the separator. If the array has two elements and the second element is a valid TXT file, assign the value to `ficheroentrada`. Otherwise, discard the command.
- `ficheroSalida`: Processes the 'ficherosalida' command. It reads the line and splits it into an array using whitespaces as the separator. If the array has two elements and the second element is a valid TXT file, assign the value to `ficherosalida`. Otherwise, discard the command.
- `clave`: Processes the 'clave' command. It reads the line and splits it into an array using whitespaces as the separator. If the array has two elements and the second element is a valid character string, assign the value to `clave`. Otherwise, discard the command.
- `formateaEntrada`: Processes the 'formateaentrada' command. It reads the line and splits it into an array using whitespaces as the separator. If the array has two elements and the second element is 'ON' or 'OFF', assign the value to `formateaentrada`. Otherwise, discard the command.
- `mostrarContenido`: Displays the content of all the attributes of the class on the screen.
- `alfabeto_Vatsayana`: Displays the Vatsayayana alfabet.
- `clave_cesar`: Displays the César key.

#### 2.3. AutoclaveMetodo Class<a name="autoclavemetodo-class"></a>

This class contains the main logic for the Autoclave method. It performs the encoding and decoding operations based on the given parameters and configuration. The class has the following methods:
- `codifica`: Performs the encoding operation. It reads the input file, processes the content, applies the Autoclave encryption algorithm, and writes the output to the specified output file. If the `formateaentrada` flag is ON, it formats the input text before processing.
- `decodifica`: Performs the decoding operation. It reads the input file, processes the content, applies the Autoclave decryption algorithm, and writes the output to the specified output file.

The AutoclaveMetodo class also has several helper methods to assist in the encoding and decoding operations:
- `reiniciarContadores`: Resets the counters used during encoding or decoding.
- `esNumero`: Checks if a given character is a number.
- `esLetra`: Checks if a given character is a letter.
- `posicionCaracter`: Gets the position of a character in the alphabet.
- `caracterPosicion`: Gets the character at a given position in the alphabet.
- `esMascara`: Checks if a given character is a mask.
- `esValido`: Checks if a given character is valid for encoding/decoding.
- `encodifica`: Applies the encoding algorithm to a given character.
- `decodifica`: Applies the decoding algorithm to a given character.
- `procesar`: Processes a given input character based on the encoding/decoding mode and the mask.
- `ejecutar`: Executes the encoding/decoding operation on the input file and writes the output to the output file.
- `escribeFichero`: Writes a given string to the output file.
- `getContenidoFichero`: Retrieves the content of a file as a string.

#### 2.4. Metodos_auxiliares Class<a name="metodos_auxiliares-class"></a>

This class contains auxiliary methods to assist the algorithms.
Methods:

- `Contiene_repetidos`: Method that indicates whether there are duplicates or not.
- `Truncar_Texto`: Method that truncates text.
- `Comprobar_clave_playfair`: Method that checks if the Playfair key is valid or not.
- `Formatear`: Method that formats text by removing special characters and spaces.
- `formatear_playfair`: Method that formats text by removing special characters except for "ñ".
- `formatear_2`: Method that formats text by removing special characters except for "ñ" and accented vowels.
- `rotateArray`: Method that rotates an array to the right or left.
- `isNumeric`: Method that checks whether a string is a numeric value or not.

#### 2.5. Playfair Class<a name="playfair-class"></a>

Class responsible for Playfair encryption and decryption.
Methods:
- `Comando_playfair_cifrar`: Method responsible for Playfair encryption.
- `Comando_playfair_descifrar`: Method responsible for Playfair decryption.

#### 2.6. Caesar Class<a name="caesar-lass"></a>

Class responsible for Caesar encryption and decryption.
Methods:
- `Comando_cargaclavecesar`: Method that loads the Caesar key.
- `Comando_clavecesar`: Method that saves the passed key in a tokenizer.
- `Comando_cesar_cifrado`: Method that encrypts using the Caesar algorithm.
- `Comando_cesar_descifrado`: Method that decrypts using the Caesar algorithm.
- `Comando_generaclavecesar`: Method that generates a random Caesar key.

#### 2.7. Vatsayayana Class<a name="vatsayayana-class"></a>

Class responsible for Vatsayayana encryption and decryption.
Methods:
- `Comando_vatsayayana`: Method that performs Vatsayayana encryption or decryption.
- `Comando_alfabetovatsyayana`: Method that loads a given alphabet as parameters.
- `Comando_generaalfabetovatsyayana`: Method that generates a random alphabet and loads it.
- `Comando_cargaalfabetovatsyayana`: Method that loads the alphabet from the input file.

### 3. Conclusions<a name="conclusions"></a>

In conclusion, this program provides a comprehensive solution for generating secure and reliable encrypted messages using various encryption methods. It allows for easy encryption and decryption based on the provided input arguments and configuration file. By understanding the functionality and usage of each class and method, users can effectively utilize this program for their encryption needs.
