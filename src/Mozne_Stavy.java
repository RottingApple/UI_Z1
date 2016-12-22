import java.util.ArrayList;
/*
 * Aplikacna trieda, vlastni viacero metod ktore pracuju s  Nodami
 */
public class Mozne_Stavy {
	private Rozmery rozmer = Rozmery.getInstance();
	
	
	 ArrayList<Character> zisti_moznosti(Noda noda){
		 int [][] povodny_stav = noda.getStav();
		 ArrayList<Character> moznosti= new ArrayList<Character>();
		 int pozicia_m = noda.getPoz_X();
		// System.out.println("Medzera je na :"+noda.getPoz_X());
		 int x = rozmer.getX();
		 int y = rozmer.getY();
		 if((pozicia_m % x) != 1){
			 moznosti.add('L');
		//	System.out.println("V lavo mozme tahat");  
		 }
		 if((pozicia_m % x) != 0){
			 moznosti.add('P');
		//	 System.out.println("V pravo mozme tahat");
		 }
		 if((pozicia_m - x) > 0){
			 moznosti.add('H');
		//	 System.out.println("Hore mozme tahat");
		 }
		 if((pozicia_m + x) <= x*y){
			 moznosti.add('D');
		//	 System.out.println("Dole mozme tahat");
		 }
		return moznosti;	
	}
	 
	void vypis(int [][] stav){
		int i,j;
		Rozmery rozmer = Rozmery.getInstance();
		for(i=0;i<rozmer.getY();i++){
			for(j=0;j<rozmer.getX();j++){
				System.out.print(stav[i][j]+", ");
			}
			System.out.println();
		}
		System.out.println();

	}
	int zvol_Cislo(int pocet){
		
		return 1 + (int)(Math.random() * ((pocet - 1) + 1));
		
	}
	
	void zmen_Stav(Noda ciel,char krok){
		int hrncek;
		int x = rozmer.getX();
		int y = rozmer.getY();
		int poz_x = (ciel.getPoz_X()-1) % x;
		int poz_y = (ciel.getPoz_X()-1) / x;
		int[][] stav = ciel.getStav();
		if(krok == 'P'){
			hrncek = stav[poz_y][poz_x+1];
			stav[poz_y][poz_x +1] = 0;
			stav[poz_y][poz_x] = hrncek;
			ciel.setPoz_X(ciel.getPoz_X()+1);
		}
		if(krok == 'L'){
			hrncek = stav[poz_y][poz_x-1];
			stav[poz_y][poz_x -1] = 0;
			stav[poz_y][poz_x] = hrncek;
			ciel.setPoz_X(ciel.getPoz_X()-1);
		}
		if(krok == 'D'){
			hrncek = stav[poz_y+1][poz_x];
			stav[poz_y+1][poz_x ] = 0;
			stav[poz_y][poz_x ] = hrncek;
			ciel.setPoz_X(ciel.getPoz_X()+x);
		}
		if(krok == 'H'){
			hrncek = stav[poz_y-1][poz_x];
			stav[poz_y-1][poz_x ] = 0;
			stav[poz_y][poz_x ] = hrncek;
			ciel.setPoz_X(ciel.getPoz_X()-x);
		}
		
	}
	double spravHash(int[][] stav){
		double vysledok= 0;
		for (int i = 0; i < rozmer.getY(); i++) {
			for (int j = 0; j < rozmer.getX(); j++) {
				vysledok += stav[i][j];
				vysledok *=10;
			}
		}
		vysledok /= 10;
		//System.out.println("Vysledok je"+vysledok);
		return vysledok;
		
	}
}
