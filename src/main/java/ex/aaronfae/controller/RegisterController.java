package ex.aaronfae.controller;

import ex.aaronfae.service.RegisterService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class RegisterController {

    private final RegisterService registerService;

    @Autowired
    public RegisterController(RegisterService registerService) {
        this.registerService = registerService;
    }


    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String register() {
        return "register";
    }

    @RequestMapping(value = "/join", method = RequestMethod.POST)
    public String register(String username, String password, RedirectAttributes redirectAttributes) {
        System.out.println("username:" + username);
        System.out.println("password:" + password);
        if (username == null || "".equals(username)) {
            redirectAttributes.addFlashAttribute("msg", "用户名不能为空");
            return "redirect:register";
        }
        if (password == null || "".equals(password)) {
            redirectAttributes.addFlashAttribute("msg", "密码不能为空");
            return "redirect:register";
        }
        if (registerService.registered(username)) {
            redirectAttributes.addFlashAttribute("msg", "用户名已存在");
            return "redirect:register";
        }
        String msg = registerService.register(username, password) ? "注册成功，并顺便帮你这傻X登录了^-^" : "^-^注册失败，你是不是傻狗";
        redirectAttributes.addFlashAttribute("msg", msg);
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(username, password, true);
        subject.login(token);
        subject.getSession().setAttribute("username", SecurityUtils.getSubject().getPrincipal());
        return "redirect:index";
    }
}
