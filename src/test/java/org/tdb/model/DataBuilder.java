package org.tdb.model;

import org.tdb.model.BaseEntity;

abstract class DataBuilder<E extends BaseEntity> {

    public abstract E create();

}
