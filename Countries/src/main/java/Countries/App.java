package Countries;

public class App {

	public static void main(String[] args) {

		Countries countries = new Countries(Continents.EUROPE);
		
		
		System.out.println("Podaj stolic� kraju:");
		System.out.println(countries.createSet());
		
		System.out.println(countries.getCorrectCountry());
		System.out.println(countries.correctCountry);
		System.out.println(countries.correctCapital);
		
		
		
	}

}
