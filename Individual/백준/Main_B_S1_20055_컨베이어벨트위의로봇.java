import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

/*
 * -컨베이어 벨트 위의 로봇-
 */

//출처 : https://www.acmicpc.net/problem/20055
public class Main_B_S1_20055_컨베이어벨트위의로봇 {
	static int N,K;
	
	static class Robot{
		int idx;
		Robot(int idx){
			this.idx = idx;
		}
		void move() {
			this.idx++;
		}
	}
	
	static boolean[] isRobot;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		Deque<Robot> robots = new LinkedList<>();
		List<Integer> list = new ArrayList<>();
		
		st = new StringTokenizer(br.readLine(), " ");
		for(int i = 0 ; i< 2*N; ++i)
			list.add(Integer.parseInt(st.nextToken()));
		
		isRobot = new boolean[N];
		
		// 0인것 세기
		int k = 0, time=0;
		List<Integer> belt = new ArrayList<>();
		belt.addAll(list);
		
		// 0인 것이 K개이상 이라면 과정 종료 그렇지 않다면 1번으로 
		while(k < K){
			//벨트가 한칸 회전한다.
			belt = move(belt);
			int isDrop = 0;
			
			//n부터 역순으로 로봇 이동할 수 있는지 확인
			for(Robot robot : robots) {
				isRobot[robot.idx] = false;
				robot.move();
				//이동하고 떨어진것 있는지 확인
				if(robot.idx < N)
					isRobot[robot.idx] = true;
				else {
					isDrop++;
				}
				
				//내릴 수 있다면
				if(robot.idx+1 == N) {
					isRobot[robot.idx] = false;
					isDrop++;
				}
				
				// 이동 할 수 있습니다.
				if(belt.get(robot.idx+1) > 0 && robot.idx+1 < N && !isRobot[robot.idx+1]) {
					//로봇 한 칸 이동 및 값 변경
					isRobot[robot.idx] = false;
					robot.idx+=1;
					belt.set(robot.idx, belt.get(robot.idx)-1);
					isRobot[robot.idx] = true;
					
					//만일 0이라면
					if(belt.get(robot.idx) == 0) {
						k++;
					}
				}
			}
			
			//올라가는 자리에 로봇 없다면 올리기
			if(!isRobot[0] && belt.get(0) != 0) {
				robots.offerLast(new Robot(0));
				belt.set(0, belt.get(0)-1);
				isRobot[0] = true;
				if(belt.get(0) == 0) {
					k++;
				}
			}
			//가장 먼저 들어온 로봇 제거
			if(isDrop>0)
				for(int i = 0; i < isDrop; ++i)
					robots.pollFirst();
			time++;
		}
		System.out.println(time);
	}
	
	public static List<Integer> move(List<Integer> list) {
		int temp = list.get(N*2-1);
		for(int i = N*2-2; i>=0; --i) {
			list.set(i+1, list.get(i));
		}
		list.set(0, temp);
		return list;
	}
}
