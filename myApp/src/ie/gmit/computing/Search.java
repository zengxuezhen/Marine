package ie.gmit.computing;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;













import ie.gmit.computing.NodeResource;
import ie.gmit.computing.R;
import ie.gmit.computing.TreeListView;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.RelativeLayout;
/**
 * To display the listview with data
 * @author Alex.Zeng
 *
 */
public class Search extends Activity{
	private TreeListView listView;

	List <NodeResource> list;
    public void onCreate(Bundle savedInstanceState) {
    	Log.i("Search oncreate", "create");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        RelativeLayout rl = new RelativeLayout(this);
        rl.setLayoutParams(new LayoutParams(RelativeLayout.LayoutParams.FILL_PARENT,RelativeLayout.LayoutParams.FILL_PARENT));
        listView = new TreeListView(this,initNodeTree());
        rl.addView(listView);
        
        
        	
       
        setContentView(rl);
        
        listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				((TreeAdapter) parent.getAdapter()).ExpandOrCollapse(position);
				if(((Node)TreeListView.ta.getItem(position)).isLeaf()){
					Intent intent=new Intent(Search.this,save.class);
					intent.putExtra("gps",getIntent().getStringExtra("gps"));
					intent.putExtra("bitmap", getIntent().getParcelableExtra("bitmap") );
					startActivity(intent);
					
					
        }
				
			}
		});
    }

    /**
     * initializing the nodes
     * @return
     */
    public List<NodeResource> initNodeTree(){
    	list = new ArrayList<NodeResource>();
    	List<String> list2=Node.getInstance().getChildrensName();
    	Log.i("enter init", "enter init");
    	for(int i=0;i<list2.size();i++){
    		String nodeName=list2.get(i);
    		NodeResource nodeResource=Add.maps.get(nodeName);
    		if(nodeResource!=null){
    			list.add(nodeResource);
    		}
    		
    	}

    	Log.i("enter list",list.toString());
    	return list;   
    }
    
    @Override
    protected void onDestroy() {
    	// TODO Auto-generated method stub
    	super.onDestroy();
    	Log.i("Search finished", "finished");
    	listView=null;
      	
    	list=null;
    }
    
    @Override
    protected void onPause() {
    	// TODO Auto-generated method stub
    	super.onPause();
    	Log.i("Search paused", "paused");
    	
    	list=null;
    	listView=null;
    }


}
