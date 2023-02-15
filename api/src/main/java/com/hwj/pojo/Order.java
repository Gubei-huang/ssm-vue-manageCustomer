package com.hwj.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Order {
    private String num;
    private String paymentUsername;
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date paymentDate;
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date expireDate;
    private float cost;
    private String type;
    private String adminUsername;
}
