// 출처 : https://programmers.co.kr/learn/courses/30/lessons/42893

function solution(word, pages) {
    let urlIndexs = new Map();
    let bodyStart = '<body>';
    let bodyEnd = '</body>';
    let idxNum=0;

    pages.map((html) => {
        let curWebUrls = html.match(/<meta property="og:url" content="(\S+)"\/>/g)[0];
        let curWebUrl = curWebUrls.slice(curWebUrls.indexOf('https://'), curWebUrls.indexOf('"/>'))
        
        // <body>
        let tagBody = html.slice(html.indexOf(bodyStart), html.indexOf(bodyEnd)).replace(bodyStart, '').replace(bodyEnd, '');
        
        // <a>
        let tagAs = [];
        let tagA;
        let tagARegex = RegExp('<a href="https:[^"]*', 'g');
        while((tagA = tagARegex.exec(tagBody)) !== null){
            tagAs.push(tagA[0].slice(tagA[0].indexOf('"')+1));
        }
        
        // word count
        let tagBodyRegex = RegExp('\\b'+word+'\\b', 'gi');
        let wordCnt=0;
        tagBody = tagBody.replace(/[^a-zA-Z]/g, ' ')
        
        while(tagBodyRegex.exec(tagBody) !== null){
            wordCnt++;
        }
        
        urlIndexs.set(curWebUrl, {
            url : curWebUrl,
            idx : idxNum,
            baseScore : wordCnt,
            externalLinkCnt : tagAs.length,
            linkUrls : tagAs,
            linkScore : 0,
            totalScore : 0,
        })
        
        idxNum++;
    })
    
    for(let [key, linkInfo] of urlIndexs){
        linkInfo.linkUrls.map((url) => {
            if(urlIndexs.has(url)){
                let linkUrl = urlIndexs.get(url);
                linkUrl.linkScore += linkInfo.baseScore/linkInfo.externalLinkCnt;
                urlIndexs.set(url, linkUrl);
            }
        })
    }
    
    for(let [key, linkInfo] of urlIndexs){
        linkInfo.totalScore = linkInfo.baseScore+linkInfo.linkScore;
    }
    
    let answerArr = [...urlIndexs.values()];
    
    answerArr = answerArr.sort((a, b) => {
        if(a.totalScore == b.totalScore){
            return a.idx - b.idx;
        }
        return b.totalScore - a.totalScore
    })
    
    return answerArr[0].idx;
}