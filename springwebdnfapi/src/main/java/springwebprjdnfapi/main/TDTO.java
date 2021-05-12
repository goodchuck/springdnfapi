package springwebprjdnfapi.main;

import java.util.ArrayList;
import java.util.List;

public class TDTO {
	private int tlall2;
	private int tsall;
	private ArrayList<String> sinarray;
	

	public ArrayList<String> getSinarray() {
		return sinarray;
	}
	public void setSinarray(ArrayList<String> sinarray) {
		this.sinarray = sinarray;
	}
	public int getTlall2() {
		return tlall2;
	}
	public void setTlall2(int tlall2) {
		this.tlall2 = tlall2;
	}
	public int getTsall() {
		return tsall;
	}
	public void setTsall(int tsall) {
		this.tsall = tsall;
	}

	
	public TDTO(int tlall2, int tsall, ArrayList<String> sinarray) {
		super();
		this.tlall2 = tlall2;
		this.tsall = tsall;
		this.sinarray = sinarray;
	}
	public TDTO() {
		
	}
	

	
	
}
