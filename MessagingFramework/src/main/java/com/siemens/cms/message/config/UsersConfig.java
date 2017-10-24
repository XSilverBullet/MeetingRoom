package com.siemens.cms.message.config;

import com.siemens.cms.message.model.User;
import com.siemens.cms.message.utils.FileUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Simon.Lau on 24-Nov-16.
 */
@Configuration
@ConfigurationProperties(prefix = "users")
public class UsersConfig {
    private String configFile;
    private List<User> users;

    public String getConfigFile() {
        return configFile;
    }

    public void setConfigFile(String configFile) {
        this.configFile = configFile;
    }

    public List<User> getUsers() {
        if (this.users == null) {
            setUsers(loadUsers(configFile));
        }
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    private List<User> loadUsers(String fileName) {
        if (fileName == null) {
            return null;
        }

        String fileContent = FileUtil.loadContentFromFile(fileName);
        JSONObject jsonObj = JSONObject.fromObject(fileContent);
        JSONArray jsonArray = JSONArray.fromObject(jsonObj.get("users"));
        if (!jsonArray.isArray()) {
            return null;
        }

        List<User> users = new LinkedList<>();
        for (Object obj : jsonArray) {
            JSONObject jsonObject = JSONObject.fromObject(obj);
            User user = (User)JSONObject.toBean(jsonObject, User.class);
            users.add(user);
        }

        return users;
    }
}
