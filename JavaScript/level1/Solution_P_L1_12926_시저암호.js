// 출처 : https://programmers.co.kr/learn/courses/30/lessons/12926

function solution(s, n) {
    var diff = 26;
    var answer = s.split('').map((a) => {
        if(/^[a-zA-Z]$/.test(a)){
            if(String.fromCharCode(a.charCodeAt()+n) > 'z' ||
            (String.fromCharCode(a.charCodeAt()) <= 'Z' && String.fromCharCode(a.charCodeAt()+n) > 'Z')){
                a = String.fromCharCode(a.charCodeAt()+n-diff);
            }
            else{
                a = String.fromCharCode(a.charCodeAt()+n);
            }
        }
        return a;
    });
    return answer.join('');
}