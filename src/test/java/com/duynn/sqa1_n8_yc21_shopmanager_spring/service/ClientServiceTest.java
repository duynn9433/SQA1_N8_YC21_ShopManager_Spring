package com.duynn.sqa1_n8_yc21_shopmanager_spring.service;

import com.duynn.sqa1_n8_yc21_shopmanager_spring.entity.Client;
import com.duynn.sqa1_n8_yc21_shopmanager_spring.repository.ClientRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;


//@ExtendWith(MockitoExtension.class)
@SpringBootTest
class ClientServiceTest {
//    @Mock
    @Autowired
    ClientRepository clientRepository;

//    @InjectMocks
    @Autowired
    ClientService clientService;

    Client client;
    @BeforeEach
    void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        client = Client.builder()
                .ID(1)
                .name("Nguyen Ngoc Duy")
                .address("Hanoi")
                .phoneNumber("0966215413")
                .isActive(true)
                .build();
    }

    @Test
    void create() {
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }

//    @Test
//    void searchClientByPhone() throws Exception {
//
//        // 1. create mock data
//        List<Client> clients = new ArrayList<>();
//        clients.add(client);
//        clients.add(client);
//
//        // 2. define behavior of Repository
//        when(clientRepository.searchByNameAndIsActiveIsTrue(anyString())).thenReturn(clients);
//
//        // 3. call service method
//        List<Client> actualClients = clientService.searchClientByPhone("0966215413");
//
//        // 4. assert the result
////        assertThat(actualBooks).isEqualTo(clients.size());
//        assertEquals(actualClients, clients);
//
//        // 4.1 ensure repository is called
//        verify(clientRepository).searchByNameAndIsActiveIsTrue("0966215413");
//    }
    @Test
    @DisplayName("Search Client By Phone Number No Record")
    void searchClientByPhone_NoRecord() throws Exception {
        List<Client> clients = new ArrayList<>();
        List<Client> actualClients = clientService.searchClientByPhone("0123456789");
        assertEquals(actualClients, clients);
    }

    @Test
    @DisplayName("Search Client By Phone Number Active False")
    void searchClientByPhone_ActiveFalse() throws Exception {
        List<Client> clients = new ArrayList<>();
        List<Client> actualClients = clientService.searchClientByPhone("1111111111");
        assertEquals(clients.size(), actualClients.size());
    }

    @Test
    @DisplayName("Search Client By Phone Number Active True")
    void searchClientByPhone_ActiveTrue() throws Exception {
        List<Client> actualClients = clientService.searchClientByPhone("2222222222");
        assertEquals(1, actualClients.size());
        Client client = new Client(4,"b","active true","2222222222",true);
        assertEquals(1, actualClients.size());
        assertEquals(client, actualClients.get(0));
    }
}