package ie.gmit.computing;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;






/**
 * This is entity class
 * @author Alex.Zeng
 *
 */
public class Node implements Serializable{
	private Node parent; 
	private List<Node> childrens = new ArrayList<Node>();
	public static HashMap<String, Node> map=new HashMap<String,Node>();
	private  List<String> childrensName = new ArrayList<String>();
	
	private int icon = -1; 
	private boolean isChecked = false; 
	private boolean isExpand = true;
	private boolean hasCheckBox = true;
	
	private boolean isVisiable = true;

	protected String parentId;
	protected String title;
	protected String value;
	protected int iconId;
	protected String curId;
	public static int count=0;
	
	public static Node self;
	
		/**
		 * Constructor
		 * 
		 * @param parentId
		 *            TODO
		 * @param curId
		 *            TODO
		 */
	public Node(String title,String value, String parentId,String curId, int iconId) {
		// TODO Auto-generated constructor stub
		this.title = title;
		this.value = value;
		this.parentId = parentId;
		this.icon = iconId;
		this.curId = curId;
	}
	
//	public Node(String parentId, String curId, String title,
//			String value, int iconId) {
//		super();
//		this.parentId = parentId;
//		this.title = title;
//		this.value = value;
//		this.iconId = iconId;
//		this.curId = curId;
//	}
	/**
	 * Constructor
	 * @param parent
	 * @param title
	 * @param value
	 * @param iconId
	 * @param curId
	 */
	public Node(Node parent, String title, String value, int iconId,
				String curId) {
			super();
			this.parent = parent;
			this.title = title;
			this.value = value;
			this.iconId = iconId;
			this.curId = curId;
		}
/**
 * Constructor
 * @param name
 * @param parent
 */
	public Node(String name,Node parent){
		super();
		this.title=name;
		this.parent=parent;
		this.curId=String.valueOf(count++);
	}
	
	/**
	 * Get the instance of current node
	 * @return
	 */
	public static Node getInstance(){
		if(self==null){
			self=new Node();
			return self;
		}
		return self;
	}
	/**
	 * Constructor
	 */
	public Node() {
		super();
	}
/**
 * get the list data backed up
 * @return
 */
	public List<String> getChildrensName() {
		return childrensName;
	}
/**
 * Set the list data
 * @param newNode
 */
	public void setChildrensName(String newNode) {
		this.childrensName.add(newNode);
	}


	/**
	 * get the parent node
	 * @return
	 *
	 */
	public Node getParent() {
		return parent;
	}
	/**
	 * set parent node
	 * @param parent
	 *
	 */
	public void setParent(Node parent) {
		this.parent = parent;
	}
	/**
	 * get child node
	 * @return
	 *
	 */
	public List<Node> getChildrens() {
		return childrens;
	}
	/**
	 * is root or not
	 * @return
	 *
	 */
	public boolean isRoot(){
		return parent ==null?true:false;
	}
	/**
	 * switch button
	 * @return
	 *
	 */
	public int getIcon() {
		return icon;
	}
	/**
	 * set the state of switch button
	 * @param icon
	 *
	 */
	public void setIcon(int icon) {
		this.icon = icon;
	}
	/**
	 * is checked or not
	 * @return
	 *
	 */
	public boolean isChecked() {
		return isChecked;
	}
	/**
	 * set the check state
	 * @param isChecked
	 */
	public void setChecked(boolean isChecked) {
		this.isChecked = isChecked;
	}
	/**
	 * is expanded or not
	 * @return
	 *
	 */
	public boolean isExplaned() {
		return isExpand;
	}
	/**
	 * set expanded state
	 * @param isExplaned
	 *
	 */
	public void setExplaned(boolean isExplaned) {
		this.isExpand = isExplaned;
	}
	/**
	 * has checkbox or not
	 * @return
	 *
	 */
	public boolean hasCheckBox() {
		return hasCheckBox;
	}
	/**
	 * set the state of checkbox
	 * @param hasCheckBox
	 *
	 */
	public void setHasCheckBox(boolean hasCheckBox) {
		this.hasCheckBox = hasCheckBox;
	}
	/**
	 * get the node name
	 * @return
	 *
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * set the node name
	 * @param title
	 *
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	/**
	 * get node value
	 * @return
	 *
	 */
	public String getValue() {
		return value;
	}
	/**
	 * set node value
	 * @param value
	 *
	 */
	public void setValue(String value) {
		this.value = value;
	}
	/**
	 * add a child node
	 * @param node
	 *
	 */
	public void addNode(Node node){
		//String node2=node.getTitle().trim();
		if(!childrens.contains(node)&&!map.containsKey(node)&&!childrensName.contains(node.getTitle())){
			if(!childrensName.contains("Start")){
				childrensName.add("Start");
			}
			childrens.add(node);
			childrensName.add(node.getTitle());
			map.put(node.getTitle(), node);
		}
	}
	/**
	 * remove a child node
	 * @param node
	 *
	 */
	public void removeNode(Node node){
		if(childrens.contains(node))
			childrens.remove(node);
	}
	/**
	 * remove a child node at a index
	 * @param location
	 *
	 */
	public void removeNode(int location){
		childrens.remove(location);
	}
	/**
	 * remove all children
	 *
	 */
	public void clears(){
		childrens.clear();
	}
	/**
	 * if the given node is the parent of the current node
	 * @param node
	 * @return
	 *
	 */
	public boolean isParent(Node node){
		if(parent == null)return false;
		if(parent.equals(node))return true;
		return parent.isParent(node);
	}
	/**
	 * get the layer
	 * @return
	 *
	 */
	public int getLevel(){
		return parent ==null?0:parent.getLevel()+1;
	}
	/**
	 * is the parent node foldable
	 * @return
	 *
	 */
	public boolean isParentCollapsed(){
		if(parent ==null)return false;
		if(!parent.isExplaned())return true;
		return parent.isParentCollapsed();
	}
	/**
	 * is leaf or not
	       * @return
	       *
	 */
	public boolean isLeaf(){
	       return childrens.size()<1?true:false;
	}
	 /**
	  * return node name
	 * @return
	 **/
	public String getCurId() {
		// TODO Auto-generated method stub
		//this.curId=title;
		return curId;
	}
	/**
	 *return parent node name
	* @return
	**/
	public String getParentId() {
		// TODO Auto-generated method stub
		//this.parentId=this.parent.getTitle();
		return parentId;
	}
	}