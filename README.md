
*NOTICE! Jenplug is under development and is not yet production-ready.*

**Jenplug** (Jenkins Plugin Generator) is a small web service designed to generate Jenkins plugins:
Submit your specification as a Build Manifest and get back your code.


USAGE
-----

### Example

	$ echo >foo.json <<EOF
	{
		...
	}
	EOF
	$ wget --post-file foo.json $HOSTNAME/api
	$ unzip foo.zip
	$ mvn -f foo clean compile
	...

### Build Manifest Specification

The build manifest is written using the JSON format.

(to be completed)

### API Specification

  * `GET /api/info` -- returns API info
  * `POST /api/1/plugin/` -- post the build manifest, retrieve the java code as a .zip archive.


DEVELOPMENT
-----------

### Bootstrap

Install maven, then compile and start the web service with:

	$ git clone https://github.com/jcsirot/jenkins-plugin-generator.git
	$ mvn -f jenkins-plugin-generator exec

The webservice starts on `localhost:9090`.

### Roadmap

  * ✓ REST API + compiler
  * ✗ Webapp form designer
  * ✗ hpi Repository
