// 출처 : https://programmers.co.kr/learn/courses/30/lessons/42890

var colCnt, rowCnt;
var candidateArr=[];
function combination(selectCnt, maxCnt, startIdx, relation, selectString){
    if(selectCnt==maxCnt){
        let set = new Set();
        let selectArr = selectString.split("").map((num) => num*1);
        
        for(let candidate of candidateArr){
            let cnt=0;
            for(let idx=0; idx<candidate.length; idx++){
                selectArr.map((num) => num==candidate[idx] ? cnt++ : cnt)
            }
            if(cnt==candidate.length)
                return;
        }
        
        for(let row=0; row<rowCnt; row++){
            let str = ""
            for(let num of selectArr){
                str += (relation[row][num]+" ");
            }
            if(set.has(str)){
                return;
            }
            set.add(str);
        }
        candidateArr.push(selectArr);
        return;
    }
    
    for(let idx=startIdx; idx<colCnt; idx++){
        combination(selectCnt+1, maxCnt, idx+1, relation, selectString+idx);
    }
}

function solution(relation) {
    colCnt = relation[0].length, rowCnt=relation.length;
    
    for(let maxCnt=1; maxCnt<=colCnt; maxCnt++){
        combination(0, maxCnt, 0, relation, "");
    }
    
    return candidateArr.length;
}
