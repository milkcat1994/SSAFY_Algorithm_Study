import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

/*
 * -맥주 마시면서 걸어가기-
 */

//출처 : https://www.acmicpc.net/problem/9205
public class Main_B_S1_9205_맥주마시면서걸어가기 {
	static int T;
	static int ux,uy,ex,ey;
	
	public static class Point {
		int x,y;
		Point(int x, int y){
			this.x = x;
			this.y = y;
		}
	}

	static int[] parents;
	
	static void initUnion(int n) {
		parents = new int[n];
		Arrays.fill(parents, -1);
	}
	
	static boolean union(int x, int y) {
		int px = findParent(x);
		int py = findParent(y);
		
		if(px == py)
			return false;
		
		parents[px] = py;
		return true;
	}
	
	static int findParent(int x) {
		if(parents[x] == -1) return x;
		return parents[x] = findParent(parents[x]);
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		T = Integer.parseInt(br.readLine());
		for(int t = 0; t < T; ++t) {
			int n = Integer.parseInt(br.readLine());
			initUnion(n+2);
			
			List<Point> list = new ArrayList<>();
			int listSize;
			st = new StringTokenizer(br.readLine(), " ");
			ux = Integer.parseInt(st.nextToken());
			uy = Integer.parseInt(st.nextToken());
			list.add(new Point(ux, uy));
			
			for(int i = 0; i < n; ++i) {
				st = new StringTokenizer(br.readLine(), " ");
				list.add(new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
			}
			st = new StringTokenizer(br.readLine(), " ");
			ex = Integer.parseInt(st.nextToken());
			ey = Integer.parseInt(st.nextToken());
			list.add(new Point(ex, ey));
			listSize = n+2;
			//각 거리가 1000 미만만 연결시킬 것이다.
			
			for (int s = 0; s < listSize-1; ++s) {
				for (int e = s+1; e < listSize; ++e) {
					if(isAbleDist(list.get(s).x, list.get(s).y, list.get(e).x, list.get(e).y))
						union(s, e);
				}
			}
			
			if(findParent(0) == findParent(listSize-1))
				System.out.println("happy");
			else
				System.out.println("sad");
		}
	}
	
	static boolean isAbleDist(int ax, int ay, int bx, int by) {
		if(Math.abs(ax-bx) + Math.abs(ay-by) <= 1000)
			return true;;
		return false;
	}
}
