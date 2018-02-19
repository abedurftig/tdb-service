package org.tdb.model;

public class UserBuilder extends DataBuilder<User> {

    private String name = "John";
    private String email = "john@email.com";
    private Long id = 1L;

    @Override
    public User create() {
        User user = new User(name, email);
        user.setId(id);
        return user;
    }

    public UserBuilder withId(Long id) {
        this.id = id;
        return this;
    }

    public UserBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public UserBuilder withEmail(String email) {
        this.email = email;
        return this;
    }

}
