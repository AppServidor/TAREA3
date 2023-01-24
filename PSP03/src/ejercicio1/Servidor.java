package ejercicio1;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {
	public static void main(String[] args) {
		// Declaramos variables
		//int numeroAleatorio = (int) (Math.random()*100+0);
		int numCliente=101;
		int numeroAleatorio=2;
        ServerSocket conexion = null; //Socket para aceptar conexiones
        Socket canal = null; //Socket para establecer canal de comunicación

		DataOutputStream streamSalida = null;
		String valorEntrada=null;

        	try {
        		conexion = new ServerSocket(2000);
        		//SO Abre puerto de escucha
        	} catch (IOException err) {
                System.err.println("No se ha podido abrir el puerto de escucha.");
                System.err.println(err.toString());
        	}
        	
        	if (conexion != null) {
        		try {
        	System.out.println("Proceso escritor. Esperando conexión");
        	canal = conexion.accept();
        	//Cliente conectado
        	//Flujos entrada-salida
        	InputStream input = canal.getInputStream();
        	DataInputStream streamEntrada = new DataInputStream(input);
        	
        	OutputStream output = canal.getOutputStream();
        	streamSalida = new DataOutputStream(canal.getOutputStream());

        	System.out.println("Conexión establecida, mando datos al lector");
  
            System.out.println("Cliente en línea");

   
            //Se le envía un mensaje al cliente usando su flujo de salida
            streamSalida.writeUTF("Petición recibida y aceptada");



        	while(numeroAleatorio!= numCliente) {
        		System.out.println("prueba bucle");
        		numCliente = Integer.parseInt(streamEntrada.readUTF());
        		System.out.println("\tEl cliente ha dicho " +numCliente);
                if (numCliente == numeroAleatorio)  {
                	streamSalida.writeUTF("ok");
                }
                streamSalida.writeUTF("Nok"); 
        		System.out.println("Número incorrecto");
        		if (numCliente>numeroAleatorio) {
        			System.out.println("Intenta un número más bajo");
            		streamSalida.writeUTF("Intenta un número más bajo");		
        		} else if (numCliente<numeroAleatorio) {
        			System.out.println("Intenta un número más alto");
        			streamSalida.writeUTF("Intenta un número más alto");				
        		}
            }
           
        		} catch (Exception err){
                    System.err.println("No se ha podido establecer conexión, " +
                    "o no ha ocurrido un fallo al escribir en el canal.");
                    System.err.print(err.toString());
        		} finally {
        			// Es necesario cerrar los recursos abiertos
        			if (canal != null) {
        				try {
        					canal.close();
        				} catch (IOException err) {
        					System.out.println("No se ha podido cerrar el Socket");
        					System.out.println(err.toString());
        				}
        			}
        			if (conexion != null) {
        				try {
        					conexion.close();
        				} catch (IOException err) {
        					System.out.println("No se ha podido cerrar el Socket");
        					System.out.println(err.toString());        					
        				}
        			}
        		}
        	}
	}

}
