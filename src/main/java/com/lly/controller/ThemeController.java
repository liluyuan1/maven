package com.lly.controller;

import com.lly.pojo.Options;
import com.lly.pojo.Theme;
import com.lly.service.OptionsService;
import com.lly.service.ThemeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashSet;
import java.util.List;

@Controller
public class ThemeController {

    @Autowired
    private ThemeService ts;

    @Autowired
    private OptionsService os;

    //查询标题是否存在
    @ResponseBody
    @RequestMapping("/title")
    public boolean selectTitle(String title){

        return ts.selectByTitle(title) ;
    }


    //add页面点击确定跳转到这  添加投票标题，以及投票选项
    @PostMapping("/addVote")
    public String addVote(String title, Integer type, @RequestParam(defaultValue = "") HashSet<String> options,
                          HttpServletRequest request,Model model){

        Integer tid =(Integer) request.getSession().getAttribute("tid");

        List<Options> optionsList = (List<Options> )request.getSession().getAttribute("optionsList");
        int up = 0;

        if (optionsList != null){
           for(Options op:optionsList){
             String option =   request.getParameter("options_"+op.getOid());

             if(option != null){
                 Options opt = new Options();
                 opt.setOid(op.getOid());
                 opt.setOptions(option);
                up+=os.updateOptions(opt);
             }else {
                os.deleteByIdOptions(op.getOid());
             }
           }
        }
        boolean fla = false;
        if(!options.isEmpty()){
            Theme theme = new Theme();
            theme.setTypes(type);
            theme.setTid(tid);
            if(!ts.selectByTitle(title)){
                theme.setTitle(title);
            }
            if (ts.addVote(theme,options)){
                fla = true;
            }
        }
        if(fla == true || up >0){
            return "redirect:/voteList";
        }
        model.addAttribute("tjsb","添加失败");

        return "add";
    }
    //点击删除  根据标题id进行删除
    @GetMapping("/shangchu")
    public String deleteVote(String tid){
        if(ts.deleteVotes(Integer.valueOf(tid))){
            return "redirect:/whView";
        }
        return "cuowu";
    }
}
