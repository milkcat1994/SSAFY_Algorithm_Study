// 출처 : https://programmers.co.kr/learn/courses/30/lessons/12930

function solution(s) {
    var answer = s.split(" ").map((str) => 
        str.split('').map((chr, idx) => idx%2 == 0 ? chr.toUpperCase() : chr.toLowerCase()).join('')
    ).join(' ');
    
    return answer;
}