import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * -최대 힙-
 * 
 * 메모리 : 29804KB
 * 시간 : 364ms
 * 풀이 시간 : 1H
 */

//출처 : https://www.acmicpc.net/problem/11279
public class Main_B_S2_11279_최대힙 {
	static int N;
	
	static int[] nodes;
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	public static void main(String[] args) throws Exception {
		init();
		
		System.out.print(solve());
	}
	
	static String solve() throws Exception {
		StringBuilder sb = new StringBuilder();
		int num, idx=1;
		for(int i=0; i<N; ++i) {
			num = Integer.parseInt(br.readLine());
			switch (num) {
			case 0:
				sb.append(deleteNode(idx)+"\n");
				if(idx != 1)
					idx--;
				break;
				
			default:
				insertNode(num, idx);
				idx++;
				break;
			}
		}
		
		return sb.toString();
	}
	
	static int deleteNode(int idx) {
		if(idx == 1) return 0;

		idx-=1;
		int num = nodes[1];
		nodes[1] = 0;
		swap(idx, 1);
		
		int curIdx = 1;
		int maxnum, maxIdx, childIdx;
		while(true) {
			childIdx = curIdx*2;
			maxIdx = curIdx;
			maxnum = nodes[curIdx];
			
			if(childIdx < idx && nodes[childIdx] > maxnum ) {
				maxIdx = childIdx;
				maxnum = nodes[childIdx];
			}
			
			childIdx += 1;
			if(childIdx < idx && nodes[childIdx] > maxnum) {
				maxIdx = childIdx;
			}
			
			if(maxIdx==curIdx)
				break;
			
			swap(curIdx, maxIdx);
			curIdx = maxIdx;
		}
		
		return num;
	}
	
	static void insertNode(int num, int idx) {
		nodes[idx] = num;
		while(idx>1) {
			if(nodes[idx/2] >= num)
				break;
			swap(idx, idx/2);
			idx/=2;
		}
	}
	
	static void swap(int parent, int child) {
		int backup = nodes[parent];
		nodes[parent] = nodes[child];
		nodes[child] = backup;
	}
	
	static void init() throws Exception {
		N = Integer.parseInt(br.readLine());
		nodes = new int[N+1];
	}
}
