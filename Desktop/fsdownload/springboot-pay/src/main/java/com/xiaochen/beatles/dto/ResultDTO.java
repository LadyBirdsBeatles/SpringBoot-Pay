package com.xiaochen.beatles.dto;

import com.alibaba.fastjson.JSONObject;
import com.xiaochen.beatles.constants.ResultConstants;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement(name = "Result")
public class ResultDTO<T> implements Serializable {
    /**
     * 返回数据
     */
    private T returnData;

    /**
     * 返回码
     *
     *
     */
    private int returnCode;
    /**
     * 返回描述
     */
    private String returnDesc;

    /**
     * 代替原有的successResult方法<br/>
     * 如果传入的model与T不相符时，会在编译期报错<br/>
     * 举例：<br/>
     * ResultDTO&lt;Integer&gt; result1 = ResultDTO.successfy("String"); -->
     * IDE报错<br/>
     * ResultDTO&lt;Integer&gt; result2 = ResultDTO.successResult("String"); -->
     * 运行期抛出ClassCastException异常<br/>
     * 另外，不建议model为Void，如果不需要返回数据，那也就没有必要用ResultDTO
     */
    public static <T> ResultDTO<T> successfy(T model, String desc) {
        return new ResultDTO<>(model, ResultConstants.SUCCESS, desc);
    }

    /**
     * 代替原有的failResult方法，详看#successfy方法的说明
     *
     */
    public static <T> ResultDTO<T> fail(String errorMsg) {
        return new ResultDTO<>(null, ResultConstants.FAIL, errorMsg);
    }

    /**
     * 重新实现toString()函数方便在日志中打印DTO信息.
     */
    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    public JSONObject toJson() {
        return (JSONObject) JSONObject.toJSON(this);
    }

//	public boolean isSuccess() {
//		return ResultConstants.SUCCESS == this.returnCode;
//	}

}
