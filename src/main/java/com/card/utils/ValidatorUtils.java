package com.card.utils;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

public class ValidatorUtils {
    private static Validator validator;

    static {
        validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    /**
     * ValidatorUtils.validateEntity(role);
     * 在controller接口中使用此方便校验实体参数的合法校验对象
     *
     * @param object 待校验对象
     * @param groups 待校验的组
     * @throws RuntimeException 校验不通过，则报RRException异常
     */
    public static void validateEntity(Object object, Class<?>... groups) throws RuntimeException {
        Set<ConstraintViolation<Object>> constraintViolations = validator.validate(object, groups);
        if (!constraintViolations.isEmpty()) {
            StringBuilder msg = new StringBuilder();
            for (ConstraintViolation<Object> constraint : constraintViolations) {
                msg.append(constraint.getMessage()).append("<br>");
            }
            throw new RuntimeException(msg.toString());
        }
    }
}
