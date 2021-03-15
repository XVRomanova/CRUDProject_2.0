package ru.xvromanova.springapp.dao;

import org.springframework.stereotype.Component;
import ru.xvromanova.springapp.models.Account;

import java.util.ArrayList;
import java.util.List;

@Component
public class AccountDAO {
    private List<Account> accounts;

    {
        accounts = new ArrayList<>();

        accounts.add(new Account(1, "Ann"));
        accounts.add(new Account(2, "Kate"));
        accounts.add(new Account(3,"Tom"));
        accounts.add(new Account(4, "Mike"));
    }

    public List<Account> index(){
        return accounts;
    }

    public  Account show(int id) {
        return accounts.stream().filter(account -> account.getId() == id).findAny().orElse(null);
    }

}
