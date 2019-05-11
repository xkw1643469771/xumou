package com.xumou.test.function.jwt;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.Test;
import sun.security.provider.DSAPrivateKey;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class JwtTest {

    private static final String SIGN_KEY = "3860c0f8-f444-4aca-9c6b-c278f4eaf4c4";

    // 生成token
    @Test
    public void generatorToken(){
        Map<String, Object> map = new HashMap<>();
        map.put("username", "123456");
        map.put("姓名", "高的兰科");
        map.put("年龄", 22);
        map.put("时间", new Date());
        Date date = new Date(System.currentTimeMillis() + 1000 * 60 * 30);
        String compact = Jwts.builder()
                .setClaims(map)
                .setExpiration(date)
                .signWith(SignatureAlgorithm.HS512, SIGN_KEY)
                .compact();
        System.out.println(date.toLocaleString());
        System.out.println(date.getTime());
        System.out.println(compact);
    }

    // 解析token
    @Test
    public void parseToken(){
        String token =
                "eyJhbGciOiJIUzUxMiJ9.eyLlp5PlkI0iOiLpq5jnmoTlhbDnp5EiLCLml7bpl7QiOjE1NTc1NDY0OTg2NDcsIuW5tOm-hCI6MjIsImV4cCI6MTU1NzU0ODI5OCwidXNlcm5hbWUiOiIxMjM0NTYifQ.vxfNd0xolUuKh8pTo-yska2idHY_qpzfo-bpd-r7Q3OSEvZoQUkSNb7NCka4iJUmYS4wWwvMKTrs82hWBdA7Ew";
        Map<String, Object> map = (Map<String, Object>) Jwts.parser()
                .setSigningKey(SIGN_KEY)
                .parse(token)
                .getBody();
        //1557548298647
        //1557548298000
        //1557548298
        System.out.println(map.get("exp"));
    }

    // 解析时间
    @Test
    public void date(){
        System.out.println(new Date(1557548298 * 1000L).toLocaleString());
    }


}
