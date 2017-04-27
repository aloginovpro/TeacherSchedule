package com.vbelova.teachers.service;

import com.vbelova.teachers.entity.User;
import com.vbelova.teachers.repository.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import static com.vbelova.teachers.service.UserServiceImpl.ADMIN;
import static com.vbelova.teachers.service.UserServiceImpl.USER;
import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

public class UserDetailsTest {

    private UserService userService;
    private UserRepository userRepository;

    private User admin;
    private User user;

    @Before
    public void init() {
        admin = createUser("admin", "ROLE_ADMIN");
        user = createUser("user", "ROLE_USER");

        userRepository = mock(UserRepository.class);
        when(userRepository.findByName("admin")).thenReturn(admin);
        when(userRepository.findByName("user")).thenReturn(user);

        userService = new UserServiceImpl(userRepository);
    }

    @Test
    public void testAdmin() {
        UserDetails adminDetails = userService.loadUserByUsername(admin.name);
        verify(userRepository).findByName(admin.name);
        assertTrue(adminDetails.getAuthorities().contains(ADMIN));
    }

    @Test
    public void testUser() {
        UserDetails userDetails = userService.loadUserByUsername(user.name);
        assertTrue(userDetails.getAuthorities().contains(USER));
        assertFalse(userDetails.getAuthorities().contains(ADMIN));
    }

    @Test(expected = UsernameNotFoundException.class)
    public void testUnknownUser() {
        userService.loadUserByUsername("unknown");
    }

    private static User createUser(String name, String role) {
        User user = new User();
        user.name = name;
        user.role = role;
        user.enabled = true;
        user.password = "123";
        return user;
    }

}
