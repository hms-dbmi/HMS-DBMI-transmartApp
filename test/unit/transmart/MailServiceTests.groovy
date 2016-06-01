package transmart

import grails.test.mixin.TestFor

/**
 * See the API for {@link grails.test.mixin.services.ServiceUnitTestMixin} for usage instructions
 */
@TestFor(MailService)
class MailServiceTests {

    void testEmptyConstructor() {
		MailService ms = new MailService()
		assertEquals('smtp.gmail.com', ms.host)
        //fail "Implement me"
    }
}
