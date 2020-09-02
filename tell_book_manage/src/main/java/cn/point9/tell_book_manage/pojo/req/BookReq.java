package cn.point9.tell_book_manage.pojo.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

/**
 * Created by limengwei on 2019-09-24
 **/
@Getter
@Setter
public class BookReq implements Serializable {

    private static final long serialVersionUID = 1L;

    //@Min(1)
    @ApiModelProperty("当前页")
    private Integer page;

    //@Size(max=50, min=1)
    @ApiModelProperty("页大小")
    private Integer size;

    @ApiModelProperty("客户名")
    private String name;

    @ApiModelProperty("客户电话")
    @Pattern(regexp = "^1(3|4|5|7|8)\\d{9}$",message = "手机号码格式错误")
    private String tell;

    @ApiModelProperty("客户邮件")
    @Email(message = "邮箱格式不正确")
    private String email;

    @ApiModelProperty("客户地址")
    private String addr;

    @ApiModelProperty("用户编码")
    private Integer userCode;
}
