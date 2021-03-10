// 출처 : https://programmers.co.kr/learn/courses/30/lessons/42746

function solution(numbers) {
    let aLength,bLength;
    var answer = numbers.map((num) => num+"")
        .sort((a,b) => {
            aLength = a.length;
            bLength = b.length;
            let maxLength = aLength + bLength;
            for(let idx=0; idx<maxLength; idx++){
                if(a.charAt(idx%aLength) == b.charAt(idx%bLength)) continue;
                return b.charAt(idx%bLength) - a.charAt(idx%aLength);
            }
            return 0;
        });
    return answer.join("").replace(/^0+/,'0');
}