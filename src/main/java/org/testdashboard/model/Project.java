package org.testdashboard.model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @author Arne
 * @since 20/11/2017
 */
@Entity
@Table(name = "project")
public class Project extends BaseEntity {

    @ManyToOne
    private Account account;

    protected Project() {}

    public Project(Account account, String name) {
        super(name);
        this.account = account;
    }

}
