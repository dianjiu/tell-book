package cn.org.dianjiu.tellbook.pojo.bo;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * Created by limengwei on 2019-08-26
 **/
@Getter
@Setter
public class RespBo<T> implements Serializable {

    private int errCode;
    private int detailCode;
    private String errMsg;
    private int retryCode;
    private T data;

    public RespBo() {

    }

    public RespBo(int errCode, String errMsg) {
        this.errCode = errCode;
        this.errMsg = errMsg;
    }

    public RespBo(int errCode, String errMsg, int retryCode) {
        this.errCode = errCode;
        this.errMsg = errMsg;
        this.retryCode = retryCode;
    }

    public RespBo(int detailCode, T data) {
        this.detailCode = detailCode;
        this.data = data;
    }

    public RespBo(int errCode, int detailCode, String errMsg, int retryCode, T data) {
        this.errCode = errCode;
        this.detailCode = detailCode;
        this.errMsg = errMsg;
        this.retryCode = retryCode;
        this.data = data;
    }
}

