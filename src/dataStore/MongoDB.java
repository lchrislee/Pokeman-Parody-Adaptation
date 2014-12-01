package dataStore;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Vector;

import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;

public class MongoDB {
	
	private final static String POKEMAN_DB_NAME = "Pokeman";
	private final static String POKEMAN_DB_COLLECTION = "allPokeman";

	private static MongoClient m ;
 	private static DB PokemanDB;
 	private static DBCollection PokemanCollection;
 	
    
    public MongoDB() throws UnknownHostException{
        this("68.181.36.24", 27017);
    }
    
    public MongoDB(String ip,int port) throws UnknownHostException{
        m = new MongoClient();
        PokemanDB = m.getDB(POKEMAN_DB_NAME);
        PokemanCollection = PokemanDB.getCollection(POKEMAN_DB_COLLECTION);
       
    }

    /*public static void main(String[] args) throws Exception{

		MongoDB db = new MongoDB();
		//PokemanCollection.remove(new BasicDBObject());
		//db.addAllCards();
		DBCursor curse = PokemanCollection.find();
		while(curse.hasNext()){
			System.out.println(curse.next());
		}
		//PokemanCollection.remove(new BasicDBObject());
		db.getPokemon();
    	
		
    }*/
    
    public HashMap<String, ArrayList<Pokemon>> getPokemon(){
    	
    	System.out.println("here");
    	
    	DBCursor c = PokemanCollection.find();
    	//Vector<DBObject> results = new Vector<DBObject>();
		ArrayList<Pokemon> pokemonList = new ArrayList<Pokemon>();
		int k = 0;
		
    	while(c.hasNext()){
    		//results.add(c.next());
    		DBObject d = c.next();
    		BasicDBList l = (BasicDBList) d.get("moves");
    		System.out.println(k);
    		pokemonList.add(convertData(d, l));
    		k++;
    		
    	}
    	
    	pokemonList.sort(new PokemonNameCompare());
    	
    	HashMap<String, ArrayList<Pokemon>> pokemonMap = new HashMap<String, ArrayList<Pokemon>>();
    	ArrayList<Pokemon> current = new ArrayList<Pokemon>();
    	for (Pokemon p : pokemonList){
    		if (current.isEmpty()){
    			current.add(p);
    		}else{
    			if (p.getName().equals(current.get(0).getName()))
    				current.add(p);
    			else{
    				pokemonMap.put(current.get(0).getName(), current);
    				current = new ArrayList<Pokemon>();
    				current.add(p);
    			}
    		}
    	}
    	pokemonMap.put(current.get(0).getName(), current);
    	
    	return pokemonMap;
    	
    }

	private Pokemon convertData(DBObject Next, BasicDBList list) {
		BasicDBObject next = (BasicDBObject) Next;
		
		String n = next.getString("Name");
		//System.out.println(n);
		int d = Integer.parseInt(next.getString("Defense"));
		int a = next.getInt("Attack");
		int s = next.getInt("Speed");
		int mh = next.getInt("HP");
		//int r = next.getInt("Rarity");
		int l = next.getInt("Level");
		System.out.println(n + a + s + mh + l);
		//BasicDBList m = next.get("moves");
		
		
		Pokemon p = new Pokemon();
		p.setName(n);
		p.setAttack(a);
		p.setDefense(d);
		p.setSpeed(s);
		p.setMaxHealth(mh);
		p.setLevel(l);
		//p.setRarity(r);
		
		Vector<Move> mlist = new Vector<Move>();
		for(int i = 0; i < list.size(); i++){
			BasicDBObject b = (BasicDBObject) list.get(i);
			String na = b.getString("name");
			int da = b.getInt("damage");
			
			Move m = new Move();
			m.setName(na);
			m.setDamage(da);
			
			
			//Move q = new Move();
			
			mlist.add(m);
			System.out.println(m.getName());
		}
		
		p.setMoveList(mlist);
		
		return p;
	}

	/*public Vector<Pokemon> convertData(Vector<DBObject> results) {
		//Vector<Pokemon> pokemon = new Vector<Pokemon>();
		
		for(int i = 0; i < results.size(); i++){
			BasicDBObject obj = (BasicDBObject) results.get(i);
			String n = obj.getString("Name");
			//System.out.println(n);
			int d = Integer.parseInt(obj.getString("Defense"));
			int a = obj.getInt("Attack");
			int s = obj.getInt("Speed");
			int mh = obj.getInt("HP");
			int r = obj.getInt("Rarity");
			int l = obj.getInt("Level");
			System.out.println(n + a + s + mh + r + l);
			//BasicDBList m = obj.get("moves");
			
			
			Pokemon p = new Pokemon();
			p.setName(n);
			p.setAttack(a);
			p.setDefense(d);
			p.setSpeed(s);
			p.setMaxHealth(mh);
			p.setLevel(l);
			p.setRarity(r);
			//pokemon.add(p);
			//ObjectID id = obj.get("moves");
			//DatamonStats ds = (DatamonStats) obj.get("datamonStats");
			//int d = ds.getDefense();
			//System.out.println(n + d+a);
			
			
			
			
		}
		
		return null;
	}*/

/*	private void addAllCards() throws FileNotFoundException {
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
		
	}*/
	private class PokemonNameCompare implements Comparator<Pokemon>{

		@Override
		public int compare(Pokemon o1, Pokemon o2) {
			return o1.getName().compareTo(o2.getName());
		}
		
	}
}
