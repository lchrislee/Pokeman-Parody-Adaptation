package dataStore;


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Vector;

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
        this("10.120.122.16", 27017);
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
		db.getPokemon();
    	
		
    }
    
    public Vector<Pokemon> getPokemon(){
    	
    	DBCursor c = PokemanCollection.find();
    	Vector<DBObject> results = new Vector<DBObject>();
    	
    	while(c.hasNext()){
    		results.add(c.next());
    	}
    	
    	return convertData(results);
    	
    }

	public Vector<Pokemon> convertData(Vector<DBObject> results) {
		Vector<Pokemon> pokemon = new Vector<Pokemon>();
		
		for(int i = 0; i < results.size(); i++){
			BasicDBObject obj = (BasicDBObject) results.get(i);
			String n = obj.getString("Name");
			//System.out.println(n);
			int d = Integer.parseInt(obj.getString("Level"));
			int a = obj.getInt("Attack");
			int s = obj.getInt("Speed");
			int mh = obj.getInt("HP");
			int r = obj.getInt("Rarity");
			int l = obj.getInt("Level");
			System.out.println(n + a + s + mh + r + l);
			
			//DatamonStats ds = (DatamonStats) obj.get("datamonStats");
			//int d = ds.getDefense();
			//System.out.println(n + d+a);
			
			
			
			
		}
		
		return null;
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