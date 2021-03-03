import java.io.BufferedReader;
import java.io.InputStreamReader;

/*
 * -최소힙-
 * 1. 배열 이용한 최소 힙 직접 구현
 * 2. Insert시 에는 가장 leaf 위치에 위치 시키며, 부모와 비교하여 부모가 클 경우 바꿔준다.
 * 2. 삭제 시에는 가장 윗 노드 값을 반환하고, 가장 말단 노드와 값을 교체한다. 해당 노드와 자식노드간 비교하며, 가장 작은 노드와 교환한다.
 * 
 * 메모리 : 29912KB
 * 시간 : 384ms
 * 풀이 시간 : 2H 40M
 */

//출처 : https://www.acmicpc.net/problem/1927
public class Main_B_S1_1927_최소힙 {
	static int N, idx;
	
	static int[] nodes;
	
	static BufferedReader br;
	public static void main(String[] args) throws Exception {
		init();
		
		System.out.print(solve());
	}
	
	static String solve() throws Exception {
		StringBuilder sb = new StringBuilder();
		
		int num;
		while(--N>=0) {
			num = Integer.parseInt(br.readLine());
			if(num == 0) {
				sb.append(delete()+"\n");
			}
			else {
				insert(num);
			}
		}
		
		return sb.toString();
	}
	
	static void insert(int num) {
		int curIdx = idx;
		nodes[curIdx] = num;
		idx++;
		
		while(curIdx != 1 && checkBigger(curIdx)) {
			swap(curIdx, curIdx/2);
			curIdx = curIdx/2;
		}
	}
	
	// 부모 노드의 값이 크다면 true로 자리를 바꾸어준다.
	static boolean checkBigger(int curIdx) {
		int parentIdx = curIdx/2;
		
		return nodes[curIdx] < nodes[parentIdx] ? true : false;
	}
	
	// 두 idx의 값 교환
	static void swap(int curIdx, int nextIdx) {
		int tInt = nodes[curIdx];
		nodes[curIdx] = nodes[nextIdx];
		nodes[nextIdx] = tInt;
	}
	
	static int delete() {
		// 아무것도 없다면 0을 반환
		if(idx==1) return 0;
		idx--;
		int curIdx = 1;
		
		//가장 윗노드 반환 및 leaf 노드 값과의 변경
		int num = nodes[curIdx];
		nodes[curIdx] = nodes[idx];
		nodes[idx] = 0;
		
		int childIdx;
		int left,right;
		while(checkLess(curIdx)) {
			childIdx = curIdx*2;
			left = childIdx < idx ? nodes[childIdx] : Integer.MAX_VALUE;
			right = childIdx+1 < idx ? nodes[childIdx+1] : Integer.MAX_VALUE;
			// 좌측이 작으며, 현재 값보다 작다면 해당 노드와 값 교환
			if(left < right && left < nodes[curIdx]) {
				swap(curIdx, childIdx);
				curIdx = childIdx;
			}
			// 우측이 작으며, 현재 값보다 작다면 해당 노드와 값 교환
			else if(left >= right && right < nodes[curIdx]) {
				swap(curIdx, childIdx+1);
				curIdx = childIdx+1;
			}
			// 그 외는 값 교환 X
			else
				break;
		}
		return num;
	}
	
	// 자식 노드에 더 작은 값이 있는 지 확인
	static boolean checkLess(int curIdx) {
		int childIdx = curIdx*2;
		int left = childIdx < idx ? nodes[childIdx] : Integer.MAX_VALUE;
		int right = childIdx+1 < idx ? nodes[childIdx+1] : Integer.MAX_VALUE;
		if((nodes[curIdx] < left && nodes[curIdx] < right)) {
			return false;
		}
		
		return true;
	}
	
	static void init() throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		nodes = new int[N+1];
		idx = 1;
	}
}
