const request = require("request");
const http = require("http");

//通过request模块发送json
requestSendJson();
function requestSendJson(){
    request(
        {
            url : "http://localhost:9998/userTest/selectUserByParam",
            method : "post",
            headers : {
                "Content-Type": "application/json",
            },
            body : "{}",
        },
        function(err, resp, body){
            console.info(body);
        }
    );
}

// 通过http模块发送json请求
// httpSendJson();
function httpSendJson(){
    var req = http.request({
        host : "localhost",
        port : 9998,
        path : "/userTest/selectUserByParam",
        method : "POST",
        headers : {
            'Content-Type':'application/json'
        }
    },function(resp){
        console.info(resp);
    });
    req.write('{"id":"123"}');
    req.end();
}

// 下载身份证图片
//reqestTest();
function reqestTest(){
    var idx = 0;
    var arr = new Array();
    arr.push("7310F043DEFB4BEFE053DF4B000A10E7")
    arr.push("7310F043DEFC4BEFE053DF4B000A10E7")
    arr.push("7310F043DEFD4BEFE053DF4B000A10E7")
    arr.push("7375FA8AE35A035DE053DF4B000A1910")
    arr.push("7375FA8AE35B035DE053DF4B000A1910")
    arr.push("7620529F4B5444E3E053DF4B000A51A7")
    arr.push("7620529F4B5544E3E053DF4B000A51A7")
    arr.push("7620529F4B5644E3E053DF4B000A51A7")
    arr.push("7620529F4B5744E3E053DF4B000A51A7")
    arr.push("7620529F4B5844E3E053DF4B000A51A7")
    arr.push("7620529F4B5944E3E053DF4B000A51A7")
    arr.push("7620529F4B5A44E3E053DF4B000A51A7")
    arr.push("7620529F4B5B44E3E053DF4B000A51A7")
    arr.push("7620529F4B5C44E3E053DF4B000A51A7")
    arr.push("7620529F4B5D44E3E053DF4B000A51A7")
    arr.push("7620529F4B5E44E3E053DF4B000A51A7")
    arr.push("7620529F4B5F44E3E053DF4B000A51A7")
    arr.push("7620529F4B6044E3E053DF4B000A51A7")
    arr.push("7620529F4B6144E3E053DF4B000A51A7")
    arr.push("7620529F4B6244E3E053DF4B000A51A7")
    arr.push("7620529F4B6344E3E053DF4B000A51A7")
    arr.push("7C168C55E2A42553E053DF4B000A70DF")
    arr.push("7C168C55E2A52553E053DF4B000A70DF")
    arr.push("72E7F4D6B21921D5E053DF4B000A818D")
    arr.push("734E180B30D87054E053DF4B000AD671")
    arr.push("7375576296507B17E053DF4B000AA8AE")
    arr.push("79FC99D057C474F8E053DF4B000AFAFA")
    arr.push("79FC99D057C574F8E053DF4B000AFAFA")
    arr.push("764597064A250370E053DF4B000A3709")
    arr.push("764597064A260370E053DF4B000A3709")
    arr.push("764597064A270370E053DF4B000A3709")
    for(var i = 0;i<arr.length;i++){
        request(
            "http://10.0.75.151:18081/fileserver/getErRecordDataById.do?fileId=" + arr[i],
            function(err, resp, body){
                console.info(idx++, body.length);
            }
        )
    }
}
