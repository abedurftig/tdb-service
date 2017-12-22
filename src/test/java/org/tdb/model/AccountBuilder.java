package org.tdb.model;

public class AccountBuilder extends DataBuilder<Account> {

    @Override
    public Account create() {
        Account account =  new Account(_name);
        account.setId(_id);
        return account;
    }

    private String _name = "John";
    private Long _id = 1L;

    public AccountBuilder withId(Long id) {
        this._id = id;
        return this;
    }

    public AccountBuilder withName(String name) {
        this._name = name;
        return this;
    }

}
