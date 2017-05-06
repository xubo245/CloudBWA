spark-submit --class org.gcdss.alignment.CloudBWA \
 --master local \
 --conf "spark.executor.extraJavaOptions=-Djava.library.path=./lib" \
 --jars /home/hadoop/cloud/adam/lib/adam-apis_2.10-0.18.3-SNAPSHOT.jar,/home/hadoop/cloud/adam/lib/adam-cli_2.10-0.18.3-SNAPSHOT.jar,/home/hadoop/cloud/adam/lib/adam-core_2.10-0.18.3-SNAPSHOT.jar \
 --total-executor-cores 20 --executor-cores 1 --executor-memory 3G \
CloudBWA.jar \
test/bwaindex/GRCH38chr1L3556522.fasta \
$1 $2 $3
## --archives ./bwa.zip \
