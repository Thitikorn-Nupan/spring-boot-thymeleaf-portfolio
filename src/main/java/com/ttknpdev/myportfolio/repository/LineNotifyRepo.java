package com.ttknpdev.myportfolio.repository;

import java.util.LinkedHashMap;

public interface LineNotifyRepo {
    LinkedHashMap<String, Object> sendLineNotifyMessage() throws Exception;
}
