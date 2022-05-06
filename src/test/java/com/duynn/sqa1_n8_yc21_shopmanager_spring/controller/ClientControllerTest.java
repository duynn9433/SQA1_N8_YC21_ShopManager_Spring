package com.duynn.sqa1_n8_yc21_shopmanager_spring.controller;

import com.duynn.sqa1_n8_yc21_shopmanager_spring.entity.Client;
import com.duynn.sqa1_n8_yc21_shopmanager_spring.repository.ClientRepository;
import com.duynn.sqa1_n8_yc21_shopmanager_spring.service.ClientService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static net.bytebuddy.matcher.ElementMatchers.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
@AutoConfigureMockMvc
class ClientControllerTest {
    @Autowired
    MockMvc mockMvc;
    @Autowired
    ClientRepository clientRepository;

    List<Client> clients;
    Client client;
    ClientService clientService;

    @BeforeEach
    void setUp() {
        client = new Client(1, "Nguyễn Ngọc Duy", "hanoi", "0966215413", true);
        clients = new ArrayList<>();
        clients.add(client);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    @DisplayName("Them khach hang thanh cong")
    public void newClient() throws Exception {
        this.mockMvc.perform(post("/client/add")
                        .param("name", "Test")
                        .param("address", "Ha noi")
                        .param("phoneNumber", "0833081220"))
                .andDo(print())
                .andExpect(view().name("client/AddSuccess"))
                .andExpect(forwardedUrl("/WEB-INF/jsp/client/AddSuccess.jsp"));
    }

    @Test
    @DisplayName("Xac nhan khach hang thanh cong")
    public void acceptClient() throws Exception {
        this.mockMvc.perform(post("/client/accept")
                        .param("name", "Test")
                        .param("address", "Ha noi")
                        .param("phoneNumber", "0833081220"))
                .andDo(print())
                .andExpect(view().name("client/AddSuccess"))
                .andExpect(forwardedUrl("/WEB-INF/jsp/client/AddSuccess.jsp"));
    }

    @Test
    @DisplayName("Khong co khach hang")
    public void searchClient() throws Exception {
        clients = new ArrayList<>();
        this.mockMvc.perform(post("/client/search")
                        .param("search_phone", "000000000"))
                .andDo(print())
                .andExpect(request().sessionAttribute("listClient", clients))
                .andExpect(view().name("manager/ManagementClientView"))
                .andExpect(forwardedUrl("/WEB-INF/jsp/manager/ManagementClientView.jsp"));
    }

    @Test
    @DisplayName("Tim thay khach hang")
    public void searchClient3() throws Exception {
        this.mockMvc.perform(post("/client/search")
                        .param("search_phone", "0966215413"))
                .andDo(print())
                .andExpect(request().sessionAttribute("listClient", clients))
                .andExpect(view().name("manager/ManagementClientView"))
                .andExpect(forwardedUrl("/WEB-INF/jsp/manager/ManagementClientView.jsp"));
    }

    @Test
    @DisplayName("Chon khach hang de sua thanh cong")
    public void editClient() throws Exception {
        this.mockMvc.perform(post("/client/edit")
                        .sessionAttr("client",client))
                .andDo(print())
                .andExpect(view().name("manager/EditClientView"))
                .andExpect(forwardedUrl("/WEB-INF/jsp/manager/EditClientView.jsp"));
    }

    @Test
    @DisplayName("Sua khong thanh cong khach hang")
    public void updateClient() throws Exception {
        clients = new ArrayList<>();
        this.mockMvc.perform(post("/client/update")
                        .param("name", "")
                        .param("address", "")
                        .param("phoneNumber", ""))
                .andDo(print())
                .andExpect(view().name("manager/edit"))
                .andExpect(forwardedUrl("/WEB-INF/jsp/manager/ManagementClientView.jsp"));
    }

    @Test
    @DisplayName("Sua khach hang thanh cong")
    public void updateClient2() throws Exception {
        clients = new ArrayList<>();
        this.mockMvc.perform(post("/client/update")
                        .param("name", "Test")
                        .param("address", "Ha noi")
                        .param("phoneNumber", "0833081220"))
                .andDo(print())
                .andExpect(request().sessionAttribute("listClient", clients))
                .andExpect(view().name("manager/ManagementClientView"))
                .andExpect(forwardedUrl("/WEB-INF/jsp/manager/ManagementClientView.jsp"));
    }

    @Test
    @DisplayName("Xoa khach hang thanh cong")
    public void deleteClient() throws Exception {
        String phone = "";
        clientService = new ClientService();
        clients = clientService.searchClientByPhone(phone.trim());
        client = clients.get(0);
        clients = new ArrayList<>();
        this.mockMvc.perform(post("/client/delete")
                        .param("id", String.valueOf(client.getID())))
                .andDo(print())
                .andExpect(request().sessionAttribute("listClient", clients))
                .andExpect(view().name("manager/ManagementClientView"))
                .andExpect(forwardedUrl("/WEB-INF/jsp/manager/ManagementClientView.jsp"));
    }
}