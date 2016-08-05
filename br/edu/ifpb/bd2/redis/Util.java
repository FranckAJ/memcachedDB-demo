package edu.ifpb.bd2.redis;

import java.io.File;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class Util {

	private final static String DESTINO = "REDIS";
	private final static String pathImages = System.getProperty("user.home") + "/" + DESTINO + "/";
	private final static String home = System.getProperty("user.home");

	public static void bytesToImage(byte[] img, String key) {
		byte[] imgBytes = img;
		try {

			File diretorio = new File(home, DESTINO);

			if (!diretorio.exists()) {
				diretorio.mkdirs();
			}

			FileOutputStream fos = new FileOutputStream(pathImages +"REDIS_"+key);
			fos.write(imgBytes);
			FileDescriptor fd = fos.getFD();
			fos.flush();
			fd.sync();
			fos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Pega a image por meio do caminho especificado e converte em Bytes
	 * 
	 * @param image
	 * @return
	 * @throws IOException
	 */
	public static byte[] imageToByte(String nome) throws IOException {

		String image = pathImages + nome;

		InputStream is = null;
		byte[] buffer = null;
		is = new FileInputStream(image);
		buffer = new byte[is.available()];
		is.read(buffer);
		is.close();
		return buffer;
	}

}
