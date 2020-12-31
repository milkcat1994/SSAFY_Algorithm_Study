import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.TreeSet;

/*
 * -보석 도둑-
 * TreeSet의 lower_bound를 찾는 higher 함수를 이용하여 알맞는 가방크기 log시간에 찾기
 * HashMap을 이용하여 해당 가방크기가 남은 갯수를 저장하고 있다.
 * 
 * PriorityQueue 대신에 List를 사용하여 poll시 마다 갱신에 대한 부담 감소
 */

//출처 : https://www.acmicpc.net/problem/1202
public class Main_B_G2_1202_보석도둑 {
	static int N, K;
	static List<Jewelry> list;
	static TreeSet<Integer> back;
	static Map<Integer, Integer> hm;
	
	public static void main(String[] args) throws Exception {
		init();
		
		System.out.print(find());
	}
	
	static long find() {
		long res=0;
		Integer ti = null;
		Jewelry jewelry;
		for(int i=0; i<N && !back.isEmpty(); ++i) {
			jewelry = list.get(i);
			ti = back.higher(jewelry.weight-1);
			if(ti == null) continue;
			
			hm.put(ti, hm.get(ti)-1);
			if(hm.get(ti) == 0) {
				back.remove(ti);
				hm.remove(ti);
			}
			res+=jewelry.price;
		}
		return res;
	}
	
	static void init() throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		list = new ArrayList<>();
		for(int i=0; i<N; ++i) {
			st = new StringTokenizer(br.readLine(), " ");
			list.add(new Jewelry(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
		}
		
		Collections.sort(list, new Comparator<Jewelry>() {
			@Override
			public int compare(Jewelry o1, Jewelry o2) {
				if(o1.price == o2.price) {
					return o1.weight - o2.weight;
				}
				return o2.price - o1.price;
			}
		});
		
		back = new TreeSet<>();
		hm = new HashMap<>();
		
		// 가방 무게
		int ti;
		for(int i=0; i<K; ++i) {
			ti = Integer.parseInt(br.readLine());
			back.add(ti);
			if(!hm.containsKey(ti))
				hm.put(ti, 1);
			else
				hm.put(ti, hm.get(ti) + 1);
		}
	}
	
	static class Jewelry {
		int weight, price;
		
		public Jewelry(int weight, int price) {
			this.weight = weight;
			this.price = price;
		}
	}
}
