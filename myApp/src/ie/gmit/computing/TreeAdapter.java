package ie.gmit.computing;
import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * The adapter is to back up the data for listview
 * @author Alex.Zeng
 *
 */
public class TreeAdapter extends BaseAdapter{
	private Context con;
    private LayoutInflater lif;
    private List<Node> all = new ArrayList<Node>();//չʾ
    private List<Node> cache = new ArrayList<Node>();//����
    private TreeAdapter tree = this;
    boolean hasCheckBox;
    private int expandIcon = -1;//չ��ͼ��
    private int collapseIcon = -1;//����ͼ��
    /**
	 * Constructor
	 */
	public TreeAdapter(Context context,List<Node>rootNodes){
		this.con = context;
		this.lif = (LayoutInflater)con.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		for(int i=0;i<rootNodes.size();i++){
			addNode(rootNodes.get(i));
		}
	}
	/**
	 * add the given node to listview
	 * @param node
	 *
	 */
	public void addNode(Node node){
		all.add(node);
		cache.add(node);
		if(node.isLeaf())return;
		for(int i = 0;i<node.getChildrens().size();i++){
			addNode(node.getChildrens().get(i));
		}
	}
	/**
	 * set icon
	 * @param expandIcon
	 * @param collapseIcon
	 *
	 */
	public void setCollapseAndExpandIcon(int expandIcon,int collapseIcon){
		this.collapseIcon = collapseIcon;
		this.expandIcon = expandIcon;
	}
	/**
	 * set the state of checkbox
	 * 
	 *
	 */
	public void checkNode(Node n,boolean isChecked){
		checkParentNode(n, isChecked);
		for(int i =0 ;i<n.getChildrens().size();i++){
			checkNode((Node)n.getChildrens().get(i), isChecked);
		}
	}
	
	public void checkParentNode(Node n, boolean isChecked){
		n.setChecked(isChecked);
		Node parent = n.getParent();
		if (parent != null) {
			if(isChecked){
				checkParentNode(parent, true);
			}else{
				boolean checked = false;
				for (int i = 0; i < parent.getChildrens().size(); i++) {
					Node child = parent.getChildrens().get(i);
					if(child.isChecked()) {
						checked = true;
						break;
					}
				}
				checkParentNode(parent, checked);
			}
		} 
	}
	
	/**
	 *get all selected nodes
	 * @return
	 *
	 */
	public List<Node>getSelectedNode(){
		List<Node>checks =new ArrayList<Node>()	;
		for(int i = 0;i<cache.size();i++){
			Node n =(Node)cache.get(i);
			if(n.isChecked())
				checks.add(n);
		}
		return checks;
	}
	/**
	 * if has checkbox
	 * @param hasCheckBox
	 *
	 */
	public void setCheckBox(boolean hasCheckBox){
		this.hasCheckBox = hasCheckBox;
	}
	/**
	 * change the state of each node
	 * @param location
	 *
	 */
	public void ExpandOrCollapse(int location){
		Node n = all.get(location);//��õ�ǰ��ͼ��Ҫ����Ľڵ� 
		if(n!=null)//�ų�������������쳣
		{
			if(!n.isLeaf()){
				n.setExplaned(!n.isExplaned());// ���ڸ÷�������������չ���������ģ�����ȡ������
				filterNode();//����һ�£��������ϼ��ڵ�չ���Ľڵ����¹���ȥ
				this.notifyDataSetChanged();//ˢ����ͼ
			}
		}
		
	}
	/**
	 * set the layer
	 * @param level
	 *
	 */
	public void setExpandLevel(int level){
		all.clear();
		for(int i = 0;i<cache.size();i++){
			Node n = cache.get(i);
			if(n.getLevel()<=level){
			 if(n.getLevel()<level)
				 n.setExplaned(true);
			 else
				 n.setExplaned(false);
			 all.add(n);
			}
		}
		
	}
	
	public void filterNode(){
		all.clear();
		for(int i = 0;i<cache.size();i++){
			Node n = cache.get(i);
			if(!n.isParentCollapsed()||n.isRoot())//���Ǹ��ڵ㲻�������߲��Ǹ��ڵ�Ķ�����ȥ
				all.add(n);
		}
	}
	/* (non-Javadoc)
	 * @see android.widget.Adapter#getCount()
	 */
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return all.size();
	}

	/* (non-Javadoc)
	 * @see android.widget.Adapter#getItem(int)
	 */
	@Override
	public Object getItem(int location) {
		// TODO Auto-generated method stub
		return all.get(location);
	}

	/* (non-Javadoc)
	 * @see android.widget.Adapter#getItemId(int)
	 */
	@Override
	public long getItemId(int location) {
		// TODO Auto-generated method stub
		return location;
	}

	/* (non-Javadoc)
	 * @see android.widget.Adapter#getView(int, android.view.View, android.view.ViewGroup)
	 */
	@Override
	public View getView(int location, View view, ViewGroup viewgroup) {

		ViewItem vi = null;
		if(view == null){
			view = lif.inflate(R.layout.list_item, null);
			vi = new ViewItem();
			vi.cb = (CheckBox)view.findViewById(R.id.cb);
			vi.flagIcon = (ImageView)view.findViewById(R.id.ivec);
			vi.tv = (TextView)view.findViewById(R.id.itemvalue);
			vi.icon =(ImageView)view.findViewById(R.id.ivicon);
			vi.cb.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
				
					Node n = (Node)v.getTag();
					checkNode(n, ((CheckBox)v).isChecked());
					tree.notifyDataSetChanged();
					
					
				}
			});
			view.setTag(vi);
		}
		else{
			vi = (ViewItem)view.getTag();
			if(vi ==null)
				System.out.println();
		}
		Node n = all.get(location);
		if(n!=null){
			if(vi==null||vi.cb==null)
				System.out.println();    
			vi.cb.setTag(n);  
			vi.cb.setChecked(n.isChecked());
			//Ҷ�ڵ㲻��ʾչ������ͼ��
			if(n.isLeaf()){
				vi.flagIcon.setVisibility(View.GONE);
			}
			else{
				vi.flagIcon.setVisibility(View.VISIBLE);
				if(n.isExplaned()){
					if(expandIcon!=-1){
						vi.flagIcon.setImageResource(expandIcon);
					}
				}
				else{
					if(collapseIcon!=-1){
						vi.flagIcon.setImageResource(collapseIcon);
					}
				}
			}
			//�����Ƿ���ʾ��ѡ��
			if(n.hasCheckBox()&&n.hasCheckBox()){
				vi.cb.setVisibility(View.VISIBLE);
			}
			else{
				vi.cb.setVisibility(View.GONE);
			}
			//�����Ƿ���ʾͷ��ͼ��
			if(n.getIcon()!=-1){
				vi.icon.setImageResource(n.getIcon());
				vi.icon.setVisibility(View.VISIBLE);
			}
			else{
				vi.icon.setVisibility(View.GONE);
			}
			//��ʾ�ı�
			vi.tv.setText(n.getTitle());
			// ��������
			view.setPadding(30*n.getLevel(), 3,3, 3);
		}
		return view;
	}
	
	/**
	 * This is an entity class
	 * @author Alex.Zeng
	 *
	 */
    public class ViewItem{
    	private CheckBox cb;
    	private ImageView icon;
    	private ImageView flagIcon;
    	private TextView tv;
    }
}
