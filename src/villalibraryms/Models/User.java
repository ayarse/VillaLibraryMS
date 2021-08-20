/*
 * Library Management System - Ayas Nasih, S1600655
 * 
 * 
 */
package villalibraryms.Models;

/**
 *
 * @author ayasnasih
 */
public class User {

    int id;
    String username;
    String password;
    int roleId;
    String displayName;
    boolean isActive;
    MembershipCard membershipCard;

    public User(int id, String username, String password, int roleId, String displayName, boolean isActive) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.roleId = roleId;
        this.displayName = displayName;
        this.isActive = isActive;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public boolean isIsActive() {
        return isActive;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }

    public Role getRole() {
        return Role.find(this.roleId);
    }

    public void setRole(Role role) {
        this.roleId = role.id;
    }

    public void setMembershipCard(MembershipCard card) {
        this.membershipCard = card;
    }

    public MembershipCard getMembershipCard() {
        return this.membershipCard;
    }
}
