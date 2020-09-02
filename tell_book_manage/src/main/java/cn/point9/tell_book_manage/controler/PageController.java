package cn.point9.tell_book_manage.controler;

import cn.point9.tell_book_manage.entity.Book;
import cn.point9.tell_book_manage.entity.User;
import cn.point9.tell_book_manage.pojo.vo.BookVO;
import cn.point9.tell_book_manage.pojo.vo.UserVO;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * Created by limengwei on 2019-09-24
 **/

@Controller
@Api(tags = "页面控制器")
public class PageController {

    private Logger logger = LoggerFactory.getLogger(PageController.class);

    @Autowired
    private UserController userController;

    @Autowired
    private BookController bookController;


//    @RequestMapping(value="/",method= RequestMethod.GET)
//    public String toPageIndex(Model model){
//        model.addAttribute("name", "点九Point9");
//        //return "redirect:/user/list";
//        //return "forward:/user/list";
//        return "index";
//    }

    @RequestMapping(value="welcome",method= RequestMethod.GET)
    public String toPageWelcome(Model model){
        return "welcome";
    }

    @RequestMapping(value="404",method= RequestMethod.GET)
    public String to404Page(Model model){
        return "404";
    }

    @RequestMapping(value="toUserAdd",method= RequestMethod.GET)
    public String toUserAdd(Model model){
        return "user/add";
    }

    @RequestMapping(value="toBookAdd",method= RequestMethod.GET)
    public String toBookAdd(Model model){
        return "book/add";
    }

    @RequestMapping(value="toUserUpdate",method= RequestMethod.GET)
    public String toUserUpdate(HttpServletRequest request, Model model){
        String id = request.getParameter("id");
        UserVO userVO = new UserVO();
        userVO.setId(Integer.valueOf(id));
        try {
            Map<String, Object> map = userController.queryUserInfo(userVO);
            String code = String.valueOf(map.get("code"));
            if("200".equals(code)){
                List<User> userList = (List<User>) map.get("data");
                logger.info("根据ID"+id+"查询用户集合为"+userList);
                model.addAttribute("user", userList.get(0));
            }
        } catch (Exception e) {
            logger.error("根据ID"+id+"查询用户信息异常"+e);
        }
        return "user/update";
    }

    @RequestMapping(value="toBookUpdate",method= RequestMethod.GET)
    public String toBookUpdate(HttpServletRequest request, Model model){
        String id = request.getParameter("id");
        BookVO bookVO = new BookVO();
        bookVO.setId(Integer.valueOf(id));
        try {
            Map<String, Object> map = bookController.queryBookInfo(bookVO);
            String code = String.valueOf(map.get("code"));
            if("200".equals(code)){
                List<Book> bookList = (List<Book>) map.get("data");
                logger.info("根据ID"+id+"查询客户集合为"+bookList);
                model.addAttribute("book", bookList.get(0));
            }
        } catch (Exception e) {
            logger.error("根据ID"+id+"查询客户信息异常"+e);
        }
        return "book/update";
    }

    @RequestMapping(value="toUpdatePassWord",method= RequestMethod.GET)
    public String toUpdatePassWord(HttpServletRequest request, Model model){
        String id = request.getParameter("id");
        model.addAttribute("userId", id);
        return "user/password";
    }

    @RequestMapping(value="toSendMail",method= RequestMethod.GET)
    public String toSendMail(HttpServletRequest request, Model model) {
        String id = request.getParameter("id");
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        model.addAttribute("bookId", id);
        model.addAttribute("bookName", name);
        model.addAttribute("bookEmail", email);
        return "book/mail";
    }

/*    @RequestMapping(value="sendSimpleMail",method= RequestMethod.GET)
    public String sendSimpleMail(@RequestBody @Valid MailVO mailVO) {
        mailService.sendSimpleMail("mail@point9.cn", "实体类中的字段错误", "在user实体类中有个字段错了");

        return "index";
    }*/

//    @RequestMapping(value="toSearchUser",method= RequestMethod.POST)
//    public String toSearchUser(@RequestBody UserVO userVO, Model model){
//        try {
//            Map<String, Object> map = userController.queryUserInfo(userVO);
//            String code = String.valueOf(map.get("code"));
//            if("200".equals(code)){
//                List<User> userList = (List<User>) map.get("data");
//                logger.info("模糊查询用户集合为"+userList);
//                model.addAttribute("userList", userList);
//            }
//        } catch (Exception e) {
//            logger.error("模糊查询用户信息异常"+e);
//        }
//        return "user/list";
//    }

}
