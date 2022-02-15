package adrozhzha.mementoproducts.core.domain;

import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public abstract class EntityTest<T extends Entity<Id>, Id> {

    protected abstract T createDomainEntity();

    protected abstract Id createEntityId();

    @Test
    public void null_isDifferentIdentity() {
        T domainEntity = createDomainEntity();
        boolean same = domainEntity.sameIdentityAs(null);
        assertThat(same, is(false));
    }

    @Test
    public void sameProduct_isSameIdentity() {
        T domainEntity = createDomainEntity();
        boolean same = domainEntity.sameIdentityAs(domainEntity);
        assertThat(same, is(true));
    }

    @Test
    public void differentProductsNoIds_haveDifferentIdentity() {
        T domainEntity1 = createDomainEntity();
        T domainEntity2 = createDomainEntity();
        boolean same = domainEntity1.sameIdentityAs(domainEntity2);
        assertThat(same, is(false));
    }

    @Test
    public void differentProductsSameIds_haveSameIdentity() {
        T domainEntity1 = createDomainEntity();
        T domainEntity2 = createDomainEntity();
        Id sameId = createEntityId();
        domainEntity1.setId(sameId);
        domainEntity2.setId(sameId);
        boolean same = domainEntity1.sameIdentityAs(domainEntity2);
        assertThat(same, is(true));
    }
}
