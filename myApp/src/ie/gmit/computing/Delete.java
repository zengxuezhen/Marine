package ie.gmit.computing;

import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

/**
 * This is class is to delete node
 * @author Alex.Zeng
 *
 */
public class Delete extends Activity {

	Spinner spinner;
	Button delete;
	ArrayAdapter<String> datas;
	String nodeName;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_delete);
		delete=(Button)findViewById(R.id.deleteNode);
		spinner=(Spinner)findViewById(R.id.parentNode);
		
		
		datas=new ArrayAdapter<String>(Delete.this, R.layout.myspinner,Node.getInstance().getChildrensName());
		
		spinner.setAdapter(datas);
		
		spinner.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				nodeName=(String)datas.getItem(position);
				
				
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub
				
			}
		});
		
      delete.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(nodeName!=""){
					Node node=Node.map.get(nodeName);
					Node parent=node.getParent();
					List<Node> children=node.getChildrens();
					
					for(int i=0;i<children.size();i++){
						Node n=children.get(i);
						n.setParent(parent);
						
						if(parent==null){
							return;
						}else {
							parent.addNode(n);
							Node.getInstance().getChildrensName().remove(n.getTitle());
							Node.map.remove(n);
						}
					}
					Log.i("info",node.toString());
					//Log.i("info",node.getParent().toString());
					//node.getParent().removeNode(node);
					Node.getInstance().getChildrensName().remove(node.getTitle());
					Node.map.remove(node);
				}
				
			}
		});
	}
	
	
}
