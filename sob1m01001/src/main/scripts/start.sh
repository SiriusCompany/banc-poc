#!/bin/bash
export config_file=/data/app/conf/app.toml
export error_config_file_path=/data/app/conf

java -jar ${project.name}.jar

tail -f /dev/null