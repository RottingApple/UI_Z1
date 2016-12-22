import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class Mainik {
	public static void main(String[] args) {
		try {
			long cas = System.currentTimeMillis();
			Rozmery rozmer = Rozmery.getInstance();
			rozmer.setStarttime(cas);
			Vypocet vyp = new Vypocet();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
