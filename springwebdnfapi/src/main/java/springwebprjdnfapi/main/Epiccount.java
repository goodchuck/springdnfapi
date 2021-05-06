package springwebprjdnfapi.main;

public class Epiccount {
	String cid;
	int ecount;
	int scount;
	String an;
	public String getCid() {
		return cid;
	}
	public void setCid(String cid) {
		this.cid = cid;
	}
	public int getEcount() {
		return ecount;
	}
	public void setEcount(int ecount) {
		this.ecount = ecount;
	}
	public int getScount() {
		return scount;
	}
	public void setScount(int scount) {
		this.scount = scount;
	}
	
	public String getAn() {
		return an;
	}
	public void setAn(String an) {
		this.an = an;
	}
	public Epiccount() {
		
	}
	
	public Epiccount(String cid, int ecount, int scount, String an) {
		super();
		this.cid = cid;
		this.ecount = ecount;
		this.scount = scount;
		this.an = an;
	}

	
	
	
}
