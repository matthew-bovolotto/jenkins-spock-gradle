node {
	stage( "Test" ) {
		try{
			sh( "echo hello world" )
		}catch(Exception e){
			slackSend(
				color: 'error',
				message: 'whole-pipeline unit tests failed.'
					)
		    throw e
		}
	}
}
