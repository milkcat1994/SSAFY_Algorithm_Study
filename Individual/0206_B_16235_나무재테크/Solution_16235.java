import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

/*
 * -나무재테크-
 */

// 출처 : https://www.acmicpc.net/problem/16235
public class Solution_16235 {
	static int[][] addInfo;
	static int[][] board;
	static int N, M, K;
	//왼쪽 위부터 시계방향
	static int[] drow = new int[]{-1, -1, -1, 0, 0, 1, 1, 1};
	static int[] dcol = new int[]{-1, 0, 1, -1, 1, -1, 0, 1};
	
	static int tempTreeSize, treeAge, treeRow, treeCol, nRow, nCol;
	
	public static class Tree {
		public int tRow,tCol,age;
		boolean live;
		
		Tree(int row, int col, int age, boolean live){
			this.tRow = row;
			this.tCol = col;
			this.age = age;
			this.live = live;
		}
	}

	static List<Tree> newTreeList = new ArrayList<Tree>();
	static Deque<Tree> treeList = new LinkedList<Tree>();
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		// 첫째 줄에 N, M, K
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());	// 배열 크기
		M = Integer.parseInt(st.nextToken());	// 나무 갯수
		K = Integer.parseInt(st.nextToken());	// 지날 년수

		addInfo = new int[N+2][N+2];
		board = new int[N+2][N+2];
		
		// 겨울에 더해질 영양소 배열 초기화 및 초기 배열 초기화
		for(int row = 1; row <= N; ++row) {
			st = new StringTokenizer(br.readLine());
			for(int col = 1; col <= N; ++col) {
				addInfo[row][col] = Integer.parseInt(st.nextToken());
				board[row][col] = 5;
			}
		} //end for(row)
		
		//다음 M개의 줄에 상도가 심은 나무의 정보 x,y,z
		//x,y:위치, z:나이
		for(int index = 0; index < M; ++index) {
			st = new StringTokenizer(br.readLine());
			treeList.offerLast(new Tree(Integer.parseInt(st.nextToken()),
					Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), true) );
		}
		
		for(int count = 0; count < K; ++count) {
			Spring();
			Winter();
		}
		
		System.out.println(treeList.size());
	}

	//봄
	//나이만큼 영양소 흡수 이후 나무나이+1
	public static void Spring() {
		Iterator<Tree> keys = treeList.iterator();
		Tree key;
		while( keys.hasNext() ){
			key = keys.next();
			treeRow = key.tRow;
			treeCol = key.tCol;
			treeAge = key.age;
			
			//나무보다 해당 영양분이 많다면 빼주고 나무 나이 +1
			if(board[treeRow][treeCol] >= treeAge) {
				board[treeRow][treeCol] -= treeAge;
				key.age = treeAge+1;
				
				// 가을
				// 나이가 5의 배수라면
				if(key.age % 5 == 0) {
					// 주변 8칸에 나이 1인 나무 생성
					treeRow = key.tRow;
					treeCol = key.tCol;
					for(int j = 0; j < 8; ++j) {
						nRow = treeRow+drow[j];
						nCol = treeCol+dcol[j];
						//0이거나 N+1인곳은 생성하면 안된다.
						if(nRow <= 0 || nCol <= 0 || nRow > N || nCol > N)
							continue;
						newTreeList.add(new Tree(nRow, nCol, 1, true));
					} //end for(j)
				}
			}
			//흡수할 영양소가 부족하다면 해당 나무 죽어서 여름에 영양분으로...
			else {
				key.live = false;
			}
		} //end while(keys)

		// 여름
		keys = treeList.iterator();
		while(keys.hasNext()) {
			key = keys.next();
			if(!key.live) {
				board[key.tRow][key.tCol] += (key.age/2);
				keys.remove();
			}
		}
		
		// 가을
		for(Tree var : newTreeList) {
			treeList.offerFirst(var);
		}
		newTreeList.clear();
	}
	
	//겨울
	// 입력받은 배열로 모든 땅에 양분 추가
	public static void Winter() {
		for(int row = 1; row <= N; ++row) {
			for(int col = 1; col <= N; ++col) {
				board[row][col] += addInfo[row][col];
			}
		} //end for(row)
	}
}