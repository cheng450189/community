package life.majiang.community.controller;


import life.majiang.community.dto.AccessTokenDto;
import life.majiang.community.dto.GitHubUserDto;
import life.majiang.community.provider.GitHubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthorizeController {

    @Autowired
    GitHubProvider gitHubProvider;

    @Value("${github.client_id}")
    private String clientId;
    @Value("${github.client_secret}")
    private String clientSecret;
    @Value("${github.redirect_uri}")
    private String RedirectUri;

    @GetMapping("/callback")
    public String callback(@RequestParam(name = "code") String code,
                           @RequestParam(name = "state") String state){
        AccessTokenDto tokenDto = new AccessTokenDto();
        tokenDto.setCode(code);
        tokenDto.setState(state);
        tokenDto.setClient_id(clientId);
        tokenDto.setRedirect_uri(RedirectUri);
        tokenDto.setClient_secret(clientSecret);
        String accessToken = gitHubProvider.getAccessToken(tokenDto);
        GitHubUserDto userDto = gitHubProvider.getUser(accessToken);
        System.out.println(userDto.getId());
        return "index";
    }
}
