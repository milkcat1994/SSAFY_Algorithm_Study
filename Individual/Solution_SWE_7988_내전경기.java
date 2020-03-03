import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

/*
 * -내전 경기-
 * 1. 각 멤버의 인접 정보 HashMap내부 List로 저장
 * 2. 모든 멤버 확인하며 팀이 없다면 팀을 지정해준다.
 * 3. 이 때 인접한 사람은 자신과 다른 팀으로 지정해준다.
 * 4. 만일 인접한 사람끼리 같은 팀이 된다면 이는 실패이다. 
 */

/*
 * 메모리 : 37696KB 
 * 시간 : 158ms 
 * 코드길이 : 2651B 
 * 소요시간 : 3H
 */

//100P
//출처 : https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AWvQZmdKUoEDFASy
public class Solution_SWE_7988_내전경기 {
	public static class Member {
		String str;
		int team;
		Member(String str){
			this.str = str;
			this.team = -1;
		}
		//HashSet이용 위한 함수
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((str == null) ? 0 : str.hashCode());
			return result;
		}
		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Member other = (Member) obj;
			if (str == null) {
				if (other.str != null)
					return false;
			} else if (!str.equals(other.str))
				return false;
			return true;
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		
		Member m1=null, m2=null;
		for(int t=1; t<=T; ++t) {
			HashMap<Member, ArrayList<Member>> hm = new HashMap<Member, ArrayList<Member>>();
			Set<Member> memSet = new HashSet<Member>();
			
			int K = Integer.parseInt(br.readLine());
			
			for(int k = 0; k < K; ++k) {
				st = new StringTokenizer(br.readLine(), " ");
				m1 = new Member(st.nextToken());
				m2 = new Member(st.nextToken());
				//인접 정보 저장.
				if(hm.containsKey(m1)) {
					hm.get(m1).add(m2);
				}
				else {
					hm.put(m1, new ArrayList<Member>());
					hm.get(m1).add(m2);
				}
				if(hm.containsKey(m2)) {
					hm.get(m2).add(m1);
				}
				else {
					hm.put(m2, new ArrayList<Member>());
					hm.get(m2).add(m1);
				}
				memSet.add(m1);
				memSet.add(m2);
			}

			boolean isAble = true;
			Member tm;
			Queue<Member> que = new LinkedList<>();
			Iterator<Member> iter = memSet.iterator();
			W:while(iter.hasNext()) {
				tm = iter.next();
				//해당 Member가 이미 팀이 있다면 패스
				if(tm.team != -1) continue;
				tm.team = 0;
				que.offer(tm);
				
				//BFS로 팀 정하기
				while(!que.isEmpty()) {
					tm = que.poll();
					for(Member m : hm.get(tm)) {
						//아직 방문한 곳이 아니라면 현재 팀의 반대 팀
						if(m.team == -1) {
							m.team = (tm.team+1)%2;
							que.offer(m);
						}
						//이미 방문한 곳인데 인접끼리 팀이 같다면 실패이다.
						else {
							if(m.team == tm.team) {
								isAble=false;
								break W;
							}
						}
					}
				}
			} //end iter.hasNext
			
			if(isAble)
				sb.append('#').append(t).append(' ').append("Yes").append('\n');
			else
				sb.append('#').append(t).append(' ').append("No").append('\n');
		} //end TestCase
		System.out.print(sb.toString());
	}
}
