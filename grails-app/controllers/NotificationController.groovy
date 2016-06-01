
class NotificationController {

	def index() {
		render "OK"
	}

	def error() {
		try {
			// "Do stuff" with the exception:
			Exception exception = request.exception?:new RuntimeException("An unknown error has occured.")
			render(view:'error', model: [excptn: exception.getMessage()])
			

		} catch ( Exception e ) {
			render(view: 'error_unknown', model: [error: e.class.toString()+'/'+e.getMessage()])
		}

	}
}
