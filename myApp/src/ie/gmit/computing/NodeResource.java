package ie.gmit.computing;
/**
 * 
 * @author Alex.Zeng
 *This is an entity class
 */
public class NodeResource {
	protected String parentId;
	protected String title;
	protected String value;
	protected int iconId;
	protected String curId;

	public NodeResource() {
		super();
	}
	
	public NodeResource(String parentId, String curId, String title,
			String value, int iconId) {
		super();
		this.parentId = parentId;
		this.title = title;
		this.value = value;
		this.iconId = iconId;
		this.curId = curId;
	}

	public NodeResource(String parentId, String curId, String title,int iconId) {
		super();
		this.parentId = parentId;
		this.title = title;
		this.iconId = iconId;
		this.curId = curId;
	}
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public void setValue(String value) {
		this.value = value;
	}


	public void setIconId(int iconId) {
		this.iconId = iconId;
	}


	public void setCurId(String curId) {
		this.curId = curId;
	}


	public String getParentId() {
		return parentId;
	}

	public String getTitle() {
		return title;
	}

	public String getValue() {
		return value;
	}

	public int getIconId() {
		return iconId;
	}

	public String getCurId() {
		return curId;
	}

}

