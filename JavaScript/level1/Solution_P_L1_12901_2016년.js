// 출처 : https://programmers.co.kr/learn/courses/30/lessons/12901

function solution(a, b) {
    var month = [31,29,31,30,31,30,31,31,30,31,30,31];
    var arr = ["SUN", "MON", "TUE", "WED", "THU", "FRI", "SAT"];
    var sum = 0;
    for(var i=0; i<a-1; i++){
        sum+=month[i];
    }
    sum+=b;
    sum+=4;
    var answer = arr[sum%7];
    return answer;
}
