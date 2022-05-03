package com.duynn.sqa1_n8_yc21_shopmanager_spring.controller;

import com.duynn.sqa1_n8_yc21_shopmanager_spring.entity.Client;
import com.duynn.sqa1_n8_yc21_shopmanager_spring.repository.ClientRepository;
import com.duynn.sqa1_n8_yc21_shopmanager_spring.service.ClientService;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.is;
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
    @DisplayName("Sai dinh dang SDT")
    public void newClient() throws Exception {
        String error = "Sai định dạng SĐT";
        this.mockMvc.perform(post("manager/add")
                        .param("name", "Test")
                        .param("address", "Test")
                        .param("phoneNumber", "aaaaaa"))
                .andDo(print())
                .andExpect(request().sessionAttribute("error", is(error)))
                .andExpect(view().name("/manager/ManagementClientView"))
                .andExpect(forwardedUrl("/WEB-INF/jsp/manager/ManagementClientView.jsp"));
    }

    @Test
    @DisplayName("Them khach hang thanh cong")
    public void newClient2() throws Exception {
        this.mockMvc.perform(post("manager/add")
                        .param("name", "Test")
                        .param("address", "Test")
                        .param("phoneNumber", "0833081220"))
                .andDo(print())
                .andExpect(request().sessionAttribute("error", is("")))
                .andExpect(view().name("/manager/ManagementClientView"))
                .andExpect(forwardedUrl("/WEB-INF/jsp/manager/ManagementClientView.jsp"));
    }

    @Test
    @DisplayName("")
    public void acceptClient() throws Exception {

    }

    @Test
    @DisplayName("Khong co khach hang nao")
    public void searchClient() throws Exception {
        clients.removeAll(clients);
        String error = "Không có khách hàng nào";
        this.mockMvc.perform(post("manager/search")
                        .param("search_phone", "0125369452"))
                .andDo(print())
                .andExpect(request().sessionAttribute("error", is(error)))
                .andExpect(request().sessionAttribute("listClient", clients))
                .andExpect(view().name("/manager/ManagementClientView"))
                .andExpect(forwardedUrl("/WEB-INF/jsp/manager/ManagementClientView.jsp"));
    }

    @Test
    @DisplayName("Tim thay khach hang")
    public void searchClient2() throws Exception {
        this.mockMvc.perform(post("manager/search")
                        .param("search_phone", "0966215413"))
                .andDo(print())
                .andExpect(request().sessionAttribute("error", is("")))
                .andExpect(request().sessionAttribute("listClient", clients))
                .andExpect(view().name("/manager/ManagementClientView"))
                .andExpect(forwardedUrl("/WEB-INF/jsp/manager/ManagementClientView.jsp"));
    }

    @Test
    @DisplayName("Sai dinh dang SDT")
    public void updateClient() throws Exception {
        String error = "Sai định dạng SĐT";
        this.mockMvc.perform(post("manager/update")
                        .param("name", "Test")
                        .param("address", "Test")
                        .param("phoneNumber", "aaaaaa"))
                .andDo(print())
                .andExpect(request().sessionAttribute("error", is(error)))
                .andExpect(request().sessionAttribute("listClient", clients.removeAll(clients)))
                .andExpect(view().name("/manager/ManagementClientView"))
                .andExpect(forwardedUrl("/WEB-INF/jsp/manager/ManagementClientView.jsp"));
    }

    @Test
    @DisplayName("Sua khach hang thanh cong")
    public void updateClient2() throws Exception {
        this.mockMvc.perform(post("manager/update")
                        .param("name", "Test")
                        .param("address", "Test")
                        .param("phoneNumber", "0833081220"))
                .andDo(print())
                .andExpect(request().sessionAttribute("error", is("")))
                .andExpect(request().sessionAttribute("listClient", clients.removeAll(clients)))
                .andExpect(view().name("/manager/ManagementClientView"))
                .andExpect(forwardedUrl("/WEB-INF/jsp/manager/ManagementClientView.jsp"));
    }

    @Test
    @DisplayName("Xoa khach hang thanh cong")
    public void deleteClient() throws Exception {
        clients = clientService.searchClientByPhone("");
        client = clients.get(0);
        this.mockMvc.perform(post("manager/delete")
                        .param("id", String.valueOf(client.getID())))
                .andDo(print())
                .andExpect(request().sessionAttribute("error", is("")))
                .andExpect(request().sessionAttribute("listClient", clients.removeAll(clients)))
                .andExpect(view().name("/manager/ManagementClientView"))
                .andExpect(forwardedUrl("/WEB-INF/jsp/manager/ManagementClientView.jsp"));
    }
}