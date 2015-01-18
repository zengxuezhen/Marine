package ie.gmit.computing;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;





import ie.gmit.computing.NodeResource;
import ie.gmit.computing.R;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

/**
 * This is class is to add relevant nodes in the list
 * @author Alex.Zeng
 *
 */
public class Add extends Activity {

	 Button add;
	Spinner spinner;
	ArrayAdapter<String> datas;
	List<String> list=new ArrayList<String>();
	EditText nodeName;
	String parentname="";
	Model model;
	ArrayList<Node> nodes=new ArrayList<Node>();
	
	static HashMap<String, NodeResource> maps=new HashMap<String, NodeResource>();
	NodeResource n1;
	//static HashMap<Integer,ArrayList<Node>> maps=new HashMap<Integer,ArrayList<Node>>();
	
	Search search=new Search();
	public static int count=0;// counts number
	
	static Node parentNode;
	static Node node;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        model=new Model();
       
      Log.i("model","has model");
        spinner=(Spinner)findViewById(R.id.spinner);
        add=(Button)findViewById(R.id.add);
        nodeName=(EditText)findViewById(R.id.nodeName);
        nodeName.setText("");
        
        datas=new ArrayAdapter<String>(getApplicationContext(),R.layout.myspinner,Node.getInstance().getChildrensName());
       
        spinner.setAdapter(datas);
      
      spinner.setOnItemSelectedListener(new OnItemSelectedListener() {

		

		@Override
		public void onNothingSelected(AdapterView<?> parent) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onItemSelected(AdapterView<?> parent, View view,
				int position, long id) {
			// TODO Auto-generated method stub
			parentname=(String)datas.getItem(position);
		}
	});
      
      
      add.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			if(nodeName.getText().toString().equals("")){
				Toast.makeText(Add.this, "Can not be null", Toast.LENGTH_SHORT).show();
				return;
			}
			if(!nodeName.getText().toString().equals("")){
				
				parentNode=Node.map.get(parentname);
				Log.i("parentnode", parentNode.toString());
			    node=new Node(nodeName.getText().toString(),parentNode);
			    node.setParent(parentNode);
				parentNode.addNode(node);
				
				
			    n1=new NodeResource(parentname, node.getTitle()," "+node.getTitle(), "dfs", R.drawable.icon_department);
				
				maps.put(node.getTitle(), n1);// put a different key with a different value in a same map
				
				nodes.add(parentNode);
				nodes.add(node);
				Node.getInstance().setChildrensName(node.getTitle());
				
				if(Node.getInstance().getChildrensName().contains(node.getTitle()))
				Toast.makeText(Add.this, "Added", Toast.LENGTH_SHORT).show();

				//maps.put(count, nodes);// count-->list
				
				//Model.lists.put(count,maps);
				
			//	Log.i("count1", ""+Add.count);
			
		   Model2.initNodeTree();
			
			//count++;
			}
		}
	});
    }
   

  

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
