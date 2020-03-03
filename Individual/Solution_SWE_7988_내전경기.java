import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

/*
 * -내전 경기-
 */

/*
 * 메모리 : -KB 
 * 시간 : -ms 
 * 코드길이 : -B 
 * 소요시간 : -M
 */

//100P
//출처 : https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AWvQZmdKUoEDFASy
public class Solution_SWE_7988_내전경기 {
	public static class Member {
		String str;
		Member(String str){
			this.str = str;
		}
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
			String res;
			Map<Member, HashSet<Member>> hm = new HashMap<Member, HashSet<Member>>();
			Set<Member> set1 = new HashSet<Member>();
			Set<Member> set2 = new HashSet<Member>();
			
			int K = Integer.parseInt(br.readLine());
			
			for(int k = 0; k < K; ++k) {
				st = new StringTokenizer(br.readLine(), " ");
				m1 = new Member(st.nextToken());
				m2 = new Member(st.nextToken());
				if(hm.containsKey(m1)) {
					hm.get(m1).add(m2);
				}
				else {
					hm.put(m1, new HashSet<Member>());
					hm.get(m1).add(m2);
				}
				if(hm.containsKey(m2)) {
					hm.get(m2).add(m1);
				}
				else {
					hm.put(m2, new HashSet<Member>());
					hm.get(m2).add(m1);
				}
			}
			//각각 가지는 시너지 저장
			set1.addAll(hm.get(m1));
			set2.addAll(hm.get(m2));

			boolean isAble = true;
			Member tm;
			//둘다 연결되지 않은 것들.
			Set<Member> anySet = new HashSet<Member>();
			Iterator<Member> iter = hm.keySet().iterator();
			while(iter.hasNext()) {
				tm = iter.next();
				if(!set1.contains(tm)) {
					//둘다 연결되지 않다면 따로 뺴두기
					if(!set2.contains(tm)) {
						anySet.add(tm);
					}
					//m2에만 연결되어있다면 m1이랑 연결 시키기
					else {
						//tm이 가지고있던 시너지 정보 set1에 추가시키기
						set1.addAll(hm.get(tm));
						iter.remove();
					}
				}
				//m1에 연결되어있다면
				else {
					//둘다 연결되어 있다면 불가하다.
					if(set2.contains(tm)) {
						isAble=false;
						break;
					}
					//m1에만 연결되어있다면
					else {
						//tm이 가지고있던 시너지 정보 set2에 추가시키기
						set2.addAll(hm.get(tm));
						iter.remove();
					}
				}
			}
			if(!isAble) {
				sb.append('#').append(t).append(' ').append("No").append('\n');
				continue;
			}
			
			//따로 빼둔것이 없다면 다 연결 될 수 있다.
			if(anySet.isEmpty()) {
				sb.append('#').append(t).append(' ').append("Yes").append('\n');
				continue;
			}
			
			W:while(!anySet.isEmpty()) {
				iter = anySet.iterator();
				while(iter.hasNext()) {
					tm = iter.next();
					if(!set1.contains(tm)) {
						//m2에만 연결되어있다면 m1이랑 연결 시키기
						if(set2.contains(tm)) {
							//tm이 가지고있던 시너지 정보 set1에 추가시키기
							set1.addAll(hm.get(tm));
							iter.remove();	//anySet에 해당 정보  삭제
						}
						//둘다 연결 되어있지 않다면 아무대나 연결하기
						else {
							set1.addAll(hm.get(tm));
							iter.remove();	//anySet에 해당 정보  삭제
						}
					}
					//m1에 연결되어있다면
					else {
						//둘다 연결되어 있다면 불가하다.
						if(set2.contains(m2)) {
							isAble=false;
							break W;
						}
						//m1에만 연결되어있다면
						else {
							//tm이 가지고있던 시너지 정보 set2에 추가시키기
							set2.addAll(hm.get(tm));
							iter.remove();
						}
					}
				}
			}
			
			if(isAble)
				res="Yes";
			else
				res="No";
			sb.append('#').append(t).append(' ').append(res).append('\n');
		} //end TestCase
		System.out.print(sb.toString());
	}
}
