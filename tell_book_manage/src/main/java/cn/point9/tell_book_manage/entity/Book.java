package cn.point9.tell_book_manage.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author Point9
 * @since 2019-09-24
 */
@TableName("t_book")
public class Book extends Model<Book> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String name;

    private String tell;

    private String email;

    private String addr;

    private Integer userCode;


    public Integer getId() {
        return id;
    }

    public Book setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Book setName(String name) {
        this.name = name;
        return this;
    }

    public String getTell() {
        return tell;
    }

    public Book setTell(String tell) {
        this.tell = tell;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public Book setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getAddr() {
        return addr;
    }

    public Book setAddr(String addr) {
        this.addr = addr;
        return this;
    }

    public Integer getUserCode() {
        return userCode;
    }

    public Book setUserCode(Integer userCode) {
        this.userCode = userCode;
        return this;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "Book{" +
        "id=" + id +
        ", name=" + name +
        ", tell=" + tell +
        ", email=" + email +
        ", addr=" + addr +
        ", userCode=" + userCode +
        "}";
    }
}
