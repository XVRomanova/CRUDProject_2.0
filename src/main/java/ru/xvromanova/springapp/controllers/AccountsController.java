package ru.xvromanova.springapp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.xvromanova.springapp.dao.AccountDAO;
import ru.xvromanova.springapp.models.Account;

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

    @GetMapping("/new")
    public String newAccount(@ModelAttribute("account") Account account) {

        return "accounts/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("account") Account account) {
        accountDAO.save(account);

        return "redirect:/accounts";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable("id") int id, Model model) {
        model.addAttribute("account", accountDAO.show(id));

        return "accounts/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("account") Account account, @PathVariable("id") int id) {
        accountDAO.update(id, account);

        return "redirect:/accounts";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        accountDAO.delete(id);

        return "redirect:/accounts";
    }

}
