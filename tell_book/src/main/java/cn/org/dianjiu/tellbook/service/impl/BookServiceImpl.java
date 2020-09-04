package cn.org.dianjiu.tellbook.service.impl;

import cn.point9.tell_book_manage.dao.BookMapper;
import cn.org.dianjiu.tellbook.entity.Book;
import cn.org.dianjiu.tellbook.pojo.req.BookReq;
import cn.org.dianjiu.tellbook.service.IBookService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Point9
 * @since 2019-09-24
 */
@Service
public class BookServiceImpl extends ServiceImpl<BookMapper, Book> implements IBookService {

    @Autowired
    private BookMapper bookMapper;

    @Override
    public IPage<Book> queryBookListByPage(BookReq bookReq) {
        long total = 0L;
        total = bookMapper.queryPageCount(bookReq);//获取总数据
        if (total != 0) {
            Integer page = bookReq.getPage();
            Integer size = bookReq.getSize();
            bookReq.setPage(page-1);
            List<Book> bookList = bookMapper.queryPageList(bookReq);
            Page<Book> bookPage = new Page<>();
            bookPage.setCurrent(page);
            bookPage.setSize(size);
            bookPage.setTotal(total);
            bookPage.setRecords(bookList);
            return bookPage;
        }
        return new Page<>();
    }
}
