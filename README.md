# Red Hat Pipeline Library
This document serves to provide necessary information and concepts into the pipeline library.

## Code Structure

There are two top-level packages:

* `com.redhat.common`:  This package contains common things that aren't specific to pipelines.  These classes/interfaces are candidates who can live elsewhere. 
* `com.redhat.pipeline`:  This package contains all the pipeline specific code.  It does not contain and Red Hat specific functionality.
* `com.redhat.step`:  This package contains all the step specific code.  It does not contain and Red Hat specific functionality.

Where possible, I've tried to use parent packages to contain things like super classes or interfaces.  Child packages then extend or implement those things found in the parent packages.

## Concepts

### Common Package

#### Defitions
One of the core concepts is the notion of a [definition](https://gitlab.cee.redhat.com/customer-platform/pipeline/blob/main/src/main/java/com/redhat/common/Definitions.java):

* Definition:  to add a definition requires a name and its "native" form.  For example a pipeline uses a string that contains markup for the pipeline, whereas a step is denoted via a class.
* Creating:  using the definition's name _and_ the initial state of the definition, we can create an instance.

### Pipeline Package

* The intermediary form of all pipelines is the Java object `org.json.JSONObject`.  One may question why?  This class is generic enough to represent the JSON, XML or YAML markup and is hierarchical in nature.

### Step package

* A step is backed by the Java object `org.json.JSONObject`.  One may question why?  This class is generic enough to represent the JSON, XML or YAML markup and is hierarchical in nature.


## To Do's

* Need a StepExecutor
* One place to run pipelines:  there are two - AbstractPipeline and AbstractPipelineExecutor