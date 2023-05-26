# AutoClave Project Documentation

## Table of Contents
1. [Introduction](#introduction)
2. [Classes](#classes)
   2.1. [Main Class](#main-class)
   2.2. [Config Class](#config-class)
   2.3. [AutoclaveMetodo Class](#autoclavemetodo-class)

## Introduction <a name="introduction"></a>

The "AutoClave" encryption is a Polyalphabetic cipher, which is a variant of the Vigenere cipher, also known as the Second Vigenere Cipher. Its main characteristic is that the message is encrypted with a key that consists of the same message appended with a primary key called the primary key. Therefore, the key sequence will be as long as the message itself.

For example, if we want to encrypt the message M = "esta es la primera practica" with the key K = "merida," using the Vigenere table (see table in the image below), we would obtain the following ciphertext:

![Screenshot](Images/VigenereTable.png)

Plain text: estaeslaprimerapractica
Key: meridaestaeslaprimerapr
Ciphertext: qwkihspsirmeprpgzmgkirr

The decryption operation is performed in a similar manner. The first characters (as many as the length of the primary key) are decrypted using the primary key, and the rest of the characters are decrypted using the plain text we are obtaining. In the example:

Ciphertext: qwkihspsirmeprpgzmgkirr
Key: meridaestaeslaprimerapr
Decrypted text: estaeslaprimerapractica

Our plain text will be a TXT file that contains ASCII characters from 'a' to 'z' (excluding 'ñ'). Any other symbol that is not one of those will not be encoded/decoded and will NOT be passed to the output file. To avoid this, pre-processing of the plain text can be performed as indicated in the configuration file or not.

Note: consider that the program is written in Spanish.

## Classes <a name="classes"></a>

The project consists of the following classes: Main Class, Config Class, and AutoclaveMetodo Class. Each of them has a set of utilities that will be detailed below.

### Main Class <a name="main-class"></a>

This is the main class of the program. In this class, we handle the input arguments that we pass to the program through a .bat file located in the project execution folder. The executable.bat file executes the program and contains the arguments that we handle in this class. The P1_si2020.bat file is used to configure how the parameters are input and selects the .jar file that performs all the programming logic.

The introduction of parameters adds them to the `args` array. For example, if we input `-f config.txt`, the `args` array will be populated as `args[0] = "-f"` and `args[1] = "config.txt"`.

The possibilities for the input arguments are as follows:
- `-f config.txt`: Opens the config.txt file and performs all the steps specified in that configuration file. The configuration file is not always named config.txt; it should be named according to the actual file name.
- `-h`: Displays the program-related help.
- Other argument or no input of arguments: Displays a message indicating the syntax that the user should follow to enter the appropriate arguments.

Regarding the program logic, there are two methods:
- `Principal`: Performs argument validation. It has three nested `if` statements:
  - First `if`: Checks if there is anything written as an argument. If there is nothing, it displays a help message.
  - Second `if`: Checks if the input argument is equal to `-h`. If it is, it calls the `help` method.
  - Third `if`: Checks if the input argument is equal to `-f`. If it is, it calls the `config` method.
- `help`: Displays a help message indicating the syntax for using the program.
- `config`: Reads the file specified in the argument, extracts the values, and sends them to the `AutoclaveMetodo` class to be processed. The `config` method uses the `BufferedReader` class to read the file.

### Config Class <a name="config-class"></a>

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

### AutoclaveMetodo Class <a name="autoclavemetodo-class"></a>

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

## Conclusion

The AutoClave project provides an implementation of the AutoClave encryption algorithm, which is a variant of the Vigenere cipher. The project consists of several classes that handle different aspects of the encryption process, including argument handling, configuration management, and the actual encoding/decoding operations. By following the provided documentation, users can effectively use the AutoClave program to encrypt and decrypt their messages.

Please note that this documentation provides a high-level overview of the project and its components. For detailed implementation details, please refer to the actual source code and comments within the code.
