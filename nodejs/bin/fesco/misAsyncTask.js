const http = require("http");
const querystring = require("querystring");
const request = require("request");
const fs = require("fs");

// 读取文件内容, 一次读完
(function(){
    fs.readFile("./misAsyncTask.json",function(err,data){
        console.info(err,data);
    });
})

// 读取文件内容, 一次读取一部分
(function(){
    var buf = Buffer.alloc(1024);
    fs.open("./misAsyncTask.json","r",function(fe,fd){
        fs.read(fd, buf, 0, buf.length, 0, function(readErr, readLen){
            console.log(buf.slice(0,readLen).toString());
        });
    });
})

//asyncByHttpPost();
function asyncByHttpPost(){
    var param = querystring.stringify({
        jsondata : '{"addtypes":[{"addType":2,"flowId":1465,"insType":4},{"addType":2,"flowId":1465,"insType":5},{"addType":2,"flowId":1465,"insType":6},{"addType":2,"flowId":1465,"insType":7},{"addType":2,"flowId":1465,"insType":8}],"entryTasklistId":4336530,"oldOrderIdStr":"5767138","orderId":5767138,"payType":1,"socialNum":"310229198409150040","uniqNo":61307594,"userDto":{"baseUserId":757610,"baseUserRoleId":3854339,"corpId":999,"currSuppId":999,"functionId":"2202941","loginModel":"M","orgId":10054,"orgIds":"10054,10044","userId":757610}}'
    });
    console.info(param);
    var esRemote = http.request({
        method : "post",
        hostname: '127.0.0.1',
        port: 8080,
        path: '/mis/esRemote/saveOrUpdateInsAddType.remote',
        headers : {
            'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'
        }
    },function(resp){
        var result = "";
        resp.on("data",function(data){
            result += data;
        });
        resp.on("end",function(){
            console.log(result);
        });
    });
    esRemote.on("error", function(err){
        console.log("连接错误: " + err.message);
    });
    esRemote.write(param);
    esRemote.end();
}


function asyncByReqiest(){
    request({
        url : 'http://localhost:8080/mis/esRemote/saveOrUpdateInsAddType.remote',
        method : "post",
        form : {
            jsondata : ""
        }
    },function(err,resp,body){
        console.log(body);
    });
}