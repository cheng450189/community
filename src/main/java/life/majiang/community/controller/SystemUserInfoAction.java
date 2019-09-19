package life.majiang.community.controller;


import io.ebean.Ebean;
import life.majiang.community.mode.SystemUserInfo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SystemUserInfoAction {

    @GetMapping("/user/all")
    public List<SystemUserInfo> user(){
        return Ebean.find(SystemUserInfo.class).findList();

    }
}
