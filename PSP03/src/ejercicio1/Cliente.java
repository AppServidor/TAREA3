package ejercicio1;

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
		String numCliente="101";
		Scanner sc = new Scanner(System.in);
		try {
			canal = new Socket("localhost", 2000); //host: localhost, port: 1234
		} catch (Exception err) {
            System.err.println("No se ha podido establecer conexión");
            System.err.println(err.toString());			
		}
			if (canal != null) {
				try {

					//BufferedReader entrada=new BufferedReader(new InputStreamReader(System.in));
		        	//Flujos entrada-salida
		        	 streamEntrada = new DataInputStream(canal.getInputStream());

		        	streamSalida = new DataOutputStream(canal.getOutputStream());
			        valorEntrada = streamEntrada.readUTF();
		            	   System.out.println(valorEntrada);
		               while (!valorEntrada.equals("Correcto")){
					       System.out.println("Inserta número para adivinar");
					       numCliente= sc.nextLine();
					       //numCliente= "2";
					       streamSalida.writeUTF(numCliente);
					       valorEntrada = streamEntrada.readUTF();
					       System.out.println(valorEntrada);
		               }
		            //   }

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