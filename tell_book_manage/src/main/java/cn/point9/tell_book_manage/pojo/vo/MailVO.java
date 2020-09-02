package cn.point9.tell_book_manage.pojo.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import java.io.Serializable;

/**
 * Created by limengwei on 2019-09-30
 **/
@Getter
@Setter
public class MailVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("收件人")
    @Email(message = "邮箱格式不正确")
    private String to;

    @ApiModelProperty("主题")
    private String subject;

    @ApiModelProperty("内容")
    private String content;
}