package entity;

/**
 * Factory for creating users.
 */
public interface UserFactory {
    /**
     * Creates a new User with the specified details.
     * @param name the name of the new user
     * @param bio a brief biography or description of the user
     * @param college the college the user is affiliated with
     * @param email the email address of the user
     * @param password the password for the user account
     * @param program the academic program the user is enrolled in
     * @param year the year of study the user is in
     * @return the newly created User instance
     */
    User create(String name, String bio, String college, String email, String password, String program, String year);

}
