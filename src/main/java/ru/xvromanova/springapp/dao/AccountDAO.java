package ru.xvromanova.springapp.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.xvromanova.springapp.models.Account;

import java.util.List;

@Component
public class AccountDAO {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public AccountDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Account> index() {
        return jdbcTemplate.query("SELECT * FROM Account", new BeanPropertyRowMapper<>(Account.class));
    }

    public Account show(int id) {
        return jdbcTemplate.query("SELECT * FROM Account WHERE id=?", new Object[]{id}, new BeanPropertyRowMapper<>(Account.class)).stream().findAny().orElse(null);
    }

    public void save(Account account) {
        jdbcTemplate.update("INSERT INTO Account VALUES(default,?,?,?)", account.getName(), account.getAge(), account.getEmail());
    }

    public void update(int id, Account updateAccount) {
        jdbcTemplate.update("UPDATE Account SET name=?, age=?, email=? WHERE id=?", updateAccount.getName(), updateAccount.getAge(), updateAccount.getEmail(), id);
    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM Account WHERE id=?", id);
    }
}
