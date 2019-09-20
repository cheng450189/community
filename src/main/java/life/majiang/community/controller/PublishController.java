package life.majiang.community.controller;


import io.ebean.Ebean;
import life.majiang.community.model.Question;
import life.majiang.community.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Controller
public class PublishController {


    @GetMapping("/publish")
    public String publish(){
        return "publish";
    }

    @PostMapping("/publish")
    public String getPublish(@RequestParam("title") String title,
                             @RequestParam("description") String description,
                             @RequestParam("tag") String tag,
                             HttpServletRequest request,
                             Model model){
        model.addAttribute("title",title);
        model.addAttribute("description",description);
        model.addAttribute("tag",tag);
        if(StringUtils.isEmpty(title)){
            model.addAttribute("error", "标题不能为空！");
            return "publish";
        }
        if(StringUtils.isEmpty(description)){
            model.addAttribute("error", "内容不能为空！");
            return "publish";
        }
        if(StringUtils.isEmpty(tag)){
            model.addAttribute("error", "标签不能为空！");
            return "publish";
        }
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
        if(user == null){
            model.addAttribute("error", "用户未登录！");
            return "publish";
        }
        Question question = new Question();
        question.setTitle(title);
        question.setDescription(description);
        question.setTag(tag);
        question.setCreator(user.getId());
        question.setGmtCreate(System.currentTimeMillis());
        question.setGmtModified(System.currentTimeMillis());
        question.setLikeCount(0);
        question.setViewCount(0);
        question.setCommentCount(0);
        question.save();
        return "redirect:/";
    }
}
