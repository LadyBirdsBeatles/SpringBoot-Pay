package com.xiaochen.beatles.pojo;

import lombok.*;

import java.io.Serializable;
import java.util.Date;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Pay implements Serializable {
    private Integer payId;

    private long payMoney;

    private Date payDate;

    private String paySources;
}