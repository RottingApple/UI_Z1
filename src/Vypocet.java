import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.CountDownLatch;

public class Vypocet {
	private Rozmery rozmer;
	private Depth_Search hladaniedohlbky;
	private Breadth_Search hladaniedosirky;
	Vypocet() throws Exception{
		rozmer = Rozmery.getInstance();
		System.out.println("Hello worlds");
		citaj_Vstup();
		System.out.println("Medzera je na "+rozmer.getZaciatok().getPoz_X());
		rozmer.getTerc().put(rozmer.getKoniec().spravHash(rozmer.getKoniec().getStav()),1);
		vyries();
		//rozmer.getZaciatok().zisti_moznosti(rozmer.getZaciatok());
		//rozmer.getZaciatok().vypis(rozmer.getZaciatok().getStav());
		//rozmer.getKoniec().zisti_moznosti(rozmer.getKoniec());
		//rozmer.getKoniec().vypis(rozmer.getKoniec().getStav());
		
 }
	/*
	 * metoda precita dany subor pre vstup
	 * meno suboru je v riadku Scanner s = new Scanner(new FileReader("input1.txt"));
	 */
	void citaj_Vstup() throws Exception{
		Scanner s = new Scanner(new FileReader("input.txt"));
		int cislo;
		Noda zaciatok_n = new Noda();
		int i,j;
		rozmer.setX(s.nextInt());
		rozmer.setY(s.nextInt());
		int [][] zaciatok = new int[rozmer.getY()][rozmer.getX()];
		for(i=0;i<rozmer.getY();i++){
			for(j=0;j<rozmer.getX();j++){
				cislo = s.nextInt();
				if(cislo == 0)
					zaciatok_n.setPoz_X(i*rozmer.getX()+j+1);
				zaciatok[i][j]=cislo;
			}
		}
		zaciatok_n.setStav(zaciatok);
		rozmer.setZaciatok(zaciatok_n);
		Noda koniec_n = new Noda();
		int [][] koniec = new int[rozmer.getY()][rozmer.getX()];
		for(i=0;i<rozmer.getY();i++){
			for(j=0;j<rozmer.getX();j++){
				cislo = s.nextInt();
				if(cislo == 0)
					koniec_n.setPoz_X(i*rozmer.getX()+j+1);
				koniec[i][j]=cislo;
			}
		}
		koniec_n.setStav(koniec);
		rozmer.setKoniec(koniec_n);
		s.close();
		System.out.println("Zaciatok je ;");
		koniec_n.vypis(zaciatok);
		System.out.println("Koniec je ;");
		koniec_n.vypis(koniec);
	}
	/*
	 * metoda spusti riesenie hlavolamu
	 * vytvori 5 vlakien ktore budu hladat do hlbky kazde po 1000 krokoch
	 * ak nenajdu cestu tak sa vytvori dalsie poschodie hladania do sirky,  tym sa rozsiri pocet cielov
	 * ked prve vlakno najde cestu tak zablokuje riesenie pre ostatne
	 * nakoniec sa vypise stav bodu spojenia a vysledna cesta
	 */
	void vyries(){
		rozmer.setVyriesene(false);
		hladaniedosirky = new Breadth_Search();
		hladaniedosirky.zacni(rozmer.getKoniec());
		int pocet_poschodi = 2;
		while(rozmer.isVyriesene() == false){
			hladaniedosirky.rozsir();
			pocet_poschodi++;
			rozmer.setPocet_f(0);
			CountDownLatch latch = new CountDownLatch(5);
			for (int i = 0; i < 5; i++) {
				Noda kopia = rozmer.getZaciatok().vytvor_Kopiu(rozmer.getZaciatok());
				new Thread (new Depth_Search(kopia,latch)).start();
			}
			try {
				latch.await();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			}
		ArrayList<Character> koniec = new ArrayList<Character>();
		koniec = hladaniedosirky.vydajKroky(rozmer.getHash_spoja());
		char znak= 'E';
		rozmer.setCesta_koniec(new ArrayList<Character>());
		for (int i = koniec.size()-1; i >= 0; i--) {
			switch (koniec.get(i)){
			case 'P': 
				znak = 'L';
				break;
			case 'L':
				znak = 'P';
				break;
			case 'H':
				znak = 'D';
				break;
			case 'D':
				znak = 'H';
				break;	
			}		
			rozmer.getCesta_koniec().add(znak);
		}
		System.out.println("Vypocet trval : "+(rozmer.getEndtime() - rozmer.getStarttime()) + " ns");
		System.out.println("Pocet poschodi v prehladavani do sirky"+pocet_poschodi);
		System.out.println("Cesta od zaciatku ku koncu je : ");
		for (int i = 0; i < rozmer.getCesta_zaciatok().size(); i++) {
			System.out.print(rozmer.getCesta_zaciatok().get(i)+" ");
		}
		for (int i = 0; i < rozmer.getCesta_koniec().size(); i++) {
			System.out.print(rozmer.getCesta_koniec().get(i)+" ");
		}
	}

}
