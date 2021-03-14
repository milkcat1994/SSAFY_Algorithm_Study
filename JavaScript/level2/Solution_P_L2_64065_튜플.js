// 출처 : https://programmers.co.kr/learn/courses/30/lessons/64065

function solution(s) {
    var answer = [];
    var splitString = s.slice(2,s.length-2).split(/},{/);
    var stringArr=[];
    
    splitString.map((str) => {
        stringArr.push(str.split(','))
    })
    stringArr.sort((a,b) => a.length-b.length)
    
    let arrLength = stringArr.length;
    let set = new Set();
    for(let idx=0; idx<arrLength; idx++){
        for(let num of stringArr[idx]){
            if(!set.has(num)){
                answer.push(num*1);
                set.add(num);
                break;
            }
        }
    }
    
    
    return answer;
}