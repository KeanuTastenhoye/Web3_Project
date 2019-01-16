package domain.model;

public enum Role {
    ADMIN("admin"),
    USER("user");

    Role(String role) {

    }

    public static Role getRole(String role){
        Role r;
        switch (role.toLowerCase()){
            case "admin":
                r = Role.ADMIN;
                break;
            default:
                r = Role.USER;
                break;
        }
        return r;
    }
}
