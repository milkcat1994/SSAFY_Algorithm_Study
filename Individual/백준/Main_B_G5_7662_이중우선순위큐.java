import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
 * -이중 우선순위 큐-
 * 
 * PriorityQueue와 HashMap을 이용한 풀이
 * 
 * + TreeMap 이용하면 더 간단히 풀이할 수 있을 듯.
 */

//출처 : https://www.acmicpc.net/problem/7662
public class Main_B_G5_7662_이중우선순위큐 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(st.nextToken());
		
		while(--T>=0) {
			st = new StringTokenizer(br.readLine(), " ");
			int k = Integer.parseInt(st.nextToken());
			String[] operations = new String[k];
			for(int i=0; i<k; ++i) {
				operations[i] = br.readLine();
			}

			PriorityQueue<Integer> minPQ = new PriorityQueue<>();
			PriorityQueue<Integer> maxPQ = new PriorityQueue<>((o1, o2) -> o2.compareTo(o1));
			HashMap<Integer,Integer> hm = new HashMap<>();
			
			solution(operations, minPQ, maxPQ, hm);
			
			if(hm.isEmpty()) {
				sb.append("EMPTY\n");
			}
			else {
				sb.append(maxPQ.peek()+" "+minPQ.peek()+"\n");
			}
		}
		System.out.println(sb.toString());
	}
	
	public static void solution(
			String[] operations,
			PriorityQueue<Integer> minPQ,
			PriorityQueue<Integer> maxPQ,
			HashMap<Integer,Integer> hm
			) {
		StringTokenizer st;
				
		String prefix;
		int postfix;
		for(String operation : operations) {
			st = new StringTokenizer(operation, " ");
			prefix = st.nextToken();
			postfix = Integer.parseInt(st.nextToken());
			
			switch (prefix) {
			case "I":
				minPQ.offer(postfix);
				maxPQ.offer(postfix);
				
				hm.put(postfix, hm.getOrDefault(postfix, 0)+1);
				break;
				
			case "D":
				// 최댓값 삭제
				if(postfix == 1) {
					deleteNum(maxPQ, hm);
				}
				// 최솟값 삭제
				else {
					deleteNum(minPQ, hm);
				}
				break;
			default:
				// Do Nothing
				break;
			}
		}
		
		cleanNum(maxPQ, hm);
		cleanNum(minPQ, hm);
	}
	
	public static void deleteNum(PriorityQueue<Integer> pq, HashMap<Integer,Integer> hm) {
		// pq가 비어있지 않고 hm에 해당 숫자가 존재 하지 않는다면 필요없는 값을 pq에서 삭제한다.
		while(!pq.isEmpty() && !hm.containsKey(pq.peek())) {
			pq.poll();
		}
		
		// pq가 비어있지 않다면 해당 값 제외 하면 됨
		if(!pq.isEmpty()) {
			int target = pq.peek();
			if(hm.get(target) == 1) {
				hm.remove(target);
			}
			else {
				hm.put(target, hm.get(target)-1);
			}
			pq.poll();
		}
	}
	
	public static void cleanNum(PriorityQueue<Integer> pq, HashMap<Integer,Integer> hm) {
		while(!pq.isEmpty()) {
			int num = pq.peek();
			if(hm.containsKey(num))
				return;
			pq.poll();
		}
	}
}
