package com.example.vo;

import com.example.config.Money;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.List;

/**
 * https://www.cnblogs.com/cangqinglang/p/11551912.html
 * https://blog.csdn.net/mzh_cn/article/details/80637015
 *
 */
@Data
@NoArgsConstructor
public class StudentVO {
    @NotBlank(message = "name不可为空")
    private String name;
    @Range(min = 0, max = 100, message = "年龄必须再[0-100]之间")
    private int age;
    @Email(message="非法邮件地址")
    private String email;
    @Pattern(regexp="^(\\d{6})(\\d{4})(\\d{2})(\\d{2})(\\d{3})([0-9]|X)$",message="身份证号不合规")
    private String cardNo;

    //自定义注解验证参数
    @Money
    private Double balance;

    @NotNull(message = "father不可为空")
    @Valid // 嵌套校验
    private FatherVO fatherVO;

    @NotEmpty(message = "fatherVOS不可为空")
    @Size(min = 1, max = 3, message = "fatherVOS长度必须在[1-3]之间")
    @Valid//list也可以嵌套校验
    private List<FatherVO> fatherVOS;
}
