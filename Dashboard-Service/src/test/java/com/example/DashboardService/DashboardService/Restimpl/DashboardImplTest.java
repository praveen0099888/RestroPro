package com.example.DashboardService.DashboardService.Restimpl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.DashboardService.DashboardService.Feigninterfaces.Billinterface;
import com.example.DashboardService.DashboardService.Feigninterfaces.Productinterface;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {DashboardImpl.class})
@ExtendWith(SpringExtension.class)
class DashboardImplTest {
    @MockBean
    private Billinterface billinterface;

    @Autowired
    private DashboardImpl dashboardImpl;

    @MockBean
    private Productinterface productinterface;

    /**
     * Method under test: {@link DashboardImpl#getcount()}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testGetcount() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "java.util.Map.size()" because "m" is null
        //       at java.util.HashMap.putMapEntries(HashMap.java:495)
        //       at java.util.HashMap.putAll(HashMap.java:783)
        //       at com.example.DashboardService.DashboardService.Restimpl.DashboardImpl.getcount(DashboardImpl.java:35)
        //       at javax.servlet.http.HttpServlet.service(HttpServlet.java:670)
        //       at javax.servlet.http.HttpServlet.service(HttpServlet.java:779)
        //   See https://diff.blue/R013 to resolve this issue.

        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "java.util.Map.putAll(java.util.Map)" because "that" is null
        //       at com.example.DashboardService.DashboardService.Restimpl.DashboardImpl.getcount(DashboardImpl.java:35)
        //   See https://diff.blue/R013 to resolve this issue.

        Productinterface productinterface = mock(Productinterface.class);
        when(productinterface.getcount()).thenReturn(new ResponseEntity<>(HttpStatus.CONTINUE));
        Billinterface billinterface = mock(Billinterface.class);
        when(billinterface.getcount()).thenReturn(new ResponseEntity<>(HttpStatus.CONTINUE));
        (new DashboardImpl(productinterface, billinterface)).getcount();
    }

    /**
     * Method under test: {@link DashboardImpl#getcount()}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testGetcount2() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "java.util.Map.size()" because "m" is null
        //       at java.util.HashMap.putMapEntries(HashMap.java:495)
        //       at java.util.HashMap.putAll(HashMap.java:783)
        //       at com.example.DashboardService.DashboardService.Restimpl.DashboardImpl.getcount(DashboardImpl.java:35)
        //       at javax.servlet.http.HttpServlet.service(HttpServlet.java:670)
        //       at javax.servlet.http.HttpServlet.service(HttpServlet.java:779)
        //   See https://diff.blue/R013 to resolve this issue.

        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "org.springframework.http.ResponseEntity.getBody()" because "ab" is null
        //       at com.example.DashboardService.DashboardService.Restimpl.DashboardImpl.getcount(DashboardImpl.java:33)
        //   See https://diff.blue/R013 to resolve this issue.

        Productinterface productinterface = mock(Productinterface.class);
        when(productinterface.getcount()).thenReturn(null);
        Billinterface billinterface = mock(Billinterface.class);
        when(billinterface.getcount()).thenReturn(new ResponseEntity<>(HttpStatus.CONTINUE));
        (new DashboardImpl(productinterface, billinterface)).getcount();
    }

    /**
     * Method under test: {@link DashboardImpl#getcount()}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testGetcount3() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "java.util.Map.size()" because "m" is null
        //       at java.util.HashMap.putMapEntries(HashMap.java:495)
        //       at java.util.HashMap.putAll(HashMap.java:783)
        //       at com.example.DashboardService.DashboardService.Restimpl.DashboardImpl.getcount(DashboardImpl.java:35)
        //       at javax.servlet.http.HttpServlet.service(HttpServlet.java:670)
        //       at javax.servlet.http.HttpServlet.service(HttpServlet.java:779)
        //   See https://diff.blue/R013 to resolve this issue.

        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "org.springframework.http.ResponseEntity.getBody()" because "cd" is null
        //       at com.example.DashboardService.DashboardService.Restimpl.DashboardImpl.getcount(DashboardImpl.java:34)
        //   See https://diff.blue/R013 to resolve this issue.

        Productinterface productinterface = mock(Productinterface.class);
        when(productinterface.getcount()).thenReturn(new ResponseEntity<>(HttpStatus.CONTINUE));
        Billinterface billinterface = mock(Billinterface.class);
        when(billinterface.getcount()).thenReturn(null);
        (new DashboardImpl(productinterface, billinterface)).getcount();
    }

    /**
     * Method under test: {@link DashboardImpl#getcount()}
     */
    @Test
    void testGetcount4() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "java.util.Map.size()" because "m" is null
        //       at java.util.HashMap.putMapEntries(HashMap.java:495)
        //       at java.util.HashMap.putAll(HashMap.java:783)
        //       at com.example.DashboardService.DashboardService.Restimpl.DashboardImpl.getcount(DashboardImpl.java:35)
        //       at javax.servlet.http.HttpServlet.service(HttpServlet.java:670)
        //       at javax.servlet.http.HttpServlet.service(HttpServlet.java:779)
        //   See https://diff.blue/R013 to resolve this issue.

        Productinterface productinterface = mock(Productinterface.class);
        when(productinterface.getcount()).thenReturn(new ResponseEntity<>(new HashMap<>(), HttpStatus.CONTINUE));
        Billinterface billinterface = mock(Billinterface.class);
        when(billinterface.getcount()).thenReturn(new ResponseEntity<>(new HashMap<>(), HttpStatus.CONTINUE));
        ResponseEntity<Map<String, Object>> actualGetcountResult = (new DashboardImpl(productinterface, billinterface))
                .getcount();
        assertTrue(actualGetcountResult.getBody().isEmpty());
        assertTrue(actualGetcountResult.hasBody());
        assertEquals(HttpStatus.OK, actualGetcountResult.getStatusCode());
        assertTrue(actualGetcountResult.getHeaders().isEmpty());
        verify(productinterface).getcount();
        verify(billinterface).getcount();
    }

    /**
     * Method under test: {@link DashboardImpl#countfallbackmethod(Exception)}
     */
    @Test
    void testCountfallbackmethod() {
        ResponseEntity<Map<String, Object>> actualCountfallbackmethodResult = dashboardImpl
                .countfallbackmethod(new Exception());
        assertEquals(1, actualCountfallbackmethodResult.getBody().size());
        assertTrue(actualCountfallbackmethodResult.hasBody());
        assertTrue(actualCountfallbackmethodResult.getHeaders().isEmpty());
        assertEquals(HttpStatus.OK, actualCountfallbackmethodResult.getStatusCode());
    }
}

