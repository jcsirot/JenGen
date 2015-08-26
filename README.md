

**Jenerator** (Jenkins Plugin Generator) is a toolkit — a library, a command line application and a REST web service — for generating ad-hoc [Jenkins plugins](https://wiki.jenkins-ci.org/display/JENKINS/Plugin+tutorial):
Submit your specification as a _Build Manifest_ and get back your plugin source code ready to be compiled!


USAGE
-----

### Example

Prepare the build manifest — here a pretend "hello" command with no parameter:

	$ echo >hello.json <<EOF
	{
		"id": "hello",
		"name": "hello",
		"description": "invoke hello command",
		"parameters": [],
		"command": {
			"executable": "hello"
		}
	}
	EOF

Get back the zipped code by using the CLI tool:

	$ java -jar jenerator-cli/target/jenerator-cli-1.0-SNAPSHOT.jar hello.json -o hello.zip

Or by using the REST service:

	$ wget --post-file hello.json -H "Content-type: application/json" localhost:9090/api/1/plugin/

Unzip the source code,
compile it and run a Jenkins instance with the plugin installed:

	$ unzip hello.zip
	$ mvn -f hello clean package hpi:run

Keep in mind the resulting artifact is an HPI file.

For the compilation you'll need a
[Java 8 SDK](http://java.oracle.com)
and
[maven 3](http://maven.apache.org).

### Build Manifest Specification

The build manifest is written using the JSON format.

Attributes:
  * `id` (required string) — artifact ID (POM attribute)
  * `name` (required string) — artifact name (POM attribute)
  * `description` (optional string)
  * `command` (required string) dict with a single attribute `executable` which value is the command to invoke
  * `parameters` — list of dicts having the following attributes:
    - `id` (required string)
    - `flag` (required string)
    - `widget` — dict having two attributes:
      - `type` in (`textbox`, `number`, `password`, `checkbox`)
      - `label` — list of dict having the following attributes:
        * `lang` (required string) ISO 3166 country code, e.g. "en", "fr"...
        * `value` (required string)

### API Specification

  * `GET /api/info` — returns the server and API versions
  * `POST /api/1/plugin/` — build manifest as body, returns the corresponding source code zip archive.


DEVELOPMENT
-----------

### Bootstrap

Install maven, then compile and start the web service with:

	$ git clone https://github.com/jcsirot/jenerator.git
	$ mvn -f jenerator clean compile exec:exec

The webservice starts on `localhost:9090`.

### Roadmap

  * ✓ REST API + compiler
  * ✗ Webapp form designer
  * ✗ HPI Repository (who want to pay the bill?)
