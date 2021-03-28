package ru.xvromanova.springapp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.xvromanova.springapp.dao.AccountDAO;
import ru.xvromanova.springapp.models.Account;

import javax.validation.Valid;

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
    public String create(@ModelAttribute("account") @Valid Account account, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            return "accounts/new";
        }

        accountDAO.save(account);

        return "redirect:/accounts";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable("id") int id, Model model) {
        model.addAttribute("account", accountDAO.show(id));

        return "accounts/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("account") @Valid Account account, BindingResult bindingResult, @PathVariable("id") int id) {
        if (bindingResult.hasErrors()){
            return "accounts/edit";
        }
        accountDAO.update(id, account);

        return "redirect:/accounts";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        accountDAO.delete(id);

        return "redirect:/accounts";
    }

}
