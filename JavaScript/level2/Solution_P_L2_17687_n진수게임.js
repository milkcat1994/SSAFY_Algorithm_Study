// 출처 : https://programmers.co.kr/learn/courses/30/lessons/17687

function solution(n, t, m, p) {
    var answer = '';
    var arr=[];
    var maxLength = m*t;
    let idx=0;
    while(arr.length<=maxLength){
        arr.push(...Number(idx).toString(n).split(""));
        idx++;
    }
    answer = arr.filter((num, idx) => idx%m == p-1).join("").toUpperCase();
    return answer.slice(0,t);
}