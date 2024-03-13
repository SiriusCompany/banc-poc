current_dir=$(shell pwd)
project_name=$(shell basename "${current_dir}" )
version=$(shell target/scripts/version.sh)
registry=hub.multiverse.io/banc-experience/

build:
	mvn clean package -DskipTests
	chmod +x ./target/scripts/*.sh
	unzip -o -d ./target ./target/*-bin.zip

clean:
	rm -rf target

image:
	sed -e 's/{{project_name}}/$(project_name)/' Dockerfile.template > Dockerfile
	docker build -t $(registry)$(project_name):$(version) .

push:
	docker push $(registry)$(project_name):$(version)
