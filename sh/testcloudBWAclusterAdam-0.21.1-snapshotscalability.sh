spark-submit --class org.gcdss.alignment.CloudBWA \
 --master spark://219.219.220.149:7077 \
 --conf "spark.executor.extraJavaOptions=-Djava.library.path=/home/hadoop/disk2/xubo/lib/" \
  --jars /home/hadoop/cloud/adam/lib/adam_2.10-0.21.1-SNAPSHOT.jar \
  --executor-memory 20G \
  --total-executor-cores $7 \
CloudBWA.jar \
/home/hadoop/disk2/xubo/ref/GRCH38L1Index/GRCH38chr1L3556522.fasta \
$1 $2 $3 $4 $5 $6
## --archives ./bwa.zip \
#--master spark://219.219.220.149:7077 \
#--master local \
#--total-executor-cores 20 --executor-cores 2 --executor-memory 20G \
#--jars /home/hadoop/cloud/adam/lib/adam-apis_2.10-0.18.3-SNAPSHOT.jar,/home/hadoop/cloud/adam/lib/adam-cli_2.10-0.18.3-SNAPSHOT.jar,/home/hadoop/cloud/adam/lib/adam-core_2.10-0.18.3-SNAPSHOT.jar \
# --jars /home/hadoop/cloud/adam/lib/adam-apis-spark2_2.10-0.21.0.jar,/home/hadoop/cloud/adam/lib/adam-cli-spark2_2.10-0.21.0.jar,/home/hadoop/cloud/adam/lib/adam-core-spark2_2.10-0.21.0.jar \
#--jars /home/hadoop/cloud/adam/lib/adam_2.10-0.21.0.jar,/home/hadoop/cloud/adam/lib/adam-apis-spark2_2.10-0.21.0.jar,/home/hadoop/cloud/adam/lib/adam-cli-spark2_2.10-0.21.0.jar,/home/hadoop/cloud/adam/lib/adam-core-spark2_2.10-0.21.0.jar \
 #--jars /home/hadoop/cloud/adam/lib/adam_2.10-0.21.0.jar \
 #--jars /home/hadoop/cloud/adam/lib/adam-core_2.10-0.19.0.jar,/home/hadoop/cloud/adam/lib/adam-cli_2.10-0.19.0.jar,/home/hadoop/cloud/adam/lib/adam-apis_2.10-0.19.0.jar \
