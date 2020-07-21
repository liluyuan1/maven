package com.lly.controller;

import com.lly.pojo.Users;
import com.lly.service.UsersService;
import com.lly.vo.UsersVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class UsersController {

    @Autowired
    private UsersService us;


    @RequestMapping("/")
    public String index(){

         return "login";
    }

    @PostMapping("/login")
    public String  login(UsersVo uvo, HttpSession session, Model model, HttpServletRequest request){

        Users users = new Users();
        BeanUtils.copyProperties(uvo,users);
        Users user = us.selectUsers(users);
        if(user != null){
          List<String> ulist = (List<String>) request.getServletContext().getAttribute("username");
          if(ulist.contains(user.getUname())){
              model.addAttribute("shibai","用户已在线");
             return "login";
          }else {
              ulist.add( user.getUname());
              request.getServletContext().setAttribute("username",ulist);
              session.setAttribute("users",user);
              return "redirect:/voteList";
          }

        }
        model.addAttribute("shibai","用户名或密码错误");

        return "login";
    }

    @PostMapping("regist")
    public String regist(UsersVo uvo,HttpSession session,Model model){
        Users users = new Users();
        BeanUtils.copyProperties(uvo,users);

        if(us.addUsers(users)>0){
            //session.setAttribute("users",users);
           // return "redirect:/voteList";
            return "login";
        }
        return "regist";
    }

    @ResponseBody
    @PostMapping("/xz")
    public boolean unameXz(String uname){
        Users users = new Users();
       users.setUname(uname);

        return us.selectByname(users);
    }

    //用户注销
    @RequestMapping("/zhuxiao")
    public String zhuxioa(HttpSession session) {
        session.invalidate();
        return "login";
    }

}
