// 출처 : https://programmers.co.kr/learn/courses/30/lessons/17681

function solution(n, arr1, arr2) {
    var answer = [];
    var tmp;
    for(var i=0; i<n; i++){
        tmp = (arr1[i] | arr2[i]).toString(2);
        answer.push('0'.repeat(n-tmp.length) + tmp+"")
    }
    
    answer = answer.map((str) => {
        return str.replace(/0/g, " ").replace(/1/g, "#");
    })
    return answer;
}