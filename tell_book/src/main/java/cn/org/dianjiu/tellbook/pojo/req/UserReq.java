package cn.org.dianjiu.tellbook.pojo.req;

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
public class UserReq implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty("用户名")
    private String userName;

    @ApiModelProperty("密码")
    private String passWord;

    @ApiModelProperty("邮箱")
    @Email(message = "邮箱格式不正确")
    private String email;

    @ApiModelProperty("年龄")
    private Integer age;
}
