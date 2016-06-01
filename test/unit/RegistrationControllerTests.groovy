import grails.test.mixin.TestFor

/**
 * See the API for {@link grails.test.mixin.web.ControllerUnitTestMixin} for usage instructions
 */
@TestFor(RegistrationController)
class RegistrationControllerTests {

    void testCannotRegisterAction() {
		when:
		controller.cannotregister()
		
		then:
		assertEquals('OK', response.json.msg?:'OK')
       //fail "Implement me"
    }
}
