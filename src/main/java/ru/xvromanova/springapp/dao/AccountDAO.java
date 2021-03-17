package ru.xvromanova.springapp.dao;

import org.springframework.stereotype.Component;
import ru.xvromanova.springapp.models.Account;

import java.util.ArrayList;
import java.util.List;

@Component
public class AccountDAO {
    private static int ACCOUNTS_COUNT;
    private List<Account> accounts;

    {
        accounts = new ArrayList<>();

        accounts.add(new Account(++ACCOUNTS_COUNT, "Ann",21,"ann@gmail.com"));
        accounts.add(new Account(++ACCOUNTS_COUNT, "Kate",30,"kate@yandex.ru"));
        accounts.add(new Account(++ACCOUNTS_COUNT,"Tom",54,"tom@gmail.com"));
        accounts.add(new Account(++ACCOUNTS_COUNT, "Mike",14,"mike12@mail.ru"));
    }

    public List<Account> index(){
        return accounts;
    }

    public  Account show(int id) {
        return accounts.stream().filter(account -> account.getId() == id).findAny().orElse(null);
    }

    public void save(Account account) {
        account.setId(++ACCOUNTS_COUNT);
        accounts.add(account);
    }

    public void update(int id, Account updateAccount) {
        Account accountToBeUpdated = show(id);

        accountToBeUpdated.setName(updateAccount.getName());
        accountToBeUpdated.setAge(updateAccount.getAge());
        accountToBeUpdated.setEmail(updateAccount.getEmail());
    }

    public void delete(int id) {
        accounts.removeIf(a -> a.getId() == id);
    }
}
