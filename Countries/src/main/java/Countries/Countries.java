package Countries;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Countries {

	String folder = "src//main//resources//";
	String ext = ".csv";
	String path = "";
	String[][] countries = new String [100][2];
	String[][] set = new String[5][2];
	int size = 0;
	String correctCountry = null;
	String correctCapital = null;
	List<String> opts = null;
	
	
	Countries(Continents continent){
		String splitted[] = null;
		
		path = folder + continent.toString() + ext;
		File file = new File(path);
		FileReader reader;
		try {
			reader = new FileReader(file);
		
			Scanner odczyt = new Scanner(reader);
			
			int i = 0;
			while(odczyt.hasNext()){
				
	//				countries[i][0] = 1 ;
				splitted = odczyt.nextLine().split(";");
				
				countries[i][0]= splitted[0].toString();
				countries[i][1]= splitted[1].toString();
				i++;
			}
			for(int x = 0; x<100; x++){
				if(countries[x][0] == null){
					this.size = x - 1;
					break;
				}
			}
		}catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public List<String> createSet(){
		int v1 = 0;
		int v2 = 0;
		int v3 = 0;
		int v4 = 0;
		int v5 = 0;
		
		
		Random rand = new Random();
		v1 = rand.nextInt(this.size);
		boolean r = true;
		while(r){
			v2 = rand.nextInt(this.size);
			if(v2 != v1){r = false;}
		};
		r = true;
		while(r){
			v3 = rand.nextInt(this.size);
			if(v3 != v2 && v3 != v1){r = false;}
		};
		r = true;
		while(r){
			v4 = rand.nextInt(this.size);
			if(v4 != v3 && v4 != v2 && v4 != v1){r = false;}
		};
		r = true;
		while(r){
			v5 = rand.nextInt(this.size);
			if(v5 != v4 && v5 != v3 && v5 != v2 && v5 != v1){r = false;}
		};
		
		this.correctCountry = countries[v1][0];
		this.correctCapital = countries[v1][1];
		
		opts = new ArrayList();
		opts.add(countries[v1][1]);
		opts.add(countries[v2][1]);
		opts.add(countries[v3][1]);
		opts.add(countries[v4][1]);
		opts.add(countries[v5][1]);
		
		long seed = System.nanoTime();
		Collections.shuffle(opts, new Random(seed));
		
		
//		System.out.println(countries[v1][0] + " : " + countries[v1][1]);
//		System.out.println(countries[v2][0] + " : " + countries[v2][1]);
//		System.out.println(countries[v3][0] + " : " + countries[v3][1]);
//		System.out.println(countries[v4][0] + " : " + countries[v4][1]);
//		System.out.println(countries[v5][0] + " : " + countries[v5][1]);
//
//		System.out.println(opts);
//		
		return opts;
//			System.out.println(countries[i][0] + " : " + countries[i][1]);	
		
	}
	
	public String getCorrectCountry(){
		System.out.println(correctCapital);
		return this.correctCountry;
	}
	
	public String getCorrectCapita(){
		return this.correctCapital;
	}
	
}

