package com.servidor;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ServidorSocket {

	public static void main(String[] args) {

		try (ServerSocket srv = new ServerSocket(15678)) {

			System.out.println("Iniciando o Servidor");
			System.out.println("Aguardando Conexão do Cliente");
			Socket cli = srv.accept();
			OutputStream out = cli.getOutputStream();
			InputStream in = cli.getInputStream();
			out.write("Bem Vindo ao Servidor Java".getBytes());
			out.flush();
			int i = 0;
			boolean sair = false;
			while (!sair) {
				if (in.available() > 0) {
					i = in.read();
					System.out.print((char) i);
					if (i == 27) {
						sair = true;
					}
				}

				if (System.in.available() > 0) {
					i = System.in.read();
					out.write(i);
					out.flush();
					if (i == 27) {
						sair = true;
					}
				}

			}
			System.out.println("Cliente Conectado");
			System.out.println("Finalizando o Servidor");
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
