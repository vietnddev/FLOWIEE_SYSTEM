package com.flowiee.pms.service.system.impl;

import com.flowiee.pms.entity.system.SystemLog;
import com.flowiee.pms.utilities.ChangeLog;
import com.flowiee.pms.repository.system.SystemLogRepository;
import com.flowiee.pms.base.service.BaseService;
import com.flowiee.pms.service.system.SystemLogService;

import com.flowiee.pms.utilities.CommonUtils;
import com.flowiee.pms.utilities.enums.ACTION;
import com.flowiee.pms.utilities.enums.LogType;
import com.flowiee.pms.utilities.enums.MODULE;
import com.flowiee.pms.utilities.enums.MasterObject;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class SystemLogServiceImpl extends BaseService implements SystemLogService {
    SystemLogRepository mvSystemLogRepository;

    @Override
    public Page<SystemLog> findAll(int pageSize, int pageNum) {
        Pageable pageable = getPageable(pageNum, pageSize, Sort.by("createdAt").descending());
        Page<SystemLog> logs = mvSystemLogRepository.findAll(pageable);
        for (SystemLog systemLog : logs.getContent()) {
            systemLog.setAccountName(systemLog.getAccount() != null ? systemLog.getAccount().getFullName() : "");
            systemLog.setContentChange(systemLog.getContentChange() != null ? systemLog.getContentChange() : "");
        }
        return logs;
    }

    @Override
    public SystemLog writeLogCreate(MODULE module, ACTION function, MasterObject object, String title, String content) {
        return this.writeLog(module, function, object, LogType.I, title, content, "-");
    }

    @Override
    public SystemLog writeLogUpdate(MODULE module, ACTION function, MasterObject object, String title, ChangeLog changeLog) {
        return this.writeLog(module, function, object, LogType.U, title, changeLog.getOldValues(), changeLog.getNewValues());
    }

    @Override
    public SystemLog writeLogUpdate(MODULE module, ACTION function, MasterObject object, String title, String content) {
        return this.writeLog(module, function, object, LogType.U, title, content, "-");
    }

    @Override
    public SystemLog writeLogUpdate(MODULE module, ACTION function, MasterObject object, String title, String content, String contentChange) {
        return this.writeLog(module, function, object, LogType.U, title, content, contentChange);
    }

    @Override
    public SystemLog writeLogDelete(MODULE module, ACTION function, MasterObject object, String title, String content) {
        return this.writeLog(module, function, object, LogType.D, title, content, "-");
    }

    @Override
    public SystemLog writeLog(MODULE module, ACTION function, MasterObject object, LogType mode, String title, String content, String contentChange) {
        return mvSystemLogRepository.save(SystemLog.builder()
                .module(module.name())
                .function(function.name())
                .object(object.name())
                .mode(mode.name())
                .title(title)
                .content(content)
                .contentChange(contentChange)
                .ip(CommonUtils.getUserPrincipal().getIp())
                .account(CommonUtils.getUserPrincipal().toEntity())
                .build());
    }
}