package org.tdb.model;

public class AccountBuilder extends DataBuilder<Account> {

    private String name = "John";
    private Long id = 1L;

    @Override
    public Account create() {
        Account account =  new Account(name);
        account.setId(id);
        return account;
    }

    public AccountBuilder withId(Long id) {
        this.id = id;
        return this;
    }

    public AccountBuilder withName(String name) {
        this.name = name;
        return this;
    }

}
