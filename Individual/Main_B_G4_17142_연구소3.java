import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

/*
 * -연구소3-
 * 1. 여러 Virus중 활성 바이러스를 선택한다. -> 이는 '2' 로 나타낸다.
 * └──비활성 Virus는 '3'으로 나타낸다. => 나중에 확산 시간을 계산할때 '0'(빈칸)과 다르게 처리해주기 위함이다.
 * 2. 활성 Virus를 총 Virus중에서 선택하여 BFS로 확산을 시작한다.
 * 3. '0'이라면 diffu(확산성공)==true로 해주어 time을 증가시킨다.
 * └──'3'(비활성 Virus)일 경우 해당 자리를 '2'로 교체만 해준다.
 * └──> 그러나 아직 빈칸이 남아있다면 시간을 증가시킨다. (칠할 수 없는 빈칸이라면 -1로 값을 변경해주므로 신경 쓸 필요 없음)
 * 4. Virus를 모두 확산시킨 뒤 '0'(빈칸)이 남아있다면 -1반환
 * └──'0'(빈칸)이 남아있지 않다면 현재까지의 최소 시간을 갱신해준다.
 */


//출처 : https://www.acmicpc.net/problem/17142
public class Main_B_G4_17142_연구소3 {
	static int N, M;
	static char[][] board;
	
	static List<Point> bList = new ArrayList<Point>();		//빈칸 List
	static List<Point> vList = new ArrayList<Point>();		//Virus List
	static Stack<Point> vStack = new Stack<Point>();		//뽑은 Virus관리 Stack
	static Queue<Point>	vQueue = new LinkedList<Point>();	//Virus확산 위한 Queue
	
	static Map<Integer,Point> map = new HashMap<Integer,Point>();	//좌표정보 map
	
	static int bSize, vSize;
	static int minResult = Integer.MAX_VALUE;
	static boolean fillAll = false;		//한번이라도 모든 빈칸 칠해졌는지
	
	//상하좌우
	static int[] drow = {-1,1,0,0};
	static int[] dcol = {0,0,-1,1};
	static int crow,ccol,nrow,ncol;
	
	public static class Point {
		int row,col;
		Point(int row, int col){
			this.row = row;
			this.col = col;
		}
		
		//List에서 Index를 구하기 위한 함수
		int getPosition() {
			return row*N+col;
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());	// N(4 ≤ N ≤ 50)
		M = Integer.parseInt(st.nextToken());	// M(1 ≤ M ≤ 10)
	
		board = new char[N][N];
		String ts;
		for (int row = 0; row < N; ++row) {
			ts = br.readLine();
			for (int col = 0; col < N; ++col) {
				board[row][col] = ts.charAt(col*2);
				map.put(row*N+col, new Point(row,col));	//Point객체 HashMap에 저장
				switch (board[row][col]) {
				case '0':
					bList.add(map.get(row*N+col));
					break;
				case '2':
					vList.add(map.get(row*N+col));
					break;
				}
			}
		}
		
		initBlank();
		vSize = vList.size();	//Virus갯수
		bSize = bList.size();	//빈칸 갯수
		pickVirus(0, 0);
		
		// 한번이라도 빈칸을 다 채웠다면 최소값 출력, 아니라면 -1출력
		if(fillAll)
			System.out.println(minResult);
		else
			System.out.println(-1);
	}
	
	/**
	 * 
	 * @param index		현재 선택할 virus의 index
	 * @param count		현재까지 뽑은 virus의 갯수
	 */
	public static void pickVirus(int index, int count) {
		//Virus를 정해진 갯수 만큼 다 선택했을 때
		if(count >= M) {
			bfs();
			initBlank();
			return;
		}
		
		Point tp;
		for(int i = index; i < vSize; ++i) {
			tp = map.get(vList.get(i).getPosition());
			//선택한 Virus 위치 '2'(활성 Virus)로 설정
			board[tp.row][tp.col] = '2';
			vStack.push(tp);
			pickVirus(i+1, count+1);
			// '3'(비활성 Virus)로 변경
			vStack.pop();
			board[tp.row][tp.col] = '3';
		}
	}
	
	//해당 바이러스 퍼트리기
	public static void bfs() {
		//선택된 Virus 모두 Stack에 넣기
		vQueue.addAll(vStack);
		
		int time = 0;		//확산에 걸리는 시간
		int blank = bSize;	//빈칸이 없는지 확인
		
		// 확산 성공했는지
		boolean diffu;
		Point tp;
		int qSize;
		while(!vQueue.isEmpty()) {
			qSize = vQueue.size();
			diffu = false;
			
			//같은 거리의 모든 곳 돌기
			while(qSize-- > 0) {
				tp = vQueue.poll();
				crow = tp.row;	ccol = tp.col;
				for(int i = 0; i < 4; ++i) {
					nrow = crow + drow[i];
					ncol = ccol + dcol[i];
					//경계선 넘어가거나 '1'(벽) 또는 '2'(활성 Virus)이면 확산 X
					if(nrow<0 || ncol<0 || nrow>=N || ncol>=N || board[nrow][ncol]=='1' || board[nrow][ncol]=='2')
						continue;
					
					// '0','3' 일때만 해당 좌표 Queue에 넣기
					vQueue.offer(map.get(nrow*N+ncol));
					switch (board[nrow][ncol]) {
					// 빈칸일 경우 확산됨(diffu)으로 설정하고 빈칸 갯수-1
					case '0':
						diffu = true;
						blank--;
						//비활성 Virus로 퍼졌을 경우 활성 Virus로 전환
					case '3':
						board[nrow][ncol] = '2';
						break;
					} //end switch(board..)
				} //end for(i)
			} //end while(qSize-->0)
			if(diffu || blank != 0) {
				time++;
			}
		} //end while(!vQueue.isEmpty())
		
		//최소 빈땅 갱신
		if(blank == 0) {
			fillAll = true;
			minResult = minResult < time ? minResult : time;
		}
	} //end bfs()

	/**
	 * <pre>
	 * 빈칸 이었던 부분 '0'으로 바꾸고,
	 * Virus였던 부분 '3'으로 교체(비활성 Virus)
	 * 현재 선택한 Virus 좌표 '2'로 설정(활성 Virus)
	 * </pre>
	 */
	public static void initBlank() {
		for(Point p : bList)
			board[p.row][p.col] = '0'; 
		
		for(Point p : vList)
			board[p.row][p.col] = '3';
		
		for(Point p : vStack)
			board[p.row][p.col] = '2';
	}
}