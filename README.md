Sample Spring Cloud Task Application
============

### Launching Task Application Jobs

- start up spring data shell
- Register Application
```
app register --name file-ingest --type task --uri file:///Users/keithkester/workspace/sample-spring-tasks/build/libs/tasks-0.0.1-SNAPSHOT.jar
```
- Create a Stream
```
task create fileIngestTask --definition file-ingest
```

- Lauch Task
```
task launch fileIngestTask --arguments "filePath=classpath:data.csv --spring.cloud.task.closecontext_enable=false"
```

