package org.tis.tools.security.entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

/**
 * description:
 *
 * @author zhaoch
 * @date 2018/6/3
 **/
public class ToolsUserDetails implements UserDetails {

    static final long serialVersionUID = -7588980448693010399L;

    private String username;

    private String password;

    private boolean enabled = true;

    private String userId;

    private String clientId;

    private Collection<? extends GrantedAuthority> authorities;

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }


    public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
        this.authorities = authorities;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    public static class ToolsUserDetailsBuilder {
        private ToolsUserDetails userDetails = new ToolsUserDetails();

        public ToolsUserDetailsBuilder withUsername(String username) {
            userDetails.setUsername(username);
            userDetails.setAuthorities(null);
            return this;
        }

        public ToolsUserDetailsBuilder withPassword(String password) {
            userDetails.setPassword(password);
            return this;
        }

        public ToolsUserDetailsBuilder withClientId(String clientId) {
            userDetails.setClientId(clientId);
            return this;
        }

        public ToolsUserDetailsBuilder withUserId(String userId) {
            userDetails.setUserId(userId);
            return this;
        }

        public ToolsUserDetails build() {
            return userDetails;
        }
    }

}
