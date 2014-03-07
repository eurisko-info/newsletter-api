package info.eurisko.config;

import static org.junit.Assert.*;

import org.junit.Test;

public class CoreConfigTest {
	@Test
	public void testDatasourceURL_allParametersPresent() throws Exception {
		assertEquals(CoreConfig.convertDatasourceURL("postgres://postgres:Passw0rd@localhost:5432/newsletter"),
				"jdbc:postgresql://localhost:5432/newsletter?user=postgres&password=Passw0rd");
	}
	@Test
	public void testDatasourceURL_portMissing() throws Exception {
		assertEquals(CoreConfig.convertDatasourceURL("postgres://postgres:Passw0rd@localhost/newsletter"),
				"jdbc:postgresql://localhost/newsletter?user=postgres&password=Passw0rd");
	}
	@Test
	public void testDatasourceURL_passwordMissing() throws Exception {
		assertEquals(CoreConfig.convertDatasourceURL("postgres://postgres@localhost:5432/newsletter"),
				"jdbc:postgresql://localhost:5432/newsletter?user=postgres");
	}
	@Test
	public void testDatasourceURL_userAndPasswordMissing() throws Exception {
		assertEquals(CoreConfig.convertDatasourceURL("postgres://localhost:5432/newsletter"),
				"jdbc:postgresql://localhost:5432/newsletter");
	}
}
