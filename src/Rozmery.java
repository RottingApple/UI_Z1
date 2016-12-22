import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.CountDownLatch;
/*
 * Datova trieda 
 * obsahuje globalne informacie ako napriklad stav zaciatku a konca alebo rozmery hlavolamu
 * aby nedoslo ku viacerym instanciam, tak je pouzity singleton
 */
public class Rozmery {
	
	static Rozmery instance = null;
	private int x;
	private int y;
	private Noda zaciatok;
	private Noda koniec;
	private HashMap<Double,Integer> terc;
	private ArrayList<Character> cesta_zaciatok;
	private ArrayList<Character> cesta_koniec;
	private int Depth=1000;
	private boolean vyriesene;
	private double hash_spoja;
	private long starttime;
	private long endtime;
	private int pocet_f;
	private final Object lock = new Object();
	private Rozmery(){
	}
	public static Rozmery getInstance(){
		if(instance == null){
			instance = new Rozmery();
			instance.terc = new HashMap<Double,Integer>();
		}
		return instance;
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public Noda getZaciatok() {
		return zaciatok;
	}
	public void setZaciatok(Noda zaciatok) {
		this.zaciatok = zaciatok;
	}
	public Noda getKoniec() {
		return koniec;
	}
	public void setKoniec(Noda koniec) {
		this.koniec = koniec;
	}
	public ArrayList<Character> getCesta_zaciatok() {
		return cesta_zaciatok;
	}
	public void setCesta_zaciatok(ArrayList<Character> cesta_zaciatok) {
		this.cesta_zaciatok = cesta_zaciatok;
	}
	public ArrayList<Character> getCesta_koniec() {
		return cesta_koniec;
	}
	public void setCesta_koniec(ArrayList<Character> cesta_koniec) {
		this.cesta_koniec = cesta_koniec;
	}
	public HashMap<Double, Integer> getTerc() {
		return terc;
	}
	public void setTerc(HashMap<Double, Integer> terc) {
		this.terc = terc;
	}
	public int getDepth() {
		return Depth;
	}
	public void setDepth(int depth) {
		Depth = depth;
	}
	public boolean isVyriesene() {
		return vyriesene;
	}
	public void setVyriesene(boolean vyriesene) {
		this.vyriesene = vyriesene;
	}
	public double getHash_spoja() {
		return hash_spoja;
	}
	public void setHash_spoja(double hash_spoja) {
		this.hash_spoja = hash_spoja;
	}
	public long getStarttime() {
		return starttime;
	}
	public void setStarttime(long starttime) {
		this.starttime = starttime;
	}
	public long getEndtime() {
		return endtime;
	}
	public void setEndtime(long endtime) {
		this.endtime = endtime;
	}
	public Object getLock() {
		return lock;
	}
	public int getPocet_f() {
		return pocet_f;
	}
	public void setPocet_f(int pocet_f) {
		this.pocet_f = pocet_f;
	}
}
