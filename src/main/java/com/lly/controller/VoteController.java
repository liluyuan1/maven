package com.lly.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.lly.pojo.Options;
import com.lly.pojo.Users;
import com.lly.pojo.Vote;
import com.lly.service.VoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@Controller
public class VoteController {


    @Autowired
    private VoteService vs;

    //查询首页
    /*
    @RequestMapping("/voteList")
    public String voteList(Model model,@RequestParam(defaultValue = "1") Integer pageIndex,String title){

        List<Map<String,Object>> list =  vs.selectAll(pageIndex,title);
        System.out.println("voteList======"+list.get(0));
        model.addAttribute("title",title);
        model.addAttribute("votelist",list);
        model.addAttribute("pageIndex",pageIndex);
        return "votelist";
    }
*/
    @RequestMapping("/voteList")
    public String voteList(Model model,@RequestParam(defaultValue = "1") Integer pageIndex,String title){

        Page<Options> page = PageHelper.startPage(pageIndex,3);
        PageHelper.orderBy("tid DESC");
        List<Map<String,Object>> list =  vs.selectAll(title);
        model.addAttribute("title",title);
        model.addAttribute("votelist",list);

        model.addAttribute("pages",page.getPages());
        model.addAttribute("pageIndex",pageIndex);
        return "votelist";
    }
    //点击维护按钮是管理员就跳转维护页面

    @RequestMapping("/whView")
    public String whView(Model model,@RequestParam(defaultValue = "1") Integer pageIndex,HttpSession session,String title) {

        Users users = (Users) session.getAttribute("users");
        if (users.getState() == 1) {
            Page<Options> page = PageHelper.startPage(pageIndex, 3);
            PageHelper.orderBy("tid DESC");
            List<Map<String, Object>> list = vs.selectAll(title);

            model.addAttribute("pages", page.getPages());
            model.addAttribute("votelist", list);
            model.addAttribute("pageIndex", pageIndex);
            model.addAttribute("wh", 1);
            model.addAttribute("title", title);
            return "votelist";
        }
        model.addAttribute("cuowu", "你不是管理员，没有权限");
        return "cuowu";
    }
    /*
    @RequestMapping("/whView")
    public String whView(Model model,@RequestParam(defaultValue = "1") Integer pageIndex,HttpSession session,String title){

        Users users = (Users) session.getAttribute("users");
        if (users.getState() == 1){
            List<Map<String,Object>> list =  vs.selectAll(pageIndex,title);

            model.addAttribute("votelist",list);
            model.addAttribute("pageIndex",pageIndex);
            model.addAttribute("wh",1);
            return "votelist";
        }
        model.addAttribute("cuowu","你不是管理员，没有权限");
        return "cuowu";
    }
*/
    //选则投票选项，点击投票添加
    @RequestMapping("/addVotes")
    public String addVotes(@RequestParam int tid,int options[], HttpSession session, Model model) {

        if(options == null) {
            model.addAttribute("cuowu","你没有选择投票选项");
            return "cuowu";
        }else {
            Users us = (Users)session.getAttribute("users");
            Vote vote = new Vote();
            vote.setVuid(us.getUid());
            vote.setVtid(tid);
            if(vs.addVote(vote,options)){
                return "redirect:/voteList";
            }
            model.addAttribute("cuowu","此标题你已经投过票不能重复投");
            return "cuowu";
        }
    }

}

