import grails.test.mixin.TestFor


/**
 * See the API for {@link grails.test.mixin.web.ControllerUnitTestMixin} for usage instructions
 */
@TestFor(ApiController)
class ApiControllerTests {

    void testJSONUserProfile() {
		when:
		def params = [id: 1]
		controller.userprofile
		
		then:
		assertEquals('EMPTY', response.json.username)
        //fail "Implement me"
    }
}
