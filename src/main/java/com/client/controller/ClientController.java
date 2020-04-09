package com.client.controller;

import com.client.entity.ClientInfo;
import com.client.service.ClientService;
import com.util.AppResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 查询客户列表
 * @author liyuxian
 * @time 2020-03-29
 */
@RestController
@RequestMapping("/client")
public class ClientController {

    private static final Logger logger = LoggerFactory.getLogger(ClientController.class);
    @Resource
    private ClientService clientService;

    /**
     * 客户列表查询
     * @param clientInfo
     * @return
     */
    @PostMapping("listClient")
    public AppResponse listClient(ClientInfo clientInfo){
        try{
            return clientService.listClient(clientInfo);
        }catch(Exception e){
            logger.error("查询客户列表错误", e);
            System.out.println(e.toString());
            throw e;
        }

    }


}
