package com.servidor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.net.ServerSocket;
import java.net.Socket;

public class ControladorSocket {

	public static void main(String[] args) {

		try (ServerSocket srv = new ServerSocket(15678)) {

			System.out.println("Iniciando o Servidor");
			System.out.println("Aguardando Conexão do Cliente");
			Socket cli = srv.accept();
			OutputStream out = cli.getOutputStream();
			InputStream in = cli.getInputStream();
			Reader inr = new InputStreamReader(in);
			BufferedReader bfr = new BufferedReader(inr);
			out.write("Bem Vindo ao Servidor Java".getBytes());
			out.flush();
			int i = 0;
			boolean sair = false;
			while (!sair) {
				if (bfr.ready()) {
						
					String linha = bfr.readLine();
					System.out.println(linha);
					if(linha.contains("SAIR") || linha.contains("EXIT")) {
						sair = true;
					} else if (linha.contains("EXEC")) {
						Runtime.getRuntime().exec(linha.substring(5));
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
