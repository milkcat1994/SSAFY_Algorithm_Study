import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

/*
 * -탑-
 * stack을 이용하여 거꾸로 확인한다.
 * 현재 타워보다 작은 타워는 필요 없으므로 삭제 하고, 큰 타워만 남겨간다.
 */

//출처 : https://www.acmicpc.net/problem/2493
public class Main_B_G5_2493_탑 {
	static class Tower {
		int h, idx;
		Tower(int h, int idx){
			this.h = h;
			this.idx = idx;
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		int N = Integer.parseInt(st.nextToken());
		Stack<Tower> stack = new Stack<>();
		int ti;
		boolean isFind;
		
		st = new StringTokenizer(br.readLine(), " ");
		for(int i=1; i<=N; ++i) {
			ti = Integer.parseInt(st.nextToken());
			isFind = false;
			while(!stack.empty()) {
				if(stack.peek().h < ti) {
					stack.pop();
				}
				else {
					isFind = true;
					break;
				}
			}
			
			if(isFind)
				sb.append(stack.peek().idx).append(' ');
			else
				sb.append("0 ");
			stack.push(new Tower(ti, i));
		}
		
		System.out.print(sb.toString());
	}
}
