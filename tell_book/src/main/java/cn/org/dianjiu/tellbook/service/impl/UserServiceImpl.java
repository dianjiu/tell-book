package cn.org.dianjiu.tellbook.service.impl;

import cn.point9.tell_book_manage.dao.UserMapper;
import cn.org.dianjiu.tellbook.entity.User;
import cn.org.dianjiu.tellbook.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Point9
 * @since 2019-09-24
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

}
