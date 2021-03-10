// 출처 : https://programmers.co.kr/learn/courses/30/lessons/60057

function solution(s) {
    var answer = s.length;
    let maxLength = s.length/2;
    
    for(var length = 1; length<=maxLength; length++){
        let arr = [];
        let tempAnswer = 0;
        for(let idx=0; idx<s.length; idx+=length) {
            arr.push({
                str : s.slice(idx,idx+length),
                cnt : 1,
            });
        }
        
        let res = [];
        res.push(arr.shift());
        
        while(arr.length!=0){
            if(res[res.length-1].str === arr[0].str){
                res[res.length-1].cnt++;
                arr.shift();
            }
            else{
                res.push(arr.shift());
            }
        }
        
        res.forEach((item) => {
            if(item.cnt == 1){
                tempAnswer+= item.str.length;
            }
            else{
                tempAnswer+= item.str.length+(item.cnt+"").length;
            }
        })
        answer = Math.min(answer, tempAnswer)
    }
    return answer;
}