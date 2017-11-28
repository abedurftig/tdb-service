package org.testdashboard.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author Arne
 * @since 21/11/2017
 */
@Entity
@Table(name = "testrun")
public class TestRun extends BaseEntity {

    @Column(unique = true)
    private String externalId;

    public TestRun() {}

    public TestRun(String name) {
        this(name, name);
    }

    public TestRun(String name, String externalId) {
        super(name);
        this.externalId = externalId;
    }

    public String getExternalId() {
        return externalId;
    }

}
