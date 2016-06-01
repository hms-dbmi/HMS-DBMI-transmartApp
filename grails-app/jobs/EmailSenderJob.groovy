
class EmailSenderJob {

	def mailService
	def concurrent = false

	static triggers = {
	}

	def execute(context) {
		log.info("Sending e-mail to "+context.mergedJobDataMap.get('to')?:'UnKnown Destination')
		mailService.sendMail([
			to: context.mergedJobDataMap.get('to'),
			subject: context.mergedJobDataMap.get('subject'),
			body: context.mergedJobDataMap.get('body'),
			attachment: context.mergedJobDataMap.get('attachment'),
			filename: context.mergedJobDataMap.get('filename'),
			filepath: context.mergedJobDataMap.get('filepath')
		])
		log.info "Mail has been sent."
	}
}
