const request = require("request");
const jquery = require("jquery");
const jsdom = require("jsdom");


// sonar
sonarParse();
function sonarParse(){
    // 拼接请求路径
    var url = "http://10.0.75.154:19000/api/issues/search?s=FILE_LINE";
    url += "&authors=houruikai,liuli,liyemin,xuxiaogang,yuyueting";
    url += "&createdAfter=2018-12-01";
    url += "&createdBefore=2019-01-22";
    url += "&severities=BLOCKER,CRITICAL,MAJOR"; // 阻断 BLOCKER 危险 CRITICAL 重要 MAJOR
    url += "&ps=500";
    url += "&p=1";
    url += "&additionalFields=_all";
    // 发送请求
    request(
        url,
        {
            method : "GET",
            json : true, // 响应JSON
        },
        function(err,resp,body){
            chechError(body);
            var getDesc = initDesc();
            var getAuthor = initAuthor();
            var issues = body.issues;
            for(var i = 0; i < issues.length; i++){
                var item = issues[i];
                var javaName = item.component.substring(item.component.lastIndexOf("/")+1);
                var line = item.line ? item.line : "-1";
                var enMsg = item.message;
                var zhMsg = getDesc(enMsg);
                var author = getAuthor(item.author);
                //console.info((i+1) + "\t" + javaName + "\t" + line + "\t" + enMsg + "\t" + zhMsg)
                console.info(author)
            }
        }
    );
    // 判断返回是否报错
    function chechError(result){
        if(result.errors != undefined && result.errors != null){
            for(var i = 0;i<result.errors.length;i++){
                throw new Error(result.errors[i].msg);
            }
        }
    }
    // 描述翻译
    function initDesc(){
        var arr = new Array();
        arr["This block of commented-out lines of code should be removed."] = "删除被注释的代码";
        arr["The Cyclomatic Complexity of this method"] = "方法圈复杂度过高";
        arr["Remove these unused method parameters"] = "删除不使用的方法参数";
        arr["if/for/while/switch/try statements"] = "条件嵌套过多";
        arr["Reduce the number of conditional operators"] = "减少条件运算符的数量";
        arr["and use the constant instead"] = "用常量代替数字";
        arr["Define a constant instead of duplicating this literal"] = "使用常量代替重复多次的文本";
        arr["Put single-quotes around '.' to use the faster \"lastIndexOf(char)\" method"] = "单个字符使用lastIndexOf(char)提高速度";
        arr["Add the \"@Override\" annotation above this method signature"] = "添加@Override注释";
        arr["Replace the synchronized class \"StringBuffer\" by an unsynchronized one such as \"StringBuilder\""] = "使用StringBuilder代替StringBuffer";
        arr["Remove this unused method parameter"] = "移除不使用的方法参数";
        arr["parameters, which is greater than 7 authorized."] = "方法参数最多7个";
        arr["Update this method so that its implementation is not identical to"] = "方法内容重复";
        arr["Use a variable binding mechanism to construct this query instead of concatenation."] = "不是字符串拼接SQL,使用绑定变量";
        arr["Missing curly brace"] = "代码没有大括号";
        arr["Change this instance-reference to a static reference"] = "改变实例为静态引用";
        arr["Remove this expression which always evaluates to"] = "移除结果恒定的表达式";
        arr["Extract this nested try block into a separate method."] = "将嵌套的try代码块提取到一个单独的方法中";
        arr["Remove this \"BigDecimal\" constructor"] = "移除BigDecimal构造方法, 使用BigDecimal.valueOf代替";
        arr["Use \"BigDecimal.valueOf\" instead"] = "移除BigDecimal构造方法, 使用BigDecimal.valueOf代替";
        arr["Refactor this method to reduce its Cognitive Complexity from"] = "重构方法, 将其认知复杂度降低";
        arr["Split it into smaller methods"] = "方法行数过多, 拆分成多个方法.";
        arr["At most one statement is allowed per line"] = "一行最多一个语句";
        arr["duplicated blocks of code must be removed"] = "删除重复的代码块";
        arr["Reduce the number of returns of this method"] = "方法返回的数字为0-3";
        arr["A \"NullPointerException\" could be thrown;"] = "可以抛空指针异常";
        arr["Add a nested comment explaining why this method is empty, throw an UnsupportedOperationException or complete the implementation"] = "方法是空的";
        //arr["xxx"] = "xxx";
        return function(str){
            for(var key in arr){
                if(str.indexOf(key) > -1){
                    return arr[key];
                }
            }
            return str;
        };
    }
    //作者翻译
    function initAuthor(){
        var arr = new Array();
        arr["yuyueting"] = "俞月婷";
        arr["liuli"] = "刘利";
        arr["houruikai"] = "候瑞凯";
        arr["xuxiaogang"] = "徐晓刚";
        arr["liyemin"] = "李业敏";
        return function(key){
            return arr[key];
        }
    }
}

// 古里古怪的
//timeCount();
function timeCount(){
    var time = (function(){
        var count = 1;
        var method = {
            time : function(){
                console.info(count++);
                return method;
            }
        }
        return method.time;
    })();
    time().time().time().time().time().time().time().time().time().time().time().time();
}

//稀奇古怪的, 什么玩意
//countBracket();
function countBracket(){
    var time = (function(){
        var count = 1;
        var countTime = function(){
            console.info(count++);
            return countTime;
        }
        return countTime;
    })();
    time()()()()()()()()()()()()()()()()()()()()()()()()()()()()()()()()()()()()()();
}


// 正则表达式
// regexp();
function regexp(){
    console.info(/^va[a-z]*l$/.test("val"));
    console.info("val".match("val"));
    //console.info("".match("method [a-zA-Z0-9_]* is"));
}

// 编码解码
// code();
function code(){
    console.info(decodeURIComponent("a%2Ca"))
    console.info(encodeURIComponent("a,a"))
}

// 发送普通get请求, 将返回的页面转为Jquery对象
// getRequest();
function getRequest(){
    request(
        "https://www.baidu.com/",
        {
            method : "get"
        },
        function(err, resp, body){
            var $ = jquery(new jsdom.JSDOM(resp.body).window);
            console.info($("body").html());
        }
    )
}

