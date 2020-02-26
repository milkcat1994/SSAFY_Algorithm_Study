import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

/*
 * -성곽-
 * 1. BFS를 이용하여 방들을 구분하고 구분함과 동시에 '방의 개수'와 '가장 넓은 방의 넓이'를 구한다.
 * 2. 모든 방을 다시 BFS를 이용하여 돌며 인접한 방들은 Set에 저장한다.
 * 3. 해당 Set들을 돌면서 두개의 방의 합이 최대인 것을 도출한다.
 */

//출처 : https://www.acmicpc.net/problem/2234
public class Main_B_G4_2234_성곽 {
	static int ROW,COL;
	static int[][] board;	//해당 칸 경계선 정보 저장
	static int[][] roomNumber;
	static boolean[][] isVisited;

	static int[] drow = new int[16];
	static int[] dcol = new int[16];
	
	static ArrayList<Set<Integer>> near = new ArrayList<Set<Integer>>();
	static ArrayList<Integer> roomCountList = new ArrayList<Integer>();
	static Queue<int[]> roomStartList = new LinkedList<int[]>();
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		COL = Integer.parseInt(st.nextToken());
		ROW = Integer.parseInt(st.nextToken());
	
		board = new int[ROW][COL];
		roomNumber = new int[ROW][COL];
		isVisited = new boolean[ROW][COL];
		for (int row = 0; row < ROW; ++row) {
			st = new StringTokenizer(br.readLine()," ");
			for (int col = 0; col < COL; ++col) {
				board[row][col] = Integer.parseInt(st.nextToken());
			}
		}
		//서쪽에 벽이 있을 때는 1을, 북쪽에 벽이 있을 때는 2를, 동쪽에 벽이 있을 때는 4를, 남쪽에 벽이 있을 때는 8을 더한 값
		drow[1<<1] = -1;	drow[1<<3] = 1;
		dcol[1<<0] = -1;	dcol[1<<2] = 1;
		
		for(int row = 0; row < ROW; ++row)
			Arrays.fill(roomNumber[row], -1);
		
		int countRoom=0, tempArea,maxArea = Integer.MIN_VALUE, maxRoom;
		for (int row = 0; row < ROW; ++row) {
			for (int col = 0; col < COL; ++col) {
				//방 배정 안된 곳이라면 배정 시키기
				if(roomNumber[row][col] == -1) {
					tempArea = divideRoom(row, col,countRoom++);
					maxArea = maxArea > tempArea ? maxArea : tempArea;
					roomCountList.add(tempArea);	//해당 방의 넓이 저장
					near.add(new HashSet<>());
					roomStartList.add(new int[] {row,col});//방의 시작 위치 저장
				}
			}
		}
		maxRoom = countRoom;
		initVisited();
		
		int cr,cc,nr,nc,bit;
		int[] tIntArr;
		//각 방으로 부터 BFS돌고 인접한 방 기억하는 Set 만들기
		for(int index = 0; index < maxRoom; ++index) {
			Queue<int[]> que = new LinkedList<>();
			que.offer(roomStartList.poll());	//해당 방의 시작 위치로 부터 BFS시작
			
			while(!que.isEmpty()) {
				tIntArr = que.poll();
				cr = tIntArr[0];	cc = tIntArr[1];
				for(int dir = 0; dir < 4; ++dir) {
					bit = 1<<dir;
					nr = cr+drow[bit];	nc = cc+dcol[bit];
					if(isOut(nr, nc) || isVisited[nr][nc]) continue;
					//만일 현재 땅과 다른 땅이라면 해당 땅 번호 Set에 추가
					if(roomNumber[nr][nc] != index) {
						near.get(index).add(roomNumber[nr][nc]);
					}
					else {
						que.offer(new int[] {nr,nc});
						isVisited[nr][nc] = true;
					}
				}
			}
		}
		
		int maxMergeRoom = 0, tmr;
		Iterator<Integer> iter;
		//저장된 Set 모두 돌면서 최대 합계 구하기
		for(int index = 0; index < maxRoom; ++index) {
			iter = near.get(index).iterator();
			while(iter.hasNext()) {
				tmr = roomCountList.get(index) + roomCountList.get(iter.next());
				maxMergeRoom = maxMergeRoom > tmr ? maxMergeRoom : tmr;
			}
		}
		
		System.out.println(maxRoom+"\n"+maxArea+"\n"+maxMergeRoom);
	}
	
	public static int divideRoom(int row, int col, int number) {
		int cr,cc,nr,nc, bit, area = 0;
		int[] tIntArr;
		cr = row;	cc = col;
		Queue<int[]> que = new LinkedList<>();
		que.offer(new int[] {cr,cc});
		isVisited[cr][cc] = true;
		roomNumber[cr][cc] = number;
		area++;
		while(!que.isEmpty()) {
			tIntArr = que.poll();
			cr = tIntArr[0];	cc = tIntArr[1];
			for(int dir = 0; dir < 4; ++dir) {
				bit = 1<<dir;
				//해당 지점 벽이라면 패스
				if((board[cr][cc] & bit) >= 1) continue;
				nr = cr+drow[bit];	nc = cc+dcol[bit];
				//나가더라도, 방문했더라도 패스
				if(isOut(nr, nc) || isVisited[nr][nc]) continue;
				
				que.offer(new int[] {nr,nc});
				roomNumber[nr][nc] = number;
				isVisited[nr][nc] = true;
				area++;
			}
		}
		return area;
	}
	
	public static void initVisited() {
		for (int row = 0; row < ROW; ++row) {
			for (int col = 0; col < COL; ++col) {
				isVisited[row][col] = false;
			}
		}
	}
	
	public static boolean isOut(int row, int col) {
		if(row<0 || col<0 || row>=ROW || col>=COL)
			return true;
		return false;
	}
}
