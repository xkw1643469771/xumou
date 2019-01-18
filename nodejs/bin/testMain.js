const my = require("./parseHtml");

my.get("http://www.baidu.com",function($){
    console.log($("div").html());
});
