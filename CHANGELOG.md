### changelog
###### 1.0.0:
- added stomp websocket server for container metrics transmission.
- generized application property.
- modeled json properties correctly for deserialization of container stats.
- added lombok config for using qualifier annotation in lombok constructors.
- added ResourceMonitor aspect and annotation, using a different thread - for sending container monitoring data.

###### 1.0.1:
- using builder pattern for cleaner testing code, due to nested json objects.
##### todo:
- add a method for running benchmarks by time, and not by number of requests.
- add better exceptions for ResourceMonitor aspect.
- add docs for all new features.
- 