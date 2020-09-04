package cn.org.dianjiu.tellbook.controler;

import cn.org.dianjiu.tellbook.config.WebSecurityConfig;
import cn.org.dianjiu.tellbook.entity.User;
import cn.org.dianjiu.tellbook.pojo.vo.UserVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class LoginController {

    private Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private UserController userController;

    @GetMapping("/")
    public String index(@SessionAttribute(WebSecurityConfig.SESSION_KEY) String userName, Model model) {
        model.addAttribute("name", userName);
        return "index";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/loginPost")
    public String loginPost(String userName, String passWord, HttpSession session) throws Exception {
        Map<String, Object> map = new HashMap<>();
        UserVO userVO = new UserVO();
        userVO.setUserName(userName);
        Map<String, Object> userInfo = userController.queryUserInfo(userVO);
        List<User> userList = (List<User>) userInfo.get("data");
        if(userList.isEmpty()||userList.size()==0){
            map.put("success", false);
            map.put("message", "用户名或密码错误！");
        }
        if(!passWord.equals(userList.get(0).getPassWord())){
            map.put("success", false);
            map.put("message", "用户名或密码错误");
        }

        // 设置session
        session.setAttribute(WebSecurityConfig.SESSION_KEY, userName);

        map.put("success", true);
        map.put("message", "登录成功");
        return "redirect:/";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        // 移除session
        session.removeAttribute(WebSecurityConfig.SESSION_KEY);
        return "redirect:/login";
    }
}
