import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * -마피아-
 * 1. 밤에만 해당 사람들의 유죄지수 변동 이라는 점 간과
 * 2. 완전 탐색으로 풀이
 */

//출처 : https://www.acmicpc.net/problem/1079
public class Main_B_G2_1079_마피아 {
	static int N,myNumber;
	static int[] players;
	static boolean[] isDied;
	static int[][] board;
	static int maxNight = 0;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		players = new int[N];
		isDied = new boolean[N];
		board = new int[N][N];
		st = new StringTokenizer(br.readLine(), " ");
		for(int i = 0; i < N; ++i)
			players[i] = Integer.parseInt(st.nextToken());
		
		for (int row = 0; row < N; ++row) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int col = 0; col < N; ++col) {
				board[row][col] = Integer.parseInt(st.nextToken());
			}
		}
		myNumber = Integer.parseInt(br.readLine());
		
		//밤이라면 마피아가 아무나 찍는다.
		if(N%2==0) {
			for(int i = 0; i < N; ++i) {
				if(i == myNumber) continue;
				for(int j = 0; j < N; ++j)
					players[j] += board[i][j];
				isDied[i] = true;
				dfs(i, 0, true);
				isDied[i] = false;
				for(int j = 0; j < N; ++j)
					players[j] -= board[i][j];
			}
		}
		//낮이라면 getDiePlayer()
		else{
			int target = getDiePlayer();
			isDied[target] = true;
			dfs(target, 0, false);
			isDied[target] = false;
		}
			
		System.out.println(maxNight);
	}
	
	//index : 현재 죽일 사람, count : 지난 밤의 개수, flag : true=밤
	public static void dfs(int index, int count, boolean flag) {
		if(index == myNumber) {
			maxNight = maxNight > count ? maxNight : count;
			return;
		}
		
		if(!flag) {
			for(int i = 0; i < N; ++i) {
				if(isDied[i] || i == myNumber) continue;
				for(int j = 0; j < N; ++j)
					players[j] += board[i][j];
				isDied[i] = true;
				dfs(i, count, true);
				isDied[i] = false;
				for(int j = 0; j < N; ++j)
					players[j] -= board[i][j];
			}
		}
		else {
			int target = getDiePlayer();
			isDied[target] = true;
			dfs(target, count+1, false);
			isDied[target] = false;
		}
	}
	
	//아침에 값 높은 사람 도출
	public static int getDiePlayer() {
		int dieP = 0, maxValue=0;
		for(int index = 0; index < N; ++index) {
			if(isDied[index]) continue;
			if(maxValue < players[index]) {
				dieP = index;
				maxValue = players[index];
			}
		}
		return dieP;
	}	
}