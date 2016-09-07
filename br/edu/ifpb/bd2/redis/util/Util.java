package edu.ifpb.bd2.redis.util;

import java.io.File;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class Util {

	private final static String DESTINO = "REDIS/FOTOS-REDIS";
	private final static String ORIGEM = "REDIS/FOTOS";
	private final static String PATH_IMAGEM = ORIGEM + "/";
	
	public static void createFolders(){
		
		File diretorio = new File(DESTINO);
		File diretorio2 = new File(ORIGEM);

		if (!diretorio.exists()) {
			diretorio.mkdirs();
		}
		
		if(!diretorio2.exists()){
			diretorio2.mkdirs();
		}
	}

	public static void bytesToImage(byte[] img, String key) {
		byte[] imgBytes = img;
		try {

			File diretorio = new File(DESTINO);

			if (!diretorio.exists()) {
				diretorio.mkdirs();
			}

			FileOutputStream fos = new FileOutputStream(PATH_IMAGEM +"REDIS_"+key);
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

		String image = PATH_IMAGEM + nome;

		InputStream is = null;
		byte[] buffer = null;
		is = new FileInputStream(image);
		buffer = new byte[is.available()];
		is.read(buffer);
		is.close();
		return buffer;
	}

}
