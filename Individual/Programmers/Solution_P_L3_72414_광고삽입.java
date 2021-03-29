/*
 * -광고 삽입-
 * 1. 시청 시작, 종료 따른 위치 logCntArr에 찍어두기
 * 
 * -- 수정
 * 1. 시작, 종료 되는 위치 logCntArr에 합해주어 다음 시간에 더해질 시청자 수 계산
 * 2. logCntArr를 전부 돌면서 해당 위치에서의 시청자수 증감 기록
 * 3. adv를 윈도우로 두고 슬라이드 하며 총 시청자 수 기록
 * 
 * 풀이 시간 : 1H 30M
 */

//출처 : https://programmers.co.kr/learn/courses/30/lessons/72414
public class Solution_P_L3_72414_광고삽입 {
	public String solution(String play_time, String adv_time, String[] logs) {
		int[] multi = {3600,60,1};
		int ti=0;
		int playSize=0;
		for(String str : play_time.split(":")) {
			playSize+=Integer.parseInt(str)*multi[ti++];
		}
		
		int advSize=0;
		ti=0;
		for(String str : adv_time.split(":")) {
			advSize+=Integer.parseInt(str)*multi[ti++];
		}
		
		int start,end;
		// [0]:start / [1]:end
		int[] logCntArr = new int[playSize+2];
		
		String[] logsArr, startArr,endArr;
		for(String str : logs) {
			logsArr = str.split("-");
			startArr = logsArr[0].split(":");
			endArr = logsArr[1].split(":");
			start=0; end=0;
			for(int i=0; i<3; ++i) {
				start+=Integer.parseInt(startArr[i])*multi[i];
				end+=Integer.parseInt(endArr[i])*multi[i];
			}
			
			logCntArr[start]++;
			logCntArr[end]--;
		}
		
		for(int i=0; i<playSize; ++i) {
			logCntArr[i+1] += logCntArr[i];
		}
		
		long tempSum=0;
		int endTime = playSize-advSize;
		long maxRes=tempSum;
		int minTime = 0;
		for(int time=0; time<=endTime; ++time) {
			if(maxRes < tempSum ) {
				maxRes = tempSum;
				minTime=time;
			}
			
			tempSum-=logCntArr[time];
			tempSum+=logCntArr[time+advSize];
		}
		
		return getTime(minTime);
	}
	
	String getTime(int time) {
		int hour = time/3600;
		int min = (time%3600) /60;
		int sec = time%60;
		
		return String.format("%2d", hour).replace(" ", "0") +":"+ String.format("%2d", min).replace(" ", "0") +":"+ String.format("%2d", sec).replace(" ", "0");
	}

	public static void main(String[] args) {
		Solution_P_L3_72414_광고삽입 sol = new Solution_P_L3_72414_광고삽입();
//		String play_time = "02:03:55";
//		String adv_time = "00:14:15";
//		String[] logs = {"01:20:15-01:45:14", "00:40:31-01:00:00", "00:25:50-00:48:29", "01:30:59-01:53:29", "01:37:44-02:02:30"};
		String play_time = "99:59:59";
		String adv_time = "25:00:00";
		String[] logs = {"69:59:59-89:59:59", "01:00:00-21:00:00", "79:59:59-99:59:59", "11:00:00-31:00:00"};
		String answer = sol.solution(play_time, adv_time, logs);
		System.out.println(answer);
	}
}