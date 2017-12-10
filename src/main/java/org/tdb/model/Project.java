package org.tdb.model;

import javax.persistence.*;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Arne
 * @since 20/11/2017
 */
@Entity
@Table(name = "project")
public class Project extends BaseEntity {

    @ManyToOne
    private Account account;

    @Column(unique = true)
    private String externalId;

    @OneToMany(mappedBy = "project")
    private Set<TestRun> testRuns = new HashSet<>();

    protected Project() {}

    public Project(Account account, String name) {
        this(account, name, name);
    }

    public Project(Account account, String name, String externalId) {
        super(name);
        this.account = account;
        this.externalId = externalId;
    }

    public Account getAccount() {
        return account;
    }

    public String getExternalId() {
        return externalId;
    }

    public Set<TestRun> getTestRuns() {
        return Collections.unmodifiableSet(testRuns);
    }

    public void addToTestRuns(TestRun testRun) {
        testRuns.add(testRun);
    }

}
