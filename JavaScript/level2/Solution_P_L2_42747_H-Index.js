// 출처 : https://programmers.co.kr/learn/courses/30/lessons/42747

function solution(citations) {
    var answer = 0;
    var arr = citations.sort((a,b) => a-b);
    let cnt;
    for(let h=0; h<=arr.length; h++){
        cnt = 0;
        for(let idx=0; idx<arr.length; idx++){
            if(h <= arr[idx])
                cnt++;
        }
        if(h <= cnt)
            answer = Math.max(answer, h);
    }
    return answer;
}