package com.xinyan.message.center.common.utils;


import com.xinyan.message.center.common.enums.ErrorMsgEnum;
import com.xinyan.message.center.common.exception.ServiceException;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

/**
 * @Author: Lsz
 * @Date: 2018/4/22 14:09
 * @Description: 参数校验类
 * @Remark:
 */
public class VerifyParamUtil {
    private final static ValidatorFactory FACTORY = Validation.buildDefaultValidatorFactory();

    /**
     * 校验对象属性
     *
     * @param object 需要校验的对象
     */
    public static void validateObject(Object object) {
        Validator validator = FACTORY.getValidator();
        Set<ConstraintViolation<Object>> violations = validator.validate(object);
        if (violations.size() == 0) {
            return;
        }
        for (ConstraintViolation<Object> violation : violations) {
            throw new ServiceException(ErrorMsgEnum.DATA_ERROR.getCode(), violation.getMessage());
        }
    }

    /**
     * 按分组校验对象属性
     *
     * @param object 对象
     * @param groups 分组class
     */
    public static void validateObject(Object object, Class<?>... groups) {
        Validator validator = FACTORY.getValidator();
        Set<ConstraintViolation<Object>> violations = validator.validate(object, groups);
        if (violations.size() == 0) {
            return;
        }
        for (ConstraintViolation<Object> violation : violations) {
            throw new ServiceException(ErrorMsgEnum.DATA_ERROR.getCode(), violation.getMessage());
        }
    }

}
