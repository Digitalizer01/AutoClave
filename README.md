## Index

## Table of Contents
1. [Introduction](#introduction)
2. [Installation](#installation)
3. [Usage](#usage)
   - [3.1. Command Line Arguments](#31-command-line-arguments)
   - [3.2. Config Class](#32-config-class)
   - [3.3. AutoclaveMethod Class](#33-autoclavemethod-class)
   - [3.4. AuxiliaryMethods Class](#34-auxiliarymethods-class)
   - [3.5. Playfair Class](#35-playfair-class)
   - [3.6. Caesar Class](#36-caesar-class)
   - [3.7. Vatsayayana Class](#37-vatsayayana-class)
4. [Conclusions](#conclusions)
5. [Bibliography](#bibliography)

### 1. Introduction

The motivation behind creating this program is the need to generate secure and reliable encrypted messages using the Vigenere table for secret communication. These messages can be both encrypted and decrypted based on the specific requirements. The program is implemented in the Java language, so familiarity with its syntax is necessary for modifications and understanding the steps indicated with comments for tracing purposes or in-depth study of its functionality.

This document aims to explain the underlying principles and the operation of this program for proper handling by the relevant personnel. We will delve into each class and explain the functionality of the methods and attributes they contain.

### 2. Paper Design

Before developing this software, a paper design was created to outline the basic encryption and decryption functionalities, as well as the required input arguments for the program to function correctly (see Figure 1).

![Paper Design](link-to-image)

*Figure 1. Paper design of the software's functionality.*

### 3. Classes

The classes that compose this program are: Main, AutoclaveMethod, AuxiliaryMethods, Config, Playfair, Caesar, and Vatsayayana. Each class has a set of utilities which will be detailed below.

#### 3.1. Main Class

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

#### 3.2. Config Class

This class is responsible for reading and processing the configuration file. It contains the following methods:

- `LeerArchivoConfiguracion`: Reads the configuration file and returns its content as a string.
- `ObtenerParametros`: Extracts the parameters from the configuration file and returns them as an array of strings.
- `ProcesarParametros`: Processes the parameters and performs the corresponding actions based on their values.

The `ProcesarParametros` method includes a switch statement that evaluates the different parameter options. The available options are as follows:

- `ejecuta`: Executes the corresponding encryption or decryption method based on the given input.
- `muestraAyuda`: Displays the program's help information.
- `noValido`: Displays an error message indicating that the provided parameter is invalid.
- `noExiste`: Displays an error message indicating that the configuration file does not exist.

The `ProcesarParametros` method also includes a call to the appropriate encryption or decryption method based on the given input.

#### 3.3. AutoclaveMethod Class

This class contains the implementation of the autoclave method, which is used for encryption and decryption. It includes the following methods:

- `Autoclave`: Performs the encryption or decryption using the autoclave method. It takes the input text and a key as parameters and returns the result as a string.

#### 3.4. AuxiliaryMethods Class

This class contains various auxiliary methods used by the program. These methods include:

- `ValidarArgumento`: Validates the input argument and returns a boolean value indicating whether the argument is valid.
- `ObtenerArchivoConfiguracion`: Returns the name of the configuration file based on the input argument.
- `MostrarAyuda`: Displays the program's help information.

#### 3.5. Playfair Class

This class contains the implementation of the Playfair cipher, which is used for encryption and decryption. It includes the following methods:

- `GeneratePlayfairMatrix`: Generates the Playfair matrix based on a given key.
- `Playfair`: Performs the encryption or decryption using the Playfair cipher. It takes the input text, a key, and a flag indicating whether it is encryption or decryption as parameters and returns the result as a string.

#### 3.6. Caesar Class

This class contains the implementation of the Caesar cipher, which is used for encryption and decryption. It includes the following methods:

- `Caesar`: Performs the encryption or decryption using the Caesar cipher. It takes the input text, a key, and a flag indicating whether it is encryption or decryption as parameters and returns the result as a string.

#### 3.7. Vatsayayana Class

This class contains the implementation of the Vatsayayana cipher, which is used for encryption and decryption. It includes the following methods:

- `Vatsayayana`: Performs the encryption or decryption using the Vatsayayana cipher. It takes the input text, a key, and a flag indicating whether it is encryption or decryption as parameters and returns the result as a string.

### 4. Conclusions

In conclusion, this program provides a comprehensive solution for generating secure and reliable encrypted messages using various encryption methods. It allows for easy encryption and decryption based on the provided input arguments and configuration file. By understanding the functionality and usage of each class and method, users can effectively utilize this program for their encryption needs.

### 5. Bibliography

- [Reference 1](link-to-reference-1)
- [Reference 2](link-to-reference-2)
- [Reference 3](link-to-reference-3)

