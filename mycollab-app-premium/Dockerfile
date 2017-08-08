FROM ubuntu:14.04
FROM java:8
MAINTAINER Hai Nguyen <hainguyen@mycollab.com>

ENTRYPOINT ["/usr/bin/java", "-jar", "/usr/share/mycollab/executor.jar"]

ADD target/staging/bin              /usr/share/mycollab/bin
ADD target/staging/config           /usr/share/mycollab/config
ADD target/staging/i18n             /usr/share/mycollab/i18n
# Add Maven dependencies (not shaded into the artifact; Docker-cached)
ADD target/staging/lib              /usr/share/mycollab/lib
# Add the service itself
ADD target/staging/executor.jar     /usr/share/mycollab/executor.jar