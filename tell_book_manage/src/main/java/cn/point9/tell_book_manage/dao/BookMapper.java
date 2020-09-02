package cn.point9.tell_book_manage.dao;

import cn.point9.tell_book_manage.entity.Book;
import cn.point9.tell_book_manage.pojo.req.BookReq;
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
