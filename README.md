
*NOTICE! JenGen is under development and is not yet production-ready.*

**JenGen** (Jenkins Plugin Generator) is a small web service for generating ad-hoc Jenkins plugins:
Submit your specification as a _Build Manifest_ and get back your plugin source code ready to be compiled.

Once compiled you'll get a Jenkins plugin [HPI archive](https://wiki.jenkins-ci.org/display/JENKINS/Plugin+tutorial) that can be installed directly on your instance.


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
	$ wget --post-file hello.json -H "Content-type: application/json" localhost:9090/api/1/plugin/

Unzip the source code,
compile it and run a Jenkins instance with the plugin installed:

	$ unzip hello.zip
	$ mvn -f hello clean package hpi:run

For the compilation you'll need a
[Java 8 SDK](http://java.oracle.com)
and
[maven 3](http://maven.apache.org).

### Build Manifest Specification

The build manifest is written using the JSON format.

Attributes:
  * `id` (required string)
  * `name` (required string)
  * `description` (optional string)
  * `command` (required string) the command to invoke
  * `parameters` -- list of dicts having the following attributes:
    - `id` (required string)
    - `flag` (required string)
    - `widget` -- dict having two attributes:
      - `type` in (`textbox`, `number`, `password`, `checkbox`)
      - `label` -- list of dict having the following attributes:
        * `lang` (required string) ISO 3166 country code, e.g. "en", "fr"...
        * `value` (required string)

### API Specification

  * `GET /api/info` -- returns the server and API versions
  * `POST /api/1/plugin/` -- expect the build manifest as body, returns the corresponding source code zip archive.


DEVELOPMENT
-----------

### Bootstrap

Install maven, then compile and start the web service with:

	$ git clone https://github.com/jcsirot/JenGen.git
	$ mvn -f JenGen clean compile exec:exec

The webservice starts on `localhost:9090`.

### Roadmap

  * ✓ REST API + compiler
  * ✗ Webapp form designer
  * ✗ HPI Repository (who want to pay the bill?)
