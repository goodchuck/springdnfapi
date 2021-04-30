package springwebprjdnfapi.main;

public class DnfDTO2 {

	private String name;
	private String date;
	private String itemName;
	
	
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	
	public DnfDTO2() {
		
	}
	public DnfDTO2(String name, String date, String itemName) {
		super();
		this.name = name;
		this.date = date;
		this.itemName = itemName;
	}
	

	
	
	
}
