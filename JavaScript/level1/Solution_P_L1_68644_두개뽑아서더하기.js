// 출처 : https://programmers.co.kr/learn/courses/30/lessons/68644

function solution(numbers) {
    var answer = [];
    let numbersLength = numbers.length;
    var answerSet = new Set();
    for(var i=0; i<numbersLength; i++){
        for(var j=i+1; j<numbersLength; j++){
            answerSet.add(numbers[i]+numbers[j]);
        }
    }
    answer = [...answerSet];
    return answer.sort((a, b) => a-b);
}