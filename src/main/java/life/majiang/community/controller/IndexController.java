package life.majiang.community.controller;


import io.ebean.Ebean;
import life.majiang.community.dto.QuestionDto;
import life.majiang.community.model.Question;
import life.majiang.community.model.User;
import life.majiang.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.List;


@Controller
public class IndexController {

    @Autowired
    QuestionService service;


    @GetMapping("/")
    public String index(HttpServletRequest request,
                        Model model){
        Cookie[] cookies = request.getCookies();
        User user = null;
        if( cookies != null){
            for (Cookie cookie : cookies) {
                if(cookie.getName().equals("token")){
                    String token = cookie.getValue();
                    user = Ebean.find(User.class).where().eq("token", token).findUnique();
                    if(user != null){
                        request.getSession().setAttribute("user", user);
                    }
                    break;
                }
            }
        }
        List<QuestionDto> questionDtoList = service.getList();
        if(questionDtoList.size() != 0){
            model.addAttribute("questions", questionDtoList);
        }
        return "index";
    }

}
