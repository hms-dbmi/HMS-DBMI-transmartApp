package transmart

import grails.test.mixin.TestFor

/**
 * See the API for {@link grails.test.mixin.services.ServiceUnitTestMixin} for usage instructions
 */
@TestFor(UserManagerService)
class UserManagerServiceTests {

    void testInitializerWithNoParameters() {
		when:
		UserManagerService srvc = new UserManagerService()
		
		then:
		assertEquals(null, srvc.email)
        //fail "Implement me"
    }
}
