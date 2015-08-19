
Jenplug -- Jenkins Plugin Generator
===================================

*NOTICE! Jenplug is under development and is not yet production-ready*

**Jenplug** is a small web service designed to generate Jenkins plugins:
Submit your specification as a Build Manifest and get back your code.

Usage
-----

### Example

	$ echo >foo.json <<EOF
	{
		...
	}
	EOF
	$ curl -X POST -d @foo.json $HOSTNAME/api

### Build Manifest

The build manifest is written using the JSON format.

(to be completed)

### API

  * `GET /api/info` -- returns API info
  * `POST /api/1/plugin/` -- post the build manifest, retrieve the java code as a .zip archive.

Development
-----------

### Bootstrap

Install maven, then compile and start the web service with:

	$ git clone https://github.com/jcsirot/jenkins-plugin-generator.git
	$ mvn -C jenkins-plugin-generator exec

The webservice starts on `localhost:9000`.

### Roadmap

  * ✓ REST API + compiler
  * ✗ Webapp form designer
  * ✗ hpi Repository
