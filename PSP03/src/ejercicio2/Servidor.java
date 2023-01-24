package ejercicio2;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {
	public static void main(String[] args) {
		// Declaramos variables
		int numeroAleatorio = (int) (Math.random()*100+0);
		int numCliente=101;
		//int numeroAleatorio=2;
        ServerSocket conexion = null; //Socket para aceptar conexiones
        Socket canal = null; //Socket para establecer canal de comunicación
        String fichero = "";
		DataOutputStream streamSalida = null;

        	try {
        		conexion = new ServerSocket(1500);
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
        	DataInputStream streamEntrada = new DataInputStream(canal.getInputStream());
        	streamSalida = new DataOutputStream(canal.getOutputStream());

        	System.out.println("Conexión establecida, mando datos al lector");
  
            System.out.println("Cliente en línea");

   
            //Recibe nombre del fichero del cliente y comprueba que existe
            fichero = streamEntrada.readUTF();
  		  try {
			  File file = new File(fichero);
			  if (!file.exists()) {
				  streamSalida.writeUTF("No existe el fichero");
			  } else {
				  streamSalida.writeUTF("Imprimo fichero");
			        BufferedReader br = new BufferedReader(new FileReader(fichero));

			         // Lectura del fichero
			         String linea;
			         String contenido="";
			         while((linea=br.readLine())!=null) {
			            contenido+=linea+"\n"; 
			         }
			         System.out.println(contenido);
			         //Mando fichero en bytes
		               
		                
		                byte[] datosFichero = contenido.getBytes();
		                
		                streamSalida.writeInt(datosFichero.length);
		                
		                for (int i = 0; i < datosFichero.length; i++) {
		                    streamSalida.writeByte(datosFichero[i]);
		                }
			
			  }

		  } catch (Exception err) {
			  System.out.println("No se ha podido escribir en el fichero");
			  err.toString();
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
