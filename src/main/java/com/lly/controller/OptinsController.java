package com.lly.controller;

import com.lly.pojo.Options;
import com.lly.pojo.Users;
import com.lly.service.OptionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class OptinsController {

    @Autowired
    private OptionsService os;

    //点击标题跳转到查看投票信息页面
    @RequestMapping("/voteView")
    public String voteView(Model model,Integer types,String title,String option,@RequestParam(defaultValue = "0") String votes,Integer otid){

        System.out.println("sssssssssss"+types);

        model.addAttribute("title",title);
        model.addAttribute("types",types);
        model.addAttribute("otid",otid);
        model.addAttribute("votes",votes);
        model.addAttribute("option",option);
        model.addAttribute("votes",Integer.valueOf(votes));
        model.addAttribute("optionsList",os.selectOptionsAll(otid));
        return "voteview";
    }

    //点击新投票根据state判断是否是管理员，是管理员跳转add.html
    @RequestMapping("/addView")
    public String  addView(HttpSession session,Model model){
        Users users = (Users)session.getAttribute("users");
        if (users.getState()==1){
            return "add";
        }
        return "cuowu";
    }

    //管理页面点击维护跳转到add.html进行投票选项修改
    @GetMapping("/update")
    public String  update(Integer tid, Integer types, String title, Model model,HttpSession session){

        model.addAttribute("types",types);
        session.setAttribute("tid",tid);
        model.addAttribute("title",title);
        session.setAttribute("optionsList",os.selectOptionsAll(tid));
        return "add";
    }

    //点击我要参与跳转vote.html
    @GetMapping("/vote")
    public String vote(String tid,Integer types,String option,@RequestParam(defaultValue = "0") String votes,String title,Model model){


        System.out.println("wwwwwwwwwwwwwwww"+types);
       List<Options> list = os.selectByIdOptons(Integer.valueOf(tid));
       model.addAttribute("tid",tid);
       model.addAttribute("list",list);
       model.addAttribute("types",types);
       model.addAttribute("option",option);
       model.addAttribute("votes",votes);
       model.addAttribute("title",title);
       return "vote";
    }
}
