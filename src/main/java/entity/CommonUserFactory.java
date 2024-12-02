package entity;

/**
 * Factory for creating CommonUser objects.
 */
public class CommonUserFactory implements UserFactory {

    @Override
    public User create(String username, String bio, String college, String email, String password, String program, String year) {
        return new CommonUser(username, bio, college, email, password, program, year);
    }
}
