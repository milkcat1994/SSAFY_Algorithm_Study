import java.util.ArrayList;
import java.util.List;

/*
 * -방금그곡-
 * 테스트 1 〉	통과 (0.52ms, 51.5MB)
 * 테스트 2 〉	통과 (0.46ms, 52.6MB)
 * 테스트 3 〉	통과 (0.67ms, 52.3MB)
 * 테스트 4 〉	통과 (0.46ms, 52.3MB)
 * 테스트 5 〉	통과 (0.51ms, 53.3MB)
 * 테스트 6 〉	통과 (0.51ms, 52.7MB)
 * 테스트 7 〉	통과 (1.39ms, 52MB)
 * 테스트 8 〉	통과 (1.17ms, 52.4MB)
 * 테스트 9 〉	통과 (1.08ms, 53.8MB)
 * 테스트 10 〉	통과 (1.18ms, 51.9MB)
 * 테스트 11 〉	통과 (1.20ms, 53.9MB)
 * 테스트 12 〉	통과 (1.25ms, 52.5MB)
 * 테스트 13 〉	통과 (1.10ms, 52.8MB)
 * 테스트 14 〉	통과 (1.18ms, 52.5MB)
 * 테스트 15 〉	통과 (1.13ms, 53.7MB)
 * 테스트 16 〉	통과 (1.13ms, 52.7MB)
 * 테스트 17 〉	통과 (1.04ms, 52.3MB)
 * 테스트 18 〉	통과 (1.09ms, 52.7MB)
 * 테스트 19 〉	통과 (1.32ms, 52.6MB)
 * 테스트 20 〉	통과 (1.17ms, 52.4MB)
 * 테스트 21 〉	통과 (1.08ms, 51.9MB)
 * 테스트 22 〉	통과 (0.75ms, 51.6MB)
 * 테스트 23 〉	통과 (1.14ms, 52.4MB)
 * 테스트 24 〉	통과 (1.12ms, 52.2MB)
 * 테스트 25 〉	통과 (0.52ms, 52.7MB)
 * 테스트 26 〉	통과 (0.50ms, 52.5MB)
 * 테스트 27 〉	통과 (0.55ms, 52.1MB)
 * 테스트 28 〉	통과 (0.56ms, 53.1MB)
 * 테스트 29 〉	통과 (8.87ms, 52.7MB)
 * 테스트 30 〉	통과 (9.82ms, 52.7MB)

 * 풀이 시간 : 1H 30M
 */

//출처 : https://programmers.co.kr/learn/courses/30/lessons/17683
public class Solution_P_L2_17683_방금그곡 {
	public String solution(String m, String[] musicinfos) {
		m = convert(m);
		List<Music> musicList = new ArrayList<>();
		for(int i=0; i<musicinfos.length; ++i) {
			musicList.add(new Music(musicinfos[i].split(",")));
		}
		
		Music resMusic = null;
		for(Music music : musicList) {
			if(music.info.contains(m)) {
				if(resMusic == null)
					resMusic = music;
				else {
					if(resMusic.musicLength < music.musicLength)
						resMusic = music;
				}
			}
		}
		
		return resMusic != null ? resMusic.title : "(None)";
	}
	
	String convert(String str) {
		return str
		.replace("C#", "H")
		.replace("D#", "I")
		.replace("F#", "J")
		.replace("G#", "K")
		.replace("A#", "L");
	}
	
	class Music {
		String title;
		int musicLength;
		String info;
		
		Music(String[] musicinfos){
			this.title = musicinfos[2];
			
			this.musicLength = getMusicLength(musicinfos[0], musicinfos[1]);
			this.info = getMusicInfo(musicinfos[3], this.musicLength);
		}
	}
	
	String getMusicInfo(String info, int length) {
		String str=convert(info);
		if(str.length() >= length) {
			return str.substring(0,length);
		}
		else {
			int left = length % str.length();
			int main = length / str.length();
			StringBuilder sb = new StringBuilder();
			for(int i=0; i<main; ++i) {
				sb.append(str);
			}
			sb.append(str.subSequence(0, left));
			return sb.toString();
		}
	}

	int getMusicLength(String startT, String endT){
		String[] st = startT.split(":");
		String[] et = endT.split(":");
		int startTime = Integer.parseInt(st[0])*60 + Integer.parseInt(st[1]);
		int endTime = Integer.parseInt(et[0])*60 + Integer.parseInt(et[1]);
		return endTime - startTime;
	}

	public static void main(String[] args) {
		Solution_P_L2_17683_방금그곡 sol = new Solution_P_L2_17683_방금그곡();
//		String m = "ABCDEFG";
//		String[] musicinfos = {"12:00,12:14,HELLO,CDEFGAB", "13:00,13:05,WORLD,ABCDEF"};
		String m = "CC#BCC#BCC#BCC#B";
		String[] musicinfos = {"03:00,03:30,FOO,CC#B", "04:00,04:08,BAR,CC#BCC#BCC#B"};
		String answer = sol.solution(m, musicinfos);
		System.out.println(answer);
	}
}