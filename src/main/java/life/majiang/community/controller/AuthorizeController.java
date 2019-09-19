package life.majiang.community.controller;


import io.ebean.Ebean;
import life.majiang.community.dto.AccessTokenDto;
import life.majiang.community.dto.GitHubUserDto;
import life.majiang.community.mapper.UserMapper;
import life.majiang.community.mode.User;
import life.majiang.community.provider.GitHubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

@Controller
public class AuthorizeController {

    @Autowired
    private GitHubProvider gitHubProvider;


    @Value("${github.client_id}")
    private String clientId;
    @Value("${github.client_secret}")
    private String clientSecret;
    @Value("${github.redirect_uri}")
    private String RedirectUri;

    @GetMapping("/callback")
    public String callback(@RequestParam(name = "code") String code,
                           @RequestParam(name = "state") String state,
                           HttpServletRequest request,
                           HttpServletResponse response){
        AccessTokenDto tokenDto = new AccessTokenDto();
        tokenDto.setCode(code);
        tokenDto.setState(state);
        tokenDto.setClient_id(clientId);
        tokenDto.setRedirect_uri(RedirectUri);
        tokenDto.setClient_secret(clientSecret);
        String accessToken = gitHubProvider.getAccessToken(tokenDto);
        GitHubUserDto userDto = gitHubProvider.getUser(accessToken);
        User user = Ebean.find(User.class).where().eq("accountId", String.valueOf(userDto.getId())).findUnique();
        if(user != null){
            response.addCookie(new Cookie("token", user.getToken()));
            return "redirect:/";
        }

        if (userDto != null ){
            user = new User();
            user.setName(userDto.getName());
            user.setToken(UUID.randomUUID().toString());
            user.setAccountId(String.valueOf(userDto.getId()));
            user.setGmtCreate(System.currentTimeMillis());
            user.setGmtModified(System.currentTimeMillis());
            user.save();
            response.addCookie(new Cookie("token", user.getToken()));
            return "redirect:/";
        }else {
            return "redirect:/";
        }
    }
}
