package life.majiang.community.provider;


import com.alibaba.fastjson.JSON;
import life.majiang.community.dto.AccessTokenDto;
import life.majiang.community.dto.GitHubUserDto;
import okhttp3.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.rmi.server.ExportException;

@Component
public class GitHubProvider {

    @Value("${github.getaccess_token_url}")
    private String GIT_HUB_URL;
    @Value("${github.get_user_url}")
    private String GET_USER_URL;

    public String getAccessToken(AccessTokenDto dto){

         MediaType mediaType = MediaType.get("application/json; charset=utf-8");

        OkHttpClient client = new OkHttpClient();

        RequestBody body = RequestBody.create(mediaType, JSON.toJSONString(dto)) ;
        Request request = new Request.Builder()
                .url(GIT_HUB_URL)
                .post(body)
                .build();
        try{
            Response response = client.newCall(request).execute();
            String s = response.body().string();
            String token = s.split("&")[0].split("=")[1];
            return token;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }


    public GitHubUserDto getUser(String accessToken){
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(GET_USER_URL + accessToken)
                .build();
        try {
            Response response = client.newCall(request).execute();
            String s = response.body().string();
            GitHubUserDto dto = JSON.parseObject(s, GitHubUserDto.class);
            return  dto;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
