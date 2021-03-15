package ru.xvromanova.springapp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.xvromanova.springapp.dao.AccountDAO;

@Controller
@RequestMapping("/accounts")
public class AccountsController {

    private final AccountDAO accountDAO;

    public AccountsController(AccountDAO accountDAO) {
        this.accountDAO = accountDAO;
    }

    @GetMapping()
    public String index(Model model){
        model.addAttribute("accounts", accountDAO.index());
        return "accounts/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model){
        model.addAttribute("account",accountDAO.show(id));
        return "accounts/show";
    }
}
