package adrozhzha.mementoproducts.core.domain;

public class UserTest extends EntityTest<User, User.Id> {

    @Override
    protected User createDomainEntity() {
        return new User();
    }

    @Override
    protected User.Id createEntityId() {
        return new UserId();
    }

    private static class UserId implements User.Id {

    }
}
