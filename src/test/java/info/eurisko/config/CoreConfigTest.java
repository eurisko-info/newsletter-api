package info.eurisko.config;

import static org.junit.Assert.*;

import java.net.URI;

import org.junit.Test;

public class CoreConfigTest {
	@Test
	public void testDatasourceURL_allParametersPresent() throws Exception {
		final URI dbUrl = new URI("postgres://postgres:Passw0rd@localhost:5432/newsletter");
		assertEquals(CoreConfig.convertDatasourceURL(dbUrl), "jdbc:postgresql://localhost:5432/newsletter?user=postgres&password=Passw0rd");
	}
	@Test
	public void testDatasourceURL_portMissing() throws Exception {
		final URI dbUrl = new URI("postgres://postgres:Passw0rd@localhost/newsletter");
		assertEquals(CoreConfig.convertDatasourceURL(dbUrl), "jdbc:postgresql://localhost/newsletter?user=postgres&password=Passw0rd");
	}
	@Test
	public void testDatasourceURL_passwordMissing() throws Exception {
		final URI dbUrl = new URI("postgres://postgres@localhost:5432/newsletter");
		assertEquals(CoreConfig.convertDatasourceURL(dbUrl), "jdbc:postgresql://localhost:5432/newsletter?user=postgres");
	}
	@Test
	public void testDatasourceURL_userAndPasswordMissing() throws Exception {
		final URI dbUrl = new URI("postgres://localhost:5432/newsletter");
		assertEquals(CoreConfig.convertDatasourceURL(dbUrl), "jdbc:postgresql://localhost:5432/newsletter");
	}
}
