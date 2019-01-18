const http = require("http");
const jquery = require("jquery");
const jsdom = require("jsdom");

var req = {
    // 响应结果
    responseBody : function(resp, callFun){
        var result = "";
        // 接受响应
        resp.on("data", function(body){
            result += body;
        });
        // 使用JQuery解析请求
        resp.on("end", function(){
            var $ = jquery(new jsdom.JSDOM(result).window);
            callFun($);
        });
    },
    // 发送get请求
    get : function(url,callFun){
        http.get(url, function(resp){
            req.responseBody(resp, callFun);
        });
    },
}
exports.get = req.get;