package dataStore;


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.net.UnknownHostException;
import java.util.ArrayList;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mongodb.*;
import com.mongodb.util.JSON;

public class MongoDB {
	
	private final static String POKEMAN_DB_NAME = "Pokeman";
	private final static String POKEMAN_DB_COLLECTION = "allPokeman";

	private static MongoClient m ;
 	private static DB PokemanDB;
 	private static DBCollection PokemanCollection;
 	
    
    public MongoDB() throws UnknownHostException{
        this("YOUR IP HERE", 27017);
    }
    
    public MongoDB(String ip,int port) throws UnknownHostException{
        m = new MongoClient(ip, port);
        PokemanDB = m.getDB(POKEMAN_DB_NAME);
        PokemanCollection = PokemanDB.getCollection(POKEMAN_DB_COLLECTION);
       
    }

    public static void main(String[] args) throws Exception{

		MongoDB db = new MongoDB();
		//PokemanCollection.remove(new BasicDBObject());
		//db.addAllCards();
		DBCursor curse = PokemanCollection.find();
		while(curse.hasNext()){
			System.out.println(curse.next());
		}
		//PokemanCollection.remove(new BasicDBObject());
		
    	
		
    }

	private void addAllCards() throws FileNotFoundException {
		Gson gson = new Gson();
		BufferedReader br = new BufferedReader(new FileReader("pokemonStats.json"));
		ArrayList<Datamon> all = gson.fromJson(br, new TypeToken<ArrayList<Datamon>>() {}.getType());
		
		for(int i = 0; i < all.size(); i++){
			String obj = gson.toJson(all.get(i));
			DBObject dbObj = (DBObject) JSON.parse(obj);
			PokemanCollection.insert(dbObj);
			System.out.println(i + "");
		}
		
		//String obj = gson.fromJson();
		
	}
   
}