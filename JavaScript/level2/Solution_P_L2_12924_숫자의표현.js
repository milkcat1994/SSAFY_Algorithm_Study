// 출처 : https://programmers.co.kr/learn/courses/30/lessons/12924

function solution(n) {
    var answer = 0;
    const MAX = 10000;
    for(let i=1; i<=MAX; i++){
        if(i==n) return answer+1;
        
        let tempSum=i;
        for(let j=i+1; j<=MAX; j++){
            tempSum+=j;
            if(tempSum==n){
                answer++;
                break;
            }
            if(tempSum>n)
                break;
        }
    }
    return answer;
}