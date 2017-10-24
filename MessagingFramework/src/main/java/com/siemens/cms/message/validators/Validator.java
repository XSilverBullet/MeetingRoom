package com.siemens.cms.message.validators;

import java.util.Map;

/**
 * Created by Matteo on 6/2/16.
 */
public interface Validator {
    boolean isValid(String token);
    Map<String, Object> getContent(String token);
}
