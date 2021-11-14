import java.util.LinkedList;
import java.util.Queue;

/*
 * 좌표를 2배로 바꾼 뒤 경계 좌표만 남긴다면 뛰어넘는 일도 없을 것이라 예상된다.
 */

//출처 : https://programmers.co.kr/learn/courses/30/lessons/87694
public class Solution_P_L3_87694_아이템줍기 {
	int[] dy = {0,0,-1,1};
	int[] dx = {1,-1,0,0};
	
	final int MAX_SIZE = 50;
	
	public int solution(int[][] rectangle, int characterX, int characterY, int itemX, int itemY) {
		return bfs(rectangle, characterX, characterY, itemX, itemY);
	}
	
	public int bfs(int[][] rectangle, int characterX, int characterY, int itemX, int itemY) {
		boolean[][] isVisited = new boolean[MAX_SIZE+1][MAX_SIZE+1];
		Queue<Point> que = new LinkedList<>();
		que.offer(new Point(characterX, characterY));
		isVisited[characterX][characterY] = true;
		
		Point tp;
		int cx,cy, nx,ny, qs;
		int time = 0;
		while(!que.isEmpty()) {
			qs = que.size();
			time++;
			while(--qs >= 0) {
				tp = que.poll();
				
				cx = tp.x; cy = tp.y;
				for(int d=0; d<4; ++d) {
					nx = cx + dx[d];
					ny = cy + dy[d];
					
					if(isOut(nx, ny) || isRectangleIn(rectangle, cx, cy, nx, ny)
							|| !isRectangleBorder(rectangle, nx, ny)
							|| isVisited[nx][ny] || !isPassAble(rectangle, cx, cy, nx, ny)) continue;
					
					if(isGoal(nx, ny, itemX, itemY)) return time;
					que.offer(new Point(nx, ny));
					isVisited[nx][ny] = true;
				}
			}
		}
		
		return -1;
	}
	
	public class Point {
		int x,y;
		
		Point(int x, int y){
			this.x = x;
			this.y = y;
		}
	}
	
	// 이동하고자 하는 방향이 사각형 안을 가로질러 가는지 판단
	public boolean isRectangleIn(int[][] rectangle, int curX, int curY, int nextX, int nextY) {
		double mx = (double) (curX + nextX) / 2;
		double my = (double) (curY + nextY) / 2;
		for(int i=0; i<rectangle.length; ++i) {
			if(rectangle[i][0] < mx && mx < rectangle[i][2]
					&& rectangle[i][1] < my && my < rectangle[i][3])
				return true;
		}
		return false;
	}
	
	// 다음 좌표가 사각형의 경계선인지 확인
	public boolean isRectangleBorder(int[][] rectangle, int curX, int curY) {
		for(int i=0; i<rectangle.length; ++i) {
			if(
				((rectangle[i][0] == curX || rectangle[i][2] == curX)
				&& rectangle[i][1] <= curY && curY <= rectangle[i][3]) ||
				(( rectangle[i][1] == curY || curY == rectangle[i][3])
				&& rectangle[i][0] <= curX && curX <= rectangle[i][2])
			)
				return true;
		}
		return false;
	}
	
	// 이동하고자 하는 방향이 사각형의 경계선인지 확인
	public boolean isPassAble(int[][] rectangle, int curX, int curY, int nextX, int nextY) {
		double mx = (double) (curX + nextX) / 2;
		double my = (double) (curY + nextY) / 2;

		for(int i=0; i<rectangle.length; ++i) {
			if(
					((rectangle[i][0] == mx || mx == rectangle[i][2])
					&& rectangle[i][1] <= my && my <= rectangle[i][3]) ||
					(( rectangle[i][1] == my || my == rectangle[i][3])
					&& rectangle[i][0] <= mx && mx <= rectangle[i][2])
				)
				return true;
		}
		return false;
	}
	
	// 전체 배열 크기 벗어나는지 확인
	public boolean isOut(int cx, int cy) {
		if(cx<0 || cy<0 || cx>MAX_SIZE || cy>MAX_SIZE)
			return true;
		return false;
	}
	
	// item에 도착하였는지 확인
	public boolean isGoal(int cx, int cy, int itemX, int itemY) {
		if(cx == itemX && cy == itemY)
			return true;
		return false;
	}
	
	public static void main(String[] args) {
		Solution_P_L3_87694_아이템줍기 sol = new Solution_P_L3_87694_아이템줍기();
		int[][] rectangle = {{1,1,7,4},{3,2,5,5},{4,3,6,9},{2,6,8,8}};
		int characterX = 1;
		int characterY = 3;
		int itemX = 7;
		int itemY = 8;
		
//		int[][] rectangle = {{2,2,5,5},{1,3,6,4},{3,1,4,6}};
//		int characterX = 1;
//		int characterY = 4;
//		int itemX = 6;
//		int itemY = 3;
		int answer = sol.solution(rectangle, characterX, characterY, itemX, itemY);
		System.out.println(answer);
	}
}