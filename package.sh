relativePath=.
baseDir=$(greadlink -f $relativePath)
warName=ROOT
logFile=nerdvana.log
rm -rf ${logFile}

# package war
# to package without running tests: mvn package -Dmaven.test.skip=true
mvn -f ${base.dir}/pom.xml clean install process-test-resources package > ${logFile}
echo "Created ${baseDir}/target/${warnName}.war