package springwebprjdnfapi.main;

public class DnfDTO2 {

	private String name;
	private String date;
	private String itemName;
	private String channelName;
	private int channelNo;
	private String dungeonName;
	
	public int getChannelNo() {
		return channelNo;
	}
	public void setChannelNo(int channelNo) {
		this.channelNo = channelNo;
	}
	public String getChannelName() {
		return channelName;
	}
	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}

	public String getDungeonName() {
		return dungeonName;
	}
	public void setDungeonName(String dungeonName) {
		this.dungeonName = dungeonName;
	}
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
	public DnfDTO2(String name, String date, String itemName, String channelName, int channelNo, String dungeonName) {
		super();
		this.name = name;
		this.date = date;
		this.itemName = itemName;
		this.channelName = channelName;
		this.channelNo = channelNo;
		this.dungeonName = dungeonName;
	}


	
	
	
}
