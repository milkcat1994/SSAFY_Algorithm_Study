import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

/*
 * -트리 순회-
 */

//출처 : https://www.acmicpc.net/problem/1991
public class Main_B_S1_1991_트리순회 {
	static int N;
	static Map<String, Node> hm;
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws Exception {
		init();
		
		// 전위
		preOrder("A");
		System.out.println(sb.toString());
		sb.setLength(0);
		// 중위
		inOrder("A");
		System.out.println(sb.toString());
		sb.setLength(0);
		
		// 후위
		postOrder("A");
		System.out.println(sb.toString());
	}
	
	static void preOrder(String ori) {
		sb.append(ori);
		
		if(hm.get(ori).l != null) {
			preOrder(hm.get(ori).l.ori);
		}
		
		if(hm.get(ori).r != null) {
			preOrder(hm.get(ori).r.ori);
		}
	}

	static void inOrder(String ori) {
		if(hm.get(ori).l != null) {
			inOrder(hm.get(ori).l.ori);
		}
		
		sb.append(ori);
		
		if(hm.get(ori).r != null) {
			inOrder(hm.get(ori).r.ori);
		}
	}

	static void postOrder(String ori) {
		if(hm.get(ori).l != null) {
			postOrder(hm.get(ori).l.ori);
		}
		
		if(hm.get(ori).r != null) {
			postOrder(hm.get(ori).r.ori);
		}
		
		sb.append(ori);
	}
	
	static void init() throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		
		hm = new HashMap<>();
		String str;
		for(int i=0; i<N; ++i) {
			st = new StringTokenizer(br.readLine(), " ");
			str = st.nextToken();
			
			hm.put(str, new Node(str, st.nextToken(), st.nextToken()));
		}
	}
	
	static class Node {
		String ori;
		Node l,r;
		
		Node(String ori, String l, String r){
			this.ori = ori;
			if(l.equals("."))
				this.l = null;
			else
				this.l = new Node(l, ".", ".");
			
			if(r.equals("."))
				this.r = null;
			else
				this.r = new Node(r, ".", ".");
		}
	}
}
