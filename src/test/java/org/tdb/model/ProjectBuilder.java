package org.tdb.model;

public class ProjectBuilder extends DataBuilder<Project> {

    private String name = "DefaultProject";
    private Long id = 1L;
    private Account account = null;

    public ProjectBuilder(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public ProjectBuilder withAccount(Account account) {
        this.account = account;
        return this;
    }

    @Override
    public Project create() {
        return new Project(account, name).withId(id);
    }

}
