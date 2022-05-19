package org.example.models;

import java.util.Objects;

public class User {
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private Role role;
    private double balance;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(email, user.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email);
    }

    public static Builder newBuilder() {
        return new User().new Builder();
    }

    public class Builder {
        private Builder() {
        }

        public Builder setEmail(String email) {
            User.this.email = email;
            return this;
        }
        public Builder setPassword(String password) {
            User.this.password = password;
            return this;
        }
        public Builder setFirstName(String firstName) {
            User.this.firstName = firstName;
            return this;
        }
        public Builder setLastName(String lastName) {
            User.this.lastName = lastName;
            return this;
        }
        public Builder setRole(Role role) {
            User.this.role = role;
            return this;
        }
        public Builder setBalance(double balance) {
            User.this.balance = balance;
            return this;
        }


        public User build() {
            return User.this;
        }
    }
}
