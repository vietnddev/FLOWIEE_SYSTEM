package com.flowiee.app.common.authorization;

import com.flowiee.app.common.utils.FlowieeUtil;
import com.flowiee.app.hethong.service.AccountService;
import com.flowiee.app.hethong.service.RoleService;
import com.flowiee.app.hethong.model.action.KhoTaiLieuAction;
import com.flowiee.app.hethong.model.module.SystemModule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class KiemTraQuyenModuleKhoTaiLieu {
    @Autowired
    private RoleService roleService;
    @Autowired
    private AccountService accountService;

    private final String module = SystemModule.KHO_TAI_LIEU.name();

    public boolean kiemTraRoleXemDocument() {
        if (accountService.getUserName().equals(FlowieeUtil.ADMINISTRATOR)) {
            return true;
        }
        final String action = KhoTaiLieuAction.READ_DOCUMENT.name();
        int accountId = accountService.findIdByUsername(accountService.getUserName());
        if (roleService.isAuthorized(accountId, module, action)) {
            return true;
        }
        return false;
    }

    public boolean kiemTraRoleThemMoiDocument() {
        if (accountService.getUserName().equals(FlowieeUtil.ADMINISTRATOR)) {
            return true;
        }
        final String action = KhoTaiLieuAction.CREATE_DOCUMENT.name();
        int accountId = accountService.findIdByUsername(accountService.getUserName());
        if (roleService.isAuthorized(accountId, module, action)) {
            return true;
        }
        return false;
    }

    public boolean kiemTraRoleCapNhatDocument() {
        if (accountService.getUserName().equals(FlowieeUtil.ADMINISTRATOR)) {
            return true;
        }
        final String action = KhoTaiLieuAction.UPDATE_DOCUMENT.name();
        int accountId = accountService.findIdByUsername(accountService.getUserName());
        if (roleService.isAuthorized(accountId, module, action)) {
            return true;
        }
        return false;
    }

    public boolean kiemTraRoleXoaDocument() {
        if (accountService.getUserName().equals(FlowieeUtil.ADMINISTRATOR)) {
            return true;
        }
        final String action = KhoTaiLieuAction.DELETE_DOCUMENT.name();
        int accountId = accountService.findIdByUsername(accountService.getUserName());
        if (roleService.isAuthorized(accountId, module, action)) {
            return true;
        }
        return false;
    }

    public boolean kiemTraRoleDiChuyenDocument() {
        if (accountService.getUserName().equals(FlowieeUtil.ADMINISTRATOR)) {
            return true;
        }
        final String action = KhoTaiLieuAction.MOVE_DOCUMENT.name();
        int accountId = accountService.findIdByUsername(accountService.getUserName());
        if (roleService.isAuthorized(accountId, module, action)) {
            return true;
        }
        return false;
    }

    public boolean kiemTraRoleSaoChepDocument() {
        if (accountService.getUserName().equals(FlowieeUtil.ADMINISTRATOR)) {
            return true;
        }
        final String action = KhoTaiLieuAction.COPY_DOCUMENT.name();
        int accountId = accountService.findIdByUsername(accountService.getUserName());
        if (roleService.isAuthorized(accountId, module, action)) {
            return true;
        }
        return false;
    }

    public boolean kiemTraRoleDownloadDocument() {
        if (accountService.getUserName().equals(FlowieeUtil.ADMINISTRATOR)) {
            return true;
        }
        final String action = KhoTaiLieuAction.DOWNLOAD_DOCUMENT.name();
        int accountId = accountService.findIdByUsername(accountService.getUserName());
        if (roleService.isAuthorized(accountId, module, action)) {
            return true;
        }
        return false;
    }

    public boolean kiemTraRoleChiaSeDocument() {
        if (accountService.getUserName().equals(FlowieeUtil.ADMINISTRATOR)) {
            return true;
        }
        final String action = KhoTaiLieuAction.SHARE_DOCUMENT.name();
        int accountId = accountService.findIdByUsername(accountService.getUserName());
        if (roleService.isAuthorized(accountId, module, action)) {
            return true;
        }
        return false;
    }
}