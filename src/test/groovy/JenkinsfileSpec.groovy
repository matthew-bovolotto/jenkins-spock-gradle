import com.homeaway.devtools.jenkins.testing.JenkinsPipelineSpecification

public class JenkinsfileSpec extends JenkinsPipelineSpecification {

	def Jenkinsfile = null

	public static class DummyException extends RuntimeException {
		public DummyException(String _message) { super(_message); }
	}

	def setup() {
		Jenkinsfile = loadPipelineScriptForTest("/Jenkinsfile")
		Jenkinsfile.getBinding().setVariable("scm", null)
	}

	def "Email is sent on build failure" () {
		setup:
			getPipelineMock("sh")("echo hello world") >> {
				throw new DummyException("Dummy test failure")
			}
		when:
			try {
				Jenkinsfile.run()
			} catch (DummyException e) {
			}
		then:
			1 * getPipelineMock("slackSend")( _ )
	}
}
