package com.example.ecommerceapp1.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.util.Date;


@Service
public class TokenService {
    public static final String token_secret="dfgshjakr45SDrte"; // random string is initialized here for token_secret

    //creating a token for a given user ID

    public  String createToken(ObjectId userId){
        // ---------------------------encoding part--------------------
        try{
            Algorithm algo=Algorithm.HMAC256(token_secret); //HMAC256 is the algo which create the token
            String token= JWT.create().withClaim("userId", userId.toString()).
                    withClaim("createdAt", new Date()).sign(algo);
            //adding  claim to the token which  is small information about the user & date ,claim is for retrieval
            return token;
            // for retrieval return token
        }catch (UnsupportedEncodingException | JWTCreationException e){
            e.printStackTrace();
        }
        return null;// if the token is not generated then return "null"
    }
    //" DECODE THE TOKEN"
    public String getUserIdToken(String token){
        try {
            Algorithm algo=Algorithm.HMAC256(token_secret);
            //JWTVerifier verifies the token,like token is valid (i.e., the signature is correct and claims are valid
            JWTVerifier jwtVerifier=JWT.require(algo).build();
            //  DecodedJWT for decoding information from the token
            DecodedJWT decodedJWT=jwtVerifier.verify(token);
            return  decodedJWT.getClaim("userId").asString(); //returning the decoded ID from the token

        }catch (UnsupportedEncodingException |JWTCreationException e){
            e.printStackTrace();
        }
        return null;
    }

    //TOKEN VALID
    public boolean isTokenValid(String token){
        String userId=this.getUserIdToken(token);
        return userId !=null;
        // return true if the userID is not nObjectIdull which indicates a valid token

    }
}


