import java.util.ArrayList;
/*
 * Trieda opisuje konkretny stav v rieseni
 * 
 */
public class Noda extends Mozne_Stavy{
	private int [][]stav;
	private char pred_krok;
	private int  poz_X;
	private ArrayList<Character> kroky;
	public Noda(){
		
	}
	public Noda(int x,int y){
		this.stav = new int[y][x];
		
	}
	public Noda(int x, int y, char pred_k,int poz_XX,int[][] stav,ArrayList<Character> kroky){
		this.stav = new int[y][x];
		this.pred_krok = pred_k;
		this.poz_X = poz_XX;
		this.stav = stav;
		this.kroky = kroky;
	}
	public int[][] getStav() {
		return stav;
	}
	public void setStavPoz(int i, int j,int hodnota){
		this.stav[i][j] = hodnota;
	}
	public void setStav(int[][] stav) {
		this.stav = stav;
	}

	public char getPred_krok() {
		return pred_krok;
	}

	public void setPred_krok(char pred_krok) {
		this.pred_krok = pred_krok;
	}

	public int getPoz_X() {
		return poz_X;
	}

	public void setPoz_X(int poz_X) {
		this.poz_X = poz_X;
	}
	public Noda vytvor_Kopiu(Noda original){
		Rozmery rozmer = Rozmery.getInstance();
		Noda kopia = new Noda(rozmer.getX(),rozmer.getY(),original.getPred_krok(),original.getPoz_X(),new int[rozmer.getY()][rozmer.getX()],new ArrayList<Character>());
		for (int i = 0; i < rozmer.getY(); i++) {
			for (int j = 0; j < rozmer.getY(); j++) {
				kopia.setStavPoz(i, j, original.getStav()[i][j]);
			}
		}
		if(original.getKroky() != null)
		for (int i = 0; i < original.getKroky().size(); i++) {
			char znak = original.getKroky().get(i);
			kopia.getKroky().add(znak);
		}
		kopia.setPred_krok(original.getPred_krok());
		return kopia;
		
	}
	public ArrayList<Character> getKroky() {
		return kroky;
	}
	public void setKroky(ArrayList<Character> kroky) {
		this.kroky = kroky;
	}
}
