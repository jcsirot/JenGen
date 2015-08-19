
*NOTICE! JenGen is under development and is not yet production-ready.*

**JenGen** (Jenkins Plugin Generator) is a small web service for generating ad-hoc Jenkins plugins:
Submit your specification as a Build Manifest and get back your plugin source code ready to be compiled.


USAGE
-----

### Example

Submit a manifest for a pretend "hello" command with no parameter,
and get the source code as a zip archive:

	$ echo >hello.json <<EOF
	{
		"id": "com.example.hello",
		"name": "hello",
		"description": "invoke hello command",
		"parameters": [],
		"command": "hello"
	}
	EOF
	$ wget --post-file hello.json $HOSTNAME/api

Unzip the source code,
compile it and run a Jenkins instance with the plugin installed:

	$ unzip $ID.zip
	$ mvn -f $ID clean package hpi:run

For the compilation you'll need a Java 8 SDK and maven 3.

### Build Manifest Specification

The build manifest is written using the JSON format.

Attributes:
  * id
  * name
  * description
  * command
  * parameters


### API Specification

  * `GET /api/info` -- returns the server and API versions
  * `POST /api/1/plugin/` -- post the build manifest, get the java code as a zip archive.


DEVELOPMENT
-----------

### Bootstrap

Install maven, then compile and start the web service with:

	$ git clone https://github.com/jcsirot/jenkins-plugin-generator.git
	$ mvn -f jenkins-plugin-generator clean compile exec

The webservice starts on `localhost:9090`.

### Roadmap

  * ✓ REST API + compiler
  * ✗ Webapp form designer
  * ✗ hpi Repository

