package com.flowiee.app.base.vld;

import com.flowiee.app.base.BaseAuthorize;
import com.flowiee.app.utils.AppConstants;
import org.springframework.stereotype.Component;

@Component
public class ValidateModuleSystem extends BaseAuthorize {
    public boolean readPermission(boolean throwException) {
        return isAuthorized(AppConstants.SYSTEM_ACTION.SYS_ROLE_READ.name(), throwException);
    }

    public boolean readAccount(boolean throwException) {
        return isAuthorized(AppConstants.SYSTEM_ACTION.SYS_ACCOUNT_READ.name(), throwException);
    }

    public boolean insertAccount(boolean throwException) {
        return isAuthorized(AppConstants.SYSTEM_ACTION.SYS_ACCOUNT_CREATE.name(), throwException);
    }

    public boolean updateAccount(boolean throwException) {
        return isAuthorized(AppConstants.SYSTEM_ACTION.SYS_ACCOUNT_UPDATE.name(), throwException);
    }

    public boolean deleteAccount(boolean throwException) {
        return isAuthorized(AppConstants.SYSTEM_ACTION.SYS_ACCOUNT_DELETE.name(), throwException);
    }

    public boolean readLog(boolean throwException) {
        return isAuthorized(AppConstants.SYSTEM_ACTION.SYS_LOG_READ.name(), throwException);
    }

    public boolean setupConfig(boolean throwException) {
        return isAuthorized("CONFIG", throwException);
    }
}