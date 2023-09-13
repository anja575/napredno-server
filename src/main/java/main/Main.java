package main;

import java.io.FileWriter;
import java.util.ArrayList;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import domen.Skola;
import so.skola.UcitajSkoleSO;

public class Main {
	
	public static void main(String[] args) {
		
		try(FileWriter file = new FileWriter("skole.json")){
			ArrayList<Skola> skole = new ArrayList<>();
			
			UcitajSkoleSO so = new UcitajSkoleSO();
			so.izvrsavanje(new Skola());
			
			skole = so.getLista();
			
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			String json = gson.toJson(skole);
			
			file.write(json);
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}

}
