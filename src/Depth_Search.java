import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.CountDownLatch;
/*
 * Prehladavanie do hlbky
 * 
 */
public class Depth_Search extends Mozne_Stavy implements Runnable{
	private Noda cesta;
	private Rozmery rozmer = Rozmery.getInstance();
	private int pocet_utriedenych=0;
	private ArrayList<Character> kroky;
	private int i;
	private CountDownLatch latch;
	public Depth_Search(Noda inak,CountDownLatch latch) {
			this.cesta = inak;
			this.latch = latch;
	}
	void zvol_Cestu(){
		ArrayList<Character> moznosti;
		kroky = new ArrayList<Character>();
		int cislo;
		int pokus;
		for (i = 0; i < rozmer.getDepth(); i++) {
			moznosti = zisti_moznosti(cesta);
			cislo = zvol_Cislo(moznosti.size());
			pokus = 0;
			/*while(pokus < moznosti.size()){
				if(skontrolujRozhodnutie(moznosti.get(cislo-1))){
					pokus = 5;
				}
				else{
					if(cislo == 1){
						cislo = moznosti.size();
					}
					else
						cislo --;
				}
				pokus ++;
			}*/
			//System.out.println("Pocet utriedenych je "+pocet_utriedenych+ "Na pokus " + pokus);
			//vypis(cesta.getStav());
			zmen_Stav(cesta, moznosti.get(cislo-1));
			kroky.add(moznosti.get(cislo-1));
			//System.out.println("Stav po");
			//vypis(cesta.getStav());
			//uzsombol.put(cesta.getStav().hashCode(),1);
			//System.out.println("Kluc ktory mam"+cesta.spravHash(cesta.getStav()));
			if(rozmer.getTerc().containsKey(cesta.spravHash(cesta.getStav()))){
				System.out.println("Hura nasiel som riesenie s i = "+i);
				i = rozmer.getDepth()+10;
			}
			//vypis(zaciatok.getStav());
		}
		//rozmer.setPocet_f(rozmer.getPocet_f()+1);
		//vypis(cesta.getStav());
	}
	@Override
	public void run() {
		zvol_Cestu();
		skontrolujRiesenie();
		latch.countDown();
	}
	 synchronized void skontrolujRiesenie(){
		 if(i > rozmer.getDepth() && (rozmer.isVyriesene() == false)){
			 	rozmer.setEndtime(System.currentTimeMillis());
				rozmer.setVyriesene(true);
				rozmer.setCesta_zaciatok(kroky);
				rozmer.setHash_spoja(cesta.spravHash(cesta.getStav()));
			}
	}
	boolean skontrolujRozhodnutie(char krok){
		int usp_x = 0;
		int usp_y = 0;
		pocet_utriedenych = 0;
		while((usp_y < rozmer.getX()) &&(cesta.getStav()[usp_y][usp_x] == rozmer.getKoniec().getStav()[usp_y][usp_x])){
			pocet_utriedenych++;
			usp_x++;
			if(usp_x % rozmer.getX() == 0){
				usp_x = 0;
				usp_y ++;
			}
			//System.out.println("usp_x je "+usp_x+" usp_y je "+usp_y);
		}
		if(usp_x == 0){
			usp_y --;
			usp_x = rozmer.getX()-1;
		}
		else{
			usp_x --;
		}
		int pozicia = cesta.getPoz_X();
		if(krok == 'P'){
			pozicia++;
		}
		if(krok == 'L'){
			pozicia--;
		}
		if(krok == 'D'){
			pozicia+= rozmer.getX();
		}
		if(krok == 'H'){
			pozicia-= rozmer.getX();
		}
		//System.out.println("Pozicia je "+pozicia);
		if((pozicia <= pocet_utriedenych) && (rozmer.getKoniec().getStav()[(pozicia -1) / rozmer.getX()][(pozicia -1) % rozmer.getX()] == cesta.getStav()[(pozicia -1) / rozmer.getX()][(pozicia -1) % rozmer.getX()]) ){
			return false;
		}
		return true;
		
	}
}
