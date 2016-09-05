import java.io.IOException;

import edu.ifpb.bd2.redis.dao.PhotoDao;
import edu.ifpb.bd2.redis.entities.Photo;
import edu.ifpb.bd2.redis.util.Util;

public class MainApp {
	public static void main(String[] args) throws IOException {
		
		Photo p = new Photo();
		p.setNome("vingadores.jpg");
		p.setImage(Util.imageToByte(p.getNome()));

		Photo p2 = new Photo();
		p2.setNome("greatest-superhero-gif-ever.gif");
		p2.setImage(Util.imageToByte(p2.getNome()));

		PhotoDao dao = new PhotoDao();

		//dao.removePhoto(p.getNome());
		//dao.savePhoto(p);
		//dao.savePhoto(p2);
		
		System.out.println(dao.getPhoto(4));
		
		System.out.println(dao.list());
		
		Util.bytesToImage(dao.getPhoto(14).getImage(), dao.getPhoto(14).getNome());
	
	}
}
