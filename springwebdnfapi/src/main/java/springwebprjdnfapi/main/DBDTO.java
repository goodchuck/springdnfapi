package springwebprjdnfapi.main;

import springwebprjdnfapi.test.Api;

public class DBDTO {

	public String[] yangid= new String[10];
	public String[] songid= new String[10];
	public String[] zoziid= new String[10];
	public String[] sungid= new String[10];
	public String[] anid= new String[10];
	public String[] chaid= new String[10];
	
	
	
	public int[] yangtimeline = new int[10];
	public int[] songtimeline = new int[10];
	public int[] zozitimeline = new int[10];
	public int[] sungtimeline = new int[10];
	public int[] antimeline = new int[10];
	public int[] chatimeline = new int[10];
	
	
	Api api = new Api();
	
	public DBDTO() {
		yangid[0] = api.searchname("prey", "체이서배메");
		yangid[1] = api.searchname("prey", "BUFF갑니닷");
		songid[0] = api.searchname("prey", "닷지닷지닷지");
		songid[1] = api.searchname("prey", "버프닷지");
		zoziid[0] = api.searchname("prey", "조지조지조지");
		zoziid[1] = api.searchname("prey", "도와조지1");
		sungid[0] = api.searchname("prey", "중화기따위");
		sungid[1] = api.searchname("prey", "하읏너무굵어");
		anid[0] = api.searchname("prey", "채찍질앗흥♥");
		anid[1] = api.searchname("prey", "야생에반젤");
		chaid[0] = api.searchname("prey", "극한의경지");
		chaid[1] = api.searchname("prey", "변치않는푸름");
		
		yangtimeline[0] = api.tlall("prey", api.searchcharacterId("prey", "체이서배메"));
		yangtimeline[1] = api.tlall("prey", api.searchcharacterId("prey", "BUFF갑니닷"));
		songtimeline[0] = api.tlall("prey", api.searchcharacterId("prey", "닷지닷지닷지"));
		songtimeline[1] = api.tlall("prey", api.searchcharacterId("prey", "버프닷지"));
		zozitimeline[0] = api.tlall("prey", api.searchcharacterId("prey", "조지조지조지"));
		zozitimeline[1] = api.tlall("prey", api.searchcharacterId("prey", "도와조지1"));
		sungtimeline[0] = api.tlall("prey", api.searchcharacterId("prey", "중화기따위"));
		sungtimeline[1] = api.tlall("prey", api.searchcharacterId("prey", "하읏너무굵어"));
		antimeline[0] = api.tlall("prey", api.searchcharacterId("prey", "채찍질앗흥♥"));
		antimeline[1] = api.tlall("prey", api.searchcharacterId("prey", "야생에반젤"));
		chatimeline[0] = api.tlall("prey", api.searchcharacterId("prey", "극한의경지"));
		chatimeline[1] = api.tlall("prey", api.searchcharacterId("prey", "변치않는푸름"));
	}
}
