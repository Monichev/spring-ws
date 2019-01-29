package com.monichev.spring.ws.ext.endpoint.ws;

import static com.monichev.spring.ws.ext.utils.TypeUtils.createTypeExt;
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

import com.monichev.spring.ws.api_ext.ApiExt;
import com.monichev.spring.ws.api_ext.types.TypeExt;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
public class ApiExtEndpointIT {

    @LocalServerPort
    private int port;

    private ApiExt apiExt;

    private static int compare(TypeExt a, TypeExt b) {
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
        URL url = new URL("http://localhost:" + port + "/spring-ws/api-ext.wsdl");
        QName qname = new QName("http://ws.spring.monichev.com/api-ext", "ApiExtImpl");
        Service service = Service.create(url, qname);
        apiExt = service.getPort(ApiExt.class);
    }

    @Test
    public void getTypeExtList() {
        List<TypeExt> actual = apiExt.getTypeExtList();
        List<TypeExt> expected = Arrays.asList(
                createTypeExt(1, "1", 1.1),
                createTypeExt(2, "2", 2.2),
                createTypeExt(3, "3", 3.3));

        assertThat("items count", actual.size(), is(expected.size()));
        for (int i = 0; i < expected.size(); ++i) {
            assertTrue("item #" + i + " equal", compare(actual.get(i), expected.get(i)) == 0);
        }
    }
}
