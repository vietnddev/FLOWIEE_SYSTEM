package com.flowiee.pms.service.system.impl;

import com.flowiee.pms.entity.system.Account;
import com.flowiee.pms.entity.system.SystemLog;
import com.flowiee.pms.repository.system.SystemLogRepository;
import com.flowiee.pms.service.BaseService;
import com.flowiee.pms.service.system.SystemLogService;

import com.flowiee.pms.utils.CommonUtils;
import com.flowiee.pms.utils.LogUtils;
import com.flowiee.pms.utils.constants.LogType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class SystemLogServiceImpl extends BaseService implements SystemLogService {
    @Autowired
    private SystemLogRepository systemLogRepo;

    @Override
    public Page<SystemLog> findAll(int pageSize, int pageNum) {
        Pageable pageable = PageRequest.of(pageNum, pageSize, Sort.by("createdAt").descending());
        return systemLogRepo.findAll(pageable);
    }

    @Override
    public SystemLog writeLogCreate(String module, String function, String object, String title, String content) {
        return this.writeLog(module, function, object, LogType.I, title, content, null);
    }

    @Override
    public SystemLog writeLogUpdate(String module, String function, String object, String title, Map<String, Object[]> logChanges) {
        return this.writeLog(module, function, object, LogType.U, title, LogUtils.getValueChanges(logChanges)[0], LogUtils.getValueChanges(logChanges)[1]);
    }

    @Override
    public SystemLog writeLogUpdate(String module, String function, String object, String title, String content) {
        return this.writeLog(module, function, object, LogType.U, title, content, null);
    }

    @Override
    public SystemLog writeLogUpdate(String module, String function, String object, String title, String content, String contentChange) {
        return this.writeLog(module, function, object, LogType.U, title, content, contentChange);
    }

    @Override
    public SystemLog writeLogDelete(String module, String function, String object, String title, String content) {
        return this.writeLog(module, function, object, LogType.D, title, content, null);
    }

    @Override
    public SystemLog writeLog(String module, String function, String object, LogType mode, String title, String content, String contentChange) {
        SystemLog systemLog = new SystemLog();
        systemLog.setModule(module);
        systemLog.setFunction(function);
        systemLog.setObject(object);
        systemLog.setMode(mode.name());
        systemLog.setTitle(title);
        systemLog.setContent(content);
        systemLog.setContentChange(contentChange);
        systemLog.setIp(CommonUtils.getUserPrincipal().getIp());
        systemLog.setAccount(new Account(CommonUtils.getUserPrincipal().getId()));
        return systemLogRepo.save(systemLog);
    }
}