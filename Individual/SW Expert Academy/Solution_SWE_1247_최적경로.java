import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/*
 * -최적경로-
 * 1. 모든 경로 완전탐색
 */

/*
 * 메모리 : 25796KB 
 * 시간 : 225ms 
 * 코드길이 : 2165B 
 * 소요시간 : 40M
 */

//150P
//출처 : https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV15OZ4qAPICFAYD
public class Solution_SWE_1247_최적경로 {
	public static class Point {
		int row, col;
		Point(int row, int col){
			this.row = row;
			this.col = col;
		}
	}
	
	static Point home,company;
	static List<Point> list;
	static int[][] eBoard;
	static boolean[] isVisited;
	static int N, minResult;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		
		for(int t = 1; t <= T; ++t) {
			minResult = Integer.MAX_VALUE;
			list = new ArrayList<>();
			
			N = Integer.parseInt(br.readLine());
			st = new StringTokenizer(br.readLine(), " ");
			
			isVisited = new boolean[N];
			home = new Point(stoi(st.nextToken()), stoi(st.nextToken()));
			company = new Point(stoi(st.nextToken()), stoi(st.nextToken()));
			eBoard = new int[N][N];
			for(int n = 0; n < N; ++n) {
				list.add(new Point(stoi(st.nextToken()), stoi(st.nextToken())));
			}
			
			int ti;
			for(int n = 0; n < N; ++n) {
				for(int cn = 0; cn < N; ++cn) {
					ti = getDiff(list.get(n), list.get(cn));
					eBoard[n][cn] = ti;
					eBoard[cn][n] = ti;
				}
			}
			
			for(int n = 0; n < N; ++n) {
				//회사와 해당 집의 거리 미리더하기
				isVisited[n]=true;
				dfs(n, getDiff(company, list.get(n)), 1);
				isVisited[n]=false;
			}
			sb.append('#').append(t).append(' ').append(minResult).append('\n');
		} //end TestCase
		System.out.print(sb.toString());
	}
	
	public static void dfs(int index, int sum,int count) {
		if(minResult < sum) return;
		if(count == N) {
			//마지막 집과 자신의 집 거리 더하기
			sum += getDiff(list.get(index), home);
			minResult = minResult < sum ? minResult : sum;
			return;
		}
		
		for(int n = 0; n < N; ++n) {
			if(isVisited[n]) continue;
			//index - n 의 값 더하기. n visit처리
			isVisited[n] = true;
			dfs(n, sum+eBoard[index][n], count+1);
			isVisited[n] = false;
		}
	}
	
	public static int getDiff(Point p1, Point p2) {
		return Math.abs(p1.row-p2.row) + Math.abs(p1.col-p2.col);
	}
	
	public static int stoi(String str) {
		return Integer.parseInt(str);
	}
}
