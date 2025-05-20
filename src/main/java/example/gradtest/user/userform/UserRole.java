package example.gradtest.user.userform;

public enum UserRole {

    Traveler("Traveler"),
    Guide("Guide");

    private final String displayName;

    UserRole(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
