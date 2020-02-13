import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * -게리맨더링-
 * 1. 먼저 첫번쨰는 무조건 선택 되었다는 가정으로 시작한다._중복되는 것 구할 필요 없다.
 * 2. index를 순차적으로 증가시키며 선택하는 경우 하지 않는 경우 두가지를 모두 실행한다.
 * 3. 일단 선택을 하였다면 나의 선택으로 각 구역이 2개로 나뉘는지 확인 할것이다.
 * 4. 이에는 BFS를 사용하였으며, 첫번째로 나오는 각 구역에서 확산한다.
 * 5. 두 구역을 모두 확산 했음에도 아직 방문하지 않은 곳이 있다면 이는 두구역 이상으로 나뉜것이므로 실패이다.
 * 6. 5번에서 실패 하였다면 최소값을 갱신 시켜줄 필요가 없으며, 성공 하였다면 최소값을 갱신시킨다.
 */

//출처 : https://www.acmicpc.net/problem/17471
public class Solution_17471 {
	static int N;
	
	static int[] arr;
	
	static int[][] vertex;
	static boolean[] isVisited;
	
	static int minResult = Integer.MAX_VALUE;
	static boolean isFind = false;
	//BFS위한 Queue
	static Queue<Integer> que = new LinkedList<Integer>();
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		arr = new int[N];
		
		//각 인구수 저장
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; ++i) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		vertex = new int[N][N];
		isVisited = new boolean[N];
		//각 연결 정보 저장 해서 배열 담기
		int tempIndex, tempN;
		for(int n = 0; n < N; ++n) {
			st = new StringTokenizer(br.readLine());
			tempN = Integer.parseInt(st.nextToken());
			for(int i = 0; i < tempN; ++i) {
				tempIndex = Integer.parseInt(st.nextToken());
				vertex[n][tempIndex-1] = 1;
			}
		}

		// 1번은 무조건 뽑힌다는 가정, 나머지 뽑을거임.
		// 전달받는 index 다음 거만 뽑으면됨
		isVisited[0] = true;
		getArea(0,1);
		//찾지 못하였다면 -1출력
		if(isFind)
			System.out.println(minResult);
		else
			System.out.println(-1);
	}
	
	/**
	 * 
	 * @param index		현재 선택할 index
	 * @param count		현재 선택한 A지구 갯수
	 */
	static void getArea(int index, int count) {
		if(count == N)
			return;
		
		//만약 잘 이어져 있다면 두 구역 차이값 갱신
		if(isConnected()) {
			int tempResult = getDiff();
			if(minResult > tempResult) {
				minResult = tempResult;
				isFind = true;
			}
		}
		
		if(index == N-1)
			return;
		
		isVisited[index+1] = true;
		getArea(index+1, count+1);
		
		//선택 안 했을때.
		isVisited[index+1] = false;
		getArea(index+1, count);
		
	}
	
	//현재 A팀,B팀 다 이어져 있는지
	//현재 선택된 index
	static boolean isConnected() {
		//BFS로 방문 하였는지 확인 하는 배열
		boolean[] visited = new boolean[N];
		//각 구역은 한번만 확인한다.
		boolean areaA=false, areaB=false;
		
		int tp;
		for(int i = 0; i < N; ++i) {
			//각 구역 모두 확인했다면 더이상 확인하지 않는다.
			if(areaA && areaB)
				break;
			
			//1번씩만 확인 하기 위한 체크
			// isVisited[i]==true라면 A구역이다.
			if(isVisited[i] && !areaA) {
				//BFS로 해당 구역 확산하며 체크
				areaA = true;
				que.offer(i);
				
				while(!que.isEmpty()) {
					tp = que.poll();
					if(!visited[tp])
						visited[tp] = true;
					
					for(int j = 0; j < N; ++j) {
						//A구역이며 아직 방문 하지 않음. 그리고 해당 index와 연결 필요
						if(isVisited[j] && !visited[j] && vertex[tp][j]==1) {
							visited[j] = true;
							que.offer(j);
						}
					}
				}
			}
			// isVisited[i]==false라면 B구역이다.
			else if(!isVisited[i] && !areaB){
				//BFS로 해당 구역 확산하며 체크
				areaB = true;
				que.offer(i);
				
				while(!que.isEmpty()) {
					tp = que.poll();
					if(!visited[tp])
						visited[tp] = true;
					
					for(int j = 0; j < N; ++j) {
						//B구역이며 아직 방문 하지 않음. 그리고 해당 index와 연결 필요
						if(!isVisited[j] && !visited[j] && vertex[tp][j]==1) {
							visited[j] = true;
							que.offer(j);
						}
					}
				}
			} //end if-else
			
		} //end for
		
		//visited배열에 false(방문하지 못한 곳) 하나라도 있으면 실패
		for(int i = 0; i < N; ++i)
			if(!visited[i])
				return false;
		return true;
	}
	
	//두팀의 차이 절대값 구하기
	static int getDiff() {
		int sumA=0,sumB=0;
		for(int i = 0; i < N; ++i) {
			//A구역
			if(isVisited[i]) 
				sumA+=arr[i];
			//B구역
			else
				sumB+=arr[i];
		}
		return Math.abs(sumA-sumB);
	}
}