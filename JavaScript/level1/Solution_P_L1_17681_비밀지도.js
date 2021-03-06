// 출처 : https://programmers.co.kr/learn/courses/30/lessons/17681

function solution(n, arr1, arr2) {
    var answer = [];
    var tmp;
    for(var i=0; i<n; i++){
        tmp = (arr1[i] | arr2[i]).toString(2);
        answer.push(new Array(n-tmp.toString().length+1).join('0') + tmp+"")
    }
    
    answer = answer.map((str) => {
        return str.split("").map((character) => {
            return character == "1" ? "#" : " ";
        }).join("");
    })
    return answer;
}