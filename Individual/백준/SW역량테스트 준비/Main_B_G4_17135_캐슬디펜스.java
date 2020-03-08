import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;
import java.util.StringTokenizer;

/*
 * -캐슬디펜스-
 * 1. HashSet을 통해 적군과 타겟지정, 죽은 적군의 좌표를 관리한다.
 * 2. 궁수 좌표는 Stack으로 관리하고 조합을 이용 선택한다.
 * 3. 궁수가 놓일 수 있는 좌표는 미리 HashMap으로 생성하여 Stack에 넣기만 한다.
 * 
 * 4. 궁수를 순열 이용하여 3명 배치한다.
 * 5. 뽑힌 궁수의 좌표에서 BFS를 이용하여 가장 가까운(왼쪽우선) 적군의 좌표객체를 tarSet에 넣는다.
 * 6. 모든 목표가 정해졌다면 해당 Set의 크기는 죽은 적군의 수이며, 해당 적군좌표는 dieSet으로 이동한다.
 * └──더이상 죽일 수 있는 적군이 없을 때 dieSet에 있는 좌표를 eSet으로 이동시켜준다.
 * 7. BFS에서는 궁수의 시작 좌표를 한칸씩 위로 올리며 가장 가까운 적을 찾아내었다.
 * └──궁수와 같은 행은 확인하지 않는다.
 */

//출처 : https://www.acmicpc.net/problem/17135
public class Main_B_G4_17135_캐슬디펜스 {
	static int N,M;
	static final int AC = 3;
	
	static int[] drow = {0,-1,0};
	static int[] dcol = {-1,0,1};
	
	static int maxResult = Integer.MIN_VALUE;
	
	static Stack<Archer> aSt = new Stack<Archer>();						//선택된 궁수 좌표
	static Map<Integer, Archer> aMap = new HashMap<Integer, Archer>();	//모든 궁수 좌표
	
	static Set<Point> eSet = new HashSet<Point>();		//적군 좌표
	static Set<Point> tarSet = new HashSet<Point>();	//타겟 좌표
	static Set<Point> dieSet = new HashSet<Point>();	//죽은 적군 좌표
	
	static Map<Integer, Point> pointMap = new HashMap<Integer, Point>();
	public static class Point {
		int row,col;
		Point(int row, int col){
			this.row = row;
			this.col = col;
		}
	}
	
	public static class Archer {
		int row,col,distance;
		
		Archer(int col,int distance){
			this.row = N;
			this.col = col;
			this.distance = distance;
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		int D = Integer.parseInt(st.nextToken());
		
		String tempString;
		//적의 위치 입력받기
		Point tp;
		for (int row = 0; row < N; ++row) {
			tempString = br.readLine();
			for (int col = 0; col < M; ++col) {
				//적이라면
				tp = new Point(row,col);
				if(tempString.charAt(col*2) == '1')
					eSet.add(tp);
				pointMap.put(getIndex(row,col), tp);
			}
		}
		
		for(int col = 0; col < M; ++col) {
			aMap.put(col, new Archer(col, D));
			pointMap.put(getIndex(N, col), new Point(N,col));
		}
		
		//궁수 위치 선택하기
		pickArcher(0,0);
		System.out.println(maxResult);
	}
	
	//가장 가까운, 왼쪽 적 찾기
	//진행된 시간따라 궁수의 좌표를 올려서 탐색한다.
	public static void bfs(int time) {
		Queue<Point> que = new LinkedList<Point>();
		int cr,cc,nr,nc,ts,depth;
		Point tp;
		for(Archer a : aSt) {
			que.offer(pointMap.get(getIndex(a.row-time, a.col)));
			boolean[][] visited = new boolean[N+1][M];
			visited[a.row-time][a.col] = true;
			depth = 0;
			//해당 궁수 자리에서 BFS탐색 시작
			W:while(!que.isEmpty()) {
				ts = que.size();
				while(--ts >= 0) {
					tp = que.poll();
					if(eSet.contains(tp)){
						//탐색한 거리가 궁수의 사정거리 안이라면 타겟이 될 수있다.
						if(depth <= a.distance-1) {
							tarSet.add(tp);
						}
						break W;
					}
					cr = tp.row;	cc = tp.col;
						
					for(int i = 0; i < 3; ++i) {
						nr = cr + drow[i];
						nc = cc + dcol[i];
						
						if(isOut(nr,nc, time) || visited[nr][nc])
							continue;
						tp = pointMap.get(getIndex(nr,nc));
						visited[nr][nc] = true;
						que.offer(pointMap.get(getIndex(nr,nc)));
					}
				} //같은 depth
				depth++;
			} //end W:while()
			que.clear();
		}
	}
	
	//pointMap에서 Point객체 찾기위한 index반환
	public static int getIndex(int row, int col) {
		return row*M+col;
	}
	
	//궁수와 같은 열이라면 확인하지 않는다.
	public static boolean isOut(int row, int col, int time) {
		if(row <0 || col <0 || col>=M || row >= N+time-1)
			return true;
		return false;
	}
	
	public static void pickArcher(int index, int count) {
		if(count == AC) {
			int time = -1;
			int tempRes = 0;
			while(++time <= N) {
				//가까운 적 찾기
				bfs(time);
				
				tempRes += tarSet.size();
				
				//중복된 적 공격가능하므로 모든 궁수가 타겟을 설정한 뒤 적 Set에서 삭제
				// eSet에서 삭제, 죽은 set에 추가
				for(Point p : tarSet) {
					eSet.remove(p);
					dieSet.add(p);
				}
				tarSet.clear();
			}
			
			maxResult = Math.max(maxResult, tempRes);
			
			//죽었던 적 다시 이동
			for(Point p : dieSet) {
				eSet.add(p);
			}
			dieSet.clear();
			
			return;
		}
		
		for(int i = index; i < M; ++i) {
			aSt.push(aMap.get(i));
			pickArcher(i+1, count+1);
			aSt.pop();
		}
	}
	
}
