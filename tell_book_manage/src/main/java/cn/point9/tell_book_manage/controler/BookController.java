package cn.point9.tell_book_manage.controler;


import cn.point9.tell_book_manage.entity.Book;
import cn.point9.tell_book_manage.pojo.req.BookReq;
import cn.point9.tell_book_manage.pojo.vo.BookVO;
import cn.point9.tell_book_manage.pojo.vo.MailVO;
import cn.point9.tell_book_manage.service.IBookService;
import cn.point9.tell_book_manage.service.IMailService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 *  @Controller 用户控制层
 * @RespController  表示本类所有方法都是作为内容返回到浏览器
 * </p>
 *
 *
 * @author Point9
 * @since 2019-09-24
 */
@Controller
@RequestMapping(value = "/book", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api(tags = "通讯录操作")
public class BookController {

    private Logger logger = LoggerFactory.getLogger(BookController.class);

    @Autowired
    private IBookService bookService;

    @Autowired
    private IMailService mailService;

    /**
     * toBookList
     * @return
     */
    @RequestMapping(value = "/toBookList",method = RequestMethod.GET)
    public String toUserList(HttpServletRequest request, Model model) {
        BookReq bookReq = new BookReq();
        bookReq.setPage(1);
        bookReq.setSize(10);
        bookReq.setUserCode(9);//当前登录用户ID
        try {
            Map<String, Object> map = queryBookListByPage(bookReq);
            String code = String.valueOf(map.get("code"));
            if("200".equals(code)){
                IPage<Book> questionPage = (IPage<Book>) map.get("data");
                logger.info("分页查询到的返回数据为："+questionPage.toString());
                List<Book> bookList = questionPage.getRecords();
                logger.info("分页查询到的客户集合为："+bookList);
                model.addAttribute("bookList", bookList);
            }
        } catch (Exception e) {
            logger.error("分页查询客户信息异常："+e);
        }
        return "book/list";
    }

    @ResponseBody
    @ApiOperation("分页查询通讯录列表")
    @PostMapping(value = "/queryBookListByPage")
    public Map<String, Object> queryBookListByPage(@RequestBody @Valid BookReq BookReq) throws Exception {
        Map<String, Object> map = new HashMap<>();
        IPage<Book> questionPage = bookService.queryBookListByPage(BookReq);
        if (questionPage.getRecords().size() == 0) {
            map.put("code", 400);
        } else {
            map.put("code", 200);
            map.put("data", questionPage);
        }
        return map;
    }

    @ResponseBody
    @ApiOperation("模糊查询")
    @PostMapping(value = "/queryBookInfo")
    public Map<String, Object> queryBookInfo(@RequestBody BookVO bookVO) throws Exception {
        Map<String, Object> map = new HashMap<>();
        Book book = new Book();
        BeanUtils.copyProperties(bookVO,book);// org.springframework.beans.BeanUtils参数顺序和Apache的相反
        QueryWrapper<Book> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(null!=book.getId(), "id", book.getId());
        queryWrapper.like(StringUtils.isNotBlank(book.getName()), "name", book.getName());
        queryWrapper.like(StringUtils.isNotBlank(book.getTell()), "tell", book.getTell());
        queryWrapper.like(StringUtils.isNotBlank(book.getEmail()), "email", book.getEmail());
        queryWrapper.like(StringUtils.isNotBlank(book.getAddr()), "addr", book.getAddr());
        queryWrapper.eq(null!=book.getUserCode(), "user_code", book.getUserCode());
        List<Book> bookList = bookService.list(queryWrapper);
        if (bookList.size()==0) {
            map.put("code", 400);
        } else {
            map.put("code", 200);
            map.put("data", bookList);
        }
        return map;
    }

    @ResponseBody
    @ApiOperation("添加客户")
    @PostMapping(value = "/addBook")
    public Map<String, Object> addBook(@RequestBody BookReq BookReq) throws Exception {
        Map<String, Object> map = new HashMap<>();
        Book Book = new Book();
        BeanUtils.copyProperties(BookReq,Book);// org.springframework.beans.BeanUtils参数顺序和Apache的相反
        boolean result = bookService.save(Book);
        if (!result) {
            map.put("code", 400);
        } else {
            map.put("code", 200);
            map.put("data", result);
        }
        return map;
    }

    @ResponseBody
    @ApiOperation("更新客户")
    @PostMapping(value = "/updateBook")
    public Map<String, Object> updateBook(@RequestBody @Valid BookVO BookVO) throws Exception {
        Map<String, Object> map = new HashMap<>();
        Book Book = new Book();
        Integer id = BookVO.getId();
        if(StringUtils.isBlank(String.valueOf(id))){
            map.put("code", 200);
            map.put("data", "客户编码不能为空！");
            return map;
        }
        BeanUtils.copyProperties(BookVO,Book);// org.springframework.beans.BeanUtils参数顺序和Apache的相反
        boolean result = bookService.updateById(Book);
        if (!result) {
            map.put("code", 400);
        } else {
            map.put("code", 200);
            map.put("data", result);
        }
        return map;
    }

    @ResponseBody
    @ApiOperation("发送邮件")
    @PostMapping(value = "/sendSimpleMail")
    public Map<String, Object> sendSimpleMail(@RequestBody @Valid MailVO mailVO) throws Exception {
        Map<String, Object> map = new HashMap<>();
        String to = mailVO.getTo();
        if(StringUtils.isBlank(String.valueOf(to))){
            map.put("code", 200);
            map.put("data", "收件人不能为空！");
            return map;
        }
        try {
            mailService.sendSimpleMail(mailVO.getTo(), mailVO.getSubject(), mailVO.getContent());
            map.put("code", 200);
            map.put("data", "发送成功！");
        } catch (Exception e) {
            map.put("code", 400);
            map.put("data", "发送失败！");
            logger.error("发送邮件异常："+e);
        }
        return map;
    }

    @ResponseBody
    @ApiOperation("删除用户")
    @PostMapping(value = "/delBook")
    public Map<String, Object> delBook(@RequestParam @Valid Integer bookId) throws Exception {
        Map<String, Object> map = new HashMap<>();
        boolean result = bookService.removeById(bookId);
        if (!result) {
            map.put("code", 400);
        } else {
            map.put("code", 200);
            map.put("data", result);
        }
        return map;
    }

    @ResponseBody
    @ApiOperation("批量删除用户")
    @PostMapping(value = "/batchDelBook")
    public Map<String, Object> batchDelBook(@RequestParam @Valid List<Integer> ids) throws Exception {
        Map<String, Object> map = new HashMap<>();
        boolean result = bookService.removeByIds(ids);
        if (!result) {
            map.put("code", 400);
        } else {
            map.put("code", 200);
            map.put("data", result);
        }
        return map;
    }

//    /**
//     * tobooklist
//     * @return
//     */
//    @RequestMapping(value = "/toBookList",method = RequestMethod.GET)
//    public String hello(HttpServletRequest request, Model model) {
//        /*request.getServletContext().setAttribute("applicationMessage",
//                "springboot-application");
//        request.getSession().setAttribute("sessionMessage", "springboot-session");
//        request.setAttribute("requestMessage", "springboot-request");
//        request.setAttribute("url2",
//                "<span style='color:red'>www.baidu.cn</span>");*/
//        model.addAttribute("name", "www.baidu.cn");
//        return "hello";
//    }

}

