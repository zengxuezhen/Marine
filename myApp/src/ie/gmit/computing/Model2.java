package ie.gmit.computing;

import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;

import android.util.Log;

/**
 * This is another model for datas
 * @author Alex.Zeng
 *
 */
public  class Model2 {
	
	
	//接收输入节点名，创建新节点添加到父节点下，并添加到search activity的listview
    public static List<NodeResource> initNodeTree(){
    	 List <NodeResource> list=new ArrayList <NodeResource>();
    	//listt=getInstance();
    	Log.i("list",list.toString());
    	
    	NodeResource n1 = new NodeResource();
    	//Log.i("count2", ""+Add.count);
    	//HashMap<Integer,ArrayList<Node>> nodes=Model.lists.get(Add.count-1);
    	
    	
    	//Log.i("elements", ""+nodes);
//    	Log.i("elements", ""+nodes[0].getTitle()+" "+nodes[1].getTitle());
    	
    	//for(int i=0;i<Add.count;i++){
    		//ArrayList<Node> list=nodes.get(i);
    		
    		n1.setParentId(Add.node.getParentId());
 	        n1.setCurId(Add.node.getCurId());
 	        n1.setTitle(Add.node.getTitle());
 	        n1.setIconId(R.drawable.icon_department);
   	
//    	if(nodes.size()==2){
//    		Log.i("into 2", ""+nodes.size());
//    		 n1.setParentId(nodes.get(0).getCurId());
//    	        n1.setCurId(nodes.get(1).getCurId());
//    	        n1.setTitle(nodes.get(1).getTitle());
//    	        n1.setIconId(R.drawable.icon_department);
//    	}
       
        
    	list.add(n1);
    	
    	for(int i=0;i<list.size();i++){
    		Log.i("listt va.ue",list.get(i)+"");
    	}
    	return list;   
    }

//    public static ArrayList <NodeResource> getInstance(){
//    	if(list==null){
//    		list=new ArrayList <NodeResource>();
//    		return list;
//    	}
//    	return list;
//    }
}
