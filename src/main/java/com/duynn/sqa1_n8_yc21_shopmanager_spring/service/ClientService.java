package com.duynn.sqa1_n8_yc21_shopmanager_spring.service;

import com.duynn.sqa1_n8_yc21_shopmanager_spring.entity.Bill;
import com.duynn.sqa1_n8_yc21_shopmanager_spring.entity.Client;
import com.duynn.sqa1_n8_yc21_shopmanager_spring.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.logging.Logger;

@Service
@Transactional(rollbackFor = Exception.class)
public class ClientService implements GeneralService<Client>{
    @Autowired
    ClientRepository clientRepository;

    @Override
    public Client create(Client client) throws Exception {
        client.setActive(true);
        Logger.getLogger(ClientService.class.getName()).info("Create client: " + client.toString());
        return clientRepository.save(client);
    }

    @Override
    public Client update(Client client) throws Exception {
        client.setActive(true);
        Logger.getLogger(ClientService.class.getName()).info("Update client: " + client.toString());
        return clientRepository.save(client);
    }

    @Override
    public int delete(int id) throws Exception {
        Logger.getLogger(ClientService.class.getName()).info("Delete client: " + id);
        return clientRepository.deleteClient(id);
    }

    public List<Client> searchClientByPhone(String phone) throws Exception {
        Logger.getLogger(ClientService.class.getName()).info("Search client by phone: " + phone);
        return clientRepository.searchByNameAndIsActiveIsTrue(phone);
    }
}
