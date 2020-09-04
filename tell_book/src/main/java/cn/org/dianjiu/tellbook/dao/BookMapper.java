package cn.org.dianjiu.tellbook.dao;

import cn.org.dianjiu.tellbook.entity.Book;
import cn.org.dianjiu.tellbook.pojo.req.BookReq;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Point9
 * @since 2019-09-24
 */
@Component
public interface BookMapper extends BaseMapper<Book> {

    long queryPageCount(BookReq bookReq);

    List<Book> queryPageList(BookReq bookReq);
}
