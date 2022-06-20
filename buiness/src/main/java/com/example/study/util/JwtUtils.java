package com.example.study.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;

import java.util.HashMap;
import java.util.Map;

public class JwtUtils {



    // 秘钥
    static final String SECRET = "X-Litemall-Token";
    // 签名是有谁生成
    static final String ISSUSER = "LITEMALL";
    // 签名的主题
    static final String SUBJECT = "this is litemall token";
    // 签名的观众
    static final String AUDIENCE = "MINIAPP";

    public static String createToken(String userId){
        try {
            Algorithm algorithm = Algorithm.HMAC256(SECRET);
            Map<String, Object> map = new HashMap<String, Object>();

            // 过期时间：2小时
            map.put("alg", "HS256");
            map.put("typ", "JWT");
            String token = JWT.create()
                    // 设置头部信息 Header
                    .withHeader(map)
                    // 设置 载荷 Payload
                    .withClaim("userId", userId)
                    .withIssuer(ISSUSER)
                    .withSubject(SUBJECT)
                    .withAudience(AUDIENCE)
                    // 生成签名的时间
//                    .withIssuedAt(nowDate)
                    // 签名过期的时间
//                    .withExpiresAt(expireDate)
                    // 签名 Signature
                    .sign(algorithm);
            return token;
        } catch (JWTCreationException exception){
            exception.printStackTrace();
        }
        return null;
    }

    //解析token并在token中取出userId
    public static Integer verifyTokenAndGetUserId(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(SECRET);
            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer(ISSUSER)
                    .build();
            DecodedJWT jwt = verifier.verify(token);
            //以上的部分时验证token是否正确，verify中会进行解码，下面可以直接使用jwt去getClaims()。

			/*
				public DecodedJWT verify(String token) throws JWTVerificationException {
			        DecodedJWT jwt = JWT.decode(token);
			        this.verifyAlgorithm(jwt, this.algorithm);
			        this.algorithm.verify(jwt);
			        this.verifyClaims(jwt, this.claims);
			        return jwt;
		   		 }
			*/

            //如果没有上面的验证，也可以直接使用JWT.decode(token)返回jwt然后再getClaims()
            Map<String, Claim> claims = jwt.getClaims();
            Claim claim = claims.get("userId");
            return claim.asInt();
        } catch (JWTVerificationException exception){
//			exception.printStackTrace();
        }

        return 0;
    }

}

