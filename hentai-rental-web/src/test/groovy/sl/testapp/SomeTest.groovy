package sl.testapp

import spock.lang.Specification

class SomeTest extends Specification{

	def "exception thrown" () {
		when: "nothing is initialized"
		def obj = 3;
		then: "exception is thrown"
		obj == 3
	}
}
