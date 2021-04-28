package springwebprjdnfapi.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Cm {
	
	public Iterator<Element> cm() {
		String url = "http://www.cgv.co.kr/movies/";
		Document doc = null;
		
		try {
			doc = Jsoup.connect(url).get();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		Elements element = doc.select("div.sect-movie-chart\"");
		System.out.println("===========");
		
		Iterator<Element> ie1 = element.select("strong.rank").iterator();
		Iterator<Element> ie2 = element.select("strong.title").iterator();
		
		while (ie1.hasNext()) {
			System.out.println(ie1.next().text()+ "\t"+ ie2.next().text());
			return ie1;
		}
		System.out.println("===========");
		return ie1;
	}
	
	public void cm2() throws UnsupportedEncodingException, IOException {
		URL url = new URL("https://finance.naver.com/");
		BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream(),"euc-kr"));
		String line; int check_line = 0;
		while((line = reader.readLine()) != null) {
			System.out.println(line);
			if(line.contains("<a href=\"/marketindex/worldExchangeDetail.nhn?marketindexCd"))
				check_line = 1;
			if(line.contains("<a href=\"/marketindex/?tabSel=worldExchange#tab_section\""))
				check_line= 0;
			if(check_line == 1) {
				if(line.contains("<a href=\"/marketindex/worldExchangeDetail.nhn?marketindexCd=")) {
					String temp = line.split(">")[2].split("<")[0];
					temp= temp.trim();
					System.out.println(temp);
				}
				if(line.contains("<td>") && !line.contains("em")) {
					String temp = line.split(">")[1].split("<")[0];
					System.out.println((temp));
				}
			}
		}
		reader.close();
	}

	public void cm3() throws UnsupportedEncodingException, IOException {
		URL url = new URL("https://dunfaoff.com/SearchResult.df?server=prey&characterid=aff735f07862974f704f0543f8e7270a");
		BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream(),"euc-kr"));
		String line; int check_line = 0;
		while((line = reader.readLine()) != null) {
			System.out.println(line);
			if(line.contains("<b class=\"sinergeDmg0\"style="))
				check_line = 1;
			if(check_line == 1) {
					String temp = line.split(">")[2].split("<")[0];
					temp= temp.trim();
					System.out.println(temp);

				}
			}
		reader.close();
	}
}
