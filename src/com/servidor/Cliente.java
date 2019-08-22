package com.servidor;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class Cliente {

	public static void main(String[] args) {

		try (Socket servidor = new Socket("127.0.0.1", 15678)) {
			InputStream in = servidor.getInputStream();
			OutputStream out = servidor.getOutputStream();
			boolean sair = false;
			while (!sair) {
				if (in.available() > 0) {
					int i = in.read();
					System.out.print((char) i);
				}
				if (System.in.available() > 0) {
					int i = System.in.read();
					out.write(i);
					out.flush();
					System.out.print((char) i);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
