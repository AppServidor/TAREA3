package ejercicio2;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Cliente {

	public static void main(String[] args) {
		Socket canal = null;
		String valorEntrada = null;
		DataOutputStream streamSalida = null;
		DataInputStream streamEntrada = null;
		Scanner sc = new Scanner(System.in);
		String fichero = "";
		byte[] contenidoFichero = null;
		try {
			canal = new Socket("localhost", 1500); //host: localhost, port: 1500
		} catch (Exception err) {
            System.err.println("No se ha podido establecer conexión");
            System.err.println(err.toString());			
		}
			if (canal != null) {
				try {


		        	//Flujos entrada-salida
		        	 streamEntrada = new DataInputStream(canal.getInputStream());

		        	streamSalida = new DataOutputStream(canal.getOutputStream());
		        	System.out.println("Introduce nombre del fichero");
		        	fichero = sc.next();
		        	streamSalida.writeUTF(fichero);
			        valorEntrada = streamEntrada.readUTF();
			        System.out.println(valorEntrada);
			        int size=0;
			        if (valorEntrada.equals("Imprimo fichero")) {
			        	
			        	size=streamEntrada.readInt();
			        	contenidoFichero = new byte[size];
				         for (int i=0;i<size;i++) {
				        	 contenidoFichero[i]=streamEntrada.readByte();
				         }
			                String contenido = new String(contenidoFichero);
			                
			                System.out.println(contenido);
			        }
			        

			        
		            	   
		            	  

				} catch (Exception err) {
					System.err.println("No se ha podido establecer conexión.");
		            System.err.println(err.toString());
				} finally {
					sc.close();
					if (streamEntrada != null) {
						try {
							streamEntrada.close();
						} catch (IOException err) {
							System.out.println("No se ha podido cerrar el InputStreamReader");
							System.out.println(err.toString());
						}
					if (canal != null) {
						try {
							canal.close();
						} catch (IOException err) {
							System.out.println("No se ha podido cerrar el Socket");
							System.out.println(err.toString());
						}
					}
					}
				}
			}

		

	}

	}