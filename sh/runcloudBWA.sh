spark-submit --class org.gcdss.alignment.CloudBWATest \
 --master local \
 --conf "spark.executor.extraJavaOptions=-Djava.library.path=./lib" \
 --jars /home/hadoop/cloud/adam/lib/adam-apis_2.10-0.18.3-SNAPSHOT.jar,/home/hadoop/cloud/adam/lib/adam-cli_2.10-0.18.3-SNAPSHOT.jar,/home/hadoop/cloud/adam/lib/adam-core_2.10-0.18.3-SNAPSHOT.jar \
 --total-executor-cores 1 --executor-cores 1 --executor-memory 3G \
CloudBWA.jar \
## --archives ./bwa.zip \
