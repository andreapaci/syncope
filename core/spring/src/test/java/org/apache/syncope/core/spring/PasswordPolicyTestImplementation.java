package org.apache.syncope.core.spring;

import org.apache.syncope.core.persistence.api.entity.Implementation;
import org.apache.syncope.core.persistence.api.entity.policy.PasswordPolicy;

import java.util.ArrayList;
import java.util.List;

public class PasswordPolicyTestImplementation implements PasswordPolicy {
        private Boolean isAllowNull;
    private  int historyLenght;
    private final List<Implementation> rulesList;
    private  String desc;
    private  String key;

    public PasswordPolicyTestImplementation() {
        this.rulesList = new ArrayList<>();
    }

    @Override
        public boolean isAllowNullPassword() {
        return  this.isAllowNull;
    }

        @Override
        public void setAllowNullPassword(boolean allowNullPassword) {
        this.isAllowNull = allowNullPassword;
    }

        @Override
        public int getHistoryLength() {
        return  this.historyLenght;
    }

        @Override
        public void setHistoryLength(int inHistoryLength) {
            this.historyLenght = inHistoryLength;
    }

        @Override
        public boolean add(Implementation rule) {
            this.rulesList.add(rule);
        return true;
    }

        @Override
        public List<Implementation> getRules() {
        return  this.rulesList;
    }

        @Override
        public String getDescription() {
        return  this.desc;
    }

        @Override
        public void setDescription(String description) {
            this.desc = description;
    }

        @Override
        public String getKey() {
        return "key";
    }

}
