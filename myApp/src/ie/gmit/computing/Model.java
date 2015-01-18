package ie.gmit.computing;

import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import android.util.Log;

/**
 * The model is to initialize the root node 
 * @author Alex.Zeng
 *
 */
public class Model {
   public static Map<Integer, HashMap<Integer,ArrayList<Node>>> lists=new HashMap<Integer, HashMap<Integer,ArrayList<Node>>>();
   
	public Model(){
		Node start=new Node("Start", null);
		
		if(!Node.getInstance().getChildrensName().contains("Start"))
			Node.getInstance().getChildrensName().add(start.getTitle());
		
		Node.map.put("Start", start);
	}
	
	
}
