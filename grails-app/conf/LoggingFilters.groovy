class LoggingFilters {
	static filters = {
		all(controller:"*", action:"*") {
			before = { log.info "parameters: ${params.inspect()} ${request.inspect()}" }
			after = { model ->
				if (model) {
					log.debug "Model: ${model?.inspect()}"
				}
			}
		}
	}
}