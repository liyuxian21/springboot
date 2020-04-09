package com.client.service;

import com.client.dao.ClientDao;
import com.client.entity.ClientInfo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.util.AppResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ClientService {

    @Resource
    private ClientDao clientDao;
    /**
     * 查询客户列表（分页）
     * @param clientInfo
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public AppResponse listClient(ClientInfo clientInfo) {
        PageHelper.startPage(clientInfo.getPageNum(),clientInfo.getPageSize());
        List<ClientInfo> clientInfoList=clientDao.listClient(clientInfo);
//        包装对象
        PageInfo<ClientInfo> pageData=new PageInfo<>(clientInfoList);
        return AppResponse.success("查询成功",pageData);
    }
}
