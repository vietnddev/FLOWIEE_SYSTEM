package com.flowiee.app.common.authorization;

import com.flowiee.app.common.utils.FlowieeUtil;
import com.flowiee.app.hethong.model.action.RoleAction;
import com.flowiee.app.hethong.service.AccountService;
import com.flowiee.app.hethong.service.RoleService;
import com.flowiee.app.hethong.model.module.SystemModule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class KiemTraQuyenModulePhanQuyen {
    @Autowired
    private RoleService roleService;
    @Autowired
    private AccountService accountService;

    private final String module = SystemModule.HE_THONG.name();

    public boolean kiemTraQuyenXem() {
        if (accountService.getUserName().equals(FlowieeUtil.ADMINISTRATOR)) {
            return true;
        }
        final String action = RoleAction.READ_ROLE.name();
        int accountId = accountService.findIdByUsername(accountService.getUserName());
        if (roleService.isAuthorized(accountId, module, action)) {
            return true;
        }
        return false;
    }

    public boolean kiemTraQuyenCapNhat() {
        if (accountService.getUserName().equals(FlowieeUtil.ADMINISTRATOR)) {
            return true;
        }
        final String action = RoleAction.UPDATE_ROLE.name();
        int accountId = accountService.findIdByUsername(accountService.getUserName());
        if (roleService.isAuthorized(accountId, module, action)) {
            return true;
        }
        return false;
    }
}