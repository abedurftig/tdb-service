package org.tdb.model;

import javax.persistence.*;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "tdb_testrun")
public class TestRun extends BaseEntity {

    private String externalId;

    @ManyToOne
    private Project project;

    @OneToMany(mappedBy = "testRun")
    private Set<TestSuite> testSuites = new HashSet<>();

    public TestRun() {}

    public TestRun(Project project, String name) {
        this(project, name, name);
    }

    public TestRun(Project project, String name, String externalId) {
        super(name);
        this.project = project;
        this.externalId = externalId;
    }

    public String getExternalId() {
        return externalId;
    }

    public Project getProject() {
        return project;
    }

    public Set<TestSuite> getTestSuites() {
        return Collections.unmodifiableSet(testSuites);
    }

    public void addToTestSuites(TestSuite testSuite) {
        testSuites.add(testSuite);
    }

}
