package org.testdashboard.model;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author Arne
 * @since 21/11/2017
 */
@Entity
@Table(name = "testrun")
public class TestRun extends BaseEntity {

    private String externalId;

}
