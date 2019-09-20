package life.majiang.community.service;


import io.ebean.Ebean;
import life.majiang.community.dto.QuestionDto;
import life.majiang.community.model.Question;
import life.majiang.community.model.User;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {


    public List<QuestionDto> getList() {
        List<Question> list = Ebean.find(Question.class).where().findList();
        List<QuestionDto> dtoList = new ArrayList<QuestionDto>();
        for (Question question : list) {
            User user = Ebean.find(User.class).where().eq("id", question.getCreator()).findUnique();
            QuestionDto dto = new QuestionDto();
            BeanUtils.copyProperties(question, dto);
            dto.setUser(user);
            dtoList.add(dto);
        }
        return dtoList;
    }
}
