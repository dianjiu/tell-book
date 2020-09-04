package cn.org.dianjiu.tellbook.controler;


import cn.org.dianjiu.tellbook.entity.User;
import cn.org.dianjiu.tellbook.pojo.req.UserReq;
import cn.org.dianjiu.tellbook.pojo.vo.UserVO;
import cn.org.dianjiu.tellbook.service.IUserService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Point9
 * @since 2019-09-24
 */
@Controller
@RequestMapping(value = "/user", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api(tags = "用户操作")
public class UserController {

    private Logger logger = LoggerFactory.getLogger(BookController.class);

    @Autowired
    private IUserService userService;

    /**
     * toUserList
     * @return
     */
    @RequestMapping(value = "/toUserList",method = RequestMethod.GET)
    public String toUserList(HttpServletRequest request, Model model) {
        Integer page=1;
        Integer size=10;
        try {
            Map<String, Object> map = queryUserListByPage(page, size);
            String code = String.valueOf(map.get("code"));
            if("200".equals(code)){
                IPage<User> questionPage = (IPage<User>) map.get("data");
                logger.info("分页查询到的返回数据为："+questionPage.toString());
                List<User> userList = questionPage.getRecords();
                logger.info("分页查询到的用户集合为："+userList);
                model.addAttribute("userList", userList);
            }
        } catch (Exception e) {
            logger.error("分页查询用户信息异常："+e);
        }
        return "user/list";
    }

    @ResponseBody
    @ApiOperation("分页查询用户列表")
    @GetMapping(value = "/queryUserListByPage/{page}/{size}")
    public Map<String, Object> queryUserListByPage(@PathVariable Integer page, @PathVariable Integer size) throws Exception {
        Map<String, Object> map = new HashMap<>();
        IPage<User> questionPage = userService.page(new Page<User>(page,size));
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
    @PostMapping(value = "/queryUserInfo")
    public Map<String, Object> queryUserInfo(@RequestBody UserVO userVO) throws Exception {
        Map<String, Object> map = new HashMap<>();
        User user = new User();
        BeanUtils.copyProperties(userVO,user);// org.springframework.beans.BeanUtils参数顺序和Apache的相反
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.like(StringUtils.isNotBlank(user.getUserName()), "user_name", user.getUserName());
        queryWrapper.like(StringUtils.isNotBlank(user.getEmail()), "email", user.getEmail());
        queryWrapper.like(null!=user.getAge(), "age", user.getAge());
        queryWrapper.eq(null!=user.getId(), "id", user.getId());
        List<User> userList = userService.list(queryWrapper);
        if (userList.size()==0) {
            map.put("code", 400);
        } else {
            map.put("code", 200);
            map.put("data", userList);
        }
        return map;
    }

    @ResponseBody
    @ApiOperation("校验原始密码")
    @RequestMapping(value = "/checkPWD",method = RequestMethod.POST)
    public Map<String, Object> checkPWD(@RequestBody UserVO userVO){
        Map<String, Object> map = new HashMap<>();
        boolean result = false;
        Integer id = userVO.getId();
        String oldPWD = userVO.getPassWord();
        if(StringUtils.isBlank(String.valueOf(id))||StringUtils.isBlank(String.valueOf(oldPWD))){
            map.put("code", 400);
            map.put("data", "参数不能为空！");
            return map;
        }
        try {
            Map<String, Object> queryMap = queryUserInfo(userVO);
            String code = String.valueOf(queryMap.get("code"));
            if("200".equals(code)){
                List<User> userList = (List<User>) queryMap.get("data");
                logger.info("根据ID"+id+"查询用户集合为"+userList);
                String passWord = userList.get(0).getPassWord();
                if(oldPWD.equals(passWord))
                    result = true;
            }
        } catch (Exception e) {
            logger.error("根据ID"+id+"查询用户密码异常"+e);
        }
        if (!result) {
            map.put("code", 400);
            map.put("data", "原始密码输入错误");
        } else {
            map.put("code", 200);
            map.put("data", "原始密码验证通过");
        }
        return map;
    }

    @ResponseBody
    @ApiOperation("添加用户")
    @RequestMapping(value = "/addUser",method = RequestMethod.POST)
    public Map<String, Object> addUser(@RequestBody UserReq userReq){
        Map<String, Object> map = new HashMap<>();
        User user = new User();
        BeanUtils.copyProperties(userReq,user);// org.springframework.beans.BeanUtils参数顺序和Apache的相反
        boolean result = userService.save(user);
        if (!result) {
            map.put("code", 400);
        } else {
            map.put("code", 200);
            map.put("data", result);
        }
        return map;
    }

    @ResponseBody
    @ApiOperation("更新用户")
    @PostMapping(value = "/updateUser")
    public Map<String, Object> updateUser(@RequestBody UserVO userVO) throws Exception {
        Map<String, Object> map = new HashMap<>();
        Integer id = userVO.getId();
        if(StringUtils.isBlank(String.valueOf(id))){
            map.put("code", 400);
            map.put("data", "用户编码不能为空！");
            return map;
        }
        User user = new User();
        BeanUtils.copyProperties(userVO,user);// org.springframework.beans.BeanUtils参数顺序和Apache的相反
        boolean result = userService.updateById(user);
        if (!result) {
            map.put("code", 400);
        } else {
            map.put("code", 200);
            map.put("data", result);
        }
        return map;
    }

    @ResponseBody
    @ApiOperation("删除用户")
    @PostMapping(value = "/delUser")
    public Map<String, Object> delUser(@RequestParam Integer userId) throws Exception {
        Map<String, Object> map = new HashMap<>();
        boolean result = userService.removeById(userId);
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
    @PostMapping(value = "/batchDelUser")
    public Map<String, Object> batchDelUser(@RequestParam List<Integer> ids) throws Exception {
        Map<String, Object> map = new HashMap<>();
        boolean result = userService.removeByIds(ids);
        if (!result) {
            map.put("code", 400);
        } else {
            map.put("code", 200);
            map.put("data", result);
        }
        return map;
    }
}

