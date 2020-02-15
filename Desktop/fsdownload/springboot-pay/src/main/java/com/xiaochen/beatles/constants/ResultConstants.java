package com.xiaochen.beatles.constants;

/**
 * Created by HUCX on 16/11/21.
 */
public interface ResultConstants {
    /**
     * 成功返回码
     */
	int SUCCESS = 0;
    /**
     * 失败返回码
     */
	int FAIL = 1;
    /**
     * 失败返回码：签名验证失败
     */
    String VERIFY_SIGNATURE_FAIL = "0013";
    /**
     * 失败返回码：切库失败
     */
    String SWITCH_DATABASE_FAIL="0011";
}
