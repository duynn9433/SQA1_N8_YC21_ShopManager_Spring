package com.duynn.sqa1_n8_yc21_shopmanager_spring.service;

import com.duynn.sqa1_n8_yc21_shopmanager_spring.entity.Bill;
import com.duynn.sqa1_n8_yc21_shopmanager_spring.entity.Client;
import com.duynn.sqa1_n8_yc21_shopmanager_spring.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class ClientService implements GeneralService<Client>{
    @Autowired
    ClientRepository clientRepository;

    @Override
    public Client create(Client client) throws Exception {
        client.setActive(true);
        return clientRepository.save(client);
    }

    @Override
    public Client update(Client client) throws Exception {
        client.setActive(true);
        return clientRepository.save(client);
    }

    @Override
    public int delete(Client client) throws Exception {
        return 0;
    }

    public int delete(int id) throws Exception {
        return clientRepository.deleteClient(id);
    }

    public List<Client> searchClientByPhone(String phone) throws Exception {
        return clientRepository.searchByNameAndIsActiveIsTrue(phone);
    }
}
