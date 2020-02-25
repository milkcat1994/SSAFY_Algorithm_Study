import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/*
 * -통나무 옮기기-
 * 1. 통나무는 3자리 Point를 지니며 이동한 거리와 통나무의 방향을 가지는 Class이다.
 * 2. Queue에 넣어 BFS를 이용하며, 이동이 가능한지, 회전이 가능한지 파악하고 이동,회전 시킨다.
 * 3. 통나무의 가운데를 기준으로 삼아 해당 자리에 방문 했었는지의 여부를 파악한다.
 * 4. 해당 자리에, 해당 방향으로 왔었다면 갈 필요가 없는 사항이다.
 * 5. 한번에 한칸의 이동 or 회전만 가능하므로 목적지 도착시 바로 종료 해도 된다.
 */

//출처 : https://www.acmicpc.net/problem/1938
public class Main_B_G3_1938_통나무옮기기 {
	static int N;
	static char[][] board;
	static boolean[][][] isVisited;
	static final int VERTICAL = 0;
	static final int HORIZENTAL = 1;
	//상하좌우
	static int[] drow = {-1,1,0,0};
	static int[] dcol = {0,0,-1,1};
	static Tree eTree;
	
	static class Point{
		int row,col;
		Point(int row, int col){
			this.row = row;
			this.col = col;
		}
		
		void move(int dir) {
			this.row += drow[dir];
			this.col += dcol[dir];
		}
		
		boolean equals(Point p) {
			if(this.row == p.row && this.col == p.col)
				return true;
			return false;
		}
	}
	
	static class Tree {
		Point[] p = new Point[3];
		int dir, count;
		Tree(Point p1, Point p2, Point p3){
			this.p[0] = p1;
			this.p[1] = p2;
			this.p[2] = p3;
			this.count = 0;
		}
		
		Tree(Point p1, Point p2, Point p3, int dir, int count){
			this.p[0] = p1;
			this.p[1] = p2;
			this.p[2] = p3;
			this.dir = dir;
			this.count = count;
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		board = new char[N][N];
		isVisited = new boolean[N][N][2];

		String tempStr;
		List<Point>[] tList = new ArrayList[2];
		tList[0] = new ArrayList<>();
		tList[1] = new ArrayList<>();
		for (int row = 0; row < N; ++row) {
			tempStr = br.readLine();
			for (int col = 0; col < N; ++col) {
				board[row][col] = tempStr.charAt(col);
				switch (board[row][col]) {
				case 'B':
					tList[0].add(new Point(row,col));
					break;
				case 'E':
					tList[1].add(new Point(row,col));
					break;
				}
			}
		}
		
		//나무 위치 저장
		Tree sTree = new Tree(tList[0].get(0),tList[0].get(1),tList[0].get(2));
		sTree.dir = getDir(sTree.p[0], sTree.p[1]);
		isVisited[sTree.p[1].row][sTree.p[1].col][sTree.dir] = true;
		//도착 위치 저장
		eTree = new Tree(tList[1].get(0),tList[1].get(1),tList[1].get(2));
		eTree.dir = getDir(eTree.p[0], eTree.p[1]);
		
		Queue<Tree> que = new LinkedList<Tree>();
		Tree tTree;
		
		que.offer(sTree);
		while(!que.isEmpty()) {
			tTree = que.poll();
			
			if(isFind(tTree, tTree.dir)) {
				System.out.println(tTree.count);
				return;
			}
			//4방향 이동
			for(int dir = 0; dir < 4; ++dir) {
				if(!canMove(tTree, dir)) continue;
				
				que.offer(moveTree(tTree,dir));
				isVisited[tTree.p[1].row+drow[dir]][tTree.p[1].col+dcol[dir]][tTree.dir] = true;
			}
			//한번 회전
			if(!canRotate(tTree)) continue;
			if(isVisited[tTree.p[1].row][tTree.p[1].col][(tTree.dir+1)%2]) continue;
			
			que.offer(rotateTree(tTree));
			isVisited[tTree.p[1].row][tTree.p[1].col][(tTree.dir+1)%2] = true;
		}
		System.out.println(0);
		
	} //end main
	
	public static Tree moveTree(Tree tree, int dir) {
		Tree tTree;
		tTree = new Tree(new Point(tree.p[0].row+drow[dir], tree.p[0].col+dcol[dir])
				, new Point(tree.p[1].row+drow[dir], tree.p[1].col+dcol[dir])
				, new Point(tree.p[2].row+drow[dir], tree.p[2].col+dcol[dir]));
		
		tTree.count = tree.count+1;
		tTree.dir = tree.dir;
		return tTree;
	}
	
	public static Tree rotateTree(Tree tree) {
		Tree tTree;
		switch (tree.dir) {
		//가로 -> 세로
		case HORIZENTAL:
			tTree = new Tree(new Point(tree.p[0].row-1, tree.p[0].col+1)
					, new Point(tree.p[1].row, tree.p[1].col)
					, new Point(tree.p[2].row+1, tree.p[2].col-1));
			break;
		//세로 -> 가로
		case VERTICAL:
		default:
			tTree = new Tree(new Point(tree.p[0].row+1, tree.p[0].col-1)
					, new Point(tree.p[1].row, tree.p[1].col)
					, new Point(tree.p[2].row-1, tree.p[2].col+1));
		}
		tTree.dir = (tree.dir+1)%2;
		tTree.count = tree.count+1;
		return tTree;
	}
	
	//해당 방향으로 이동 할 수 있는지
	public static boolean canMove(Tree tree, int dir) {
		int tr,tc;
		for(int i = 0; i < 3; ++i) {
			tr = tree.p[i].row+drow[dir];	tc = tree.p[i].col+dcol[dir];
			if(isOut(tr, tc) || board[tr][tc] == '1')
				return false;
			if(isOut(tr, tc) || board[tr][tc] == '1')
				return false;
			
			if(i==1 && isVisited[tr][tc][tree.dir])
				return false;
		}
		
		return true;
	}
	
	//해당 tree가 가로 세로인지 보고 회전 가능한지 파악
	public static boolean canRotate(Tree tree) {
		int tr,tc;
		switch (tree.dir) {
		//가로
		case HORIZENTAL:
			for(int i = 0; i < 3; ++i) {
				tr = tree.p[i].row;	tc = tree.p[i].col;
				if(isOut(tr-1, tc) || board[tr-1][tc] == '1')
					return false;
				if(isOut(tr+1, tc) || board[tr+1][tc] == '1')
					return false;
			}
			break;
		//세로
		case VERTICAL:
			for(int i = 0; i < 3; ++i) {
				tr = tree.p[i].row;	tc = tree.p[i].col;
				if(isOut(tr, tc-1) || board[tr][tc-1] == '1')
					return false;
				if(isOut(tr, tc+1) || board[tr][tc+1] == '1')
					return false;
			}
		}
		return true;
	}
	
	public static boolean isOut(int row, int col) {
		if(row<0 || col<0 || row>=N || col>=N)
			return true;
		return false;
	}
	
	//찾았는지 확인
	public static boolean isFind(Tree tree, int dir) {
		if(eTree.p[1].equals(tree.p[1]) && eTree.dir == dir)
			return true;
		return false;
	}
	
	public static int getDir(Point p1, Point p2) {
		if(p1.row == p2.row)
			return HORIZENTAL;
		return VERTICAL;
	}
}