import java.util.ArrayList;
/*
 * Prehladavanie do sirky
 * zoznam cielov je spajany zoznam stavov na konkretnom poschodi aby sa setrila pamat
 * stavy su transformovane do hash kluca v podobe floatu a ulozene do hashmapy
 */
public class Breadth_Search extends Mozne_Stavy{
	private ArrayList<Noda> zoznam_cielov;
	private ArrayList<Character> moznosti;
	private ArrayList<Noda> temp;
	private Rozmery rozmer = Rozmery.getInstance();
	/*
	 * inicializacia zaciatocneho stavu a spajaneho zoznamu cielov 
	 */
	void zacni(Noda zaciatok){
		moznosti = zisti_moznosti(zaciatok);
		zoznam_cielov = new ArrayList<Noda>();
		for (int i = 0; i < moznosti.size(); i++) {
			Noda nova = new Noda();
			nova = zaciatok.vytvor_Kopiu(zaciatok);
			nova.setKroky(new ArrayList<Character>());
			nova.zmen_Stav(nova, moznosti.get(i));
			nova.setPred_krok(moznosti.get(i));
			rozmer.getTerc().put(nova.spravHash(nova.getStav()), 1);
			nova.getKroky().add(moznosti.get(i));
			zoznam_cielov.add(nova);
		}
	}
	/*
	 * rozirenie hladania sirky o jedno poschodie
	 * 
	 */
	void rozsir(){
		Noda novacik;
		ArrayList<Noda> temp = new ArrayList<Noda>();
		for (int i = 0; i < zoznam_cielov.size(); i++) {
			moznosti = zisti_moznosti(zoznam_cielov.get(i));
			//System.out.println("Idem robit tento stav ma tolkoto moznosti"+moznosti.size());
			//vypis(zoznam_cielov.get(i).getStav());
			//System.out.println("Posledny krok je "+zoznam_cielov.get(i).getPred_krok());
			for (int j = 0; j < moznosti.size(); j++) {
				if(neopakujuSa(moznosti.get(j),zoznam_cielov.get(i).getPred_krok())){
					novacik = zoznam_cielov.get(i).vytvor_Kopiu(zoznam_cielov.get(i));
					novacik.zmen_Stav(novacik, moznosti.get(j));
					novacik.getKroky().add(moznosti.get(j));
					novacik.setPred_krok(moznosti.get(j));
					rozmer.getTerc().put(novacik.spravHash(novacik.getStav()), 1);
					temp.add(novacik);
					//vypis(novacik.getStav());
				}
			}
		}
		zoznam_cielov = temp;
	}
	private boolean neopakujuSa(Character character, Character predosly) {
		if(character == 'P' && predosly == 'L')
		return false;
		if(character == 'L' && predosly == 'P')
		return false;
		if(character == 'H' && predosly == 'D')
		return false;
		if(character == 'D' && predosly == 'H')
		return false;
		
		return true;
	}
	ArrayList<Character> vydajKroky(double hash){
		for (int i = 0; i < zoznam_cielov.size(); i++) {
			if(zoznam_cielov.get(i).spravHash(zoznam_cielov.get(i).getStav()) == hash){
				System.out.println("Bod spojenia je : ");
				zoznam_cielov.get(i).vypis(zoznam_cielov.get(i).getStav());
				return zoznam_cielov.get(i).getKroky();
			}
		}
		return null;
	}
	
	public ArrayList<Noda> getZoznam_cielov() {
		return zoznam_cielov;
	}
	public void setZoznam_cielov(ArrayList<Noda> zoznam_cielov) {
		this.zoznam_cielov = zoznam_cielov;
	}
}
