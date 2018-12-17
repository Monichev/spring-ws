package com.monichev.spring.ws.endpoint.ws;

import static com.monichev.spring.ws.utils.TypeUtils.createTypeA;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertTrue;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import javax.annotation.PostConstruct;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;

import com.monichev.spring.ws.api_a.ApiA;
import com.monichev.spring.ws.api_a.types.TypeA;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
public class ApiAEndpointIT {

    @LocalServerPort
    private int port;

    private ApiA apiA;

    private static int compare(TypeA a, TypeA b) {
        if (a == b) {
            return 0;
        }
        if (a == null || b == null) {
            return a == null ? -1 : 1;
        }
        int result = Objects.compare(a.getId(), b.getId(), Long::compareTo);
        if (result != 0) {
            return result;
        }
        result = Objects.compare(a.getName(), b.getName(), String::compareTo);
        if (result != 0) {
            return result;
        }
        result = Objects.compare(a.getTimestamp(), b.getTimestamp(), (t1, t2) -> {
            int r = Objects.compare(t1.getSeconds(), t2.getSeconds(), Long::compareTo);
            if (r != 0) {
                return r;
            }
            return Objects.compare(t1.getMicros(), t2.getMicros(), Integer::compareTo);
        });
        return result;
    }

    @PostConstruct
    private void init() throws MalformedURLException {
        URL url = new URL("http://localhost:" + port + "/spring-ws/api-a.wsdl");
        QName qname = new QName("http://ws.spring.monichev.com/api-a", "ApiAImpl");
        Service service = Service.create(url, qname);
        apiA = service.getPort(ApiA.class);
    }

    @Test
    public void getTypeAList() {
        List<TypeA> actual = apiA.getTypeAList();
        List<TypeA> expected = Arrays.asList(
                createTypeA(1, "1", 1.1),
                createTypeA(2, "2", 2.2),
                createTypeA(3, "3", 3.3));

        assertThat("items count", actual.size(), is(expected.size()));
        for (int i = 0; i < expected.size(); ++i) {
            assertTrue("item #" + i + " equal", compare(actual.get(i), expected.get(i)) == 0);
        }
    }

    @Test
    public void addTypeA() {
        TypeA expected = createTypeA(-1, "add", 99.99);
        apiA.addTypeA(expected);
        List<TypeA> actual = apiA.getTypeAList();
        apiA.removeTypeA(-1);
        assertTrue("item added", compare(actual.get(actual.size() - 1), expected) == 0);
    }

    @Test
    public void removeTypeA() {
        TypeA expected = createTypeA(-2, "delete", 0.0);
        apiA.addTypeA(expected);
        TypeA actual = apiA.removeTypeA(-2);
        assertTrue("item deleted", compare(actual, expected) == 0);

        actual = apiA.removeTypeA(-2);
        expected = null;
        assertTrue("item not deleted", compare(actual, expected) == 0);
    }
}
