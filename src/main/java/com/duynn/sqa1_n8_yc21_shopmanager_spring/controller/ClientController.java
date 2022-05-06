package com.duynn.sqa1_n8_yc21_shopmanager_spring.controller;

import com.duynn.sqa1_n8_yc21_shopmanager_spring.entity.Bill;
import com.duynn.sqa1_n8_yc21_shopmanager_spring.entity.Client;
import com.duynn.sqa1_n8_yc21_shopmanager_spring.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/client")
public class ClientController {
    @Autowired
    private ClientService clientService;

    @PostMapping("/add")
    public String newClient(@ModelAttribute("client") Client client,
                            HttpServletRequest request) {
        String url ="";

        try {
            //check validation
            request.setAttribute("client", client);
            url ="client/AddSuccess";
        } catch (Exception e) {
            e.printStackTrace();
            url = "client/AddClient";
            request.setAttribute("errors", e.getMessage());
        }
        return url;
    }
    @PostMapping("/accept")
    public String acceptClient(@ModelAttribute("client") Client client, HttpServletRequest request) {
        String url ="";
        try {
            clientService.create(client);
            request.setAttribute("client", client);
            String return_view = (String) request.getSession().getAttribute("return_view");
            if(return_view != null) {
                ((Bill) request.getSession().getAttribute("bill")).setClient(client);
                url = return_view;
                request.getSession().setAttribute("error", "");
                return url;
            }
            url ="client/AddSuccess";
            request.getSession().setAttribute("addClientMsg", "Thêm thành công khách hàng");
        } catch (Exception e) {
            e.printStackTrace();
            url = "client/AddSuccess";
            request.setAttribute("errors", e.getMessage());
        }
        return url;
    }
    @PostMapping("/search")
    public String searchClient(@RequestParam("search_phone") String phone, HttpServletRequest request) {
        String url ="";
        HttpSession session = request.getSession();
        try {
            List<Client> list = clientService.searchClientByPhone(phone.trim());
            session.setAttribute("listClient", list);
            url = "manager/ManagementClientView";
        } catch (Exception e){

        }
        return url;
    }

    @PostMapping("/edit")
    public String updateClient(@ModelAttribute("client") Client client, HttpServletRequest request) {
        String url ="";
        HttpSession session = request.getSession();
        String eid = request.getParameter("eid");
        String ename = request.getParameter("ename");
        String eaddress = request.getParameter("eaddress");
        String ephoneNumber = request.getParameter("ephoneNumber");

        session.setAttribute("id",eid);
        session.setAttribute("name",ename);
        session.setAttribute("address",eaddress);
        session.setAttribute("phoneNumber",ephoneNumber);

        url = "manager/EditClientView";
        return url;
    }

    @GetMapping("/delete")
    public String deleteClient(@RequestParam("id") int id, HttpServletRequest request) {
        String url = "manager/ManagementClientView";
        try {
            HttpSession session = request.getSession();
            int res = clientService.delete(id);
            List<Client> list = new ArrayList<>();
            session.setAttribute("listClient", list);
            url = "manager/ManagementClientView";
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return url;
    }

    @PostMapping("/update")
    public String updateClient(@RequestParam("name") String name,
                               @RequestParam("address") String address,
                               @RequestParam("phoneNumber") String phoneNumber,
                               @RequestParam("id") String sid,
                               HttpServletRequest request) {
        int id = Integer.parseInt(sid);
        String url = "";
        Client client = new Client();
        client.setID(id);
        client.setName(name);
        client.setPhoneNumber(phoneNumber);
        client.setAddress(address);

        System.out.println(client);
        try {
            Client success = clientService.update(client);
            request.getSession().removeAttribute("listClient");
            url = "manager/ManagementClientView";
        } catch (Exception e) {

        }
        return url;
    }

}

