package cn.zheng.controller;

import cn.zheng.dto.MoodDTO;
import cn.zheng.service.MoodService;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.DispatcherServlet;

import java.util.List;
import java.util.Random;

/**
 * @author Zheng Shaobo
 * @version 1.0   2021/3/26 19:16
 */
@Controller
@RequestMapping("/mood")
public class MoodController {
    @Autowired
    private MoodService moodService;

    @RequestMapping("/findAll")
    public String findAll(Model model) {
        List<MoodDTO> moodDTOList = moodService.findAll();
        model.addAttribute("moods", moodDTOList);
        return "mood";
    }

    @RequestMapping("/{moodId}/praise")
    public String praise(Model model, @PathVariable(value = "moodId")String moodId,
                         @RequestParam(value = "userId")String userId) {
        boolean isPraise = moodService.praiseMood(userId, moodId);
        List<MoodDTO> moodDTOList = moodService.findAll();
        model.addAttribute("moods", moodDTOList);
        model.addAttribute("isPraise", isPraise);
        return "mood";
    }

    @RequestMapping("/{moodId}/praiseForRedis")
    public String praiseForRedis(Model model, @PathVariable(value = "moodId")String moodId,
                                 @RequestParam(value = "userId")String userId) {
        Random random = new Random();
        userId = random.nextInt(100) + "";
        boolean isPraise = moodService.praiseMoodForRedis(userId, moodId);
        List<MoodDTO> moodDTOList = moodService.findAllForRedis();
        model.addAttribute("moods", moodDTOList);
        model.addAttribute("isPraise", isPraise);
        return "mood";
    }

}
