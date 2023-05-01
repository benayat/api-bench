<h2>api-bench</h2>
<p>this repo has the following objectives: </p>
<ol>
<li>create a spring boot cli project for api benchmarking, as an alternative to jmeter, with some more usefull results</li>
<li>determine the use of some new technologies which came with java 19, and are still in preview mode</li>
</ol>

##### motivation for the project:
- jmeter only allows to simply send requests, but I need a more microservices oriented information, like docker container resource management.
- I want to study and test the new project loom virtual threads, as well as the structured concurrency, and it's a good chance to do that.

##### technologies
- spring aop - for measuring methods and api load-tests runtime, without polluting my business code, and without the use of needless and complicated api filters and interceptors.
- docker engine api - get stats for memory usage with the api, using webclient and basic auth.
- link to the docs: https://docs.docker.com/engine/api/v1.42/#tag/Container/operation/ContainerLogs
##### todo: 
- create another aspect for getting container stats from every second to add to the report.
- think about creating a report, and also about getting temp results instead of end-results with mean value. 
- the docker api request is different with curl - it sends data every second. I need to understand how it works, and implement something similar in the docker client here.
- run a sanity test on everything - from the de/serialization of the container stats to the requests with the new webclient for both bench and docker api requests.
- run a sanity test for the cli application, and make sure the argument are right.
- make sure only the necessary args are prompted from user. otherwise - default values in application.properties.
