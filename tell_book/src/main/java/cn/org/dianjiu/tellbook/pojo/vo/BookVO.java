package cn.org.dianjiu.tellbook.pojo.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

/**
 * Created by limengwei on 2019-09-30
 **/
@Getter
@Setter
public class BookVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("客户编码")
    private Integer id;

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